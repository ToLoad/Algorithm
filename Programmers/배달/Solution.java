import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        // 인접 리스트
        ArrayList<Node> list[] = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) list[i] = new ArrayList<>();
        
        for (int i = 0; i < road.length; i++) {
            int cur = road[i][0];
            int next = road[i][1];
            int time = road[i][2];
            
            list[cur].add(new Node(next, time));
            list[next].add(new Node(cur, time));
        }
        
        // 다익스트라
        int[] town = new int[N + 1];
        Arrays.fill(town, Integer.MAX_VALUE);
        town[1] = 0;
        
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(1, 0));
        
        while (!q.isEmpty()) {
            Node node = q.poll();
            
            for (int i = 0; i < list[node.next].size(); i++) {
                int next = list[node.next].get(i).next;
                int time = list[node.next].get(i).time;
                
                if (town[next] > node.time + time) {
                    town[next] = node.time + time;
                    q.add(new Node(next, town[next]));
                }
            }
        }
        
        // 개수 세기
        for (int i : town) {
            if (i <= K) answer++;
        }

        return answer;
    }
}

class Node implements Comparable<Node> {
    int next;
    int time;
    
    Node (int next, int time) {
        this.next = next;
        this.time = time;
    }
    
    @Override
    public int compareTo(Node o) {
        return time - o.time;
    }
}