import java.util.Arrays;

public class Task1 {
  private int max = 0;
  private int location1;
  private int location2;

  public int maximalSquare(int[][] matrix, int h) {
      if (matrix == null || matrix.length == 0) {
          System.out.println("Invalid Matrix or Matrix Length is 0");
          return 0;
      }
      // Create a 2D Array (O(mn) space) to keep track of largest square
      // At particular indices from given matrix
      // Fill with -1's initially (eventually will be filled with >= 0)
      int [][] memo = new int[matrix.length][matrix[0].length];
      for (int i = 0; i < memo.length; i++) {
          Arrays.fill(memo[i], -1);
      }

      // Start from top left corner.
      maximalSquareRec(matrix, memo, 0, 0, h);

      // Square position can be found by taking the longest length and adding it to the location (subtract 1)

      // If max area is 0 then no  answer.
      if (max == 0) {
          return -1;
      }
      System.out.format("%d %d %d %d\n", location1, location2, location1 + max - 1, location2 + max - 1);
      return max;
  }

  private int maximalSquareRec(int[][] matrix, int[][] memo, int i, int j, int h) {
      if (i > matrix.length - 1 || j > matrix[i].length - 1)
          return 0;

      if (memo[i][j] != -1) {
          return memo[i][j];
      }
      // Recursively check each value below, to the right, and diagonally rightward
      int down = maximalSquareRec(matrix, memo, i + 1, j, h);
      int right = maximalSquareRec(matrix, memo, i, j + 1, h);
      int diagonal = maximalSquareRec(matrix, memo, i + 1, j + 1, h);

      // If said value is >= h then we add 1 to the resulting minimum of the
      // recursive calls above.
      // We will loop through the entire graph once (O(mn)) time, and it will
      // break down the matrix slowly.
      // This is pretty similar to what was discussed in a previous lecture
      // Thus I don't think this needs much explaining.
      if (matrix[i][j] >= h) {
          memo[i][j] = Math.min(Math.min(down, right), diagonal) + 1;
      }
      else {
          memo[i][j] = 0;
      }

      // If memo[i][j] ends up being greater than our current max
      // We record the answer and its position
      if (memo[i][j] > max) {
          location1 = i;
          location2 = j;
          max = Math.max(memo[i][j], max);
      }
      return memo[i][j];
  }
}
