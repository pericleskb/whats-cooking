package com.example.whatscooking.data;

import android.os.Build;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.whatscooking.LiveDataTestUtil;
import com.example.whatscooking.TestRecipeDetailsBuildDirector;
import com.example.whatscooking.TestUtils;
import com.example.whatscooking.data.daos.FakeRecipeDao;
import com.example.whatscooking.data.daos.FakeRecipeDetailsDao;
import com.example.whatscooking.data.entities.Recipe;
import com.example.whatscooking.data.entities.RecipeDetails;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.util.Stack;

import javax.inject.Inject;

import static com.google.common.truth.Truth.assertThat;

@RunWith(AndroidJUnit4.class)
@Config(sdk = {Build.VERSION_CODES.P})
public class DefaultRecipeRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Inject
    DefaultRecipeRepository recipeRepository;
    Stack<Recipe> recipesStack;
    FakeRecipeDetailsDao fakeRecipeDetailsDao;
    FakeRecipeDao fakeRecipeDao;
    TestRecipeDetailsBuildDirector director;

    @Before
    public void setUp() {
        director = new TestRecipeDetailsBuildDirector();
        fakeRecipeDetailsDao = new FakeRecipeDetailsDao();
        fakeRecipeDao = new FakeRecipeDao();

        recipesStack = TestUtils.getRecipesStack();

        fakeRecipeDetailsDao.insert(director.buildFullRecipeDetails());
        fakeRecipeDao.insert(recipesStack.pop());
        fakeRecipeDetailsDao.insert(director.buildFullRecipeDetails());
        fakeRecipeDao.insert(recipesStack.pop());

        ResetableDefaultRecipeRepository.resetInstance();
        recipeRepository = ResetableDefaultRecipeRepository.getInstance(fakeRecipeDetailsDao, fakeRecipeDao);
    }

    @Test
    public void getAllRecipesDetails_whenRepositoryCreated_thenExistingRecipeDetailsAreAdded() throws InterruptedException {
        assertThat(fakeRecipeDetailsDao.recipesList.size()).isEqualTo(LiveDataTestUtil.getOrAwaitValue(
                recipeRepository.getAllRecipesDetails()).size());
    }

    @Test
    public void getAllRecipes_whenRepositoryCreated_thenExistingRecipesAreAdded() throws InterruptedException {
        assertThat(fakeRecipeDao.recipesList.size()).isEqualTo(LiveDataTestUtil.getOrAwaitValue(
                recipeRepository.getAllRecipes()).size());
    }

    @Test
    public void getRecipe_whenRecipeTitleProvided_thenFetchSpecifiedRecipe() throws InterruptedException {
        Recipe recipe = recipesStack.pop();
        fakeRecipeDao.insert(recipe);
        Recipe fetchedRecipe = LiveDataTestUtil.getOrAwaitValue(recipeRepository.getRecipe(recipe.title));
        assertThat(recipe.title).isEqualTo(fetchedRecipe.title);
        assertThat(recipe.instructions.get(0)).isEqualTo(fetchedRecipe.instructions.get(0));
        assertThat(recipe.ingredients.get(3)).isEqualTo(fetchedRecipe.ingredients.get(3));
    }


    @Test
    public void getRecipeDetails_whenRecipeTitleProvided_thenFetchSpecifiedRecipeDetails() throws InterruptedException {
        RecipeDetails recipe = director.buildFullRecipeDetails();
        fakeRecipeDetailsDao.insert(recipe);
        RecipeDetails fetchedRecipe = LiveDataTestUtil.getOrAwaitValue(recipeRepository.getRecipeDetails(recipe.title));
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
        recipeRepository.insertRecipe(director.buildFullRecipeDetails(), recipesStack.pop());
        recipeRepository.insertRecipe(director.buildFullRecipeDetails(), recipesStack.pop());
        assertThat(recipesLengthBefore + 2).isEqualTo(fakeRecipeDetailsDao.recipesList.size());
    }
}