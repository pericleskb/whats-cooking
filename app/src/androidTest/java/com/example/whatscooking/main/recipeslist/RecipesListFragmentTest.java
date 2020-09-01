package com.example.whatscooking.main.recipeslist;

import android.content.Intent;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static com.example.whatscooking.TestUtils.withRecyclerView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.*;
import androidx.test.filters.MediumTest;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.example.whatscooking.R;
import com.example.whatscooking.main.MainTestActivity;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class RecipesListFragmentTest {

    ActivityScenario<MainTestActivity> scenario;

    @Before
    public void setUp() {
        scenario = ActivityScenario.launch(MainTestActivity.class).onActivity(activity -> {
            RecipesListFragment fragment = new RecipesListFragment();
            activity.startActivityFromFragment(fragment, new Intent(activity, MainTestActivity.class), 0);
        });
    }

    @Test
    public void RecipesListFragment_DisplayedInUi() {
        Espresso.onView(withRecyclerView(R.id.recipe_recycler_view).atPosition(0))
                .check(matches(hasDescendant(withText("Time:"))));
        Espresso.onView(withRecyclerView(R.id.recipe_recycler_view).atPosition(0))
                .check(matches(hasDescendant(withText("Difficulty:"))));
        Espresso.onView(withId(R.id.recipe_recycler_view)).perform(RecyclerViewActions.scrollToPosition(4));
        Espresso.onView(withRecyclerView(R.id.recipe_recycler_view).atPosition(4))
                .check(matches(hasDescendant(withText("Venison steak"))));
        Espresso.onView(withRecyclerView(R.id.recipe_recycler_view).atPositionOnView(4, R.id.time_value))
                .check(matches(isDisplayed()));
    }

    //TODO Test if the ui updates when a recipe is deleted
}