// https://www.codetree.ai/missions/8/problems/min-integer-output/description

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num != 0) q.offer(num);
            else {
                if (q.isEmpty()) sb.append(0).append("\n");
                else sb.append(q.poll()).append("\n");
            }
        }

        System.out.println(sb);
	}
}