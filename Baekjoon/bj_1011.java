import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_1011 {
    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            result = Integer.MAX_VALUE;
            move(start, 0, 0, end);
            sb.append(result + "\n");
        }
        System.out.println(sb.toString());
    }

    static void move(int cur, int speed, int cnt, int end) {
        if (cur >= end) return;

        if (cur == end - 1) {
            if (speed >= 0 && speed <= 2) {
                result = Math.min(result, cnt + 1);
                return;
            }
            return;
        }

        if (speed - 1 >= 0) move(cur + speed - 1, speed - 1, cnt + 1, end);
        if (speed != 0) move(cur + speed, speed, cnt + 1, end);
        move(cur + speed + 1, speed + 1, cnt + 1, end);
    }
}

