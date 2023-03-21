import java.util.*;
import java.io.*;

public class bj_15683 {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Direction {
        boolean top;
        boolean down;
        boolean left;
        boolean right;

        Direction (boolean top, boolean down, boolean left, boolean right) {
            this.top = top;
            this.down = down;
            this.left = left;
            this.right = right;
        }
    }

    static class CCTV {
        int id;
        int r;
        int c;

        CCTV (int id, int r, int c) {
            this.id = id;
            this.r = r;
            this.c = c;
        }
    }

    static ArrayList<Direction>[] cctvDir;
    static ArrayList<CCTV> cctvList;
    static int[] result;

    static int[][] map;
    static int N, M;
    static int min = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        cctvList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0 && map[i][j] != 6) 
                    cctvList.add(new CCTV(map[i][j], i, j));
            }
        }

        result = new int[cctvList.size()];
        dirInit();

        dfs(cctvList.size(), 0);
        System.out.println(min);
    }

    static void dfs(int r, int depth) {
        if (r <= depth) {
            // CCTV별 방향설정 완료 -> 남은 크기 확인
            min = Math.min(min, checkSize());
            // System.out.println(Arrays.toString(result));
            
            return;
        }

        CCTV cur = cctvList.get(depth);
        for (int i = 0; i < cctvDir[cur.id].size(); i++) {
            result[depth] = i; // CCTV의 방향 설정
            dfs(r, depth + 1);
        }
    }

    static int checkSize() {
        int[][] cMap = new int[N][M]; // 남은 크기 체크용 맵

        // 각 CCTV별로 볼 수 있는 위치 체크
        for (int i = 0; i < cctvList.size(); i++) {
            CCTV cur = cctvList.get(i);
            cMap[cur.r][cur.c] = 1;

            // 탐색해야하는 방향
            Direction dir = cctvDir[cur.id].get(result[i]);

            // 위로 가기
            if (dir.top) {
                int nr = cur.r;
                int nc = cur.c;
                while (true) {
                    nr -= 1;
                    if (checkLength(nr, nc)) {
                        cMap[nr][nc] = 1;
                    } else break;
                }
            }

            // 아래로 가기
            if (dir.down) {
                int nr = cur.r;
                int nc = cur.c;
                while (true) {
                    nr += 1;
                    if (checkLength(nr, nc)) {
                        cMap[nr][nc] = 1;
                    } else break;
                }
            }

            // 왼쪽으로 가기
            if (dir.left) {
                int nr = cur.r;
                int nc = cur.c;
                while (true) {
                    nc -= 1;
                    if (checkLength(nr, nc)) {
                        cMap[nr][nc] = 1;
                    } else break;
                }
            }

            // 오른쪽로 가기
            if (dir.right) {
                int nr = cur.r;
                int nc = cur.c;
                while (true) {
                    nc += 1;
                    if (checkLength(nr, nc)) {
                        cMap[nr][nc] = 1;
                    } else break;
                }
            }
        }

        int sum = N * M;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 6) cMap[i][j] = 1;
                sum -= cMap[i][j];
            }
        }

        return sum;
    }

    static boolean checkLength(int r, int c) {
        // 맵 밖이거나 벽을 만나면 못감감
        if (r < 0 || c < 0 || r >= N || c >= M) return false;
        if (map[r][c] == 6) return false;

        return true;
    }

    static void dirInit() {
        // 1번 : {상}, {하}, {좌}, {우}
        // 2번 : {상, 하}, {좌, 우}
        // 3번 : {상, 좌}, {상, 우}, {하, 좌}, {하, 우}
        // 4번 : {상, 좌, 우}, {하, 좌, 우}, {상, 하, 좌}, {상, 하, 우}
        // 5번 : {상, 하, 좌, 우}

        cctvDir = new ArrayList[6];
        for (int i = 1; i <= 5; i++) {
            cctvDir[i] = new ArrayList<>();
        }

        cctvDir[1].add(new Direction(true, false, false, false));
        cctvDir[1].add(new Direction(false, true, false, false));
        cctvDir[1].add(new Direction(false, false, true, false));
        cctvDir[1].add(new Direction(false, false, false, true));

        cctvDir[2].add(new Direction(true, true, false, false));
        cctvDir[2].add(new Direction(false, false, true, true));

        cctvDir[3].add(new Direction(true, false, true, false));
        cctvDir[3].add(new Direction(true, false, false, true));
        cctvDir[3].add(new Direction(false, true, true, false));
        cctvDir[3].add(new Direction(false, true, false, true));

        cctvDir[4].add(new Direction(true, false, true, true));
        cctvDir[4].add(new Direction(false, true, true, true));
        cctvDir[4].add(new Direction(true, true, true, false));
        cctvDir[4].add(new Direction(true, true, false, true));

        cctvDir[5].add(new Direction(true, true, true, true));
    }
}
