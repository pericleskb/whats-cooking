package com.example.whatscooking.data;

import android.os.Build;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.whatscooking.LiveDataTestUtil;
import com.example.whatscooking.TestRecipeBuildDirector;
import com.example.whatscooking.TestRecipeDetailsBuildDirector;
import com.example.whatscooking.data.daos.FakeRecipeDao;
import com.example.whatscooking.data.daos.FakeRecipeDetailsDao;
import com.example.whatscooking.data.entities.Recipe;
import com.example.whatscooking.data.entities.RecipeDetails;
import com.example.whatscooking.utilities.Constants;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.google.common.truth.Truth.assertThat;

@RunWith(AndroidJUnit4.class)
@Config(sdk = {Build.VERSION_CODES.P})
public class DefaultRecipeRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    DefaultRecipeRepository recipeRepository;
    FakeRecipeDetailsDao fakeRecipeDetailsDao;
    FakeRecipeDao fakeRecipeDao;
    TestRecipeDetailsBuildDirector recipeDetailsDirector;
    TestRecipeBuildDirector recipeDirector;
    Retrofit retrofit;
    ExecutorService executorService;

    @Before
    public void setUp() {

        executorService = Executors.newFixedThreadPool(4);
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        fakeRecipeDetailsDao = new FakeRecipeDetailsDao();
        fakeRecipeDao = new FakeRecipeDao();

        recipeDetailsDirector = new TestRecipeDetailsBuildDirector();
        recipeDirector = new TestRecipeBuildDirector();
    }

    //TODO add mock server
    @Test
    public void getRecipeDetails_whenReadingFromStorage_thenRecipeDetailsAreRead() throws InterruptedException {
        initAndPopulateDB(5);
        List<RecipeDetails> recipeDetailsList =
                LiveDataTestUtil.getOrAwaitValue(recipeRepository.getRecipesDetails());
        assertThat(recipeDetailsList.size()).isEqualTo(5);
    }

    @Test
    void getRecipeDetails_whenNoAppsInStorage_thenAppsLoadedFromServer() {
        initAndPopulateDB(0);
    }

    @Test
    public void getRecipe_whenRecipeTitleProvided_thenFetchSpecifiedRecipe() throws InterruptedException {
        initAndPopulateDB(2);
        Recipe recipe = recipeDirector.buildRecipe();
        fakeRecipeDao.insert(recipe);
        Recipe fetchedRecipe = LiveDataTestUtil.getOrAwaitValue(recipeRepository.getRecipe(recipe.title));
        assertThat(recipe.title).isEqualTo(fetchedRecipe.title);
        assertThat(recipe.instructions.get(0)).isEqualTo(fetchedRecipe.instructions.get(0));
        assertThat(recipe.ingredients.get(3)).isEqualTo(fetchedRecipe.ingredients.get(3));
    }

    private void initAndPopulateDB(int recipesAmount) {
        for (int i = 0; i < recipesAmount; i++) {
            fakeRecipeDetailsDao.insert(recipeDetailsDirector.buildFullRecipeDetails());
            fakeRecipeDao.insert(recipeDirector.buildRecipe());
        }
        ResetableDefaultRecipeRepository.resetInstance();
        recipeRepository = ResetableDefaultRecipeRepository.getInstance(fakeRecipeDetailsDao,
                fakeRecipeDao, retrofit, executorService);
    }
}