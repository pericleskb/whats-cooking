package com.example.whatscooking.main.recipeslist;

import android.os.Build;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.whatscooking.LiveDataTestUtil;
import com.example.whatscooking.TestRecipeBuildDirector;
import com.example.whatscooking.TestRecipeDetailsBuildDirector;
import com.example.whatscooking.FakeRepository;
import com.example.whatscooking.data.entities.Recipe;

import java.util.Stack;

import static com.google.common.truth.Truth.assertThat;

// Do not use the default JUnit4 test runner since we rely on Android code for these tests
@RunWith(AndroidJUnit4.class)
@Config(sdk = {Build.VERSION_CODES.P})
public class RecipesListViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    RecipesListViewModel recipesListViewModel;
    FakeRepository fakeRepository;
    TestRecipeDetailsBuildDirector recipeDetailsDirector;
    TestRecipeBuildDirector recipeDirector;

    @Before
    public void setupViewModel() {
        recipeDetailsDirector = new TestRecipeDetailsBuildDirector();
        recipeDirector = new TestRecipeBuildDirector();
        fakeRepository = new FakeRepository();
        fakeRepository.insertRecipe(recipeDetailsDirector.buildFullRecipeDetails(),
                recipeDirector.buildRecipe());
        recipesListViewModel =
                new RecipesListViewModel(ApplicationProvider.getApplicationContext(),
                        fakeRepository);
    }

    @Test
    public void getAllRecipesDetails_whenViewModelCreated_thenExistingRecipesGetAdded()
            throws InterruptedException {
        int numberOfRecipes = fakeRepository.getNumberOfRecipes();
        assertThat(LiveDataTestUtil.getOrAwaitValue(recipesListViewModel.getAllRecipesDetails())
                .size()).isEqualTo(numberOfRecipes);
    }

    @Test
    public void insert_whenNewRecipeAdded_thenInsertRecipeIsCalledInRepository()
            throws InterruptedException {
        int numberOfRecipes = fakeRepository.getNumberOfRecipes();
        recipesListViewModel.insert(recipeDetailsDirector.buildFullRecipeDetails(),
                recipeDirector.buildRecipe());
        assertThat(LiveDataTestUtil.getOrAwaitValue(recipesListViewModel.getAllRecipesDetails())
                .size()).isEqualTo(numberOfRecipes + 1);
    }
}