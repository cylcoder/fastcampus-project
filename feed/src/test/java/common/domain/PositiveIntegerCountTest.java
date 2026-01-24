package common.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class PositiveIntegerCountTest {

  @Test
  void givenCountIsZero_whenIncrease_thenCountIsOne() {
    // given
    PositiveIntegerCount count = new PositiveIntegerCount();

    // when
    count.increase();

    // then
    assertThat(count.getCount()).isEqualTo(1);
  }

  @Test
  void givenCountIsOne_whenDecrease_thenCountIsZero() {
    // given
    PositiveIntegerCount count = new PositiveIntegerCount();
    count.increase();

    // when
    count.decrease();

    // then
    assertThat(count.getCount()).isZero();
  }

  @Test
  void givenCountIsZero_whenDecrease_thenThrowsException() {
    // given
    PositiveIntegerCount count = new PositiveIntegerCount();

    // when & then
    assertThatThrownBy(count::decrease).isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Count cannot be negative.");
  }

}