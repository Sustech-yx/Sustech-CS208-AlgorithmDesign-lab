package Lab6;

import Utils.SampleGenerationDevice;

import java.io.IOException;
import java.util.Random;

public class testB {
    static final int MAGIC_BOUND = 200_000;
    public static void main(String[] args) throws IOException {
        Random random = new Random();
        SampleGenerationDevice spg = new SampleGenerationDevice();
        int n = 200_000;
        int m = 1000000;
        spg.write(n + " " + m + "\n");
        find = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            find[i] = i;
        }
        int cnt = n - 1, x, y, x1, y1, p, q, t;

        while (cnt > 0) {
            p = random.nextInt(MAGIC_BOUND + 5000);
            q = random.nextInt(MAGIC_BOUND + 5000);
            x = p % n;
            y = q % n;
            x1 = findT(x);
            y1 = findT(y);
            if (x1 != y1) {
                t = random.nextInt(MAGIC_BOUND);
                find[x1] = y1;
                cnt --;
                x1 ++; y1 ++;
                spg.write(x1 + " " + y1 + " " + t + "\n");
            }
        }
        for (int i = 0; i < m - 1; i++) {
            spg.write(random.nextInt(MAGIC_BOUND) + " ");
        }
        spg.write(random.nextInt(MAGIC_BOUND) + "\n");
        spg.work();
    }
    static int[] find;
    static int findT (int index) {
        if (index == find[index]) return index;
        else {
            find[index] = findT(find[index]);
            return find[index];
        }
    }
}
