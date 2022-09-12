import java.util.Scanner;

class Solution {
    private final static int N              = 4;  
    private final static int MAX_QUERYCOUNT = 1000000;

    private static int digits[] = new int[N]; 
    private static int digits_c[] = new int[10];

    private static int T;                          

	private static int querycount;
	
    // the value of limit_query will be changed in evaluation
	private final static int limit_query = 2520;

    static class Result {
        public int strike;                                
        public int ball;
	}
	
	private static boolean isValid(int guess[]) {
		int guess_c[] = new int[10];
		
		for (int count = 0; count < 10; ++count) guess_c[count] = 0;
		for (int idx = 0; idx < N; ++idx) {
			if (guess[idx] < 0 || guess[idx] >= 10 || guess_c[guess[idx]] > 0) return false;
			guess_c[guess[idx]]++;
		}
		return true;
	}
	
	// API : return a result for comparison with digits[] and guess[]
    public static Result query(int guess[]) {
		Result result = new Result();
		
		if (querycount >= MAX_QUERYCOUNT) {
			result.strike = -1;
			result.ball = -1;
			return result;
		}
		
		querycount++;
		
		if (!isValid(guess)) {
			result.strike = -1;
			result.ball = -1;
			return result;
		}
		
		result.strike = 0;
		result.ball = 0;

		for (int idx = 0; idx < N; ++idx)
			if (guess[idx] == digits[idx])
				result.strike++;
			else if (digits_c[guess[idx]] > 0)
				result.ball++;
		
		return result;
	}

	private static void initialize(Scanner sc) {
		for (int count = 0; count < 10; ++count) digits_c[count] = 0;
		
		String input;
		do input = sc.next(); while(input.charAt(0) < '0' || input.charAt(0) > '9');

		for (int idx = 0; idx < N; ++idx) {
			digits[idx] = input.charAt(idx) - '0';
			digits_c[digits[idx]]++;
		}
		
		querycount = 0;
	}

	private static boolean check(int guess[]) {
		for (int idx = 0; idx < N; ++idx)
			if (guess[idx] != digits[idx]) return false;
		return true;
	}
	
	public static void main(String[] args) throws Exception
	{
		
		int total_score = 0;
		int total_querycount = 0;
		
		//System.setIn(new java.io.FileInputStream("res/sample_input.txt"));

		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		UserSolution usersolution = new UserSolution();
		for (int testcase = 1; testcase <= T; ++testcase) {
			initialize(sc);

			int guess[] = new int[N];
            usersolution.doUserImplementation(guess);

			if (!check(guess)) querycount = MAX_QUERYCOUNT;
			if (querycount <= limit_query) total_score++;
			System.out.printf("#%d %d\n", testcase, querycount);
			total_querycount += querycount;
		}
		if (total_querycount > MAX_QUERYCOUNT) total_querycount = MAX_QUERYCOUNT;
		System.out.printf("total score = %d\ntotal query = %d\n", total_score * 100 / T, total_querycount);
		sc.close();
	}
}

class UserSolution {
    public final static int N = 4;
	static int dataCnt; // 검증된 데이터 개수
	static boolean[] checkNum;
	static int[][] failData;
	static int[] num;

    public void doUserImplementation(int guess[]) {
        // Random 을 통해서 아무 숫자 4개를 뽑아 스트라이크나 볼이 하나가 나올 때 까지 하기
        // query 함수를 이용해서 4 스트라이크를 성공시켜야함.
        // 스트라이크가 나왔을 때 스트라이크 위치를 찾기
        // 볼이 나왔을 때 제대로된 위치를 찾기

		// --------------------------------------------

		// 초기화
		num = guess;
		dataCnt = 0;
		checkNum = new boolean[10];
		failData = new int[10][6];

		dfs(0);
    }

	static boolean dfs(int n) {
		// 중복 없는 4자리 숫자를 찾았다면
		if (n >= 4) {
			if (!isPossible()) return false;

			Solution.Result result = Solution.query(num);
			if (result.strike == 4) return true;

			// 정답은 아니지만 가능성있는 숫자 추가
			for (int i = 0; i < 4; i++) failData[dataCnt][i] = num[i];
			failData[dataCnt][4] = result.strike;
			failData[dataCnt][5] = result.ball;
			dataCnt++;

			return false;
		}

		for (int i = 0; i < 10; i++) {
			if (checkNum[i]) continue;
			num[n] = i;
			checkNum[i] = true;

			if (dfs(n + 1)) return true; // 반복 중에 정답을 찾으면 종료
			checkNum[i] = false;
		}

		return false;
	}

	static boolean isPossible() {
		for (int i = 0; i < dataCnt; i++) { // 검증된 숫자 목록만큼 반복
			int strike = 0;
			int ball = 0;

			for (int j = 0; j < 4; j++) {
				if (failData[i][j] == num[j]) strike++;
				else if (checkNum[failData[i][j]]) ball++;
			}

			/* 
			현재 찾은 숫자를 검증된 숫자를 기반으로 비교해서 스트라이크 볼이 일치한다면 가능성이 있음.
			query를 적게 호출해야하기 때문에 검증된 숫자로 미리 비교를 해보는 로직이 추가된 것

			EX)
			첫 번째 검증된 숫자는 무조건 0123이 들어가는데 예제 1번의 8975와 비교했을 때 0스트라이크 0볼이다
			그래서 다음 숫자를 탐색할 때 0123과 같은 숫자가 있는 숫자들은 전부 탈락
			0123과 비교했을 때 처음으로 0스트라이크 0볼이 나오는 4567이 두번 째 검증된 숫자가 되는 것
			*/
			if ((failData[i][4] != strike) || (failData[i][5] != ball)) return false;
		}
		return true;
	}
}
