import java.util.*;

class Solution {
    static int[] number;
    static int[] result;
    static boolean[] visited;
    static int answer;
    
    public int solution(int[] number) {
        this.number = number;
        result = new int[3];
        visited = new boolean[number.length];
        
        dfs(0, 3, 0);
        return answer;
    }
    
    static void dfs(int start, int end, int cur) {
        if (cur == end) {
            int sum = Arrays.stream(result).sum();
            if (sum == 0) answer++;
            return;
        }
        
        for (int i = start; i < number.length; i++) {
            if (!visited[i]) {
                result[cur] = number[i];
                visited[i] = true;
                dfs(i + 1, end, cur + 1);
                visited[i] = false;
            }
        }
    }
}