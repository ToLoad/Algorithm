// https://www.codetree.ai/missions/5/problems/sort-by-height/description

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        ArrayList<Person> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String name = st.nextToken();
            int height = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list.add(new Person(name, height, weight));
        }
        Collections.sort(list);
        for (Person person : list) {
            System.out.println(person.name + " " + person.height + " " + person.weight);
        }
    }
}

class Person implements Comparable<Person> {
    String name;
    int height;
    int weight;

    Person (String name, int height, int weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    @Override
    public int compareTo(Person o) {
        return height - o.height;
    }
}