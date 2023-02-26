import java.util.*;

class Solution {
    static int[][] users;
    static int[] emoticons;
    
    static int[] rate = {10, 20, 30, 40};
    static int[] resultRate;
    static int subs, amount;
    static int[] max;
    
    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;
        
        resultRate = new int[emoticons.length];
        max = new int[2];
        findRate(emoticons.length, 0);
        
        return max;
    }
    
    static void findRate(int r, int cur) {
        if (r <= cur) {
            getPrice();
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            resultRate[cur] = rate[i];
            findRate(r, cur + 1);
        }
    }

    // 각 할인률별로 임티플러스 가입자수와 판매액 계산
    static void getPrice() {
        subs = 0;
        amount = 0;
        
        for (int i = 0; i < users.length; i++) {
            int sum = 0;
            for (int j = 0; j < emoticons.length; j++) {
                if (users[i][0] <= resultRate[j]) {
                    sum += cal(emoticons[j], resultRate[j]);
                }
            }
            
            if (sum >= users[i][1]) subs += 1;
            else amount += sum;
        }
        
        if (max[0] < subs) {
            max[0] = subs;
            max[1] = amount;
        } else if (max[0] == subs) {
            max[1] = Math.max(max[1], amount);
        }
    }
    
    static int cal(int price, int rate) {
        return price * (100 - rate) / 100;
    }
}