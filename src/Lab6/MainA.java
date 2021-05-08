package Lab6;

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
        r.start();
        solve();
        r.end();
        out.close();
    }
    static void ini () {

    }
    static void solve() {
        int n = in.nextInt();
        if (n == 1) {
            out.println(in.nextInt());
            return;
        }
        long[] array = new long[n];
        long answer = 0;
        long max = Integer.MIN_VALUE;
        long min = Integer.MAX_VALUE;
        boolean hasMax = false;
        boolean hasMin = false;
        for (int i = 0; i < n; i++) {
            array[i] = in.nextLong();
            if (array[i] > 0) {
                min = Math.min(array[i], min);
                hasMin = true;
                answer += array[i];
            }
            else if (array[i] < 0) {
                max = Math.max(array[i], max);
                hasMax = true;
                answer -= array[i];
            }
            else {
                hasMax = hasMin = true;
            }
        }
        if (!hasMax) {
            answer -= 2 * min;
        }
        if (!hasMin) {
            answer += 2 * max;
        }
        out.println(answer);
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
            // out.println((endTime - startTime) + "ms");
        }
    }
}

