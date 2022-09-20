// https://www.codetree.ai/missions/8/problems/nearest-point/description

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		PriorityQueue<Node> q = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			q.offer(new Node(r, c));
		}

		while (m-- > 0) {
			Node node = q.poll();
			q.offer(new Node(node.r + 2, node.c + 2));
		}

		Node result = q.poll();
		System.out.println(result.r + " " + result.c);
	}
	static class Node implements Comparable<Node> {
		int r;
		int c;

		Node (int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Node o) {
			if ((r + c) == (o.r + o.c)) {
				if (r == o.r) return c - o.c;
				else return r - o.r;
			}
			
			return (r + c) - (o.r + o.c);
		}
	}
}