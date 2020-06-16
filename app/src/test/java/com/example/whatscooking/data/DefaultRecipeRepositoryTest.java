package com.example.whatscooking.data;

import android.os.Build;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.whatscooking.LiveDataTestUtil;
import com.example.whatscooking.TestUtils;
import com.example.whatscooking.data.daos.FakeRecipeInfoDao;
import com.example.whatscooking.data.entities.RecipeInfo;

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
    Stack<RecipeInfo> recipesStack;
    FakeRecipeInfoDao recipeInfoDao;

    @Before
    public void setUp() {
        recipeInfoDao = new FakeRecipeInfoDao();
        recipesStack = TestUtils.getRecipesStack();
        recipeInfoDao.insert(recipesStack.pop());
        recipeInfoDao.insert(recipesStack.pop());
        ResetableDefaultRecipeRepository.resetInstance();
        recipeRepository = ResetableDefaultRecipeRepository.getInstance(recipeInfoDao);
    }

    @Test
    public void getAllRecipes_returnAllRecipes() throws InterruptedException {
        assertThat(recipeInfoDao.recipesList.size()).isEqualTo(LiveDataTestUtil.getOrAwaitValue(
                recipeRepository.getAllRecipesInfo()).size());
    }

    @Test
    public void insert_whenRecipeArrayInserted_thenInsertForAllRecipesIsCalled() {
        int recipesLengthBefore = recipeInfoDao.recipesList.size();
        RecipeInfo[] recipesArray = {recipesStack.pop(), recipesStack.pop()};
        recipeRepository.insertRecipe(recipesArray);
        assertThat(recipesLengthBefore + 2).isEqualTo(recipeInfoDao.recipesList.size());
    }
}