import java.util.*;
import java.io.*;

public class Main {
    // 방향 전환의 편리를 위해 0, 3과 1, 2로 대칭되게 설정
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, 1, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        
        // 테스트 케이스 횟수 만큼 반복
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            // 구슬의 현 위치 입력받기
            ArrayList<Marble> marbleList = new ArrayList<>();
            for (int i = 0; i < m ; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int r = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken()) - 1;
                char temp = st.nextToken().charAt(0);
                
                // 방향에 따른 dir 설정
                int dir = 2;

                if (temp == 'U') dir = 0;
                else if (temp == 'D') dir = 3;
                else if (temp == 'R') dir = 1;

                marbleList.add(new Marble(r, c, dir));
            }
            sb.append(moveMarbles(n, marbleList) + "\n");
        }
        System.out.println(sb);
    }

    static int moveMarbles(int n, ArrayList<Marble> marbleList) {
        // 구슬이 2N 만큼 움직이면 제자리로 돌아오는데 그 동안 충돌이 일어나지 않았으면 이후로도 일어나지않음.
        // 즉 2N 반복 이후에 남아있는 구슬의 수를 찾으면 된다.
        for (int i = 0; i < 2 * n; i++) {
            ArrayList<Marble>[][] nextMarbleList = new ArrayList[n][n]; // 다음 위치의 구슬들
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) nextMarbleList[r][c] = new ArrayList<>();
            }

            for (Marble marble : marbleList) {
                int nr = marble.r + dr[marble.dir];
                int nc = marble.c + dc[marble.dir];

                // 방향을 바꿔주어야함
                if (nr >= n || nr < 0 || nc >= n || nc < 0) {
                    // 현재 위치에 방향만 바뀐 상태로 저장
                    nextMarbleList[marble.r][marble.c].add(new Marble(marble.r, marble.c, 3 - marble.dir));
                } else {
                    nextMarbleList[nr][nc].add(new Marble(nr, nc, marble.dir));
                }
            }

            // 중첩된 구슬들 제거하고 아닌 구슬들을 넣어주기
            marbleList.clear();
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if (nextMarbleList[r][c].size() == 1) {
                        marbleList.add(nextMarbleList[r][c].get(0));
                    }
                }
            }
        }

        return marbleList.size();
    }

    static class Marble {
        int r;
        int c;
        int dir;

        Marble (int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }
}