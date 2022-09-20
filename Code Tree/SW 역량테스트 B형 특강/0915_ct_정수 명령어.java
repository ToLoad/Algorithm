import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			int k = Integer.parseInt(br.readLine());
			TreeSet<Integer> treeSet = new TreeSet<>();
			for (int i = 0; i < k; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				char cmd = st.nextToken().charAt(0);
				int n = Integer.parseInt(st.nextToken());

				if (cmd == 'I') treeSet.add(n);
				else if (cmd == 'D') {
					if (n == 1) treeSet.pollLast();
					else treeSet.pollFirst();
				}
			}
			if (treeSet.size() == 0) sb.append("EMPTY\n");
			else sb.append(treeSet.last() + " " + treeSet.first() + "\n");
		}
		System.out.println(sb);
	}
}