package com.example.whatscooking.main.recipepage;

import android.os.Build;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.whatscooking.TestRecipeDetailsBuildDirector;
import com.example.whatscooking.TestUtils;
import com.example.whatscooking.FakeRepository;
import com.example.whatscooking.data.entities.Recipe;
import com.example.whatscooking.data.entities.RecipeDetails;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.util.Stack;

import static com.example.whatscooking.LiveDataTestUtil.getOrAwaitValue;
import static com.google.common.truth.Truth.assertThat;

@RunWith(AndroidJUnit4.class)
@Config(sdk = {Build.VERSION_CODES.P})
public class RecipeViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    FakeRepository fakeRepository;
    RecipeViewModel recipeViewModel;
    Stack<Recipe> recipeStack = TestUtils.getRecipesStack();
    Recipe recipe;
    RecipeDetails recipeDetails;
    TestRecipeDetailsBuildDirector director;

    @Before
    public void setUpViewModel() {
        director = new TestRecipeDetailsBuildDirector();
        fakeRepository = new FakeRepository();
        recipe = recipeStack.pop();
        recipeDetails = director.buildFullRecipeDetails();
        fakeRepository.insertRecipe(recipeDetails, recipe);
        recipeViewModel = new RecipeViewModel(ApplicationProvider.getApplicationContext(),
                fakeRepository, recipeDetails.title);
    }

    @Test
    public void getRecipeLiveData_whenViewModelCreated_thenRecipeGetsAddedCorrectly()
            throws InterruptedException {
        Recipe myRecipe = getOrAwaitValue(recipeViewModel.getRecipeLiveData());
        assertThat(myRecipe.title).isEqualTo(recipe.title);
        assertThat(myRecipe.instructions.get(1)).isEqualTo(recipe.instructions.get(1));
        assertThat(myRecipe.ingredients.get(3)).isEqualTo(recipe.ingredients.get(3));
    }

    @Test
    public void getRecipeDetailsLiveData_whenViewModelCreated_thenRecipeDetailsGetsAddedCorrectly()
            throws InterruptedException {
        RecipeDetails myRecipeDetails = getOrAwaitValue(recipeViewModel.getRecipeDetailsLiveData());
        assertThat(myRecipeDetails.title).isEqualTo(recipeDetails.title);
        assertThat(myRecipeDetails.timeMinutes).isEqualTo(recipeDetails.timeMinutes);
        assertThat(myRecipeDetails.description).isEqualTo(recipeDetails.description);
    }

    @Test
    public void getRecipeLiveData_whenRecipeChanges_thenViewModelGetsUpdated() {
        int expectedIngredientsSize = recipeViewModel.getRecipeLiveData().getValue().ingredients.size();
        fakeRepository.addIngredient("10gr of Plutonium X");
        expectedIngredientsSize++;
        assertThat(expectedIngredientsSize).
                isEqualTo(recipeViewModel.getRecipeLiveData().getValue().ingredients.size());
    }

    @Test
    public void getRecipeDetailsLiveData_whenRecipeDetailsChanges_thenViewModelGetsUpdated() {
        fakeRepository.changeRecipeDetailsServings(99);
        assertThat(99).
                isEqualTo(recipeViewModel.getRecipeDetailsLiveData().getValue().servings);
    }
}