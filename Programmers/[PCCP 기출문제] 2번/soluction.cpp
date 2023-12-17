// 1. 덩어리 탐색
// 2. 각 덩어리별로 번호를 붙임
// 3. 번호별 석유량을 기록
// 4. 한 줄씩 파면서 번호를 기록, 각 번호의 석유량을 더한값이 제일 많은 줄 찾기

#include <string>
#include <vector>
#include <queue>
#include <map>
#include <set>
#include <iostream>

using namespace std;

vector<vector<int>> maps;
int dr[4] = {-1, 1, 0, 0};
int dc[4] = {0, 0, -1, 1};
int n, m;
bool visited[501][501];
map<int, int> cnt;
int index = 1;

void bfs(int r, int c) {
    int count = 0; // 현재 덩어리 개수
    queue<pair<int, int>> q;
    q.push({r, c});
    maps[r][c] = index;
    visited[r][c] = true;
    
    while (!q.empty()) {
        int cr = q.front().first;
        int cc = q.front().second;
        count += 1;
        q.pop();
        
        for (int d = 0; d < 4; d++) {
            int nr = cr + dr[d];
            int nc = cc + dc[d];
            
            if (nr < 0 || nc < 0 || nr >= n || nc >= m || 
               maps[nr][nc] == 0 || visited[nr][nc]) continue;
            maps[nr][nc] = index; // 2. 각 덩어리별로 번호를 붙임
            visited[nr][nc] = true;
            q.push({nr, nc});
        }
    }
    
    cnt[index] = count; // 3. 번호별 석유량을 기록
    index += 1;
}

int getAnswer() {
    int answer = 0;
    
    // m 가로, n 세로
    for (int i = 0; i < m; i++) {
        int sum = 0;
        set<int> s;
        for (int j = 0; j < n; j++) {
            if (maps[j][i] == 0) continue;
            s.insert(maps[j][i]);
        }
        
        for (auto it : s) {
            sum += cnt[it];
        }
        answer = max(answer, sum);
    }
    return answer;
}

int solution(vector<vector<int>> land) {
    n = land.size();
    m = land[0].size();
    maps = land;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (land[i][j] > 0 && !visited[i][j]) {
                bfs(i, j); // 1. 덩어리 탐색
            }
        }
    }
    
    // 4. 한 줄씩 파면서 번호를 기록, 각 번호의 석유량을 더한값이 제일 많은 줄 찾기
    return getAnswer();
}