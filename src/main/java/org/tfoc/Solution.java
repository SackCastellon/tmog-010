package org.tfoc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * The class containing the solution to this exercise
 */
public class Solution {

  private static final char QUEEN = 'Q';
  private static final int[] EMPTY_ARRAY = new int[0];
  private static final String EMPTY_ROW = ".".repeat(20);

  public List<List<String>> solveNQueens(int n) {
    if (n < 0) throw new IllegalArgumentException("number of queens must be positive");

    return solve(EMPTY_ARRAY, n).map(it -> toString(it, n)).toList();
  }

  private static Stream<int[]> solve(int[] rows, int n) {
    if (rows.length == n) return Stream.of(rows);
    return IntStream.range(0, n)
        .parallel()
        .filter(col -> isSafe(rows, col))
        .boxed()
        .flatMap(col -> solve(addColumn(rows, col), n));
  }

  private static boolean isSafe(int[] rows, int newCol) {
    var rowCount = rows.length;
    for (int row = 0; row < rowCount; row++) {
      var col = rows[row];

      var left = newCol - (rowCount - row);
      var right = newCol + (rowCount - row);

      if (col == left || col == newCol || col == right) return false;
    }

    return true;
  }

  private static int[] addColumn(int[] ints, int col) {
    var copy = Arrays.copyOf(ints, ints.length + 1);
    copy[ints.length] = col;
    return copy;
  }

  private static List<String> toString(int[] rows, int n) {
    return Arrays.stream(rows).mapToObj(col -> {
      var builder = new StringBuilder(EMPTY_ROW);
      builder.setLength(n);
      builder.setCharAt(col, QUEEN);
      return builder.toString();
    }).toList();
  }

}
