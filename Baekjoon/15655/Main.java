import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] array;
    static int[] result;
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
        Arrays.sort(array);

        result = new int[M];
        combi(0, 0, M);
        System.out.println(sb);
    }

    static void combi(int cnt, int start, int m) {
        if (cnt >= m) {
            for (int i : result)
                sb.append(i + " ");
            sb.append("\n");
            return;
        }

        for (int i = start; i < array.length; i++) {
            result[cnt] = array[i];
            combi(cnt + 1, i + 1, m);
        }
    }
}
