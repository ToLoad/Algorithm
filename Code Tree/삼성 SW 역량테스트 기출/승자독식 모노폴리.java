import java.io.*;
import java.util.*;

public class Main {
	static int dr[] = {0, -1, 1, 0, 0};
	static int dc[] = {0, 0, 0, -1, 1};
	static int n, m, k;
	static Land land[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 격자의 크기
		m = Integer.parseInt(st.nextToken()); // 플레이어의 수
		k = Integer.parseInt(st.nextToken()); // 독점 계약 턴 수

		// 격자 정보
		land = new Land[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp != 0) {
					land[i][j] = new Land(temp, k, new Player(temp, 0, new String[5]));
				}
			}
		}

		// 초기 방향
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= m; i++) {
			int dIdx = Integer.parseInt(st.nextToken());
			for (int r = 0; r < n; r++) {
				for (int c = 0; c < n; c++) {
					if (land[r][c] != null && land[r][c].player.num == i) land[r][c].player.dIdx = dIdx;
				}
			}
		}

		// 각 플레이어의 방향별 우선순위
		for (int i = 1; i <= m; i++) {
			String dir[] = new String[5];
			for (int j = 1; j <= 4; j++) {
				dir[j] = br.readLine();
			}

			for (int r = 0; r < n; r++) {
				for (int c = 0; c < n; c++) {
					if (land[r][c] != null && land[r][c].player.num == i) land[r][c].player.dir = dir;
				}
			}
		}

		// 시뮬레이션 시작
		// 플레이어는 한 칸씩 이동
		// 본인이 인접한 4방향 중 아무도 독점계약 하지 않은 칸
		// 없다면 본인이 독점게약한 땅, 여러개라면 우선순위에 따라
		// 이동한 방향이 플레이어의 새로운 방향이됨
		// 모두 이동후 겹침 확인 -> 겹치면 번호가 작은 플레이어가 살아남음
		// 아무도 계약하지 않은 땅에서는 계약 진행
		// 모두 이동 후 전체땅의 남은 계약 수 줄이기

		int cnt = 0;
		while (m > 1) {
			Land nLand[][] = new Land[n][n];
			// 플레이어를 찾기, 찾으면 이동 시키기
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					// 플레이어는 없고 땅이 계약된 경우
					if (land[i][j] != null && land[i][j].player == null) {
						// 계약횟수를 줄이고 계약횟수가 만료되었을 경우에는 옮기지 않음
						land[i][j].remain -= 1;
						if (land[i][j].remain > 0) {
							if (nLand[i][j] != null) { // 옮기려는 땅에 이미 플레이어가 있는 경우
								nLand[i][j].host = land[i][j].host;
								nLand[i][j].remain = land[i][j].remain;
							} else nLand[i][j] = new Land(land[i][j].host, land[i][j].remain, null);
						}
					}

					// 플레이어가 있는 경우 (새맵으로 현재 위치의 땅도 옮기고 플레이어도 옮겨줘야함)
					if (land[i][j] != null && land[i][j].player != null) {
						// 계약횟수를 줄이고 계약횟수가 만료되었을 경우에는 옮기지 않음
						// 플레이어를 제외하고 땅만 옮김
						land[i][j].remain -= 1;
						if (land[i][j].remain > 0) {
							nLand[i][j] = new Land(land[i][j].host, land[i][j].remain, null);
						}

						// 플레이어 옮기기 (방향은 dir[1] ~ dir[4]에 담겨있다.)
						int curDir = land[i][j].player.dIdx;
						String dir[] = land[i][j].player.dir[curDir].split(" ");

						// 아무도 독점계약하지 않은 칸 찾기
						boolean isFind = false;
						for (int d = 1; d <= 4; d++) {
							if (isFind) continue;
							int nr = i + dr[Integer.parseInt(dir[d - 1])];
							int nc = j + dc[Integer.parseInt(dir[d - 1])];

							if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
							if (land[nr][nc] == null) {
								// 해당 칸에 누가 이미 도착해있다면
								if (nLand[nr][nc] != null) {
									// 현재 플레이어가 번호가 더 작으면 덮어씌운다
									if (nLand[nr][nc].player.num > land[i][j].player.num) {
										nLand[nr][nc].player = new Player(land[i][j].player.num, Integer.parseInt(dir[d - 1]), land[i][j].player.dir.clone());
										nLand[nr][nc].host = land[i][j].host;
										nLand[nr][nc].remain = k;
										m--;
									} else if (nLand[nr][nc].player.num < land[i][j].player.num) { // 이미 해당 위치에 번호가 더 작은 플레이어가 있다면
										m--;
									}
								} else { // 아무도 없다면 플레이어를 옮기고 해당 플레이어 땅으로 변경
									nLand[nr][nc] = new Land(land[i][j].host, k, new Player(land[i][j].player.num, Integer.parseInt(dir[d - 1]), land[i][j].player.dir.clone()));
								}
								isFind = true;
							}
						}


						// 빈 땅이 없으면 본인 땅으로 이동
						if (!isFind) {
							for (int d = 1; d <= 4; d++) {
								if (isFind) continue;
								int nr = i + dr[Integer.parseInt(dir[d - 1])];
								int nc = j + dc[Integer.parseInt(dir[d - 1])];

								if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
								if (land[nr][nc] != null && land[nr][nc].host == land[i][j].player.num) {
									if (nLand[nr][nc] == null)  {
										nLand[nr][nc] = new Land(land[nr][nc].host, land[nr][nc].remain, new Player(land[i][j].player.num, Integer.parseInt(dir[d - 1]), land[i][j].player.dir.clone()));
									} else	nLand[nr][nc].player = new Player(land[i][j].player.num, Integer.parseInt(dir[d - 1]), land[i][j].player.dir.clone());
									isFind = true;
								}
							}
						}
					}
				}
			}

			// System.out.println(cnt + "==============");
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (nLand[i][j] != null) {
						land[i][j] = new Land(nLand[i][j].host, nLand[i][j].remain, nLand[i][j].player == null ? null : new Player(nLand[i][j].player.num, nLand[i][j].player.dIdx, nLand[i][j].player.dir.clone()));
						// System.out.println(i + " " + j + " || " +land[i][j].host + " " + land[i][j].remain);
						// if (nLand[i][j].player != null)
						// 	System.out.println("플레이어 : " + i + " " + j + " || " +land[i][j].player.num + " " + land[i][j].player.dIdx);
					}
				}
			}
			cnt++;
			if (cnt > 1000) {
				System.out.println(-1);
				return;
			}
		}
		System.out.println(m);
		System.out.println(cnt);
	}

	static int getPlayerCnt() {
		int cnt = 0;
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				if (land[r][c] != null) cnt++;
			}
		}
		return cnt;
	}

	static class Land {
		int host; // 주인
		int remain; // 남은 게약 수
		Player player; // 현재 이 땅에 있는 사람

		Land (int host, int remain, Player player) {
			this.host = host;
			this.remain = remain;
			this.player = player;
		}
	}

	static class Player {
		int num;
		int dIdx;
		String dir[];

		Player (int num, int dIdx, String dir[]) {
			this.num = num;
			this.dIdx = dIdx;
			this.dir = dir;
		}
	}
}