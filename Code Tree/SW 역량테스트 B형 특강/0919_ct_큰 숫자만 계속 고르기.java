// https://www.codetree.ai/missions/8/problems/keep-picking-the-big-number/description

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
	
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            q.offer(Integer.parseInt(st.nextToken()));
        }

        while (m-- > 0) {
            q.offer(q.poll() - 1);
        }
        System.out.println(q.poll());
	}
}