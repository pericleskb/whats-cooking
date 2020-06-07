package com.example.whatscooking.ui.main;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.*;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;


import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.whatscooking.R;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class MainFragmentTest {

    @Test
    public void MainFragment_DisplayedInUi() {
        FragmentScenario.launchInContainer(MainFragment.class, null, R.style.AppTheme, null);

        Espresso.onView(ViewMatchers.withId(R.id.recipe_title)).check()
    }
}