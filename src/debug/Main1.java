package debug;

import java.io.*;
import java.util.*;

public class Main1 {
    public static int[] parent;
    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int nodeNum = in.nextInt();
        int queryNum = in.nextInt();

        PriorityQueue<branch> bq = new PriorityQueue<>(Comparator.comparingLong(o -> o.w));
        PriorityQueue<query> qq = new PriorityQueue<>(Comparator.comparingLong(o -> o.w));
        for (int i = 0; i < nodeNum-1; i++) {
            bq.offer(new branch(in.nextInt()-1, in.nextInt()-1, in.nextLong()));
        }
        for (int i = 0; i < queryNum; i++) {
            qq.offer(new query(in.nextLong(),i));
        }

        long[] result = new long[queryNum];
        Set<root> rs = new HashSet<>();
        parent = new int[nodeNum];

        initial(nodeNum);
        branch b = bq.poll();
        int root1 = findRoot(b.u);
        int root2 = findRoot(b.v);
        rs.add(new root(union(root1,root2), 2));

        int preSeq = 0;
        root[] index = new root[2];
        for (int i = 0; i < queryNum; i++) {
            query q = qq.poll();
            long pathNum = 0;
            if (bq.isEmpty()){
                result[q.seq] = result[preSeq];
            }else {
                long limit = q.w;
                if (b.w <= limit) {
                    while (!bq.isEmpty() && bq.peek().w <= limit) {
                        b = bq.poll();
                        root1 = findRoot(b.u);
                        root2 = findRoot(b.v);
                        int belong = 0;
                        for (root r : rs) {
                            if (root1 == r.index || root2 == r.index) {
                                union(root1, root2);
                                r.num++;
                                index[belong] = r;
                                belong++;
                            }
                        }
                        if (belong == 0)
                            rs.add(new root(union(root1, root2), 2));
                        else if (belong == 2) {
                            root r1 = index[0];
                            root r2 = index[1];
                            rs.remove(r1);
                            rs.remove(r2);
                            rs.add(new root(union(r1.index, r2.index), r1.num + r2.num - 2));
                        }
                    }
                    for (root r : rs) {
                        long size = r.num;
                        pathNum += (size * (size - 1) / 2);
                    }
                }
                result[q.seq] = pathNum;
            }
            preSeq = q.seq;
        }

        out.print(result[0]);
        for (int i = 1; i < queryNum; i++) {
            out.print(" " + result[i]);
        }
        out.close();
    }

    public static void initial(int nodeNum){
        for (int i = 0; i < nodeNum; i++) {
            parent[i] = i;
        }
    }

    public static int union(int root1, int root2){
        if (root1 < root2) {
            parent[root2] = root1;
            return root1;
        }else{
            parent[root1] = root2;
            return root2;
        }
    }

    public static int findRoot(int i) {
        int j = i;
        while (i != parent[i])	i = parent[i];
        while (parent[j] != i) {
            int k = parent[j];
            parent[j] = i;
            j = k;
        }
        return i;
    }

    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }

}

class branch{
    public int u;
    public int v;
    public long w;

    public branch(int u, int v, long w){
        this.u = u;
        this.v = v;
        this.w = w;
    }
}

class query{
    public long w;
    public int seq;

    public query(long w, int seq){
        this.w = w;
        this.seq = seq;
    }
}

class root{
    public int index;
    public int num;

    public root(int index, int num){
        this.index = index;
        this.num = num;
    }
}
//lab10 b from hky