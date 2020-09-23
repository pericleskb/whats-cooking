package com.example.whatscooking.data;

import android.os.Build;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.whatscooking.LiveDataTestUtil;
import com.example.whatscooking.TestConstants;
import com.example.whatscooking.TestRecipeBuildDirector;
import com.example.whatscooking.TestRecipeDetailsBuildDirector;
import com.example.whatscooking.data.daos.FakeRecipeDao;
import com.example.whatscooking.data.daos.FakeRecipeDetailsDao;
import com.example.whatscooking.data.entities.Recipe;
import com.example.whatscooking.data.entities.RecipeDetails;
import com.example.whatscooking.utilities.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
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
    MockWebServer server;

    @Before
    public void setUp() throws IOException {

        server = new MockWebServer();
        server.start();
        executorService = Executors.newFixedThreadPool(4);

        fakeRecipeDetailsDao = new FakeRecipeDetailsDao();
        fakeRecipeDao = new FakeRecipeDao();

        recipeDetailsDirector = new TestRecipeDetailsBuildDirector();
        recipeDirector = new TestRecipeBuildDirector();
    }

    @Test
    public void getRecipeDetails_whenServerNotAvailable_thenReadOnlyFromStorage() throws InterruptedException {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        initAndPopulateDB(5);

        List<RecipeDetails> recipeDetailsList =
                LiveDataTestUtil.getOrAwaitValue(recipeRepository.getRecipesDetails());
        assertThat(recipeDetailsList.size()).isEqualTo(5);
    }

    @Test
    public void getRecipeDetails_whenNoAppsInStorage_thenAppsLoadedFromServer() throws InterruptedException, IOException {
        HttpUrl baseUrl = server.url("/recipeDetails/");
        retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).build();
        initAndPopulateDB(0);

        MockResponse response = new MockResponse()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .setBody(TestConstants.recipeDetailsResponse);
        server.enqueue(response);

        List<RecipeDetails> recipeDetailsReceived =
                LiveDataTestUtil.getOrAwaitValue(recipeRepository.getRecipesDetails());

        RecordedRequest request = server.takeRequest();
        assertThat(request).isEqualTo("/recipeDetails/");

        // This is how to read a collection of objects from json using gson
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<RecipeDetails>>(){}.getType();
        List<RecipeDetails> recipeListSent = gson.fromJson(TestConstants.recipeDetailsResponse, collectionType);
        assertThat(recipeListSent.size()).isEqualTo(recipeDetailsReceived.size());
        assertThat(recipeDetailsReceived.containsAll(recipeListSent));

        server.shutdown();
    }

    @Test
    public void getRecipeDetails_whenNewAppsOnServer_thenCombineStorageAndServerRecipes() throws InterruptedException, IOException {
        HttpUrl baseUrl = server.url("/recipeDetails/");
        retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).build();
        initAndPopulateDB(5);

        MockResponse response = new MockResponse()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .setBody(TestConstants.recipeDetailsResponse);
        server.enqueue(response);

        List<RecipeDetails> recipeDetailsReceived =
                LiveDataTestUtil.getOrAwaitValue(recipeRepository.getRecipesDetails());

        RecordedRequest request = server.takeRequest();
        assertThat(request).isEqualTo("/recipeDetails/");

        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<RecipeDetails>>(){}.getType();
        // Local storage should be overwritten
        List<RecipeDetails> recipeListSent = gson.fromJson(TestConstants.recipeDetailsResponse, collectionType);
        assertThat(recipeListSent.size()).isEqualTo(recipeDetailsReceived.size());
        assertThat(recipeDetailsReceived.containsAll(recipeListSent));

        server.shutdown();
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