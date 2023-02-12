import java.io.*;
import java.util.*;

public class bj_10845 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> qList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String order = st.nextToken();

            if (order.equals("push")) {
                int num = Integer.parseInt(st.nextToken());
                qList.add(num);
            } else if (order.equals("pop")) {
                if (qList.isEmpty()) sb.append("-1\n");
                else {
                    sb.append(qList.get(0) + "\n");
                    qList.remove(0);
                }
            } else if (order.equals("size")) {
                sb.append(qList.size() + "\n");
            } else if (order.equals("empty")) {
                if (qList.isEmpty()) sb.append("1\n");
                else sb.append("0\n");
            } else if (order.equals("front")) {
                if (qList.isEmpty()) sb.append("-1\n");
                else sb.append(qList.get(0) + "\n");
            } else {
                if (qList.isEmpty()) sb.append("-1\n");
                else sb.append(qList.get(qList.size() - 1) + "\n");
            }
        }
        System.out.println(sb);
    }
}
