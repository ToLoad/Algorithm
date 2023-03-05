import java.io.*;
import java.util.*;

public class bj_2146 {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int idx;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 각각의 육지를 확인
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] != 0) check(i, j);
            }
        }

        // 다리 찾기(각 섬마다)
        for (int i = 1; i <= idx; i++) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (map[r][c] == i) {
                        find(r, c, i);
                    }
                }
            }   
        }

        System.out.println(min);

        // for (int i = 0; i < N; i++) {
        //     for (int j = 0; j < N; j++) {
        //         System.out.print(map[i][j] + " ");
        //     }
        //     System.out.println();
        // }
    }

    static void check(int r, int c) {
        idx += 1;
        Queue<Node> q = new LinkedList<>();

        q.offer(new Node(r, c, 0));
        visited[r][c] = true;
        map[r][c] = idx;

        while (!q.isEmpty()) {
            Node node = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = node.r + dr[d];
                int nc = node.c + dc[d];

                if (nr >= N || nc >= N || nr < 0 || nc < 0 || visited[nr][nc] || map[nr][nc] == 0) continue;

                visited[nr][nc] = true;
                q.offer(new Node(nr, nc, 0));
                map[nr][nc] = idx;
            }
        }
    }

    static void find(int r, int c, int num) {
        boolean isCan = false;
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr >= N || nc >= N || nr < 0 || nc < 0) continue;
            if (map[nr][nc] == 0) isCan = true;
        }

        // 바다와 이어져있다면 다리 건설
        if (isCan) {
            visited = new boolean[N][N];
            Queue<Node> q = new LinkedList<>();
            visited[r][c] = true;
            q.offer(new Node(r, c, 0));

            while (!q.isEmpty()) {
                Node node = q.poll();
                if (map[node.r][node.c] != 0 && map[node.r][node.c] != num) {
                    min = Math.min(min, node.cnt - 1);
                    return;
                }
    
                for (int d = 0; d < 4; d++) {
                    int nr = node.r + dr[d];
                    int nc = node.c + dc[d];
    
                    if (nr >= N || nc >= N || nr < 0 || nc < 0 || visited[nr][nc]) continue;
    
                    // 바다이거나 다른 육지일 때만 방문
                    if (map[nr][nc] == 0 || map[nr][nc] != num) {
                        visited[nr][nc] = true;
                        q.offer(new Node(nr, nc, node.cnt + 1));
                    }
                }
            }


        } else return;
    }

    static class Node {
        int r;
        int c;
        int cnt;

        Node (int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
}
