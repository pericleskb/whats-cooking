package com.example.whatscooking.data;

import android.os.Build;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.whatscooking.LiveDataTestUtil;
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
    Stack<RecipeDetails> recipeDetailsStack;
    Stack<Recipe> recipesStack;
    FakeRecipeDetailsDao fakeRecipeDetailsDao;
    FakeRecipeDao fakeRecipeDao;

    @Before
    public void setUp() {
        fakeRecipeDetailsDao = new FakeRecipeDetailsDao();
        fakeRecipeDao = new FakeRecipeDao();

        recipeDetailsStack = TestUtils.getRecipesDetailsStack();
        recipesStack = TestUtils.getRecipesStack();

        fakeRecipeDetailsDao.insert(recipeDetailsStack.pop());
        fakeRecipeDao.insert(recipesStack.pop());
        fakeRecipeDetailsDao.insert(recipeDetailsStack.pop());
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
    public void insert_whenRecipeIsInserted_thenRecipeDaoInsertIsCalled() {
        int recipesLengthBefore = fakeRecipeDetailsDao.recipesList.size();
        recipeRepository.insertRecipe(recipeDetailsStack.pop(), recipesStack.pop());
        recipeRepository.insertRecipe(recipeDetailsStack.pop(), recipesStack.pop());
        assertThat(recipesLengthBefore + 2).isEqualTo(fakeRecipeDetailsDao.recipesList.size());
    }
}