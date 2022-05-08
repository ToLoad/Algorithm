import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static boolean isCan = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        makeS(S, T);
        System.out.println(isCan ? "1" : "0");
    }

    static void makeS(String S, String T) {
        if (S.length() == T.length()) {
            if (S.equals(T)) isCan = true;
            return;
        }

        if (T.charAt(T.length() - 1) == 'A') {
            makeS(S, T.substring(0, T.length() - 1));
        }

        if (T.charAt(0) == 'B') {
            StringBuffer sb = new StringBuffer(T.substring(1));
            makeS(S, sb.reverse().toString());
        }
    }
}
