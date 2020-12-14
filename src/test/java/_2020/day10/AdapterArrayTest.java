package _2020.day10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdapterArrayTest {

    @Test
    void countDistinctArrangements_sample() {
        AdapterArray adapterArray = new AdapterArray();

        assertEquals(19208, adapterArray.countDistinctArrangements());
    }
}