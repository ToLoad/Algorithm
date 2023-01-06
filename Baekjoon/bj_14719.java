import java.io.*;
import java.util.*;

public class bj_14719 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());

		int[] height = new int[w];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < w; i++) height[i] = Integer.parseInt(st.nextToken());

		int result = 0;
		// 탐색하는 지점의 좌우에 벽이 있어서 물이 고일 수 있는지 없는지를 계산
		for (int i = 1; i < w - 1; i++) {
			int curHeight = height[i];
			int leftMax = curHeight;
			int rightMax = curHeight;

			for (int j = 0; j < i; j++) {
				if (height[j] > curHeight) leftMax = Math.max(leftMax, height[j]);
			}

			for (int j = i + 1; j < w; j++) {
				if (height[j] > curHeight) rightMax = Math.max(rightMax, height[j]);
			}

			if (Math.min(leftMax, rightMax) > curHeight) {
				result += Math.min(leftMax, rightMax) - curHeight;
			}
		}
		System.out.println(result);
	}
}