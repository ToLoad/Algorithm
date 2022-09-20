import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		TreeSet<Integer> treeSet = new TreeSet<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			treeSet.add(Integer.parseInt(st.nextToken()));
		}

		for (int i = 0; i < m; i++) {
			int cur = Integer.parseInt(br.readLine());
			if (treeSet.ceiling(cur) == null) sb.append("-1\n");
			else sb.append(treeSet.ceiling(cur) + "\n");
		}
		System.out.println(sb);
	}
}