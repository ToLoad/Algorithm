import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_18111 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        int[][] ground = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                ground[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(ground[i][j], max);
                min = Math.min(ground[i][j], min);
            }
        }

        // 땅의 최소부터 최대까지의 값 중에서 어느 값으로 다지는 것이 가장 효율적인지 찾기
        int time = Integer.MAX_VALUE;
        int height = -1;

        for (int i = min; i <= max; i++) {
            int curTime = 0;
            int block = B;

            // 모든 땅을 돌면서 지정된 높이(i) 로 맞춰주기
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    int diff = ground[j][k] - i;

                    if (diff > 0) {
                        curTime += Math.abs(diff) * 2;
                        block += Math.abs(diff);
                    } else if (diff < 0) {
                        curTime += Math.abs(diff);
                        block -= Math.abs(diff);
                    }
                }
            }

            // 전부 다 돌았을 때 남은 블럭이 0 이상이여야 가능한 경우다
            if (block >= 0) {
                // 시간이 같다면 더 높은 높이를 가진 값이 정답이기 때문에 >= 으로 비교한다.
                if (time >= curTime) {
                    time = curTime;
                    height = i;
                }
            }
        }
        System.out.println(time + " " + height);
    }
}
