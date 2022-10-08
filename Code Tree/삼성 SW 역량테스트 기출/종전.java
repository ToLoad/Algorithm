import java.io.*;
import java.util.*;

public class Main {
	static int n, total;
	static int map[][];
	static boolean boarder[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				total += map[i][j];
			}
		}

		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 1; k < n; k++) {
					for (int l = 1; l < n; l++) {
						if (isPossibleToDraw(i, j, k, l)) {
							answer = Math.min(answer, getScore(i, j, k, l));
						}
					}
				}
			}
		}
		System.out.println(answer);
	}

	static boolean isPossibleToDraw(int r, int c, int k, int l) {
		return isRange(r - k, c + k) && isRange(r - k - l, c + k - l) && isRange(r - l, c - l);
	}

	static boolean isRange(int r, int c) {
		return 0 <= r && r < n && 0 <= c && c < n;
	}

	static int getScore(int r, int c, int k, int l) {
		int population[] = new int[5];
		drawBorder(r, c, k, l);

		for (int i = 0; i < r - l; i++) {
			for (int j = 0; j <= c + k - l && !boarder[i][j]; j++) {
				population[0] += map[i][j];
			}
		}

		for (int i = r - l; i < n; i++) {
			for (int j = 0; j < c && !boarder[i][j]; j++) {
				population[1] += map[i][j];
			}
		}

		for (int i = 0; i <= r - k; i++) {
			for (int j = n - 1; j >= c + k - l + 1 && !boarder[i][j]; j--) {
				population[2] += map[i][j];
			}
		}

		for (int i = r - k + 1; i < n; i++) {
			for (int j = n - 1; j >= c && !boarder[i][j]; j--) {
				population[3] += map[i][j];
			}
		}

		population[4] = total - population[0] - population[1] - population[2] - population[3];
		Arrays.sort(population);
		return population[4] - population[0];
	}

	static void drawBorder(int r, int c, int k, int l) {
		// 우측 상단, 좌측 상단, 좌측 하단, 우측 하단으로 가는 방향
		int[] dr = {-1, -1, 1, 1};
		int[] dc = {1, -1, -1, 1};

		boarder = new boolean[n][n];
		for (int i = 0; i < n; i++) Arrays.fill(boarder[i], false);

		for (int d = 0; d < 4; d++) {
			for (int q = 0; q < (d % 2 == 0 ? k : l); q++) {
				r += dr[d];
				c += dc[d];
				boarder[r][c] = true;
			}
		}
	}
}