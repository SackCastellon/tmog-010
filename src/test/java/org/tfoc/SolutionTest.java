package org.tfoc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Slf4j
class SolutionTest {
  private Solution solution;

  static Stream<Arguments> queensN() {
    return Stream.of(
        arguments(1, 1),
        arguments(2, 0),
        arguments(3, 0),
        arguments(4, 2),
        arguments(5, 10),
        arguments(6, 4),
        arguments(7, 40),
        arguments(8, 92),
        arguments(9, 352),
        arguments(10, 724),
        arguments(11, 2680),
        arguments(12, 14200),
        arguments(13, 73712),
        arguments(14, 365596),
        arguments(15, 2279184),
        arguments(16, 14772512)
    );
  }

  @BeforeEach
  void setUp() {
    solution = new Solution();
  }

  @Test
  void queens1() {
    var result = solution.solveNQueens(1);

    assertThat(result).containsExactlyInAnyOrder(
        List.of("Q")
    );
  }

  @Test
  void queens4() {
    var result = solution.solveNQueens(4);

    assertThat(result).hasSize(2).containsExactlyInAnyOrder(
        List.of(
            ".Q..",
            "...Q",
            "Q...",
            "..Q."
        ),
        List.of(
            "..Q.",
            "Q...",
            "...Q",
            ".Q.."
        )
    );
  }

  @Test
  void queens6() {
    var result = solution.solveNQueens(6);

    assertThat(result).hasSize(4).containsExactlyInAnyOrder(
        List.of(
            "..Q...",
            ".....Q",
            ".Q....",
            "....Q.",
            "Q.....",
            "...Q.."
        ),
        List.of(
            "...Q..",
            "Q.....",
            "....Q.",
            ".Q....",
            ".....Q",
            "..Q..."
        ),
        List.of(
            ".Q....",
            "...Q..",
            ".....Q",
            "Q.....",
            "..Q...",
            "....Q."
        ),
        List.of(
            "....Q.",
            "..Q...",
            "Q.....",
            ".....Q",
            "...Q..",
            ".Q...."
        )
    );
  }

  @ParameterizedTest
  @MethodSource
  void queensN(int n, int expected) {
    var result = solution.solveNQueens(n);

    assertThat(result).hasSize(expected);
  }
}
