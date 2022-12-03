class Solution {
    public int solution(String s) {
        int answer = 0;
        
        // 기준 문자
        char stWord = s.charAt(0);
        int stCnt = 1; 
        
        // 다른 문자
        int otherCnt = 0;
        
        for (int i = 1; i < s.length(); i++) {
            char word = s.charAt(i);
            
            if (stWord == word) stCnt++;
            else otherCnt++;            

            // 일치하면 분리
            if (stCnt == otherCnt) {
                answer++;
                
                // 추가로 탐색이 가능하다면 값 초기화
                if (s.length() > i + 1) {
                    stCnt = 1;
                    otherCnt = 0;
                    stWord = s.charAt(++i);
                }
            }
        }
        
        // 횟수가 다른 상태에서 종료됬을 때도 분리
        if (stCnt != otherCnt) answer++;
        
        return answer;
    }
}