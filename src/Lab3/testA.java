package Lab3;

import java.util.*;
import java.io.*;

public class testA {
    public static void main(String[] args) throws IOException {
        Random random = new Random();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("in.in"));
        int t = 100;
        bufferedWriter.write(t + "\n");
        int m, n, s;
        for (int i = 0; i < t; i++) {
            m = random.nextInt(100);
            n = random.nextInt(100);
            s = m + n;
            bufferedWriter.write(s + " " + m + " " + n + "\n");
        }
        bufferedWriter.close();
    }
}
