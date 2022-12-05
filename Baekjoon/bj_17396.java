import java.io.*;
import java.util.*;

public class bj_17396 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		// 시야에 보이는지 확인
		boolean[] isSaw = new boolean[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			if (Integer.parseInt(st.nextToken()) == 1) isSaw[i] = true; 
		}

		// 인접리스트 작성
		ArrayList<Champ> bifuList[] = new ArrayList[n];
		for (int i = 0; i < n; i++) bifuList[i] = new ArrayList<>();

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());

			bifuList[a].add(new Champ(b, t));
			bifuList[b].add(new Champ(a, t));
		}

		// 탐색 시작
		long[] dijk = new long[n];
		Arrays.fill(dijk, Long.MAX_VALUE);

		PriorityQueue<Champ> q = new PriorityQueue<>();
		boolean[] visited = new boolean[n];
		dijk[0] = 0;
		q.offer(new Champ(0, 0));

		while (!q.isEmpty()) {
			Champ champ = q.poll();
			if (visited[champ.position]) continue;
			visited[champ.position] = true;

			for (int i = 0; i < bifuList[champ.position].size(); i++) {
				Champ next = bifuList[champ.position].get(i);

				// 더 빠르게 접근이 가능하다면
				if (dijk[next.position] > champ.time + next.time) {
					// 넥서스 이거나 시야에 보이지 않을 때
					if (next.position == n - 1 || !isSaw[next.position]) {
						dijk[next.position] = champ.time + next.time;
						q.offer(new Champ(next.position, dijk[next.position]));
					}
				}
			}
		}

		if (dijk[n - 1] == Long.MAX_VALUE) System.out.println("-1");
		else System.out.println(dijk[n - 1]);
	}

	static class Champ implements Comparable<Champ> {
		int position;
		long time;

		Champ (int position, long time) {
			this.position = position;
			this.time = time;
		}

		@Override
		public int compareTo(Champ o) {
			return (int) (time - o.time);
		}
	}
}