import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj_4485 {
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		
		while (true) {
			int N = Integer.parseInt(br.readLine());
			if (N == 0) break;
			
			int[][] map = new int[N][N];
			int[][] dist = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					dist[i][j] = Integer.MAX_VALUE;
				}
			}
			
			PriorityQueue<Link> q = new PriorityQueue<>();
			q.offer(new Link(0, 0, map[0][0]));
			dist[0][0] = map[0][0];
			
			while (!q.isEmpty()) {
				Link link = q.poll();
				if (link.r == N - 1 && link.c == N - 1) break;
				
				for (int i = 0; i < 4; i++) {
					int nr = link.r + dr[i];
					int nc = link.c + dc[i];
					
					if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
					if (dist[nr][nc] > dist[link.r][link.c] + map[nr][nc]) {
						dist[nr][nc] = dist[link.r][link.c] + map[nr][nc];
						q.offer(new Link(nr, nc, dist[nr][nc]));
					}
				}
			}
			
			cnt++;
			sb.append("Problem " + cnt + ": " + dist[N - 1][N - 1] + "\n");
		}
		System.out.println(sb);
	}
	
	static class Link implements Comparable<Link> {
		int r;
		int c;
		int lost;
		
		Link (int r, int c, int lost) {
			this.r = r;
			this.c = c;
			this.lost = lost;
		}

		@Override
		public int compareTo(Link o) {
			return lost - o.lost;
		}
	}
}