package Lab11;

import java.io.*;
import java.util.*;

public class MainB {
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
        int t = 1;
        r.start();
        ini();
        while (t -- > 0) {
            solve();
        }
        r.end();
        out.close();
    }
    static void ini () {

    }
    static void solve() {
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        long answer = 0;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (arr[i] + arr[j] > arr[k] && arr[i] + arr[k] > arr[j] && arr[j] + arr[k] > arr[i])
                        answer ++;
                }
            }
        }
        out.print(answer);
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
            // out.println("\n" + (endTime - startTime) + "ms");
        }
    }
}


