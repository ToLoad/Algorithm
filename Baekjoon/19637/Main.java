import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] name = new String[N];
        int[] power = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            name[i] = st.nextToken();
            power[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            long num = Long.parseLong(br.readLine());
            int left = 0;
            int right = N - 1;

            while (left <= right) {
                int mid = (left + right) / 2;

                if (power[mid] < num) left = mid + 1;
                else right = mid - 1;
            }
            sb.append(name[left] + "\n");
        }
        System.out.println(sb);
    }
}
