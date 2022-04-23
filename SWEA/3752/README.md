## [문제 바로가기](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWHPkqBqAEsDFAUn)

---

### 문제 읽기

시험의 각 문제의 배점이 배열로 주어진다.

이 시험에서 받을 수 있는 점수의 경우의 수를 구하는 문제

---

### 풀이방법

중복이 발생하면 안되기 때문에 Set을 사용했고

Set을 통해 문제 점수를 입력하고

새로운 값을 받을 때마다 Set에서 다음 문제의 배점을 추가해주었다.

Ex) 시작은 0점 -> 다음 문제가 2점이면 0과 0에서 2를 더한 2가 Set에 저장

그 다음 문제가 3점이면 0 + 3, 2 + 3 두개가 더해져서 Set에 0, 2, 3, 5가 저장되어있게됨

해당 방식으로 계속 진행

그 다음 Set의 크기를 출력하였다.
