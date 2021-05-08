package Lab10;

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
            out.println((endTime - startTime) + "ms");
        }
    }

    public static void main(String[] args) {
        int n = in.nextInt();
        Run r = new Run();
        ini();
        while (n -- > 0) {
            solve();
        }
        r.end();
        out.close();
    }
    static long[] pow = new long[62];
    static void ini () {
        pow[0] = 1;
        for (int i = 1; i < 62; i++) {
            pow[i] = pow[i-1] * 2;
        }
    }
    static long answer;
    static void solve() {
        long start = in.nextLong();
        long end = in.nextLong();
        if (start > end) {
            out.println(0);
            return;
        }
        answer = 0;
        out.println(calL(1, end) - calL(1, start - 1));
    }
    static long calL (long start, long end) {
        if (start > end) return 0;
        if (start == end) return calL(start);
        int index = 0;
        if (start == 1 && (index = Arrays.binarySearch(pow, end + 1)) >= 0) {
            return pow[index] / 2;
        }
        for (int i = 0; i < 62; i++) {
            if (pow[i] == end) {
                return pow[i-1] + 1;
            }
        }
        for (int i = 0; i < 61; i++) {
            if (pow[i] < end && pow[i + 1] > end) {
                index = i;
                break;
            }
        }
        // (1,11)->(1,7)+(8)+(9,11)->(1,7)+(8)+conv(5,7)

        return calL(1, pow[index] - 1) + convL(pow[index] + 1, pow[index], end) + calL(pow[index]);
    }
    static int calL(long i) {
        while (i % 2 == 0) {
            i /= 2;
        }
        if (i % 4 == 1) {
            return 1;
        }
        return 0;
    }
    static long convL (long start, long mid, long end) {
        long len = end - start + 1;
        return len - (calL(1, mid - 1) - calL(1, 2 * mid - end - 1));
    }
}
