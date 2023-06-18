class Solution {
    public int solution(String[] board) {
        // OX 개수가 같은경우
        // O가 개수가 하나더 많은경우
        int answer = 0;
        
        int oCnt = 0;
        int xCnt = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char status = board[i].charAt(j);
                if (status == 'O') oCnt++;
                else if (status == 'X') xCnt++;
            }
        }
        
        // 개수 체크
        if (oCnt == xCnt) answer = 1;
        if (xCnt + 1 == oCnt) answer = 1;
        
        // 승리에 따른 개수 체크
        if (win('O', board)) {
            if (oCnt == xCnt)
                return answer = 0;
        }
        
        if (win('X', board)) {
            if (oCnt > xCnt)
                return answer = 0;
        }
        
        return answer;
    }
    
    public boolean win(char c, String[] board) {
        boolean win = false;
        
        for(int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == c && 
                board[i].charAt(0) == board[i].charAt(1) && 
                board[i].charAt(1) == board[i].charAt(2))
                win = true;
            
            if (board[0].charAt(i) == c && 
                board[0].charAt(i) == board[1].charAt(i) && 
                board[1].charAt(i) == board[2].charAt(i))
                win = true;
        }
        
        if (board[0].charAt(0) == c && 
            board[0].charAt(0) == board[1].charAt(1) && 
            board[1].charAt(1) == board[2].charAt(2))
            win = true;
        
        if (board[0].charAt(2) == c && 
            board[0].charAt(2) == board[1].charAt(1) && 
            board[1].charAt(1) == board[2].charAt(0))
            win = true;
        
        return win;
    }
}