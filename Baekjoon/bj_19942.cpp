#include <iostream>
#include <vector>
using namespace std;

struct Ingredients {
    int mp, mf, ms, mu, cost;
};

int N;
Ingredients needs;
Ingredients haveIngredi[16];
vector<int> cur, answer;
int minCost = 50000;

void solve(int num, int mp, int mf, int ms, int mu, int cost) {
    if (num == N) {
        if (mp >= needs.mp && mf >= needs.mf && ms >= needs.ms &&
            mu >= needs.mu) {
            if (minCost > cost) {
                answer = cur;
                minCost = cost;
            } else if (minCost == cost &&
                       answer >
                           cur) { // vector의 요소의 대소를 비교 둘의 size가
                                  // 다르다면 size가 큰 vector를 큰 것으로 판단
                answer = cur;
                minCost = cost;
            }
        }
        return;
    }

    cur.push_back(num);
    solve(num + 1, mp + haveIngredi[num].mp, mf + haveIngredi[num].mf,
          ms + haveIngredi[num].ms, mu + haveIngredi[num].mu,
          cost + haveIngredi[num].cost);
    cur.pop_back();
    solve(num + 1, mp, mf, ms, mu, cost);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int mp, mf, ms, mu, cost;
    cin >> N >> mp >> mf >> ms >> mu;
    needs = {mp, mf, ms, mu, 0};

    for (int i = 0; i < N; i++) {
        cin >> mp >> mf >> ms >> mu >> cost;
        haveIngredi[i] = {mp, mf, ms, mu, cost};
    }

    solve(0, 0, 0, 0, 0, 0);
    if (minCost == 50000) {
        cout << -1;
        return 0;
    }

    cout << minCost << '\n';
    for (int i : answer) {
        cout << i + 1 << ' ';
    }

    return 0;
}
