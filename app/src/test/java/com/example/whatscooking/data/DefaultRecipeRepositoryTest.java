package com.example.whatscooking.data;

import android.os.Build;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.whatscooking.LiveDataTestUtil;
import com.example.whatscooking.TestUtils;

import org.junit.Before;
import org.junit.BeforeClass;
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
    FakeRecipeDao recipeDao;

    @Before
    public void setUp() {
        recipeDao = new FakeRecipeDao();
        recipesStack = TestUtils.getRecipesStack();
        recipeDao.insertAll(recipesStack.pop());
        recipeDao.insertAll(recipesStack.pop());
        ResetableDefaultRecipeRepository.resetInstance();
        recipeRepository = ResetableDefaultRecipeRepository.getInstance(recipeDao);
    }

    @Test
    public void getAllRecipes_returnAllRecipes() throws InterruptedException {
        assertThat(recipeDao.recipesList.size()).isEqualTo(LiveDataTestUtil.getOrAwaitValue(
                recipeRepository.getAllRecipes()).size());
    }

    @Test
    public void insert_whenRecipeArrayInserted_thenInsertForAllRecipesIsCalled() {
        int recipesLengthBefore = recipeDao.recipesList.size();
        Recipe[] recipesArray = {recipesStack.pop(), recipesStack.pop()};
        recipeRepository.insert(recipesArray);
        assertThat(recipesLengthBefore + 2).isEqualTo(recipeDao.recipesList.size());
    }
}