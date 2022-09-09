// https://www.codetree.ai/missions/2/problems/move-to-max-adjacent-cell-simultaneously/description

import java.util.*;
import java.io.*;

public class Main {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ArrayList<Node> ballList = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            ballList.add(new Node(r - 1, c - 1));
        }

        while (t-- > 0) {
            // 구슬 움직이기
            int[][] ballMap = new int[n][n];
            for (int i = 0; i < ballList.size(); i++) {
                Node node = ballList.get(i);

                // 상하좌우 중 가장 큰 값
                int max = Integer.MIN_VALUE;
                Node next = new Node(-1, -1);

                for (int d = 0; d < 4; d++) {
                    int nr = node.r + dr[d];
                    int nc = node.c + dc[d];

                    if (nr >= n || nr < 0 || nc >= n || nc < 0) continue;
                    if (max < map[nr][nc]) {
                        max = map[nr][nc];
                        next = new Node(nr, nc);
                    }
                }
                ballMap[next.r][next.c]++;
            }

            // 겹치는 구슬 없애고 남은 구슬 재등록
            ballList.clear();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (ballMap[i][j] == 1) {
                        ballList.add(new Node(i, j));
                    }
                }
            }
        }
        System.out.println(ballList.size());
    }

    static class Node {
        int r;
        int c;

        Node (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}