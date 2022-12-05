import java.io.*;
import java.util.*;

public class bj_5766 {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			if (n == 0 && m == 0) break;
	
			TreeSet<Integer> playerSet = new TreeSet<>();
			Map<Integer, Integer> scoreMap = new HashMap<>();
			TreeSet<Integer> scoreSet = new TreeSet<>();
			scoreSet.add(1);
	
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					int player = Integer.parseInt(st.nextToken());
					playerSet.add(player);
	
					if (!scoreMap.containsKey(player)) scoreMap.put(player, 1);
					else {
						scoreMap.put(player, scoreMap.get(player) + 1);
						scoreSet.add(scoreMap.get(player));
					}
				}
			}
	
			int secPoint = scoreSet.lower(scoreSet.last());
			for (int player : playerSet) {
				if (scoreMap.get(player) == secPoint) sb.append(player + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}