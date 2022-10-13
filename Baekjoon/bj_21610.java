import java.io.*;
import java.util.*;

public class bj_21610 {
	static int dr[] = {0, -1, -1, -1, 0, 1, 1, 1};
	static int dc[] = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int n, map[][];
	static boolean isCloud[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		map = new int[n][n];
		isCloud = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 비바라기 시전시 생성된 비구름
		isCloud[n - 1][0] = true;
		isCloud[n - 1][1] = true;
		isCloud[n - 2][0] = true;
		isCloud[n - 2][1] = true;

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			moveCloud(d, s);
			itsRainyDay();
			waterCopy();
			makeNewCloud();
			// print();
		}
		System.out.println(sumWater());
	}

	static void moveCloud(int d, int s) {
		boolean nCloud[][] = new boolean[n][n]; // 바뀐 구름의 위치를 저장

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(isCloud[i][j]) {
					int nr = i;
					int nc = j;
					for (int cnt = 0; cnt < s; cnt++) {
						nr += dr[d - 1];
						nc += dc[d - 1];

						if (nr < 0) nr = n - 1;
						if (nr >= n) nr = 0;
						if (nc < 0) nc = n - 1;
						if (nc >= n) nc = 0;
					}
					nCloud[nr][nc] = true;
				}
			}
		}
		isCloud = nCloud.clone();
	}

	static void itsRainyDay() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (isCloud[i][j]) {
					map[i][j] += 1;
				}
			}
		}
	}

	static void waterCopy() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (isCloud[i][j]) {
					for (int d = 0; d < 8; d++) {
						if (d % 2 == 0) continue;
						int nr = i + dr[d];
						int nc = j + dc[d];

						if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
						if (map[nr][nc] > 0) map[i][j] += 1;
					}
				}
			}
		}
	}

	static void makeNewCloud() {
		boolean nCloud[][] = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (isCloud[i][j]) continue;
				if (map[i][j] >= 2) {
					map[i][j] -= 2;
					nCloud[i][j] = true;
				}
			}
		}
		isCloud = nCloud.clone();
	}

	static int sumWater() {
		int sum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sum += map[i][j];
			}
		}
		return sum;
	}

	static void print() {
		System.out.println();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}