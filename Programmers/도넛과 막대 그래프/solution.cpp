#include <string>
#include <vector>
#include <algorithm>

using namespace std;
const int MAX = 1000001;

int input[MAX]; // 들어오는 간선
int output[MAX]; // 나가는 간선

// 나가는 간선만 존재하면 생성한 정점
bool isCreatorNode(int node) {
    return input[node] == 0 && output[node] >= 2;
}

// 정점에서 생성된 그래프수 - (막대 그래프 수 + 8자 그래프 수)
int getDonutGraphCount(vector<int> &answer) {
    return output[answer[0]] - (answer[2] + answer[3]);
}

// 나가는 간선 없음
bool isBarGraph(int node) {
    return output[node] == 0;
}

// 들어오는 간선 2개이상 나가는 간선 2개
bool is8Graph(int node) {
    return input[node] >= 2 && output[node] == 2;
}

vector<int> solution(vector<vector<int>> edges) {
    int maxNode = 0;
    
    for (vector<int> edge : edges) {
        input[edge[1]]++;
        output[edge[0]]++;
        
        maxNode = max(maxNode, max(edge[0], edge[1]));
    }
    
    vector<int> answer(4, 0);
    for (int node = 1; node <= maxNode; node++) {
        if (isCreatorNode(node)) {
            answer[0] = node;
        } else if (isBarGraph(node)) {
            answer[2]++;
        } else if (is8Graph(node)) {
            answer[3]++;
        }
    }
    answer[1] = getDonutGraphCount(answer);
    
    return answer;
}