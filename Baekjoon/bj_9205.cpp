#include <iostream>
#include <queue>
using namespace std;

struct Node {
    int x, y;
};

Node store[101];
Node festival;
Node home;
bool visited[101];

bool bfs(int n) {
    queue<Node> q;
    q.push(home);

    while (!q.empty()) {
        Node cur = q.front();
        q.pop();

        if (abs(festival.x - cur.x) + abs(festival.y - cur.y) <= 1000)
            return true;

        for (int i = 0; i < n; i++) {
            if (visited[i])
                continue;
            if (abs(store[i].x - cur.x) + abs(store[i].y - cur.y) <= 1000) {
                visited[i] = true;
                q.push({store[i].x, store[i].y});
            }
        }
    }
    return false;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int t;
    cin >> t;

    while (t--) {
        int n;
        cin >> n;
        cin >> home.x >> home.y;
        for (int i = 0; i < n; i++) {
            cin >> store[i].x >> store[i].y;
        }
        cin >> festival.x >> festival.y;

        bool isCan = bfs(n);
        if (isCan)
            cout << "happy\n";
        else
            cout << "sad\n";

        fill(visited, visited + 101, false); // 배열 초기화
    }
    return 0;
}
