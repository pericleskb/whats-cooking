package com.example.whatscooking.main.recipepage;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;

import com.example.whatscooking.R;
import com.example.whatscooking.main.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.example.whatscooking.TestUtils.withRecyclerView;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class RecipeFragmentTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp() {
        Espresso.onView(withRecyclerView(R.id.recipe_recycler_view).atPosition(0)).perform(click());
    }

    @Test
    public void recipePage_whenDisplayed_thenRecipeDetailsVisible() {
        Espresso.onView(withId(R.id.recipe_image)).check(matches(isDisplayed()));
    }
}
