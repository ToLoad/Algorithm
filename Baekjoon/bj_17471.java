import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_17471 {
    static int N;
    static int[] population;
    static boolean[] visited;
    static ArrayList<Integer>[] list;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        population = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }
        
        // 인접리스트 추가
        list = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) list[i] = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            for (int j = 0; j < a; j++) {
                list[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        min = Integer.MAX_VALUE;
        // 1개에서 N - 1개까지 뽑기
        ArrayList<Integer> A = new ArrayList<>();
        for (int i = 1; i <= N / 2; i++) {
            find(0, i, 1, A);
        }
        if (min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }
    static void find(int depth, int r, int start, ArrayList<Integer> A) {
        if (depth >= r) {
            ArrayList<Integer> B = new ArrayList<>();
            for (int i = 1; i <= N; i++) {
                if (A.contains(i)) continue;
                B.add(i);
            }
            // 둘다 연결이 되어있다면
            if (isConnect(A) && isConnect(B)) {
                // 인구차이 계산
                int aCnt = 0;
                int bCnt = 0;
                for (int i : A) aCnt += population[i];
                for (int i : B) bCnt += population[i];

                min = Math.min(min, Math.abs(bCnt - aCnt));
            }
            return;
        }

        for (int i = start; i <= N; i++) {
            A.add(i);
            find(depth + 1, r, i + 1, A);
            A.remove(A.size() - 1);
        }
    }

    static boolean isConnect(ArrayList<Integer> curList) {
        boolean[] check = new boolean[N + 1];
        check[curList.get(0)] = true;
        Queue<Integer> q = new LinkedList<>();
        q.offer(curList.get(0));

        int count = 1;
        while (!q.isEmpty()) {
            int start = q.poll();
            for (int i : list[start]) {
                if (!check[i] && curList.contains(i)) {
                    check[i] = true;
                    count++;
                    q.offer(i);
                }
            }
        }

        if (count == curList.size()) return true;
        return false;
    }
}
