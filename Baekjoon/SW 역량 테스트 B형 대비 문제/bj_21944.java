import java.io.*;
import java.util.*;
public class bj_21944 {
	/* 
	 * TreeSet
	 * 중복 데이터를 저장하지 않고 저장 순서를 유지하지 않음
	 * 자동으로 지정한 CompareTo에 따라 정렬된다.
	*/
	static TreeSet<Problem> treeSet = new TreeSet<>();
	static List<TreeSet<Problem>> subTrees = new ArrayList<>();
	static Map<Integer, Problem> hMap = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int i = 0; i <= 100; i++) {
			subTrees.add(new TreeSet<>());
		}

		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int P = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			int G = Integer.parseInt(st.nextToken());

			subTrees.get(G).add(new Problem(P, L, G));
			treeSet.add(new Problem(P, L, G));
			hMap.put(P, new Problem(L, G));
		}

		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			
			switch (command) {
				case "recommend": sb.append(recommend(st) + "\n");
				break;

				case "recommend2": sb.append(recommend2(st) + "\n");
				break;

				case "recommend3": sb.append(recommend3(st) + "\n");
				break;

				case "add": add(st);
				break;

				case "solved": solved(st);
				break;
			}
		}
		System.out.println(sb);
	}

	static int recommend(StringTokenizer st) {
		int type = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());

		if (x == 1) {
			return subTrees.get(type).last().num;
		} else {
			return subTrees.get(type).first().num;
		}
	}

	static int recommend2(StringTokenizer st) {
		int x = Integer.parseInt(st.nextToken());

		if (x == 1) {
			return treeSet.last().num;
		} else {
			return treeSet.first().num;
		}
	}

	static int recommend3(StringTokenizer st) {
		int x = Integer.parseInt(st.nextToken());
		int diff = Integer.parseInt(st.nextToken());

		// lower : 제공된 값보다 작은 값 중 가장 큰 값 (인자값 미포함) 있다면 해당 객체 리턴
		// higher : 제공된 값보다 큰 값 중 가장 작은 값 (인자값 미포함) 있다면 해당 객체 리턴
		// floor : 제공된 값과 같거나 작은 값 중 가장 큰 값 (인자값 포함) 있다면 해당 객체 리턴
		// ceiling : 제공된 값보다 크거나 같은 값 중 가장 작은 값 (인자값 포함) 있다면 해당 객체 리턴

		if (x == 1) {
			if (treeSet.ceiling(new Problem(0, diff, 0)) == null) return -1;
			else return treeSet.ceiling(new Problem(0, diff, 0)).num;
		} else {
			if (treeSet.lower(new Problem(0, diff, 0)) == null) return -1;
			else return treeSet.lower(new Problem(0, diff, 0)).num;
		}
	}

	static void add(StringTokenizer st) {
		int P = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());

		subTrees.get(G).add(new Problem(P, L, G));
		treeSet.add(new Problem(P, L, G));
		hMap.put(P, new Problem(L, G));
	}

	static void solved(StringTokenizer st) {
		int num = Integer.parseInt(st.nextToken());

		if (!hMap.containsKey(num)) return;

		int diff = hMap.get(num).diff;
		int type = hMap.get(num).type;
		hMap.remove(num);

		treeSet.remove(new Problem(num, diff, type));
		subTrees.get(type).remove(new Problem(num, diff, type));
	}
	static class Problem implements Comparable<Problem> {
		int num;
		int diff;
		int type;

		Problem (int diff, int type) {
			this.diff = diff;
			this.type = type;
		}

		Problem (int num, int diff, int type) {
			this.num = num;
			this.diff = diff;
			this.type = type;
		}

		@Override
		public int compareTo(Problem o) {
			if (o.diff == diff) {
				return num - o.num;
			}
			return diff - o.diff;
		}
	}
}