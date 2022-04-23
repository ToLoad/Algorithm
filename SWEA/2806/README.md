## [문제 바로가기](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV7GKs06AU0DFAXB)

---

### 문제 읽기

NQueen 문제

N X N 의 체스판에 N개의 퀸을 서로 다른 퀸이 공격하지 못하게 놓는 경우의 수 찾기

---

### 풀이방법

N 크기의 1차원 배열을 만든다.

배열의 Index가 행이 되고 원소 값이 열이된다.

재귀를 통해 현재 행에 몇 번째 열에 퀸을 놓을 것인지 결정하고

해당 위치에 놓을 수 있는지 체크한다음 가능하다면 다음 재귀로 넘어간다.

모든 퀸을 배치 완료했으면 count를 증가시켜준다.
