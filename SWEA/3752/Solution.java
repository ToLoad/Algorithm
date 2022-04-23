import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());

            HashSet<Integer> set = new HashSet<>();
            set.add(0);

            int[] score = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                score[i] = Integer.parseInt(st.nextToken());

                // 현재까지 구한 모든 점수에서 새 점수를 더해준다.
                HashSet<Integer> clone = new HashSet<>();
                clone.addAll(set);
                Iterator<Integer> iter = clone.iterator();
                while (iter.hasNext()) {
                    set.add(iter.next() + score[i]);
                }
            }
            sb.append("#" + t + " " + set.size() + "\n");
        }
        System.out.println(sb);
    }

}
