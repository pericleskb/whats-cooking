package com.example.whatscooking.adapters;

import com.example.whatscooking.data.Recipe;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class ConvertersTest {

    @Test
    public void fromInteger_null_returnsNull() {
        Converters converters = new Converters();
        Integer value = null;
        Recipe.Difficulty difficulty = converters.fromInteger(value);
        assertThat(difficulty).isNull();
    }

    @Test
    public void fromInteger_two_returnsMedium() {
        Converters converters = new Converters();
        Integer value = 2;
        Recipe.Difficulty difficulty = converters.fromInteger(value);
        assertThat(difficulty).isEquivalentAccordingToCompareTo(Recipe.Difficulty.medium);
    }

    @Test
    public void difficultyToInt_null_returnsNull() {
        Converters converters = new Converters();
        Recipe.Difficulty difficulty = null;
        Integer value = converters.difficultyToInt(difficulty);
        assertThat(value).isNull();
    }

    @Test
    public void difficultyToInt_easy_returnsOne() {
        Converters converters = new Converters();
        Recipe.Difficulty difficulty = Recipe.Difficulty.easy;
        Integer value = converters.difficultyToInt(difficulty);
        assertThat(value).isEqualTo(1);
    }
}