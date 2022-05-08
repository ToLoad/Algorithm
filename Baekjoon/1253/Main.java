import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }
        int answer = 0;

        // 이분 탐색
        Arrays.sort(array);

        for (int i = 0; i < N; i++) {
            int left = 0;
            int right = N - 1;
            int target = array[i];
            boolean isFind = false;

            while (true) {
                // 자기 자신을 제외하고 나머지 수들의 합에서 나와야되므로 추가된 조건
                if (i == left) left++;
                if (i == right) right--;
                if (left >= right) break;

                // 타겟 찾기
                int sum = array[left] + array[right];
                if (sum > target) right--;
                else if (sum < target) left++;
                else if (sum == target) {
                    isFind = true;
                    break;
                }
            }
            if (isFind) answer++;
        }
        System.out.println(answer);
    }
}
