import java.io.*;
import java.util.*;

public class Main {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	static int n, minDist, time;
	static int[][] map;
	static Robot robot;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		robot = new Robot(0, 0, 2, 0);

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					robot.r = i;
					robot.c = j;
					map[i][j] = 0;
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

		time = 0;
		while (true) {
			// 거리가 가장 가까운 제거 가능한 몬스터 찾기
			minDist = 1;
			Monster dMonster = findNearMonster();
			if (dMonster.r == n + 1) break; // 더 이상 진행 불가

			// 몬스터 제거하고 로봇 이동
			robot.r = dMonster.r;
			robot.c = dMonster.c;
			robot.kill += 1;
			map[robot.r][robot.c] = 0;
			time += minDist; // 이동한 거리만큼 시간 증가

			// 레벨업
			if (robot.kill == robot.level) {
				robot.level += 1;
				robot.kill = 0;
			}
		}
		System.out.println(time);
	}


	// BFS 탐색은 거리별로 순차적으로 탐색이 진행되므로 현재 거리에서 몬스터를 찾기 못했다면 다음 거리에서 찾고
	// 찾았을 경우 탐색을 종료하고 찾은 몬스터를 리턴하게끔 설계
	static Monster findNearMonster () {
		Monster dMonster = new Monster(n + 1, n + 1);
		boolean[][] visited = new boolean[n][n];
		Queue<Robot> q = new LinkedList<>();
		q.offer(new Robot(robot.r, robot.c, robot.level, 0));
		visited[robot.r][robot.c] = true;

		boolean isCan = false; // 몬스터를 찾았는지 확인 여부(못찾았다면 거리를 더 늘려야하므로)

		// 여기서 kill을 거리계산용으로 사용
		while (!q.isEmpty()) {
			Robot cur = q.poll();
			
			// 최소거리에 몬스터가 없었다면 최소거리를 늘려준다.
			if (!isCan) minDist = cur.kill;

			// 현재 거리에서 처치가능한 몬스터를 발견했다면
			if (cur.kill == minDist && map[cur.r][cur.c] < cur.level && map[cur.r][cur.c] != 0) {
				isCan = true;

				// 거리가 같다면 왼쪽 상단이 먼저
				if (dMonster.r > cur.r) {
					dMonster = new Monster(cur.r, cur.c);
				}

				if (dMonster.r == cur.r && dMonster.c > cur.c) {
					dMonster = new Monster(cur.r, cur.c);
				}
				continue;
			}

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (nr < 0 || nc < 0 || nr >= n || nc >= n || visited[nr][nc] || map[nr][nc] > cur.level) continue;
				visited[nr][nc] = true;
				q.offer(new Robot(nr, nc, cur.level, cur.kill + 1));
			}
		}

		return dMonster;
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