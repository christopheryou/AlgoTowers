public class Task3 {
    private int l1, l2, l3, l4;
    private int area;
    public int maximalRectangle(int[][] matrix, int h) {
        if (matrix == null || matrix.length == 0) {
            System.out.println("Invalid Matrix or Matrix Length is 0");
            return 0;
        }
        // FIRST 2 FOR LOOPS:
        // I will describe what the for loops are doing here (outside of the loops)
        // in order to make the code look a little cleaner.
        // Essentially there are 6 loops and I will give an example of what happens.
        // For each vertex of the matrix, we will check to make sure that it is >= h

        // NEXT 4 FOR LOOPS:
        // The Next 4 for loops work together to determine the area of rectangles that
        // can be formed from the given vertices that we are observing.  They also determine
        // If a rectangle can be made to every other vertex in the matrix and this is checked
        // by verifying that the index is >= h.
        // If a rectangle can be made to another vertex, then we determine if the area from our
        // current vertex to that new vertex is larger than our previous max area.
        // If it is, then we record its location and satisfy the constraint with a new highest area.
        for (int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] >= h) {
                    for(int p = i; p < matrix.length; p++){
                        for(int q = j; q < matrix[i].length; q++) {
                            if(matrix[p][q] >= h) {
                                boolean check = true;
                                for(int x = i; x <= p; x++) {
                                    for(int y = j; y <= q; y++){
                                        if(matrix[x][y] < h) {
                                            check = false;
                                            break;
                                        }
                                        if(!check) {
                                            break;
                                        }
                                    }
                                }

                                // If at any point our rectangle is disrupted
                                // we do not check for the area as we do not
                                // have a rectangle.

                                // If we do have a rectangle, we check if the area needs
                                // to be updated (though unnecessary with max)
                                // and then we record the vertices.
                                if(check) {
                                    if (area < (p-i+1)*(q-j+1)) {
                                        area = Math.max(area, (p-i+1)*(q-j+1));
                                        l1 = i;
                                        l2 = j;
                                        l3 = p;
                                        l4 = q;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (area == 0) {
            return -1;
        }
        System.out.format("%d %d %d %d\n", l1, l2, l3, l4);
        return area;
    }
}
