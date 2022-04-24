import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static int N, L, R;
    static int[][] countries;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        countries = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                countries[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = -1;
        boolean isCan = true; // while문 진입을 위해 초기값 true

        while (isCan) {
            // 이동 완료 후 날짜 증가 및 방문 기록 초기화
            isCan = false;
            day++;
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j])
                        if (move(i, j))
                            isCan = true; // 오늘 이동 가능한 나라가 있었으면 true -> 다음날 탐색 진행
                }
            }
        }
        System.out.println(day);
    }

    static boolean move(int r, int c) {
        // 현재 칸에서 이동 여부
        boolean isMove = false;

        // 현재 칸에서 이동 가능한 나라 목록
        ArrayList<Node> moveList = new ArrayList<>();

        // 이동 가능한 나라의 인구수 합
        int sum = countries[r][c];

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(r, c));
        moveList.add(new Node(r, c));
        visited[r][c] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = node.r + dr[i];
                int nc = node.c + dc[i];

                if (nr >= N || nc >= N || nr < 0 || nc < 0 || visited[nr][nc])
                    continue;

                int diff = Math.abs(countries[node.r][node.c] - countries[nr][nc]);
                if (diff >= L && diff <= R) {
                    isMove = true;
                    visited[nr][nc] = true;
                    sum += countries[nr][nc];
                    moveList.add(new Node(nr, nc));
                    q.offer(new Node(nr, nc));
                }
            }
        }

        // 이동 가능한 나라들의 인구를 바꿔준다.
        int avgPerson = sum / moveList.size();
        for (Node node : moveList) {
            countries[node.r][node.c] = avgPerson;
        }

        return isMove;
    }
}

class Node {
    int r;
    int c;

    Node(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
