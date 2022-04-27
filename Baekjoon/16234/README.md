## [문제 바로가기](https://www.acmicpc.net/problem/16234)

---

### 문제 읽기

N x N 크기의 땅의 각 칸마다 나라가 존재한다.

나라의 인구수는 배열로 주어진다.

인접한 나라의 인구 차이가 L명 이상 R명 이하라면 하루만 연합이 된다.

각 연합은 인구가 이동이 가능하며 인구수는 (연합의 인구수) / (연합을 이루고 있는 칸의 개수) 로 재정의된다.

인구 이동이 총 며칠 동안 발생하는지 구하는 문제

---

### 풀이방법

코드가 조금 지저분한 느낌이 있다.

만약 첫 날부터 인구 이동이 불가능하다면 0을 리턴해야해서

day의 초기값은 -1 이다

또 while 문 진입을 위해 오늘 인구가 이동 했는지 즉, 연합의 생성 여부를 파악하는 isCan은 true로 초기값을 지정했다.

main 함수의 while문 내부에서 다시 isCan을 false로 지정하고 날짜를 하루 증가시키고 오늘 방문 여부를 초기화해주었다.

main 함수의 while은 전체 나라를 돌면서 연합이 생성 가능한지를 확인한다.

연합 생성 여부는 move 함수를 통해서 확인하며

해당 함수는 현재 탐색하고 있는 나라가 주변과 연합이 가능한지 확인하고

가능하다면 연합 나라의 목록과 각 인구수의 합을 계산하면서 BFS 탐색을 진행한다.

그 뒤에 나라 목록의 인구수를 구한 평균값으로 바꿔주고 연합이 생성되었기 때문에 true를 리턴하고

해당 true값을 받아서 main에서는 오늘 연합이 생성되고 인구 이동이 되었음으로 확인한다.

만약 해당 위치에서 연합이 생성이 불가능하고 인구 이동이 없다면 false를 리턴해서 main 함수에서 isCan의 값이 바뀌지 않고 반복문을 탈출한 뒤

결과값인 day를 리턴하게 된다.