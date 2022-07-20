import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj_1525 {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String position = "";

        for(int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            for (int j = 0; j < 3; j++) {
                position += st.nextToken();
            }
        }

        Queue<String> q = new LinkedList<>();
        Map<String,Integer> m = new HashMap<>();
        q.offer(position);
        m.put(position, 0);

        while(!q.isEmpty()){
            String cur = q.poll();
            int blank = cur.indexOf("0");
            int r = blank / 3;
            int c = blank % 3;

            for(int i = 0; i < 4; i++){
                int nr = dr[i] + r;
                int nc = dc[i] + c;
                int move = nr * 3 + nc;

                if(nr >= 0 && nr < 3 && nc >= 0 && nc < 3){
                    StringBuilder next = new StringBuilder(cur);
                    char temp = next.charAt(move);
                    next.setCharAt(blank, temp);
                    next.setCharAt(move, '0');
                    String nextStr = next.toString();
                    if(!m.containsKey(nextStr)){
                        q.offer(nextStr);
                        m.put(nextStr, m.get(cur) + 1);
                    }
                }
            }
        }

        if (m.containsKey("123456780")) System.out.println(m.get("123456780"));
        else System.out.println(-1);
        

    }
}