import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static ArrayList<Integer> numbers;
    static ArrayList<Character> operators;

    static boolean[] visited;
    static int[] result;
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        numbers = new ArrayList<>();
        operators = new ArrayList<>();

        String temp = br.readLine();
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                numbers.add(temp.charAt(i) - '0');
            } else {
                operators.add(temp.charAt(i));
            }
        }
        max = Integer.MIN_VALUE;
        DFS(numbers.get(0), 0);
        System.out.println(max);
    }

    // 연산
    public static int calc(char op, int n1, int n2) {
        switch (op) {
        case '+':
            return n1 + n2;
        case '-':
            return n1 - n2;
        case '*':
            return n1 * n2;
        }
        return -1;
    }

    // DFS, 백트래킹 활용.
    public static void DFS(int result, int opIdx) {
        // 주어진 연산자의 개수를 초과하였을 경우.
        if (opIdx >= operators.size()) {
            max = Math.max(max, result);
            return;
        }

        // 괄호가 없는 경우
        int res1 = calc(operators.get(opIdx), result, numbers.get(opIdx + 1));
        DFS(res1, opIdx + 1);

        // 괄호가 있는 경우
        if (opIdx + 1 < operators.size()) {
            // result의 오른쪽에 있는 값을 연산함.
            int res2 = calc(operators.get(opIdx + 1), numbers.get(opIdx + 1), numbers.get(opIdx + 2));

            // 현재 result와 방금 구한 괄호 값을 연산한 결과와 괄호 오른쪽에 존재하는 연산자의 인덱스를 넘김.
            DFS(calc(operators.get(opIdx), result, res2), opIdx + 2);
        }
    }
}
