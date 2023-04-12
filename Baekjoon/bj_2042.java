import java.util.*;
import java.io.*;

public class bj_2042 {
    static final int CHANGE = 1;
    static final int SUM = 2;

    static int N;
    static long[] number;
    static long[] segmentTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        number = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            number[i] = Long.parseLong(br.readLine());
        }

        segmentTree = new long[(N + 1) * 4];
        makeTree(1, N, 1);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int mode = Integer.parseInt(st.nextToken());

            if (mode == CHANGE) {
                int id = Integer.parseInt(st.nextToken());
                long newNumber = Long.parseLong(st.nextToken());

                updateTree(1, N, 1, id, newNumber - number[id]);
                number[id] = newNumber;
            } else if (mode == SUM) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (a < b)
                    System.out.println(getSum(1, N, 1, a, b));
                else
                    System.out.println(getSum(1, N, 1, b, a));
            }
        }
    }

    static long makeTree(int start, int end, int index) {
        if (start == end) return segmentTree[index] = number[start];

        int mid = (start + end) / 2;
        return segmentTree[index] = makeTree(start, mid, index * 2) + makeTree(mid + 1, end, index * 2 + 1);
    }

    static void updateTree(int start, int end, int index, int id, long value) {
        if (id < start || id > end) return;

        // 범위에 포함되면 값을 변경
        segmentTree[index] += value;
        if (start == end) return; // 트리 제일 아래까지 탐색완료시 종료

        int mid = (start + end) / 2;
        updateTree(start, mid, index * 2, id, value);
        updateTree(mid + 1, end, index * 2 + 1, id, value);
    }

    static long getSum(int start, int end, int index, int left, int right) {
        if (right < start || left > end) return 0;
        if (left <= start && right >= end) return segmentTree[index];

        int mid = (start + end) / 2;
        return getSum(start, mid, index * 2, left, right) + getSum(mid + 1, end, index * 2 + 1, left, right);
    }
}
