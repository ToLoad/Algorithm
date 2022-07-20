import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class bj_10026 {
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[][] array = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			String temp = br.readLine();
			for (int j = 0; j < N; j++) {
				array[i][j] = temp.charAt(j);
			}
		}
		
		boolean[][] visit = new boolean[N][N];
		int no = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visit[i][j]) {
					Queue<Node> q = new LinkedList<>();
					q.offer(new Node(i, j));
					visit[i][j] = true;
					no++;
					
					char curColor = array[i][j];
					while (!q.isEmpty()) {
						Node node = q.poll();
						
						for (int d = 0; d < 4; d++) {
							int nr = node.r + dr[d];
							int nc = node.c + dc[d];
							
							if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
							if (!visit[nr][nc] && array[nr][nc] == curColor) {
								q.offer(new Node(nr, nc));
								visit[nr][nc] = true;
							}
						}
					}
				}
			}
		}
		
		int yes = 0;
		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visit[i][j]) {
					Queue<Node> q = new LinkedList<>();
					q.offer(new Node(i, j));
					visit[i][j] = true;
					yes++;
					
					char curColor = array[i][j];
					while (!q.isEmpty()) {
						Node node = q.poll();
						
						for (int d = 0; d < 4; d++) {
							int nr = node.r + dr[d];
							int nc = node.c + dc[d];
							
							if (nr < 0 || nc < 0 || nr >= N || nc >= N || visit[nr][nc]) continue;
							if (curColor == 'R' || curColor == 'G') {
								if (array[nr][nc] == 'R' || array[nr][nc] == 'G') {
									q.offer(new Node(nr, nc));
									visit[nr][nc] = true;
								}
							} else {
								if (array[nr][nc] == curColor) {
									q.offer(new Node(nr, nc));
									visit[nr][nc] = true;
								}
							}
						}
					}
				}
			}
		}
		
		System.out.println(no + " " + yes);
	
	}
	
	static class Node {
		int r;
		int c;
		
		Node (int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}