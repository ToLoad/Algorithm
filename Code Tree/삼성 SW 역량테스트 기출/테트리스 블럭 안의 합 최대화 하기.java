import java.io.*;
import java.util.*;

/*
 * 처음에 19개를 모두 배열에 미리 적어두고 탐색하는 방법으로 하려다가 너무 무식한 것 같아서
 * 다른 탐색 방법으로 시도하다가 안되서 다시 원상태로 돌아왔다.
 * 기존에는 모양을 나타내는 배열이 아닌 dr과 dc로 구성된 배열로 하였는데 코드를 다 지워버린터라 다시 적기 귀찮아서
 * 해설에 있는 블럭 형태를 가져와서 코드를 구성하였다.
 * 
 * 원래 구성하려고 했던 배열
 * (일자 막대가 수평일 때랑 수직일 때를 표현한 것)
 * int[][] dr = {	{0, 0, 0, 0},
 * 					{0, 1, 2, 3}	};
 * int[][] dc = {	{0, 1, 2, 3},
 * 					{0, 0, 0, 0}	};
 * 
 * 이렇게 19줄을 해두고 19번 반복을 돌리려했다.
 */
public class Main {
	static int[][][] blocks =  { 
						  { {1, 1, 1, 1},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0} },

                          { {1, 0, 0, 0},
                            {1, 0, 0, 0},
                            {1, 0, 0, 0},
                            {1, 0, 0, 0} },

                          { {1, 1, 0, 0},
                            {1, 1, 0, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0} },

                          { {1, 0, 0, 0},
                            {1, 0, 0, 0},
                            {1, 1, 0, 0},
                            {0, 0, 0, 0} },

                          { {0, 1, 0, 0},
                            {0, 1, 0, 0},
                            {1, 1, 0, 0},
                            {0, 0, 0, 0} },

                          { {1, 1, 1, 0},
                            {1, 0, 0, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0} },

                          { {1, 1, 1, 0},
                            {0, 0, 1, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0} },

                          { {1, 1, 0, 0},
                            {0, 1, 0, 0},
                            {0, 1, 0, 0},
                            {0, 0, 0, 0} },

                          { {1, 1, 0, 0},
                            {1, 0, 0, 0},
                            {1, 0, 0, 0},
                            {0, 0, 0, 0} },

                          { {0, 0, 1, 0},
                            {1, 1, 1, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0} },

                          { {1, 0, 0, 0},
                            {1, 1, 1, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0} },

                          { {1, 0, 0, 0},
                            {1, 1, 0, 0},
                            {0, 1, 0, 0},
                            {0, 0, 0, 0} },

                          { {0, 1, 1, 0},
                            {1, 1, 0, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0} },

                          { {1, 1, 0, 0},
                            {0, 1, 1, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0} },

                          { {0, 1, 0, 0},
                            {1, 1, 0, 0},
                            {1, 0, 0, 0},
                            {0, 0, 0, 0} },

                          { {1, 0, 0, 0},
                            {1, 1, 0, 0},
                            {1, 0, 0, 0},
                            {0, 0, 0, 0} },

                          { {1, 1, 1, 0},
                            {0, 1, 0, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0} },

                          { {0, 1, 0, 0},
                            {1, 1, 0, 0},
                            {0, 1, 0, 0},
                            {0, 0, 0, 0} },

                          { {0, 1, 0, 0},
                            {1, 1, 1, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0} } };

	static int n, m;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int max = -1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				max = Math.max(max, getMaxSum(i, j));
			}
		}
		System.out.println(max);
	}

	static int getMaxSum(int r, int c) {
		int max = -1;

		for (int i = 0; i < blocks.length; i++) {
			int sum = 0;
			for (int a = 0; a < 4; a++) {
				for (int b = 0; b < 4; b++) {
					int nr = r + a;
					int nc = c + b;

					if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue;
					if (blocks[i][a][b] == 1) sum += map[nr][nc];
				}
			}
			max = Math.max(max, sum);
		}
		return max;
	}
}