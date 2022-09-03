// https://www.codetree.ai/missions/5/problems/next-level/description

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        Level level = new Level();
        System.out.println(level);

        level = new Level(st.nextToken(), Integer.parseInt(st.nextToken()));
        System.out.println(level);
    }
}

class Level {
    String id = "codetree";
    int level = 10;

    Level() {}

    Level (String id, int level) {
        this.id = id;
        this.level = level;
    }

    @Override
    public String toString() {
        return "user " + id + " lv " + level;
    }
}