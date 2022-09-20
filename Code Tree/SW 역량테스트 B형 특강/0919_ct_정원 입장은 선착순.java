// https://www.codetree.ai/missions/8/problems/admission-to-the-garden-is-on-a-first-come-first-served-basis/description

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        ArrayList<Person> waitingList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = i + 1;
            int arrive = Integer.parseInt(st.nextToken());
            int stay = Integer.parseInt(st.nextToken());

            waitingList.add(new Person(num, arrive, stay));
        }
        // 사람이 두명 이하일 때 비교를 위해서 추가
        waitingList.add(new Person(n + 1, Integer.MAX_VALUE, 0));

        Collections.sort(waitingList);

        PriorityQueue<Person> q = new PriorityQueue<>((o1, o2) -> o1.num - o2.num);
        int exitTime = 0;
        int answer = Integer.MIN_VALUE;

        for (int i = 0; i <= n; i++) {
            int num = waitingList.get(i).num;
            int arrive = waitingList.get(i).arrive;
            int stay = waitingList.get(i).stay;

            while (arrive >= exitTime && !q.isEmpty()) {
                // 현재 사람이 도착할 시간이 앞 인원이 나간 시간 보다 뒤라면
                Person next = q.poll();
                int nextArrive = next.arrive;
                int nextStay = next.stay;

                // 현재 최대 대기시간과 다음 사람이 대기한 시간을 비교해줌
                answer = Math.max(answer, exitTime - nextArrive);

                // 앞 사람이 나간 시간에서 현재 인원이 머무는 시간을 더해서 계산해줌
                exitTime += nextStay;
            }
            
            // 앞에 사람이 나간 시간보다 도착시간이 더 뒤면 바로 입장 시키고 나가는 시간을 바꿔줌
            if (arrive >= exitTime) exitTime = arrive + stay;
            else q.offer(new Person(num, arrive, stay));
        }
        System.out.println(answer);
    }

    static class Person implements Comparable<Person> {
        int num;
        int arrive;
        int stay;

        Person (int num, int arrive, int stay) {
            this.num = num;
            this.arrive = arrive;
            this.stay = stay;
        }

        @Override
        public int compareTo(Person o) {
            if (arrive == o.arrive) return num - o.num;
            return arrive - o.arrive;
        }
    }
}