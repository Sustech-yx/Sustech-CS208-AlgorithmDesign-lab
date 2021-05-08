package Lab3;

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
        int t = in.nextInt();
        r.start();
        ini();
        while (t -- > 0) {
            solve();
        }
        r.end();
        out.close();
    }
    static boolean[] check = new boolean[26];
    static int[] pow = new int[26];
    static void ini () {
        check[0] = true;
        check['i'-'a'] = true;
        check['e'-'a'] = true;
        check['o'-'a'] = true;
        check['u'-'a'] = true;
        pow[0] = 1;
        for (int i = 1; i < 26; i++) {
            pow[i] = pow[i-1] * 2;
        }
    }
    static void solve() {
        char[] array = in.next().toCharArray();
        int n = array.length;
        Graph myGraph = new Graph(array, n);
    }
    static class Graph {
        boolean[] hasV = new boolean[26];
        int number = 0;
        int[] sum = new int[26];
        int[][] adjToM = new int[26][26];
        byte[] find;

        public Graph (char[] array, int n) {
            for (int i = 0; i < n; i++) {
                if (isY(array[i])) continue;
                hasV[array[i]-'a'] = true;
                if (i == n - 1) break;
                if (!isY(array[i+1]) && array[i] != array[i+1]) {
                    adjToM[array[i]-'a'][array[i+1]-'a'] ++;
                    adjToM[array[i+1]-'a'][array[i]-'a'] ++;
                }
            }
            if (!isY(array[n-1])) hasV[array[n-1] - 'a'] = true;
            for (int i = 0; i < 26; i++) {
                if (hasV[i]) number ++;
            }
            find = new byte[number];
            int index = 0;
            for (int i = 0; i < 26; i ++) {
                if (hasV[i]) {
                    find[index] = (byte) i;
                    index ++;
                }
            }
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    sum[i] += adjToM[i][j];
                }
            }
            work();
            out.println(answer);
        }
        int answer;
        public void work () {
            answer = 0;
            if (number <= 1) return;
            boolean[] bString = new boolean[number];
            bString[number - 1] = true;

            int temp, index;
            int maxLoop = pow[number - 1];
            int[] answerList = new int[maxLoop - 1];
            answerList[0] = sum[find[number - 1]];
            answer = answerList[0];

            for (int i = 1; i < maxLoop - 1; i ++) {
                index = -1;
                for (int j = 0; j < number - 1; j ++) {
                    bString[j] = ((i >>> j) & 1) == 1;
                    if (index == -1 && bString[j]) {
                        index = j;
                        bString[j] = false;
                    }
                }
                temp = answerList[i - pow[index]] + sum[find[index]];
                int p = find[index], q;

                for (int k = 0; k < number; k ++) {
                    q = find[k];
                    if (bString[k]) {
                        temp -= adjToM[p][q] + adjToM[q][p];
                    }
                }
                answerList[i] = temp;
                answer = Math.max(answer, temp);
            }
        }
    }
    static boolean isY (char c) {
        return check[c-'a'];
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
            out.println((endTime - startTime) + " ms");
        }
    }
}

