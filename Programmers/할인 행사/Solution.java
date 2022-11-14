import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        Map<String, Integer> needMap = new HashMap<>(); // 필요한 것들
        Map<String, Integer> checkMap; // 현재 가능한 개수
        int sum = 0; // 필요햔 물품의 합
        
        // 필요한 것들 추가
        for (int i = 0; i < want.length; i++) {
            needMap.put(want[i], number[i]);
            sum += number[i];
        }
        
        int end = discount.length - sum;
        
        // i일부터 i + 10일까지 원하는 물건이 구입가능한지 확인
        for (int i = 0; i <= end; i++) {
            checkMap = new HashMap<>();
            for (int j = i; j < i + sum; j++) {
                if (checkMap.containsKey(discount[j])) {
                    checkMap.put(discount[j], checkMap.get(discount[j]) + 1);
                } else checkMap.put(discount[j], 1);
            }
            
            // 현재 구입한 물건이 필요한 물건과 일치하는지 확인
            boolean isCan = true;
            for (int j = 0; j < want.length; j++) {
                if (needMap.get(want[j]) != checkMap.get(want[j])) {
                    isCan = false;
                }
            }
            if (isCan) answer++;
        }
        return answer;
    }
}