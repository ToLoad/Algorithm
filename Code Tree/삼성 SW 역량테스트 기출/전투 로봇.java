import java.io.*;
import java.util.*;

public class Main {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	static int n;
	static int[][] map;
	static Robot robot;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		robot = new Robot(0, 0, 2, 0);

		// 레벨별로 몬스터 관리
		ArrayList<Monster> monsterList[] = new ArrayList[7];
		for (int i = 1; i < 7; i++) monsterList[i] = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					robot.r = i;
					robot.c = j;
					map[i][j] = 0;
				}

				if (map[i][j] != 0) {
					monsterList[map[i][j]].add(new Monster(i, j));
				}
			}
		}

		/*
		 * 로봇 이동 순서
		 * 1. 자신보다 레벨이 낮은 몬스터가 있다면 없애러감 (없으면 종료)
		 * 2. 그 중 가장 가까운 몬스터에게 감
		 * 3. 거리가 같다면 왼쪽 상단이 먼저
		 * 4. 전투 로봇은 본인의 레벨과 같은 수의 몬스터를 없앨 때마다 레벨이 상승
		 */

		int time = 0;
		while (true) {
			// 거리가 가장 가까운 제거 가능한 몬스터 찾기
			boolean isCan = false;
			Monster dMonster = new Monster(0, 0);
			int monsterLevel = 0;
			int minDist = Integer.MAX_VALUE;

			int level = robot.level;
			if (level > 6) level = 7; // 몬스터 최대레벨은 6이므로 거기까지만 탐색하게 최대값 설정
			for (int i = 1; i < level; i++) {
				for (Monster monster : monsterList[i]) {
					int dist = getDist(monster);
					if (dist == -1) continue; // 이동이 불가능한 곳
					isCan = true;

					// 거리가 같다면 왼쪽 상단이 먼저
					if (dist == minDist) {
						if (dMonster.r > monster.r) {
							dMonster = monster;
							monsterLevel = i;
						}

						if (dMonster.r == monster.r && dMonster.c > monster.c) {
							dMonster = monster;
							monsterLevel = i;
						}
					}

					if (dist < minDist) {
						dMonster = monster;
						monsterLevel = i;
						minDist = dist;
					}
				}
			}
			if (!isCan) break; // 제거할 몬스터가 없으면 종료

			// 몬스터 제거하고 로봇 이동
			robot.r = dMonster.r;
			robot.c = dMonster.c;
			robot.kill += 1;
			time += minDist;
			map[dMonster.r][dMonster.c] = 0;
			monsterList[monsterLevel].remove(dMonster);

			// 레벨업
			if (robot.kill == robot.level) {
				robot.level += 1;
				robot.kill = 0;
			}
		}
		System.out.println(time);
	}


	// 굳이 몬스터 하나씩 거리계산을 하지않고 한번의 탐색에서 같은 거리에 있는 몬스터들을 다 정리해두고
	// 해당 몬스터 중에서 하나를 제거하는게 시간이 더 빠르고 효율적일 것 같음.
	static int getDist (Monster monster) {
		boolean[][] visited = new boolean[n][n];
		Queue<Robot> q = new LinkedList<>();
		q.offer(new Robot(robot.r, robot.c, robot.level, 0));
		visited[robot.r][robot.c] = true;

		// 여기서 kill을 거리계산용으로 사용
		while (!q.isEmpty()) {
			Robot cur = q.poll();
			if (cur.r == monster.r && cur.c == monster.c) return cur.kill;

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (nr < 0 || nc < 0 || nr >= n || nc >= n || visited[nr][nc] || map[nr][nc] > cur.level) continue;
				visited[nr][nc] = true;
				q.offer(new Robot(nr, nc, cur.level, cur.kill + 1));
			}
		}

		return -1;
	}

	static class Robot {
		int r;
		int c;
		int level;
		int kill;

		Robot (int r, int c, int level, int kill) {
			this.r = r;
			this.c = c;
			this.level = level;
			this.kill = kill;
		}
	}

	static class Monster {
		int r;
		int c;

		Monster (int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}