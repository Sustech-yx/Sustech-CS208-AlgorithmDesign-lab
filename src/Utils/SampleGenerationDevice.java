package Utils;

import java.io.*;
import java.util.*;

public class SampleGenerationDevice {
    private StringBuilder sb;
    private BufferedWriter bw;
    private Scanner in;
    private Random rng;

    public SampleGenerationDevice () throws IOException {
        sb = new StringBuilder();
        rng = new Random();
        bw = new BufferedWriter(new FileWriter("in.txt"));
        in = new Scanner(System.in);
    }

    public void write (String str) {
        sb.append(str);
    }

    public void write (long n) {
        write(n + "");
    }

    public void line () {
        write("\n");
    }

    public void work () throws IOException {
        bw.write(sb.toString());
        bw.close();
    }

    public int random (int bound) {
        return rng.nextInt(bound);
    }

    public void gen (int loop, int bound, boolean hasFalse) {
        for (int i = 0; i < loop; i++) {
            write((rng.nextInt(bound) * (!hasFalse ? 1 : random(100) % 2 == 0 ? -1 : 1)) + " ");
        }
        line();
    }
}
