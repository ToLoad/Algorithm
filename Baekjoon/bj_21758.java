import java.io.*;
import java.util.*;

public class bj_21758 {
	static int n;
	static int[] place;
	static long[] rightSum, leftSum;

	static long max;
	static long total;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		place = new int[n];
		rightSum = new long[n];
		leftSum = new long[n];


		// 오른쪽으로의 누적합과 왼쪽으로의 누적합을 구해야함
		long sum = 0;
		for (int i = 0; i < n; i++) {
			place[i] = Integer.parseInt(st.nextToken());

			sum += place[i];
			rightSum[i] = sum;
		}

		sum = 0;
		for (int i = n - 1; i >= 0; i--) {
			sum += place[i];
			leftSum[i] = sum;
		}

		total = rightSum[n - 1];
		case1();
		case2();
		case3();

		System.out.println(max);
	}

	static void case1() {
		long bee1, bee2;

		for (int i = 1; i <= n - 2; i++) {
			bee1 = total - place[0] - place[i];
			bee2 = total - rightSum[i];
			max = Math.max(max, bee1 + bee2);
		}
	}

	static void case2() {
		long bee1, bee2;

		for (int i = n - 2; i >= 1; i--) {
			bee1 = total - place[n - 1] - place[i];
			bee2 = total - leftSum[i];
			max = Math.max(max, bee1 + bee2);
		}
	}

	static void case3() {
		long bee1, bee2;

		for (int i = 1; i <= n - 2; i++) {
			bee1 = rightSum[i] - place[0];
			bee2 = leftSum[i] - place[n - 1];
			max = Math.max(max, bee1 + bee2);
		}
	}
}