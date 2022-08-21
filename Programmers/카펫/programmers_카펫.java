class Solution {
    public int[] solution(int brown, int yellow) {
        int sum = brown + yellow;
        int[] answer = new int[2];
        
        // j 가로, i 세로
        for (int i = 1; i < sum; i++) {
            int j = 0;
            
            if (sum % i != 0) continue;
            else j = sum / i;
            
            if (i > j) continue;
            
            if (yellow == (i - 2) * (j - 2)) {
                answer[0] = j;
                answer[1] = i;
            }
        }
        
        return answer;
    }
}