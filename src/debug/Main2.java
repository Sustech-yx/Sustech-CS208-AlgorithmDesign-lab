package debug;

import java.io.*;
import java.util.*;

public class Main2 {
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
    static Reader in;
    static PrintWriter out;

    public static void main(String[] args)  {
        in = new Reader();
        out = new PrintWriter(System.out);
        Task solver = new  Task();
        try {
            solver.solve(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();
    }
    static class Task {
        static int[] fa;
        static long[] number ;
        public void solve(Reader in, PrintWriter out) throws IOException {
            int n = in.nextInt();
            int m = in.nextInt();
            Run r = new Run();
            int max_edge = -1;
            fa = new int[n + 1];
            number = new long[n + 1];
            edge[] edges = new edge[n-1];
            for (int i = 0; i < n + 1; i++) {
                fa[i] = i;
            }
            for (int i = 0; i < n + 1; i++) {
                number[i] = 1;
            }
            for (int i = 0; i < n - 1; i++) {
                int p1 = in.nextInt();
                int p2 = in.nextInt();
                int w = in.nextInt();
                edges[i] = new edge(p1, p2, w);
                max_edge = Math.max(w, max_edge);
            }
            long[] result = new long[max_edge + 1];
            Arrays.sort(edges, Comparator.comparingInt(o -> o.w));

            //start
            Arrays.fill(result,0);
            for(edge ed :edges){
                int r1 = find(ed.p1);
                int r2 = find(ed.p2);
                result[ed.w] +=(number[r1])*(number[r2]);
                merge(r1,r2);
            }

            long re = 0;
            for(int i = 0;i<result.length;i++){
                if(result[i]!=0){
                    result[i]+=re;
                    re = result[i];
                }else{
                    result[i] = re;
                }
            }
            long temp;

            for (int i = 0;i<m;i++) {
                temp = in.nextLong();
                if (temp>max_edge)
                    out.print(result[max_edge]+" ");
                else out.print(result[(int)temp]+" ");
            }
            r.end();
        }

        public void merge(int i, int j) {
            //merge i into j
            if(number[j]>number[i]){
                fa[i] = j;
                number[j]+=number[i];
            }else{
                fa[j] = i;
                number[i]+=number[j];
            }

        }

        public int find(int i) {
            if(i!=fa[i]){
                fa[i] = find(fa[i]);
            }
            return fa[i];
        }

    }

    static class edge{
        int w;
        int p1;
        int p2;

        public edge(int p1,int p2,int w){
            this.w=w;
            this.p1=p1;
            this.p2=p2;
        }

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
// From zxy, tle60
}

