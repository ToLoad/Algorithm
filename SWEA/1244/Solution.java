import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static char[] numbers;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String numberStr = st.nextToken();
            int cnt = Integer.parseInt(st.nextToken());

            numbers = numberStr.toCharArray();
            result = 0;
            changeLocation(cnt, 0);
            sb.append("#" + t + " " + result + "\n");
        }
        System.out.println(sb);
    }

    static void changeLocation(int cnt, int start) {
        if (cnt == 0) {
            result = Math.max(result, Integer.parseInt(new String(numbers)));
            return;
        }

        for (int i = start; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                char temp = numbers[i];
                numbers[i] = numbers[j];
                numbers[j] = temp;
                changeLocation(cnt - 1, i);
                numbers[j] = numbers[i];
                numbers[i] = temp;
                
            }
        }
    }
}
