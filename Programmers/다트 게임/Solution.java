class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        
        int preNum = 0; // 이전 수
        for (int i = 0; i < dartResult.length(); i++) {
            int num = dartResult.charAt(i) - '0';
            i++;
            if (dartResult.charAt(i) - '0' == 0) { // 숫자가 10일 경우
                num = 10;
                i++;
            }

            // 옵션 확인 후 계산
            char option = dartResult.charAt(i);
            int sum = cal(num, option);
            
            // 추가 옵션 확인
            if (i + 1 < dartResult.length()) {
                char plusOption = dartResult.charAt(i + 1);
                if (plusOption == '*') {
                    i++;
                    sum *= 2;
                    answer += preNum; // 이전 것을 두배시키기 때문에 한번더 더해줌
                } else if (plusOption == '#') {
                    i++;
                    sum *= -1;
                }
                                
            }
            // 합산
            preNum = sum;
            answer += sum;
        }
        return answer;
    }
    
    static int cal(int num, char option) {
        if (option == 'S') {
            return (int) Math.pow(num, 1);
        } else if (option == 'D') {
            return (int) Math.pow(num, 2);
        }
        
        return (int) Math.pow(num, 3);
    }
}