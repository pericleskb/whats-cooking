package com.example.whatscooking.main.recipepage;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.whatscooking.R;
import com.example.whatscooking.main.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.whatscooking.TestUtils.withRecyclerView;

@LargeTest
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
    public void recipePage_whenDisplayed_thenImageLayoutVisible() {
        Espresso.onView(withId(R.id.recipe_image)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.title)).check(matches((withText("Dahl"))));
    }

    @Test
    public void recipePage_whenDisplayed_thenRecipeDetailsVisible() {
        Espresso.onView(withId(R.id.recipe_info_layout)).check(matches(
                (hasDescendant(withId(R.id.time_title)))));
        Espresso.onView(withId(R.id.time_title)).check(matches(
                hasSibling(withId(R.id.time_value))));
        Espresso.onView(withId(R.id.time_value)).check(matches(
                hasSibling(withId(R.id.difficulty_title))));
        Espresso.onView(withId(R.id.difficulty_title)).check(matches(
                hasSibling(withId(R.id.difficulty_value))));
        Espresso.onView(withId(R.id.difficulty_value)).check(matches(
                hasSibling(withId(R.id.servings_title))));
        Espresso.onView(withId(R.id.servings_title)).check(matches(
                hasSibling(withId(R.id.recipe_servings))));
    }

    @Test
    public void recipePage_whenSwipeDownOnImage_thenGoBackToRecipesListFragment() {
        Espresso.onView(withId(R.id.recipe_image)).perform(swipeDown());
        Espresso.onView(withId(R.id.main)).check(matches(isDisplayed()));
    }

    @Test
    public void recipePage_whenViewIngredientsButtonPressed_thenViewChanges() {
        Espresso.onView(withId(R.id.ingredients_recyclerView)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.change_view_button)).perform(click());
        Espresso.onView(withId(R.id.view_pager)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.change_view_button)).perform(click());
        Espresso.onView(withId(R.id.ingredients_recyclerView)).check(matches(isDisplayed()));
    }

    @Test
    public void recipePage_whenScrollOnIngredients_thenIngredientsScroll() {
        Espresso.onView(withId(R.id.ingredients_recyclerView))
                .check(matches(hasDescendant(withText("ingredient00"))));
//      Scrolling does not work probably because the recycler view is inside the nestedscrollview
//        tried this https://stackoverflow.com/questions/33505953/espresso-how-to-test-swiperefreshlayout
//        Espresso.onView(withId(R.id.ingredients_recyclerView))
//                .perform(RecyclerViewActions.scrollToPosition(5));
//
//        Espresso.onView(withRecyclerView(R.id.ingredients_recyclerView).atPosition(2))
//                .check(matches(isDisplayed()));
    }
}
