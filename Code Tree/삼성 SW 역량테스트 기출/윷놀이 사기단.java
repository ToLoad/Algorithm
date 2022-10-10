import java.io.*;
import java.util.*;

public class Main {
	static int[] point = {
		0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 0,
		13, 16, 19, 0, 0,
		22, 24, 0, 0, 0,
		28, 27, 26, 0, 0,
		0, 0, 0, 0, 0,
		25, 30, 35, 40
	};
	static ArrayList<Integer> moveCnt;
	static int[] curPos;
	static int maxScore;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		moveCnt = new ArrayList<>();
		while (st.hasMoreTokens()) {
			moveCnt.add(Integer.parseInt(st.nextToken()));
		}

		curPos = new int[4];
		maxScore = Integer.MIN_VALUE;
		searchMaxScore(0, 0);
		System.out.println(maxScore);
	}

	static void searchMaxScore(int cnt, int score) {
		if (cnt >= 10) {
			maxScore = Math.max(maxScore, score);
			return;
		}

		// 현재 턴에 움직일 말 선택
		for (int i = 0; i < 4; i++) {
			// 이미 도착한 말
			if (curPos[i] == 20) continue;

			int temp = curPos[i];

			if (isBlue(curPos[i])) {
				curPos[i] = getNextPos(curPos[i] + 16, moveCnt.get(cnt) - 1); // 16을 더하면 갈림길로 이동하게끔 배열이 구성되어있음.
			} else {
				curPos[i] = getNextPos(curPos[i], moveCnt.get(cnt));
			}

			if (!isOverlapped())
				searchMaxScore(cnt + 1, score + point[curPos[i]]);

			// 턴을 진행한 후 이전 위치의 값을 넣어줌
			curPos[i] = temp;
		}
	}

	static boolean isBlue(int curPos) {
		return curPos != 0 && curPos % 5 == 0;
	}

	static int getNextPos(int curPos, int move) {
		if (curPos == 20) return 20;

		if (move == 0) return curPos;

		int nextPos = curPos + 1;

		// 생성된 맵에서 가야할 길로 이동시켜준다.
		if (curPos == 23 || curPos == 27 || curPos == 33)
			nextPos = 41;
		else if (curPos == 19) nextPos = 44;
		else if (curPos == 44) nextPos = 20;

		return getNextPos(nextPos, move - 1);
	}

	static boolean isOverlapped() {
		for (int i = 0; i < 4; i++) {
			for (int j = i + 1; j < 4; j++) {
				if (curPos[i] == curPos[j] &&
					curPos[i] != 0 &&
					curPos[i] != 20)
					return true;
			}
		}
		return false;
	}
}