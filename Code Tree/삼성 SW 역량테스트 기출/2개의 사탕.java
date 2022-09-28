import java.util.*;
import java.io.*;

public class Main {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static char[][] map;
    static boolean[][][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader breader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(breader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 빨간 사탕과 파란 사탕의 위치
        int rr = 0;
        int rc = 0;

        int br = 0;
        int bc = 0;

        int exitR = 0;
        int exitC = 0;

        map = new char[n][m];
        visited = new boolean[n][m][n][m];
        for (int i = 0; i < n; i++) {
            String temp = breader.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = temp.charAt(j);
                if (map[i][j] == 'R') {
                    rr = i;
                    rc = j;
                    map[i][j] = '.';
                } else if (map[i][j] == 'B') {
                    br = i;
                    bc = j;
                    map[i][j] = '.';
                } else if (map[i][j] == 'O') {
                    exitR = i;
                    exitC = j;
                }
            }
        }

        // 탐색 시작
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(rr, rc, br, bc, 0));
        visited[rr][rc][br][bc] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            // 목적지에 도착하였으면 종료
            if (map[cur.rr][cur.rc] == 'O') {
                System.out.println(cur.cnt);
                return;
            }

            if (cur.cnt == 10) continue;

            // 상하좌우로 기울이기
            for (int d = 0; d < 4; d++) {
                // 이동할 위치
                int nrr = cur.rr;
                int nrc = cur.rc;
                int nbr = cur.br;
                int nbc = cur.bc;

                // 현재 위치
                int crr = cur.rr;
                int crc = cur.rc;
                int cbr = cur.br;
                int cbc = cur.bc;

                while (true) {
                    // 이동할 때마다 현재 위치를 바꿔준다.
                    crr = nrr;
                    crc = nrc;
                    cbr = nbr;
                    cbc = nbc;

                    // 빨간 사탕 이동
                    nrr += dr[d];
                    nrc += dc[d];
                    if (nrr < 0 || nrc < 0 || nrr >= n || nrc >= m || map[nrr][nrc] == '#') { // 이동 불가면 이전 위치로 바꿈
                        nrr = crr;
                        nrc = crc;
                    }

                    // 파란 사탕 이동
                    nbr += dr[d];
                    nbc += dc[d];
                    if (nbr < 0 || nbc < 0 || nbr >= n || nbc >= m || map[nbr][nbc] == '#') { // 이동 불가면 이전 위치로 바꿈
                        nbr = cbr;
                        nbc = cbc;
                    }

                    if (nrr == nbr && nrc == nbc) {
                        // 두 사탕이 모두 빠지면 실패
                        if (map[nrr][nbc] == 'O') {
                            nrr = -1;
                            nrc = -1;
                            nbr = -1;
                            nbc = -1;
                            break;
                        }
                        // 두 사탕의 위치가 같아지면 이전 위치로 바꾸고 이동 종료
                        nrr = crr;
                        nrc = crc;
                        nbr = cbr;
                        nbc = cbc;
                        break;
                    } else {
                        // 빨간색 사탕이 빠진 상태에서 기울이기를 계속 진행해서 파란색도 빠지면 안되기때문에 계속 돌림
                        if (nrr != -1 && map[nrr][nrc] == 'O') {
                            nrr = -1;
                            nrc = -1;
                        }

                        // 파란색 사탕이 빠지면 무조건 안되므로 종료
                        if (map[nbr][nbc] == 'O') {
                            nrr = -1;
                            nrc = -1;
                            nbr = -1;
                            nbc = -1;
                            break;
                        }
                    }

                    // 두 사탕의 위치가 변하지 않으면 더 이상 이동이 불가한 것이므로 종료
                    if (nrr == crr && nrc == crc && nbr == cbr && nbc == cbc) break;
                }
                if (nrr == -1 && nrc == -1 && nbr == -1 && nbc == -1) continue; // 실패한 경우 제거
                if (nrr == -1 && nrc == -1) { // 빨간색 사탕만 탈출한 경우
                    q.offer(new Node(exitR, exitC, nbr, nbc, cur.cnt + 1));
                    visited[exitR][exitC][nbr][nbc] = true;
                    continue;
                }
                if (visited[nrr][nrc][nbr][nbc]) continue;

                q.offer(new Node(nrr, nrc, nbr, nbc, cur.cnt + 1));
                visited[nrr][nrc][nbr][nbc] = true;
            }
        }
        System.out.println("-1");
    }

    static class Node {
        int rr;
        int rc;
        int br;
        int bc;
        int cnt;

        Node (int rr, int rc, int br, int bc, int cnt) {
            this.rr = rr;
            this.rc = rc;
            this.br = br;
            this.bc = bc;
            this.cnt = cnt;
        }
    }
}