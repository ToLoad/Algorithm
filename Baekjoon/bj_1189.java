import java.io.*;
import java.util.*;

public class bj_1189 {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	static int r, c, k;
	static int endR, endC;
	static char[][] map;
	static boolean[][] visited;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		// 출발지와 도착지
		int startR = r - 1;
		int startC = 0;
		endR = 0;
		endC = c - 1;

		visited = new boolean[r][c];
		map = new char[r][c];
		for (int i = 0; i < r; i++) {
			String temp = br.readLine();
			for (int j = 0; j < c; j++) {
				map[i][j] = temp.charAt(j);
			}
		}

		visited[startR][startC] = true;
		dfs(1, startR, startC);
		System.out.println(answer);
	}

	static void dfs(int cnt, int curR, int curC) {
		if (cnt > k) return;

		if (cnt == k && curR == endR && curC == endC) {
			answer++;
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nr = curR + dr[d];
			int nc = curC + dc[d];

			if (nr < 0 || nc < 0 || nr >= r || nc >= c || visited[nr][nc] || map[nr][nc] == 'T') continue;
			visited[nr][nc] = true;
			dfs(cnt + 1, nr, nc);
			visited[nr][nc] = false;
		}
	}
}