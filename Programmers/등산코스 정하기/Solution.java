import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        // 각 지점에 대한 인접리스트 생성
        ArrayList<Node> list[] = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) list[i] = new ArrayList<>();
        
        for (int i = 0; i < paths.length; i++) {
            int start = paths[i][0];
            int end = paths[i][1];
            int w = paths[i][2];
            
            list[start].add(new Node(end, w));
            list[end].add(new Node(start, w));
        }

        // 다익스트라 준비
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        // 산봉우리 확인용
        boolean[] isSummit = new boolean[n + 1];
        for (int i = 0; i < summits.length; i++) {
            isSummit[summits[i]] = true;
        }
        
        // 출발지만 큐에 넣어준다
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < gates.length; i++) {
            pq.offer(new Node(gates[i], 0));
            dist[gates[i]] = 0;
        }
        
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            
            // 산봉우리거나 현재 INTENSITY가 더 크다면 넘김
            if (isSummit[node.next] || node.time > dist[node.next]) continue;

            for (int i = 0; i < list[node.next].size(); i++) { // foreach로 바꾸면 단순화됨
                int next = list[node.next].get(i).next;
                int time = list[node.next].get(i).time;
                
                int intensity = Math.max(node.time, time); // INTENSITY 값을 선택
                if (intensity < dist[next]) {
                    dist[next] = intensity;
                    pq.offer(new Node(next, dist[next]));
                }
            }
        }
        
        // 답 찾기
        Arrays.sort(summits); // INTENSITY가 동일하다면 산봉우리 번호가 제일 낮은 등산코스를 선택해야하므로

        int[] answer = new int[]{0, Integer.MAX_VALUE};

        for (int summit : summits) {
            if (dist[summit] < answer[1]) {
                answer[1] = Math.min(answer[1], dist[summit]);
                answer[0] = summit;
            }
        }
        return answer;
    }
    
    static class Node implements Comparable<Node> {
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
}