package Lab5;

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
        ini();
        solve();
        r.end();
        out.close();
    }
    static void ini () {

    }
    static void solve() {
        int n, m, k;
        n = in.nextInt();
        m = in.nextInt();
        k = in.nextInt();
        long t = in.nextLong();
        int[] people = new int[n];
        int[] house = new int[m];
        for (int i = 0; i < n; i++) {
            people[i] = in.nextInt();
        }
        for (int i = 0; i < m; i++) {
            house[i] = in.nextInt();
        }
        int[] counter = new int[m];
        int count = 0;
        Arrays.sort(people);
        Arrays.sort(house);
        int index = 0;
        int house_position, people_position;
        for (int i = 0; i < n && index < m; i++) {
            people_position = people[i];
            house_position = house[index];
            if (Math.abs(people_position - house_position) > t) {
                if (people_position > house_position) {
                    index ++;
                    i --;
                }
            } else if (counter[index] >= k) {
                index ++;
                i --;
            } else {
                counter[index] ++;
                count ++;
            }
        }
        out.println(count);
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

