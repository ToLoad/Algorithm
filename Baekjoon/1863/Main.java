import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[50002];
        int result = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr[i] = y;
        }

        Stack<Integer> s = new Stack<>();
        for (int i = 0; i <= n; i++) {
            while(!s.empty() && s.peek() > arr[i]) {
                result += 1;
                s.pop();
            }

            if (!s.empty() && s.peek() == arr[i]) continue;
            s.push(arr[i]);
        }
        System.out.println(result);
    }
}
