import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.io.FileWriter;
import java.io.BufferedWriter;


class AlgoTowersLogger {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        String[] inputs = input.split(" ");
        int m = Integer.parseInt(inputs[0]);
        int n = Integer.parseInt(inputs[1]);
        int h = Integer.parseInt(inputs[2]);
        int[][] mat = new int[m][n];
        for (int i = 0; i < m; i++) {
            String line = s.nextLine();
            String[] lines = line.split(" ");
            for (int j = 0; j < n; j++) {
                mat[i][j] = Integer.parseInt(lines[j]);
            }
        }

        try {
            File file = new File("logs.csv");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw;
            BufferedWriter bw;
            fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);
            long startTime = System.nanoTime();
            int answer = 0;
            switch(Integer.parseInt(args[0]))
            {
                case 1:
                Task1 t1 = new Task1();
                answer = t1.maximalSquare(mat, h);
                break;

                case 2:
                Task2 t2 = new Task2();
                answer = t2.maximalSquare(mat, h);
                break;

                case 3:
                Task3 t3 = new Task3();
                answer = t3.maximalRectangle(mat, h);
                break;

                case 4:
                Task4 t4 = new Task4();
                answer = t4.maximalRectangle(mat, h);
                break;

                case 5:
                Task5 t5 = new Task5();
                answer = t5.maximalRectangle(mat, h);
                break;

                default:
                System.out.println("No known task");
                break;
            }
            long endTime = System.nanoTime();
            if (answer == -1) {
                System.out.format("No max square or rectangle of h = %d\n", h);
            }
            long duration = (endTime - startTime) / 1000000;
            bw.write("m,n,h,time,task\n");
            String logger = String.format("%d,%d,%d,%d,%d\n",m,n,h,(int)duration,Integer.parseInt(args[0]));
            bw.write(logger);
            bw.close();
            fw.close();
        }
        catch (IOException e) {
            System.out.println("Exception caught");
        }
    }
}
