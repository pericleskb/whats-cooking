package com.example.whatscooking.main.recipeslist;

import android.content.Intent;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static com.example.whatscooking.TestUtils.withRecyclerView;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.filters.MediumTest;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.example.whatscooking.R;
import com.example.whatscooking.TestRecipeDetailsBuildDirector;
import com.example.whatscooking.main.MainActivity;

@MediumTest
public class RecipesListFragmentTest {

    ActivityScenario<MainActivity> scenario;
    private static TestRecipeDetailsBuildDirector director;

    @BeforeClass
    public static void setup() {
        director = new TestRecipeDetailsBuildDirector();
    }

    @Before
    public void setUp() {
        scenario = ActivityScenario.launch(MainActivity.class).onActivity(activity -> {
            RecipesListFragment fragment = new RecipesListFragment();
            activity.startActivityFromFragment(fragment, new Intent(activity, MainActivity.class), 0);
        });
    }

    @Test
    public void recipeList_whenDisplayed_thenRecipeDetailsVisible() {
        Espresso.onView(withRecyclerView(R.id.recipe_recycler_view).atPosition(0))
                .check(matches(hasDescendant(withText("Time:"))));
        Espresso.onView(withRecyclerView(R.id.recipe_recycler_view).atPosition(0))
                .check(matches(hasDescendant(withText("Difficulty:"))));
    }

    @Test
    public void recipeList_whenScrollDown_thenNewRecipesAppear() {
        Espresso.onView(withId(R.id.recipe_recycler_view)).perform(RecyclerViewActions.scrollToPosition(4));
        Espresso.onView(withRecyclerView(R.id.recipe_recycler_view).atPosition(4))
                .check(matches(hasDescendant(withText("Venison steak"))));
        Espresso.onView(withRecyclerView(R.id.recipe_recycler_view).atPositionOnView(4, R.id.time_value))
                .check(matches(isDisplayed()));
    }

    //TODO Test if the ui updates when a recipe is deleted
}