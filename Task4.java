import java.util.Stack;
import java.util.Arrays;

public class Task4 {
    private int max = 0;
    private int l1, l2, l3, l4;
    public int maximalRectangle(int matrix[][], int h){
        if (matrix == null || matrix.length == 0) {
            System.out.println("Invalid Matrix or Matrix Length is 0");
            return 0;
        }
        // We will create a 2D array of size row x columns
        // We will fill the array with 0's to begin
        int memo[][] = new int[matrix.length][matrix[0].length];
        for (int[] row : memo) {
            Arrays.fill(row, 0);
        }
        // For each row we will keep track of the height per column
        // of the largest possible rectangle
        // If a value is greater than or equal to h we will increase the height by 1
        // if it is not, we will set it to 0 as we no longer have a good rectangle
        // We need to copy the previous value in order to account for how much height we've already accumulated
        // Only copy in the rows after row 0.
        boolean first = true;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (!first)
                    memo[i][j] = memo[i-1][j];
                if(matrix[i][j] < h) {
                    memo[i][j] = 0;
                }
                else {
                    memo[i][j] += 1;
                }
            }
            first = false;
            // Doing it this way lets you check for the best answer in each of your total memo rows.
            // This memo array is processed to calculate the area of the largest rectangle thus far  with the function below
            int area = calculateLargestRectangle(memo[i], i);
            // If we have a better area, we update the verticpes
            // l3 is x2.  so x2 = the current row.
            if (area > max){
                l3 = i;
                max = Math.max(area, max);
            }
        }

        if (max == 0) {
            return -1;
        }

        // l4 is found by finding the distance in the x coordinates
        // dividing the area by that distance
        // and adding that last dimension - 1 to the y1 coordinate.
        // this gives us our second column.
        int lengthBetweenX =  Math.abs(l3 - l1) + 1;

        int yCoordinate = max / lengthBetweenX - 1;

        l4 = l2 + yCoordinate;

        System.out.format("%d %d %d %d\n", l1, l2, l3, l4);
        return max;
    }

    public int calculateLargestRectangle(int[] memo, int row) {
        // To find the largest rectangle using the data structure above
        // We can essentially find the largest rectangle in a histogram.
        if (memo == null || memo.length == 0) {
            return 0;
        }
        // The stack will hold the values from memo basically ensuring
        // that we store in increasing order the values
        Stack<Integer> stack = new Stack<Integer>();
        int largest = 0;
        int i = 0;
        // For all values in the memo
        while (i < memo.length) {
            // If the value is higher than the value on top of the stack, push it on the stack
            if (stack.isEmpty() || memo[i] >= memo[stack.peek()]) {
                stack.push(i);
                i++;
            }
            // If the value is less, then calculate the area of the rectangle
            // with the value on the top of the stack as the smallest value.
            // i is used as the index on the right of the value at the top of stack
            // and the elementt that comes before is the left.
            else {
                int top = stack.peek();
                stack.pop();
                int wd;
                if (stack.isEmpty()) {
                    wd = i;
                }
                else {
                    wd = i - stack.peek() - 1;
                }
                int curArea = memo[top] * wd;
                // If we determine the found area is greater
                // We will locate the vertices.
                // We know l1 is = row - (memo[top] - 1) because
                // We know l1 (x1) is above in our search.

                // We know l2 (y1) is either equal to the top number in the Stack
                // as we are traversing the indexes of the memo.
                // or it is some difference less than the top item on the stack
                // due to some items being popped off before.
                // we find the true start by determining the difference between the top item
                // and the next item in the stack and subtracting 1 (to include itself).
                // l2 (y1) is the difference between that and the top.
                if (largest < curArea) {
                    largest = Math.max(curArea, largest);
                    if (largest > max) {
                        l1 = row - (memo[top] - 1);
                        int z = 0;
                        if (!stack.isEmpty()) {
                            z = top - stack.peek() - 1;
                        }
                        l2 = top - z;
                    }
                }
            }
        }
        // The remaining values are popped from the stack and you calculate the area
        // with all the values as the smallest value (or bar in a histogram example).
        // This is the same calculation as above.
        while (!stack.isEmpty()) {
            int top = stack.peek();
            stack.pop();
            int wd;
            if (stack.isEmpty()) {
                wd = i;
            }
            else {
                wd = i - stack.peek() - 1;
            }
            int curArea = memo[top] * wd;

            if (largest < curArea) {
                largest = Math.max(curArea, largest);
                if (largest > max) {
                    l1 = row - (memo[top] - 1);
                    int z = 0;
                    if (!stack.isEmpty()) {
                        z = top - stack.peek() - 1;
                    }
                    l2 = top - z;
                }
            }
        }
    return largest;
    }
}
