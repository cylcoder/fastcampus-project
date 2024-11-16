package org.fastcampus.common.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositiveIntegerCounterTest {

    @Test
    void givenCreated_whenIncrease_thenCountIsOne() {
        // given
        PositiveIntegerCounter positiveIntegerCounter = new PositiveIntegerCounter();

        // when
        positiveIntegerCounter.increase();
        
        // then
        assertEquals(1, positiveIntegerCounter.getCount());
    }

    @Test
    void givenCreatedIncreased_whenDecrease_thenCountIsOne() {
        // given
        PositiveIntegerCounter positiveIntegerCounter = new PositiveIntegerCounter();
        positiveIntegerCounter.increase();

        // when
        positiveIntegerCounter.decrease();

        // then
        assertEquals(0, positiveIntegerCounter.getCount());
    }

    @Test
    void givenCreated_whenDecrease_thenCountIsZero() {
        // given
        PositiveIntegerCounter positiveIntegerCounter = new PositiveIntegerCounter();

        // when
        positiveIntegerCounter.decrease();

        // then
        assertEquals(0, positiveIntegerCounter.getCount());
    }

}