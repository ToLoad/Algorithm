// https://www.codetree.ai/missions/5/problems/007/description

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        Zero zero = new Zero(st.nextToken(), st.nextToken(), Integer.parseInt(st.nextToken()));
        System.out.println(zero);
    }
}

class Zero {
    String code;
    String point;
    int time;

    Zero (String code, String point, int time) {
        this.code = code;
        this.point = point;
        this.time = time;
    }

    @Override
    public String toString() {
        return "secret code : " + code + "\nmeeting point : " + point + "\ntime : " + time;
    }
}