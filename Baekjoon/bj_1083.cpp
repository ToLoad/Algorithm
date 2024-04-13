#include <iostream>

using namespace std;

int N, S;
int A[51];

void input() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N;
    for (int i = 0; i < N; i++) {
        cin >> A[i];
    }
    cin >> S;
}

int main() {
    input();

    int start = 0;
    while (start < N && S > 0) {
        int maxIndex = start;

        // 가장 큰 값의 위치 찾기
        for (int i = start; i <= min(N - 1, start + S); i++) {
            if (A[maxIndex] < A[i]) {
                maxIndex = i;
            }
        }

        // 해당 위치로 이동
        for (int i = maxIndex; i > start; i--) {
            swap(A[i], A[i - 1]);
            S--;
        }

        // 다음 위치 탐색
        start++;
    }

    for (int i = 0; i < N; i++) {
        cout << A[i] << " ";
    }

    return 0;
}