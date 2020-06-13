package com.example.whatscooking.ui.main;

import static com.example.whatscooking.TestUtils.withRecyclerView;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.*;
import androidx.test.filters.MediumTest;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.example.whatscooking.R;



@MediumTest
@RunWith(AndroidJUnit4.class)
public class MainFragmentTest {

    @Test
    public void MainFragment_DisplayedInUi() {
        FragmentScenario.launchInContainer(MainFragment.class, null, R.style.AppTheme, null);

        Espresso.onView(withId(R.id.recipe_recycle_view)).perform(RecyclerViewActions.scrollToPosition(4));

        Espresso.onView(withRecyclerView(R.id.recipe_recycle_view).atPosition(4))
                .check(matches(hasDescendant(withText("Venison steak"))));
    }
}