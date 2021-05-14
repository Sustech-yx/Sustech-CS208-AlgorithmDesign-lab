package Lab5;

import java.util.*;
import java.io.*;

public class testB {
    static int[] find;
    static final int MAGIC_BOUND = 200_000;
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        Random random = new Random();
        BufferedWriter bw = new BufferedWriter(new FileWriter("in.in"));

        // int n = in.nextInt();
        int n = 200_000;
        find = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            find[i] = i;
        }
        bw.write(n + "\n");
        int weight;
        for (int i = 0; i < n; i++) {
            weight = random.nextInt(Integer.MAX_VALUE);
            bw.write(weight + " ");
        }
        bw.write("\n");
        int cnt = n - 1, x, y, x1, y1, p, q, t;

        while (cnt > 0) {
            p = random.nextInt(MAGIC_BOUND);
            q = random.nextInt(MAGIC_BOUND);
            x = p % n;
            y = q % n;
            x1 = findT(x);
            y1 = findT(y);
            if (x1 != y1) {
                t = random.nextInt(Integer.MAX_VALUE);
                find[x1] = y1;
                cnt --;
                x1 ++; y1 ++;
                bw.write(x1 + " " + y1 + " " + t + "\n");
            }
        }
        bw.close();
    }
    static int findT (int index) {
        if (index == find[index]) return index;
        else {
            find[index] = findT(find[index]);
            return find[index];
        }
    }
}
