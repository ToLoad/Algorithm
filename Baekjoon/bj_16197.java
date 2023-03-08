import java.util.*;
import java.io.*;

public class bj_16197 {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int N, M;
    static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        boolean isFind = false;
        int coinAr = 0;
        int coinAc = 0;
        int coinBr = 0;
        int coinBc = 0;

        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            String temp = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = temp.charAt(j);
                if (board[i][j] == 'o' && !isFind) {
                    coinAr = i;
                    coinAc = j;
                    isFind = true;
                } else if (board[i][j] == 'o' && isFind) {
                    coinBr = i;
                    coinBc = j;
                }
            }
        }

        // BFS 탐색 시작
        int result = -1;
        boolean[][][][] visited = new boolean[N][M][N][M]; // A의 r, c 좌표, B의 r, c 좌표 기록
        Queue<Coins> q = new LinkedList<>();
        q.offer(new Coins(coinAr, coinAc, coinBr, coinBc, 0));
        visited[coinAr][coinAc][coinBr][coinBc] = true;

        while (!q.isEmpty()) {
            Coins cur = q.poll();

            if (cur.cnt >= 10) break;

            for (int d = 0; d < 4; d++) {
                int nAr = cur.aR + dr[d];
                int nAc = cur.aC + dc[d];
                int nBr = cur.bR + dr[d];
                int nBc = cur.bC + dc[d];

                // 벽일 경우 움직일 수 없음
                if (checkWall(nAr, nAc)) {
                    nAr = cur.aR;
                    nAc = cur.aC;
                }

                if (checkWall(nBr, nBc)) {
                    nBr = cur.bR;
                    nBc = cur.bC;
                }

                int remainCoin = 0;
                // 남아있는 코인 개수 확인
                if (nAr >= 0 && nAc >= 0 && nAr < N && nAc < M) remainCoin++;
                if (nBr >= 0 && nBc >= 0 && nBr < N && nBc < M) remainCoin++;

                if (remainCoin == 1) {
                    result = cur.cnt + 1;
                    q = new LinkedList<>();
                    break;
                } else if (remainCoin == 2 && !visited[nAr][nAc][nBr][nBc]) {
                    q.offer(new Coins(nAr, nAc, nBr, nBc, cur.cnt + 1));
                    visited[nAr][nAc][nBr][nBc] = true;
                }
            }
        }

        System.out.println(result);
    }

    static boolean checkWall(int r, int c) {
        if (r >= 0 && c >= 0 && r < N && c < M && board[r][c] == '#') return true;

        return false;
    }
    
    static class Coins {
        int aR;
        int aC;
        int bR;
        int bC;
        int cnt;

        Coins (int aR, int aC, int bR, int bC, int cnt) {
            this.aR = aR;
            this.aC = aC;
            this.bR = bR;
            this.bC = bC;
            this.cnt = cnt;
        }
    }
}