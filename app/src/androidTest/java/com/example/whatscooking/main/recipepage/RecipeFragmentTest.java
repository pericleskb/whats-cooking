package com.example.whatscooking.main.recipepage;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;

import com.example.whatscooking.R;
import com.example.whatscooking.main.MainTestActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.whatscooking.TestUtils.withRecyclerView;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class RecipeFragmentTest {

    ActivityScenario<MainTestActivity> scenario;

    @Before
    public void setUp() {
        scenario = ActivityScenario.launch(MainTestActivity.class).onActivity(activity -> {
            RecipeFragment fragment = new RecipeFragment();
            activity.startActivityFromFragment(fragment, new Intent(activity, MainTestActivity.class), 0);
        });
    }

    @Test
    public void recipePage_whenDisplayed_thenRecipeDetailsVisible() {
        Espresso.onView(withRecyclerView(R.id.recipe_recycler_view).atPosition(0))
                .check(matches(hasDescendant(withText("Time:"))));
        Espresso.onView(withRecyclerView(R.id.recipe_recycler_view).atPosition(0))
                .check(matches(hasDescendant(withText("Difficulty:"))));
    }
}
