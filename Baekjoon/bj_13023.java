import java.io.*;
import java.util.*;

public class bj_13023 {
    static ArrayList<Integer>[] list;
    static boolean[] visited;
    static boolean isCan;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N];
        for (int i = 0; i < N; i++) list[i] = new ArrayList<Integer>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (!isCan) dfs(i, 1);
        }

        if (isCan) System.out.println(1);
        else System.out.println(0);
        
    }

    static void dfs(int cur, int cnt) {
        if (cnt == 5) {
            isCan = true;
            return;
        }

        visited[cur] = true;
        for (int i : list[cur]) {
            if (!visited[i]) dfs(i, cnt + 1);
        }
        visited[cur] = false;
    }
}