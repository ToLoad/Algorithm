import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        // 정렬을 통해 현재 구간과 다음 구간이 같은 미사일로 요격되는지 확인가능하게 만들기.
        int answer = 0;
        Arrays.sort(targets, (o1, o2) -> {
            return o1[1] - o2[1];
        });
        
        int interceptPoint = -1; // 요격 포인트
        for (int i = 0; i < targets.length; i++) {
            // 요격 포인트가 없을 때
            if (interceptPoint == -1) {
                answer += 1;
                interceptPoint = targets[i][1] - 1;
                continue;
            }
            
            // 현재 구간에서 요격 가능한 포인트라면
            if (interceptPoint >= targets[i][0] && interceptPoint <= targets[i][1]) continue;
            
            // 새로운 요격 포인트 지정
            answer += 1;
            interceptPoint = targets[i][1] - 1;
        }
            
        
        return answer;
    }
}