import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static boolean[] visited;
    static int[] result;
    static int[] array;

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        array = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        // 수열을 오름차순으로 출력해야함
        Arrays.sort(array);

        result = new int[M];
        visited = new boolean[N];
        find(0, M);
        System.out.println(sb);
    }
    
    static void find(int cnt, int m) {
        if (cnt >= m) {
            for (int i : result) {
                sb.append(i + " ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < array.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                result[cnt] = array[i];
                find(cnt + 1, m);
                visited[i] = false;
            }
        }

    }
}
