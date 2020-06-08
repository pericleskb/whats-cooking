package com.example.whatscooking.utilities;

import org.junit.Test;
import static com.google.common.truth.Truth.assertThat;

public class MediaOperationsTest {

    @Test
    public void getUniqueImageName_twoAsyncCalls_generateTwoDifferentNames() throws InterruptedException {
        String name1 = MediaOperations.getUniqueImageName();
        Thread.sleep(1000);
        String name2 = MediaOperations.getUniqueImageName();
        assertThat(name1).doesNotMatch(name2);
    }
}