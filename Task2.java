import java.util.Arrays;
public class Task2 {
    private int location1;
    private int location2;
    private int max = 0;
    public int maximalSquare(int[][] matrix, int h) {
        if (matrix == null || matrix.length == 0) {
            System.out.println("Invalid Matrix or Matrix Length is 0");
            return 0;
        }
        // We use a similar form of memoization as in Task1.
        // The + 1 is to prevent an index out of bounds
        int[] memo = new int[matrix[0].length + 1];
        Arrays.fill(memo, 0);
        int prev = 0;
        // We start at index 1, 1 as opposed to 0, 0
        // Because we are looking at the index diagonally prior to our current index
        // i.e., start at 1, 1 look at 0, 0.
        // We look at each row and are recording the square that can be formed.
        // The way we do this is by keeping track of an array called memo.
        // Memo is a 1D array of length equal to columns and is updated each stage of the loop

        // The reason I use 1, 1 is because I felt it would be easiest this way to observe each row based on
        // What the previous row was.  That way I can update the current row more easily.
        // I could have just as easily used 0, 0 and added 1 to look at the current row,
        // but I felt this to be easier.
        for (int i = 1; i <= matrix.length; i++) {
            for (int j = 1; j <= matrix[0].length; j++) {
                // Record memo[j] to determine if we need to update.
                int temp = memo[j];
                // If the location is valid (>= h)
                // Then we update memo[j] with our new information
                // We take the min because if any of the values are 0, then we only want to have 1 as our result
                // If they are >= 0, then we could have even greater numbers.
                // Once done, we check if memo[j] is our max and record its location if necessary.
                if (matrix[i - 1][j - 1] >= h) {
                    memo[j] = Math.min(Math.min(memo[j - 1], prev), memo[j]) + 1;
                    if (max <= memo[j]) {
                        location1 = i-1;
                        location2 = j-1;
                        max = Math.max(max, memo[j]);
                    }
                } else {
                    memo[j] = 0;
                }
                prev = temp;
            }
        }

        // This check is to make sure there's an answer
        if (max == 0) {
            return -1;
        }
        System.out.format("%d %d %d %d\n", location1 - (max - 1), location2 - (max - 1), location1, location2);
        return max;
    }
}
