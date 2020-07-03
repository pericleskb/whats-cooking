package com.example.whatscooking.data;

import android.os.Build;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.whatscooking.LiveDataTestUtil;
import com.example.whatscooking.TestUtils;
import com.example.whatscooking.data.daos.FakeRecipeDetailsDao;
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
    Stack<RecipeDetails> recipesStack;
    FakeRecipeDetailsDao recipeDetailsDao;

    @Before
    public void setUp() {
        recipeDetailsDao = new FakeRecipeDetailsDao();
        recipesStack = TestUtils.getRecipesStack();
        recipeDetailsDao.insert(recipesStack.pop());
        recipeDetailsDao.insert(recipesStack.pop());
        ResetableDefaultRecipeRepository.resetInstance();
        recipeRepository = ResetableDefaultRecipeRepository.getInstance(recipeDetailsDao);
    }

    @Test
    public void getAllRecipes_returnAllRecipes() throws InterruptedException {
        assertThat(recipeDetailsDao.recipesList.size()).isEqualTo(LiveDataTestUtil.getOrAwaitValue(
                recipeRepository.getAllRecipesDetails()).size());
    }

    @Test
    public void insert_whenRecipeArrayInserted_thenInsertForAllRecipesIsCalled() {
        int recipesLengthBefore = recipeDetailsDao.recipesList.size();
        RecipeDetails[] recipesArray = {recipesStack.pop(), recipesStack.pop()};
        recipeRepository.insertRecipe(recipesArray);
        assertThat(recipesLengthBefore + 2).isEqualTo(recipeDetailsDao.recipesList.size());
    }
}