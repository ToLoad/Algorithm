import java.util.*;

class Solution {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    public int solution(String[] maps) {
        int answer = 0;
        
        int row = maps.length;
        int col = maps[0].length();
        
        boolean[][][] visited = new boolean[row][col][2];
        
        Node start = new Node(0, 0, 0);
        Node lever = new Node(0, 0, 0);
        
        char[][] map = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                map[i][j] = maps[i].charAt(j);
                if (map[i][j] == 'S') {
                    start = new Node(i, j, 0);
                } else if (map[i][j] == 'L') {
                    lever = new Node(i, j, 0);
                }
            }
        }
        
        // BFS
        // 레버까지 거리
        Queue<Node> q = new LinkedList<>();
        q.offer(start);
        visited[start.r][start.c][0] = true;
        
        while (!q.isEmpty()) {
            Node cur = q.poll();
            
            if (map[cur.r][cur.c] == 'L') {
                answer += cur.cnt;
                break;
            }
            
            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                
                if (nr >= row || nc >= col || nr < 0 || nc < 0 || map[nr][nc] == 'X') continue;
                if (!visited[nr][nc][0]) {
                    visited[nr][nc][0] = true;
                    q.offer(new Node(nr, nc, cur.cnt + 1));
                }
            }
        }
        
        // 레버에 도달하지 못하면 종료
        if (answer == 0) return -1;
        
        // BFS 레버부터 출구까지
        q = new LinkedList<>();
        q.offer(lever);
        visited[lever.r][lever.c][1] = true;
        boolean isCan = false;
        
        while (!q.isEmpty()) {
            Node cur = q.poll();
            
            if (map[cur.r][cur.c] == 'E') {
                answer += cur.cnt;
                isCan = true;
                break;
            }
            
            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                
                if (nr >= row || nc >= col || nr < 0 || nc < 0 || map[nr][nc] == 'X') continue;
                if (!visited[nr][nc][1]) {
                    visited[nr][nc][1] = true;
                    q.offer(new Node(nr, nc, cur.cnt + 1));
                }
            }
        }
        
        if (!isCan) return -1;
        return answer;
    }
    
    static class Node {
        int r;
        int c;
        int cnt;
        
        Node (int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
}
