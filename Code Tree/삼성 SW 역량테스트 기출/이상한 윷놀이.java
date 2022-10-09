import java.io.*;
import java.util.*;

public class Main {
	// 오른쪽 왼쪽 윗쪽 아랫쪽
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {1, -1, 0, 0};
	static int n, k;
	static int[][] map;
	static ArrayList<Node>[][] statusList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		map = new int[n][n];
		statusList = new ArrayList[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				statusList[i][j] = new ArrayList<>();
			}
		}

		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;

			statusList[r][c].add(new Node(i, d));
		}

        // 현재 말이 겹쳐 있을 때 해당 말 위에 엎여있는 말만 가져간다.
        // 아래 있는 말은 놔두고 감
        
		int ans = -1;
		for (int t = 1; t <= 1000; t++) {
			boolean isDone = simulate();
			if (isDone) {
				ans = t;
				break;
			}
		}
		System.out.println(ans);
	}

	static boolean simulate() {
		for (int i = 0; i < k; i++) {
			int[] curPoint = findNumDir(i);

			int dir = curPoint[2];
			int nr = curPoint[0] + dr[dir];
			int nc = curPoint[1] + dc[dir];

			boolean isReverse = false;

			// 다음 위치가 범위 밖이거나 파란색일 경우
			if (!isRange(nr, nc) || map[nr][nc] == 2) {
				dir = (dir % 2 == 0) ? dir + 1 : dir - 1;
				nr = curPoint[0] + dr[dir];
				nc = curPoint[1] + dc[dir];

				// 방향 전환 후 위치가 또 파란색이거나 빨간색 일 때
				if (!isRange(nr, nc) || map[nr][nc] == 2) {
					nr = curPoint[0];
					nc = curPoint[1];
				} else if (map[nr][nc] == 1) isReverse = true;
			} else if (map[nr][nc] == 1) isReverse = true;

			// 현재 말 위치에 있는 모든 말들
			ArrayList<Node> popList = popPieces(curPoint[0], curPoint[1], i);
			Node bottom = popList.get(0);
			bottom.dir = dir;
			popList.set(0, bottom);

			if (isReverse) {
				Collections.reverse(popList);
			}

			statusList[nr][nc].addAll(popList);
			if (statusList[nr][nc].size() >= 4) return true;
		}
		return false;
	}

	static int[] findNumDir(int num) {
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				for (Node node : statusList[r][c]) {
					if (node.order == num) {
						int[] temp = {r, c, node.dir};
						return temp;
					}
				}
			}
		}

		return new int[3];
	}

	static boolean isRange(int r, int c) {
		return 0 <= r && 0 <= c && r < n && c < n;
	}

	static ArrayList<Node> popPieces(int r, int c, int order) {
		ArrayList<Node> list = new ArrayList<>();
		for (int i = 0; i < statusList[r][c].size(); i++) {
			if (order == statusList[r][c].get(i).order) {
				// 현재 말을 포함한 그 위에 있는 말까지 가져가기
				for (int j = i; j < statusList[r][c].size(); j++) {
					list.add(statusList[r][c].get(j));
				}
				
				// 다 가져갔으면 지우기
				statusList[r][c].subList(i, statusList[r][c].size()).clear();
				break;
			}
		}

		return list;
	}

	static class Node {
		int order; 	// 순서
		int dir;	// 방향

		Node (int order, int dir) {
			this.order = order;
			this.dir = dir;
		}
	}
}