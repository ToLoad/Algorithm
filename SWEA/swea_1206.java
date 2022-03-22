import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_1206 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= 10; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] apt = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                apt[i] = Integer.parseInt(st.nextToken());
            }
            int left = 0;
            int right = 0;
            int result = 0;

            for (int i = 2; i < N - 2; i++) {
                left = Math.max(apt[i - 1], apt[i - 2]); // 왼쪽 두 채 중에 높은 것
                right = Math.max(apt[i + 1], apt[i + 2]); // 우측 두 채 중에 높은 것

                int cur = apt[i]; // 현재 층 수
                
                // 조망권이 확보된 상태라면
                if (cur > left && cur > right) {
                    // 확보된 만큼 더해준다
                    result += cur - Math.max(left, right);
                }
            }
            sb.append("#" + t + " " + result + "\n");
        }
        System.out.println(sb);
    }
}
