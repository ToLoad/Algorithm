import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		TreeSet<Node> treeSet = new TreeSet<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			treeSet.add(new Node(r, c));
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			Node node = treeSet.ceiling(new Node(r, c));
			if (node == null) {
				sb.append("-1 -1\n");
			} else {
				sb.append(node.r + " " + node.c + "\n");
			}
		}
		System.out.println(sb);
	}
	static class Node implements Comparable<Node> {
		int r;
		int c;

		Node (int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Main.Node o) {
			if (r == o.r) {
				return c - o.c;
			} else {
				return r - o.r;
			}
		}
	}
}