import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * swea_2072
 */
public class swea_2072 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int result = 0;
            for (int i = 0; i < 10; i++) {
                int temp = Integer.parseInt(st.nextToken());
                if (temp % 2 == 1)
                    result += temp;
            }
            sb.append("#" + t + " " + result + "\n");
        }
        System.out.println(sb);
    }
}