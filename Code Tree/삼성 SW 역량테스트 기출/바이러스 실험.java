import java.io.*;
import java.util.*;

public class Main {
	static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
	static int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[][] map = new int[n][n];
		for (int i = 0; i < n; i++) Arrays.fill(map[i], 5);

		int[][] addEnergy = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				addEnergy[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		PriorityQueue<Virus> virusQueue = new PriorityQueue<>();

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());

			virusQueue.offer(new Virus(r, c, age));
		}

		// 시뮬레이션
		while (k-- > 0) {
			// 1번 : 어린 바이러스부터 양분을 섭취시키고 섭취를 못한 바이러스는 죽임
			PriorityQueue<Virus> nextQueue = new PriorityQueue<>();
			ArrayList<Virus> deadList = new ArrayList<>();
			while (!virusQueue.isEmpty()) {
				Virus virus = virusQueue.poll();
				if (map[virus.r][virus.c] < virus.age) {
					deadList.add(virus);
					continue; // 양분이 부족
				}
				
				map[virus.r][virus.c] -= virus.age;
				nextQueue.offer(new Virus(virus.r, virus.c, virus.age + 1));
			}

			// 2번 : 죽은 바이러스가 양분으로 변함
			for (Virus virus : deadList) {
				map[virus.r][virus.c] += virus.age / 2;
			}

			// 3번 : 바이러스 번식 진행, 5의 배수인 것만
			while (!nextQueue.isEmpty()) {
				Virus virus = nextQueue.poll();
				virusQueue.add(virus);

				if (virus.age % 5 == 0) {
					for (int d = 0; d < 8; d++) {
						int nr = virus.r + dr[d];
						int nc = virus.c + dc[d];

						if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
						virusQueue.add(new Virus(nr, nc, 1));
					}
				}
			}

			// 4번 : 양분 추가
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] += addEnergy[i][j];
				}
			}
		}
		System.out.println(virusQueue.size());
	}

	static class Virus implements Comparable<Virus> {
		int r;
		int c;
		int age;

		Virus (int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
		}

		@Override
		public int compareTo(Virus o) {
			return age - o.age;
		}
	}
}