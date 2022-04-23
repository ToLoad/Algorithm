import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    static int N;
    static int[] array;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            array = new int[N];
            count = 0;
            find(0);

            sb.append("#" + t + " " + count + "\n");
        }
        System.out.println(sb);
    }
    
    static void find(int depth) {
        if (depth == N) {
            count++;
            return;
        }

        for (int i = 0; i < N; i++) {
            array[depth] = i;

            // 해당 위치에 배치가 가능하다면 다음 재귀로
            if (isCan(depth)) {
                find(depth + 1);
            }
        }
    }

    static boolean isCan(int col) {
        for (int i = 0; i < col; i++) {
            // 같은 행에 있으면 안됨
            if (array[col] == array[i])
                return false;

            // 열과 행의 차이가 같다면 대각선에 놓여있다는 것이므로 X
            if (Math.abs(col - i) == Math.abs(array[col] - array[i]))
                return false;
        }
        return true;
    }
}
