import java.io.*;
import java.util.*;

public class Main {
	static int n, h;
	static boolean[][] isLine;
	static ArrayList<Line> candidateList = new ArrayList<>();

	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());		// 고객의 수
		int m = Integer.parseInt(st.nextToken());	// 이미 존재하는 유실선의 수
		h = Integer.parseInt(st.nextToken());		// 취약 지점의 수
		
		isLine = new boolean[h + 1][n + 1]; 		// 유실선 여부[취약점 위치][연결된 고객]
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			isLine[a][b] = true;
		}

		// 추가 가능한 유실선 정리
		for (int i = 1; i <= h; i++) {
			for (int j = 1; j < n; j++) {
				if (!isLine[i][j]) candidateList.add(new Line(i, j));
			}
		}

		// 최소로 필요한 유실선의 수 찾기
		dfs(0, 0);
		System.out.println(min);
	}

	static void dfs(int cur, int cnt) {
		if (cnt >= min) return; 		// 현재 탐색에서 필요한 유실선의 수가 이미 탐색된 유실선 보다 많다면 탐색하지 않기
		if (isPossible()) min = cnt;	// 바로 위 코드에서 cnt와 min을 비교했기 때문에 현재 유실선 배열이 가능하면 최소값임.

		// 추가할 수 있는 유실선이 3개이므로 그 이상은 탐색하지 않음, 더이상 추가가능한 유실선이 없을 때도 탐색하지 않음
		if (cnt == 3 || cur == candidateList.size()) return; 

		dfs(cur + 1, cnt); // 현재 추가가능한 유실선을 추가하지 않고 다음으로 넘어가는 경우

		// 현재 추가가능한 유실선을 추가하고 다음으로 가는 경우
		Line line = candidateList.get(cur);
		isLine[line.lossPoint][line.position] = true;
		dfs(cur + 1, cnt + 1);
		isLine[line.lossPoint][line.position] = false;
	}

	// 현재 추가된 유실선들로 버그를 해결할 수 있는지 확인
	static boolean isPossible() {
		// 선이 겹쳐지도록 유실선을 추가할 수 없음.
		for (int a = 1; a <= h; a++) {
			for (int b = 2; b < n; b++) {
				if (isLine[a][b] && isLine[a][b - 1]) return false;
			}
		}

		// 각 고객이 맞는 데이터를 받는지 확인하기
		int[] num = new int[n + 1];
		for (int i = 1; i <= n; i++) num[i] = i;

		// 탐색 중 이어진 유실선이 있으면 서로의 데이터를 바꿔주기
		for (int a = 1; a <= h; a++) {
			for (int b = 1; b < n; b++) {
				if (isLine[a][b]) {
					int temp = num[b];
					num[b] = num[b + 1];
					num[b + 1] = temp;
				}
			}
		}

		// 모든 고객이 자신의 데이터를 제대로 받는지 확인
		for (int i = 1; i <= n; i++) {
			if (num[i] != i) return false;
		}

		return true;
	}

	static class Line {
		int lossPoint;
		int position;

		Line (int lossPoint, int position) {
			this.lossPoint = lossPoint;
			this.position = position;
		}
	}
}