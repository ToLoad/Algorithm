#include <queue>
#include <string>
#include <vector>

#define MAX 101
using namespace std;

int dr[] = {-1, 1, 0, 0};
int dc[] = {0, 0, -1, 1};

int rowLength, colLength;
int startR, startC;
bool visited[MAX][MAX];

int getAnswer(vector<string> board) {
    queue<pair<pair<int, int>, int>> q;

    visited[startR][startC] = true;
    q.push(make_pair(make_pair(startR, startC), 0));

    while (!q.empty()) {
        int r = q.front().first.first;
        int c = q.front().first.second;
        int cnt = q.front().second;

        if (board[r][c] == 'G') {
            return cnt;
        }
        q.pop();

        for (int d = 0; d < 4; d++) {
            // 끝에 도달할 때까지 이동
            int nr = r;
            int nc = c;

            while (true) {
                nr += dr[d];
                nc += dc[d];

                if (nr < 0 || nc < 0 || nr >= rowLength || nc >= colLength ||
                    board[nr][nc] == 'D') {
                    nr -= dr[d];
                    nc -= dc[d];
                    break;
                }
            }

            // 도달한 좌표 넣기
            if (!visited[nr][nc]) {
                q.push(make_pair(make_pair(nr, nc), cnt + 1));
                visited[nr][nc] = true;
            }
        }
    }

    return -1;
}

int solution(vector<string> board) {
    rowLength = board.size();
    colLength = board[0].size();

    for (int i = 0; i < rowLength; i++) {
        for (int j = 0; j < colLength; j++) {
            if (board[i][j] == 'R') {
                startR = i;
                startC = j;
            }
        }
    }

    return getAnswer(board);
}