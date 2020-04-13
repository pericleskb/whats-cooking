package com.example.whatscooking.viewmodels;

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
import com.example.whatscooking.TestUtils;
import com.example.whatscooking.data.FakeTestRepository;
import com.example.whatscooking.data.Recipe;

import java.util.Stack;

import static com.google.common.truth.Truth.assertThat;

// Do not use the default JUnit4 test runner since we rely on Android code for these tests
@RunWith(AndroidJUnit4.class)
@Config(sdk = {Build.VERSION_CODES.P})
public class RecipesListViewModelTest {

    // Will run its code before and after every test
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    RecipesListViewModel recipesListViewModel;
    Stack<Recipe> recipes;

    @Before
    public void setupViewModel() {
        recipesListViewModel =
                new RecipesListViewModel(ApplicationProvider.getApplicationContext(),
                        new FakeTestRepository());
        recipes = new TestUtils(ApplicationProvider.getApplicationContext()).getRecipesStack();
    }

    @Test
    public void insert_addRecipe() throws InterruptedException {
        recipesListViewModel.insert(recipes.pop());

        assertThat(LiveDataTestUtil.getOrAwaitValue(recipesListViewModel.getAllRecipes())
                .size()).isEqualTo(1);
    }
}