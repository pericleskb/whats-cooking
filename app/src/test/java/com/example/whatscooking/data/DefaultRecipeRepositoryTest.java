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

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

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

    @Before
    public void setUp() {
        recipeDetailsDirector = new TestRecipeDetailsBuildDirector();
        recipeDirector = new TestRecipeBuildDirector();
        fakeRecipeDetailsDao = new FakeRecipeDetailsDao();
        fakeRecipeDao = new FakeRecipeDao();

        fakeRecipeDetailsDao.insert(recipeDetailsDirector.buildFullRecipeDetails());
        fakeRecipeDao.insert(recipeDirector.buildRecipe());
        fakeRecipeDetailsDao.insert(recipeDetailsDirector.buildFullRecipeDetails());
        fakeRecipeDao.insert(recipeDirector.buildRecipe());

        ResetableDefaultRecipeRepository.resetInstance();
        recipeRepository = ResetableDefaultRecipeRepository.getInstance(fakeRecipeDetailsDao, fakeRecipeDao);
    }

    @Test
    public void getAllRecipesDetails_whenRepositoryCreated_thenExistingRecipeDetailsAreAdded()
            throws InterruptedException {
        assertThat(fakeRecipeDetailsDao.recipesList.size()).isEqualTo(LiveDataTestUtil.getOrAwaitValue(
                recipeRepository.getAllRecipesDetails()).size());
    }

    @Test
    public void getAllRecipes_whenRepositoryCreated_thenExistingRecipesAreAdded()
            throws InterruptedException {
        assertThat(fakeRecipeDao.recipesList.size()).isEqualTo(LiveDataTestUtil.getOrAwaitValue(
                recipeRepository.getAllRecipes()).size());
    }

    @Test
    public void getRecipe_whenRecipeTitleProvided_thenFetchSpecifiedRecipe() throws InterruptedException {
        Recipe recipe = recipeDirector.buildRecipe();
        fakeRecipeDao.insert(recipe);
        Recipe fetchedRecipe = LiveDataTestUtil.getOrAwaitValue(recipeRepository.getRecipe(recipe.title));
        assertThat(recipe.title).isEqualTo(fetchedRecipe.title);
        assertThat(recipe.instructions.get(0)).isEqualTo(fetchedRecipe.instructions.get(0));
        assertThat(recipe.ingredients.get(3)).isEqualTo(fetchedRecipe.ingredients.get(3));
    }


    @Test
    public void getRecipeDetails_whenRecipeTitleProvided_thenFetchSpecifiedRecipeDetails()
            throws InterruptedException {
        RecipeDetails recipe = recipeDetailsDirector.buildFullRecipeDetails();
        fakeRecipeDetailsDao.insert(recipe);
        RecipeDetails fetchedRecipe = LiveDataTestUtil.getOrAwaitValue(
                recipeRepository.getRecipeDetails(recipe.title));
        assertThat(recipe.title).isEqualTo(fetchedRecipe.title);
        assertThat(recipe.difficulty).isEqualTo(fetchedRecipe.difficulty);
        assertThat(recipe.imageUri).isEqualTo(fetchedRecipe.imageUri);
        assertThat(recipe.servings).isEqualTo(fetchedRecipe.servings);
        assertThat(recipe.description).isEqualTo(fetchedRecipe.description);
        assertThat(recipe.timeMinutes).isEqualTo(fetchedRecipe.timeMinutes);
    }

    @Test
    public void insert_whenRecipeIsInserted_thenRecipeDaoInsertIsCalled() {
        int recipesLengthBefore = fakeRecipeDetailsDao.recipesList.size();
        recipeRepository.insertRecipe(recipeDetailsDirector.buildFullRecipeDetails(),
                recipeDirector.buildRecipe());
        recipeRepository.insertRecipe(recipeDetailsDirector.buildFullRecipeDetails(),
                recipeDirector.buildRecipe());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(recipesLengthBefore + 2).isEqualTo(fakeRecipeDetailsDao.recipesList.size());
    }
}