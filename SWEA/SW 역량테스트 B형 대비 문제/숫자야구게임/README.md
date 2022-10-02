## [문제 바로가기](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV4su3xKXFUDFAUf)

---

### 풀이방법

DFS를 통해 가능한 모든 숫자의 경우의 수를 찾는다.(중복 없는 순열)

그 중에서 정답인 것을 찾으면 되지만 query의 횟수를 최소로 호출해야하기 때문에 검증된 숫자 배열을 추가한다.

해당 배열은 query 함수를 통해 스트라이크와 볼의 개수를 체크한 배열로 query를 호출하기 전에 해당 배열과 비교하여 query로 비교할 만한 숫자인지를 확인해준다. (주석 참고)

query로 비교할만한 숫자라고 생각되면 비교하고 정답이 아니라면 또 다른 검증된 배열로써 추가해준다.

이를 반복하면 10회 이내에 해당하는 숫자를 찾을 수 있다.
