package Lab11;

import java.io.*;
import java.util.*;

public class MainA {
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
        public int nextInt() {
            return Integer.parseInt(next());
        }
        public long nextLong() {
            return Long.parseLong(next());
        }
        public boolean hasNext() {
            try {
                String string = reader.readLine();
                if (string == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(string);
                return tokenizer.hasMoreTokens();
            } catch (IOException e) {
                return false;
            }
        }
    }
    static InputReader in = new InputReader(System.in);
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) {
        Run r = new Run();
        int t = in.nextInt();
        r.start();
        ini();
        while (t -- > 0) {
            solve();
        }
        r.end();
        out.close();
    }
    static long[][][] magicArray = new long[3][63][3];
    static long[] pow = new long[63];
    static void ini () {
        pow[0] = 1;
        for (int i = 1; i < 63; i++) {
            pow[i] = pow[i-1] * 2;
        }

        magicArray[0][0][0] = 1;
        magicArray[0][0][1] = 0;
        magicArray[0][0][2] = 0;
        for (int i = 1; i < 63; i++) {
            magicArray[0][i][0] = magicArray[0][i-1][0] + magicArray[0][i-1][2];
            magicArray[0][i][1] = magicArray[0][i-1][1] + magicArray[0][i-1][0];
            magicArray[0][i][2] = magicArray[0][i-1][2] + magicArray[0][i-1][1];
        }
        for (int i = 0; i < 63; i++) {
            magicArray[1][i][0] = magicArray[0][i][2];
            magicArray[1][i][1] = magicArray[0][i][0];
            magicArray[1][i][2] = magicArray[0][i][1];
        }
        for (int i = 0; i < 63; i++) {
            magicArray[2][i][0] = magicArray[1][i][2];
            magicArray[2][i][1] = magicArray[1][i][0];
            magicArray[2][i][2] = magicArray[1][i][1];
        }
    }
    static long L, R, N, n;
    static void solve() {
        L = 0;
        R = 0;
        N = 0;
        n = in.nextLong();
        divide(n);
        out.println(L + " " + R + " " + N);
    }
    static void divide (long end) {
        divide(end, 0);
    }

    /**
     * @param end
     * @param switchCount the number show that how many count of switch does this part of string go through. The number
     *                    can only be 0, 1 and 2. You know that why.
     */
    static void divide (long end, int switchCount) {
        int index = 0;
        for (int i = 0; i < 62; i++) {
            if (end >= pow[i] && end <= pow[i+1]) {
                index = i;
                break;
            }
        }
        cal(index, switchCount);
        if (end != pow[index]) {
            divide(end - pow[index], (switchCount + 1) % 3);
        }
    }
    static void cal (int index, int switchCount) {
        L += magicArray[switchCount][index][0];
        R += magicArray[switchCount][index][1];
        N += magicArray[switchCount][index][2];
    }
    static class Run {
        long startTime;
        long endTime;
        public Run () {
            start();
        }

        public void start () {
            startTime = System.currentTimeMillis();
        }
        public void end () {
            endTime = System.currentTimeMillis();
            out.println("\n" + (endTime - startTime) + "ms");
        }
    }
}

