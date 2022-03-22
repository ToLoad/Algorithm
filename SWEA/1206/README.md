## [문제 바로가기](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV134DPqAA8CFAYh&categoryId=AV134DPqAA8CFAYh&categoryType=CODE&problemTitle=1206&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1)

---

### 문제 읽기

조망권이 확보된 아파트 가구수를 찾는다.
현재 층 수에서 양쪽으로 2칸 안에 다른 아파트가 보이지않아야한다.

시작 지점에서 2칸, 끝나는 지점에서 2칸은 공백이 있다.

---

### 풀이방법

좌측 두 칸 중에 더 높은 아파트의 층수를 left에 기록
우측 두 칸 중에 더 높은 아파트의 층수를 right에 기록
만약 현재 아파트의 층 수가 left나 right보다 높다면 조망권이 확보된 가구수가 있다는 뜻
그렇다면 현재 아파트의 층 수에서 left, right 중 큰 값을 빼면 조망권이 확보된 가구수가 몇 개인지 알 수 있음
