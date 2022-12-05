import java.io.*;
import java.util.*;

public class bj_1283 {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Set<Character> set = new HashSet<>();
		set.add(' '); // 공백 방지

		for (int i = 0; i < n; i++) {
			boolean isFind = false;
			String word = br.readLine();

			// 모든 단어의 첫 글자부터 확인
			StringTokenizer st = new StringTokenizer(word);
			String fWord = "";
			while (st.hasMoreTokens()) {
				String temp = st.nextToken();
				char cur = temp.charAt(0);
				char lCur = temp.toLowerCase().charAt(0);
				if (isFind) fWord += temp + " ";
				else {
					if (!set.contains(lCur)) {
						set.add(lCur);
						isFind = true;
	
						String back = temp.substring(1);
	
						fWord += "[" + cur + "]" + back + " ";
					} else fWord += temp + " ";
				}
			}
			if (isFind) {
				sb.append(fWord + "\n");
				continue;
			}

			// 나머지 글자들 순서대로 확인
			String lowerWord = word.toLowerCase();
			for (int j = 0; j < lowerWord.length(); j++) {
				if (isFind) continue;

				char cur = lowerWord.charAt(j);
				if (!set.contains(cur)) {
					set.add(cur);
					isFind = true;

					String front = word.substring(0, j);
					char own = word.charAt(j);
					String back = "";
					if (j + 1 < lowerWord.length()) {
						back = word.substring(j + 1);
					}

					word = front + "[" + own + "]" + back;
				}
			}
			sb.append(word + "\n");
		}
		System.out.println(sb);
	}
}