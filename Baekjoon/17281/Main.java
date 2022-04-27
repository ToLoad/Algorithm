import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;           // 이닝 수 
    static int[][] players; // 이닝당 플레이어 결과
    static int[] order;     // 타순
    static boolean[] visited;

    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        players = new int[N][9];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 9; j++) {
                players[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 타순 정하기
        order = new int[9];
        visited = new boolean[9];
        visited[0] = true; // 1번 타자는 이미 자리가 4번으로 정해져있음
        findOrder(0);
        System.out.println(max);
    }

    static void findOrder(int depth) {
        if (depth >= 9) {
            max = Math.max(max, playGame());
            return;
        }
        if (depth == 3) {
            findOrder(depth + 1);
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (!visited[i]) {
                order[depth] = i;
                visited[i] = true;
                findOrder(depth + 1);
                visited[i] = false;
            }
        }

    }
    
    static int playGame() {
        int score = 0;
        int turn = 0;
        for (int i = 0; i < N; i++) {
            int out = 0;
            Queue<Integer> runner = new LinkedList<>();
            while (true) {
                // i 이닝의 순서가 turn번째인 타자의 결과는?
                int result = players[i][order[turn]];
                if (result == 0) out++;
                else if (result == 1) {
                    int size = runner.size();
                    for (int r = 0; r < size; r++) {
                        int temp = runner.poll() + 1;
                        if (temp > 3) score++;
                        else runner.add(temp);
                    }
                    runner.add(1);
                } else if (result == 2) {
                    int size = runner.size();
                    for (int r = 0; r < size; r++) {
                        int temp = runner.poll() + 2;
                        if (temp > 3) score++;
                        else runner.add(temp);
                    }
                    runner.add(2);
                } else if (result == 3) {
                    score += runner.size();
                    runner.clear();
                    runner.add(3);
                } else {
                    score += runner.size() + 1;
                    runner.clear();
                }
                turn++;
                if (turn > 8) turn = 0;
                if (out >= 3) break;
            }
        }
        return score;
    }
}
