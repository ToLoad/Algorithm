import java.util.*;

class Solution {
    public int solution(int n) {
        int answer = 0;
        
        String num = "";
        while (n != 0) {
            num += n % 3;
            n = n / 3;
        }
        
        for (int i = 0; i < num.length(); i++) {
            int cur = num.charAt(i) - '0';
            int cnt = num.length() - i - 1;
            
            answer += cur * Math.pow(3, cnt);
        }
        
        return answer;
    }
}