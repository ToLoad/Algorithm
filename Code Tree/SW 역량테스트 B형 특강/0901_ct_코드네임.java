import java.util.*;
// https://www.codetree.ai/missions/5/problems/code-name/description

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Student> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            list.add(new Student(st.nextToken(), Integer.parseInt(st.nextToken())));
        }

        int min = Integer.MAX_VALUE;
        String minName = "";
        for (Student student : list) {
            if (min > student.score) {
                min = student.score;
                minName = student.name;
            }
        }

        System.out.println(minName + " " + min);
    }
}

class Student {
    String name;
    int score;

    Student (String name, int score) {
        this.name = name;
        this.score = score;
    }
}