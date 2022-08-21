import java.util.*;

class Solution {
    public int solution(String name) {
        // 글자를 입력하면 다시 A로 초기화가 됨 !!
        // 그렇기 떄문에 좌우이동은 다른 위치에 글자가 A일떄만 해야 이득이고 아니면 손해임
        // 상하는 A에서 Z로 뒤로 돌아가는 것과 정방향 중 가까운 것만 찾으면됨
        int answer = 0;
        int length = name.length();
        int leftRight = length - 1;
        
        for (int i = 0; i < length; i++) {
            // 아스키 코드 A = 65
            char word = name.charAt(i);
            
            // 상하
            answer += Math.min(word - 'A', 'Z' - word + 1);
            
            // 좌우
            int A = i + 1; // A위치 찾기
            while (A < length && name.charAt(A) == 'A') A++;
            
            // 우측과 좌측 중 더 빠른 것 찾기
            leftRight = Math.min(leftRight, i * 2 + length - A);
            leftRight = Math.min(leftRight, (length - A) * 2 + i);
        }        
        return answer + leftRight;
    }
}