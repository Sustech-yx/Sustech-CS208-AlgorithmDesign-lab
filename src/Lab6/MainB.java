package Lab6;

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
        ini();
        r.start();
        solve();
        r.end();
        out.close();
    }
    static void ini () {

    }
    static void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        int u, v, w;
        a = new int[n];
        b = new int[n];
        vertices = new Vertex[n];
        for (int i = 0; i < n; i++)
            vertices[i] = new Vertex(i + 1);
        for (int i = 0; i < m; i++) {
            u = in.nextInt();
            v = in.nextInt();
            w = in.nextInt();
            vertices[u-1].adjacentTo.add(new Edge(vertices[v-1], w));
        }
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            b[i] = in.nextInt();
        }
        num = n;
        P = vertices[n-1];
        dijkstra(vertices[0]);
        out.print(vertices[n-1].distance == MAGIC_NUMBER ? -1 : vertices[n-1].distance);
        // printPath(P);
        out.close();

    }

    static PriorityQueue<Vertex> q = new PriorityQueue<>(Comparator.comparingLong(o -> o.distance));

    static void printPath (Vertex v) {
        if (v.pre != null) {
            printPath(v.pre);
            out.print(" to ");
        }
        out.print(v.value);
    }

    static int num;
    static Vertex P;
    static int[] a;
    static int[] b;
    static Vertex[] vertices;

    static void dijkstra (Vertex v) {
        long waitingTime;
        int value;
        v.distance = 0;
        q.add(v);
        while (!q.isEmpty()) {
            Vertex u = q.poll();
            if (!u.known) {
                u.known = true;
                for (int j = 0; j < u.adjacentTo.size(); j ++) {
                    Edge edge = u.adjacentTo.get(j);
                    if (edge.vertex.known) continue;
                    value = edge.vertex.value - 1;
                    waitingTime = ((u.distance + edge.weight) % (a[value] + b[value])) >= a[value] ? 0 : (a[value] - ((u.distance + edge.weight) % (a[value] + b[value])));
                    // out.println(waitingTime);
                    if (edge.vertex.distance > u.distance + edge.weight + waitingTime) {
                        edge.vertex.distance = Math.min(edge.vertex.distance, u.distance + edge.weight + waitingTime);
                        q.remove(edge.vertex);
                        q.add(edge.vertex);
                    }
                }
            }
        }
    }
    static final long MAGIC_NUMBER = Long.MAX_VALUE;
    static class Vertex {
        public Vertex pre;
        boolean visited;
        int value;
        public long distance;
        public boolean known;
        ArrayList<Edge> adjacentTo;
        long arriveTimeStamp;
        public Vertex(int value) {
            distance = MAGIC_NUMBER;
            visited = false;
            this.value = value;
            known = false;
            adjacentTo = new ArrayList<>(10);
        }
        public long getDistance() {
            return distance;
        }
    }
    static class Edge {
        private final Vertex vertex;
        private final long weight;
        public Edge(Vertex v, long weight) {
            this.weight = weight;
            vertex = v;
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

