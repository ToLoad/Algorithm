#include <iostream>
#include <queue>

#define MAX 200
using namespace std;

int K, W, H;
int maps[MAX][MAX];
bool visited[MAX][MAX][31];

int horseR[] = {-2, -1, 1, 2, -2, -1, 1, 2};
int horseC[] = {-1, -2, -2, -1, 1, 2, 2, 1};
int monkeyR[] = {0, 0, -1, 1};
int monkeyC[] = {-1, 1, 0, 0};

void input() {
    cin >> K >> W >> H;
    for (int i = 0; i < H; i++) {
        for (int j = 0; j < W; j++) {
            cin >> maps[i][j];
        }
    }
}

void findWay() {
    queue<pair<pair<int, int>, pair<int, int>>> q;
    q.push(make_pair(make_pair(0, 0), make_pair(0, 0)));
    visited[0][0][0] = true;

    while (q.empty() == 0) {
        int r = q.front().first.first;
        int c = q.front().first.second;
        int cnt = q.front().second.first;
        int horseCnt = q.front().second.second;
        q.pop();

        if (r == H - 1 && c == W - 1) {
            cout << cnt;
            return;
        }

        // 말처럼 이동가능하면 해당 경우랑 기본 경우를 둘 다 큐에 넣어 보내기
        if (horseCnt < K) {
            for (int d = 0; d < 8; d++) {
                int nr = r + horseR[d];
                int nc = c + horseC[d];
                if (nr < 0 || nc < 0 || nr >= H || nc >= W)
                    continue;
                if (maps[nr][nc] == 1 || visited[nr][nc][horseCnt + 1] == true)
                    continue;

                visited[nr][nc][horseCnt + 1] = true;
                q.push(make_pair(make_pair(nr, nc),
                                 make_pair(cnt + 1, horseCnt + 1)));
            }
        }

        // 기본 이동 경우
        for (int d = 0; d < 4; d++) {
            int nr = r + monkeyR[d];
            int nc = c + monkeyC[d];

            if (nr < 0 || nc < 0 || nr >= H || nc >= W)
                continue;
            if (maps[nr][nc] == 1 || visited[nr][nc][horseCnt] == true)
                continue;

            visited[nr][nc][horseCnt] = true;
            q.push(make_pair(make_pair(nr, nc), make_pair(cnt + 1, horseCnt)));
        }
    }
    cout << -1;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    input();
    findWay();

    return 0;
}