import java.io.*;
import java.util.*;

public class Main {
	static int n, L;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = 0;
		for (int i = 0; i < n; i++) {
			if (checkHorizon(map[i], i)) {
				// System.out.println("가로 " + i);
				answer++;
			}
			if (checkVertical(i)) {
				// System.out.println("세로 " + i);
				answer++;
			}
		}

		System.out.println(answer);
	}

	static boolean checkHorizon(int[] road, int a) {
		boolean[] isBuild = new boolean[n];
		int preHeight = road[0];

		for (int i = 1; i < n; i++) {
			if (Math.abs(road[i] - preHeight) >= 2) return false; // 높이차이가 2보다 크면 경사로를 만들 수 없어 지나가기 불가능
			if (road[i] == preHeight) continue; // 이전 보도블럭과 높이가 같다면 지나갈 수 있음.
			
			if (road[i] == preHeight + 1) { // 올라가는 경사로
				// 한 칸 이전부터 길이만큼
				for (int j = 1; j <= L; j++) {
					if (i - j < 0) return false; // 원하는 만큼 경사로가 확보안되서 건설 실패
					if (isBuild[i - j]) return false; // 이미 해당 지역에 경사로가 건설되어있음.
					if (road[i - j] != preHeight) return false; // 바닥면이 확보 안됨.
				}

				// 경사로 건설 여부 체크
				for (int j = 1; j <= L; j++) isBuild[i - j] = true;
			} else if (road[i] == preHeight - 1) { // 내려가는 경사로
				if (isBuild[i]) return false;
				// 길이 확인
				for (int j = 1; j < L; j++) {
					if (i + j >= n) return false; // 원하는 만큼 경사로가 확보안되서 건설 실패
					if (isBuild[i + j]) return false; // 이미 해당 지역에 경사로가 건설되어있음.
					if (road[i + j] != road[i]) return false; // 바닥면이 확보 안됨.
				}

				// 경사로 건설 여부 체크
				for (int j = 0; j < L; j++) isBuild[i + j] = true;
				i += L - 1;
			}
			preHeight = road[i];
		}

		return true;
	}

	static boolean checkVertical(int start) {
		boolean[] isBuild = new boolean[n];
		int preHeight = map[0][start];

		for (int i = 1; i < n; i++) {
			if (Math.abs(map[i][start] - preHeight) >= 2) return false; // 높이차이가 2보다 크면 경사로를 만들 수 없어 지나가기 불가능
			if (map[i][start] == preHeight) continue; // 이전 보도블럭과 높이가 같다면 지나갈 수 있음.

			if (map[i][start] == preHeight + 1) { // 올라가는 경사로
				// 한 칸 이전부터 길이만큼
				for (int j = 1; j <= L; j++) {
					if (i - j < 0) return false; // 원하는 만큼 경사로가 확보안되서 건설 실패
					if (isBuild[i - j]) return false; // 이미 해당 지역에 경사로가 건설되어있음.
					if (map[i - j][start] != preHeight) return false; // 바닥면이 확보 안됨.
				}

				// 경사로 건설 여부 체크
				for (int j = 1; j <= L; j++) isBuild[i - j] = true;
			} else if (map[i][start] == preHeight - 1) { // 내려가는 경사로
				if (isBuild[i]) return false;
				// 길이 확인
				for (int j = 1; j < L; j++) {
					if (i + j >= n) return false; // 원하는 만큼 경사로가 확보안되서 건설 실패
					if (isBuild[i + j]) return false; // 이미 해당 지역에 경사로가 건설되어있음.
					if (map[i + j][start] != map[i][start]) return false; // 바닥면이 확보 안됨.
				}

				// 경사로 건설 여부 체크
				for (int j = 0; j < L; j++) isBuild[i + j] = true;
				i += L - 1;
			}
			preHeight = map[i][start];
		}

		return true;
	}
}
