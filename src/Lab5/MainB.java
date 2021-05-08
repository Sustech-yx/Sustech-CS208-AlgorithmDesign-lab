package Lab5;

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
        r.start();
        solve();
        r.end();
        out.close();
    }
    static void solve() {
        Graph graph = new Graph();
    }
    static class Graph {

        ArrayList<Edge>[] adjTo;
        int n;
        long[] node_weight;
        long[] investment;
        long[] income;
        long[] pure_income;

        public Graph () {
            n = in.nextInt();
            node_weight = new long[n];
            investment = new long[n];
            income = new long[n];
            pure_income = new long[n];
            for (int i = 0; i < n; i++) {
                node_weight[i] = in.nextLong();
            }
            adjTo = new ArrayList[n];

            for (int i = 0; i < n; i++) {
                adjTo[i] = new ArrayList<>();
            }
            int p, q; long w;
            for (int i = 0; i < n - 1; i++) {
                p = in.nextInt() - 1;
                q = in.nextInt() - 1;
                w = in.nextLong();
                adjTo[p].add(new Edge(q, w));
                adjTo[q].add(new Edge(p, w));
            }

            work();
        }

        private void work () {
            out.println(calculate());
        }
        public long calculate () {
            long answer = 0;
            calculate(0, -1);
            ArrayList<StrangePair> a = new ArrayList<>(adjTo[0].size()/2);
            ArrayList<StrangePair> b = new ArrayList<>(adjTo[0].size()/2);
            int to;
            for (Edge e : adjTo[0]) {
                to = e.to;
                if (pure_income[e.to] >= 0) {
                    a.add(new StrangePair(investment[to], income[to], pure_income[to]));
                } else {
                    b.add(new StrangePair(investment[to], income[to], pure_income[to]));
                }
            }
            a.sort(Comparator.comparingLong(o -> o.investment));
            b.sort(Comparator.comparingLong(o -> -o.income));
            long initial_HP = node_weight[0];

            for (StrangePair p : a) {
                if (initial_HP >= p.investment) {
                    initial_HP -= p.investment - p.income;
                } else {
                    answer += p.investment - initial_HP;
                    initial_HP = p.income;
                }
            }
            for (StrangePair p : b) {
                if (initial_HP >= p.investment) {
                    initial_HP -= p.investment - p.income;
                } else {
                    answer += p.investment - initial_HP;
                    initial_HP = p.income;
                    if (initial_HP < 0) {
                        answer -= initial_HP;
                        initial_HP = 0;
                    }
                }
            }
            return answer;
        }
        private void calculate (int node, int from) {
            for (Edge e : adjTo[node]) {
                if (e.to == from) continue;
                investment[e.to] = e.weight;
                if (adjTo[e.to].size() == 1) {
                    income[e.to] = node_weight[e.to] - e.weight;
                    pure_income[e.to] = income[e.to] - e.weight;
                } else calculate(e.to, node);
            }
            if (from == -1) return;
            for (Edge e : adjTo[node]) {
                pure_income[node] += pure_income[e.to];
            }
            pure_income[node] += node_weight[node] - 2 * investment[node];
            income[node] = pure_income[node] + investment[node];
        }
    }
    static class Edge {
        int to;
        long weight;

        public Edge (int to, long weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    static class StrangePair {
        long investment;
        long income;
        long pure_income;
        public StrangePair (long a, long b, long c) {
            this.investment = a;
            this.income = b;
            this.pure_income = c;
        }
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

