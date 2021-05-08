package Lab2;

import java.io.*;
import java.util.*;

public class Main {
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
        r.start();
        ini();
        int t = in.nextInt();
        while (t -- > 0) {
            solve();
        }
        r.end();
        out.close();
    }
    static void ini () {

    }
    static int[] difficulty;
    static void solve() {
        int n = in.nextInt();
        difficulty = new int[n];
        for (int i = 0; i < n; i++) {
            difficulty[i] = in.nextInt();
        }
        Arrays.sort(difficulty);
        int big, mid, small, sum = 0, max = 0;
        for (int i = n - 1; i >= 0; i --) {
            mid = 0;
            big = difficulty[i];
            sum = big + mid;

            for (int j = i - 1; j >= 0; j --) {
                small = 0;
                if (big % difficulty[j] == 0) continue;
                mid = difficulty[j];
                sum = big + mid + small;

                for (int k = j - 1; k >= 0; k --) {
                    if (big % difficulty[k] == 0 || mid % difficulty[k] == 0) continue;
                    small = difficulty[k];
                    sum = big + mid + small;
                    break;
                }
                if (sum >= max) {
                    max = sum;
                } else break;
            }

            if (sum >= max) {
                max = sum;
            } else break;
        }
        out.println(max);
    }
    static class Run {
        long startTime;
        long endTime;
        public Run () {}

        public void start () {
            startTime = System.currentTimeMillis();
        }
        public void end () {
            endTime = System.currentTimeMillis();
            out.println((endTime - startTime) + "ms");
        }
    }
}

