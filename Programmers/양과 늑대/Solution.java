import java.util.*;
 
class Solution {
	static ArrayList<Integer>[] childs;
	static int[] info;
	static int maxSheepCnt = 0;
 
	public int solution(int[] info, int[][] edges) {
		this.info = info;
		childs = new ArrayList[info.length];
        
		for (int[] edge : edges) {
			int parent = edge[0];
			int child = edge[1];
			if (childs[parent] == null) childs[parent] = new ArrayList<>();
			childs[parent].add(child);
		}
 
		List<Integer> list = new ArrayList<>();
		dfs(0, 0, 0, list);
		return maxSheepCnt;
	}
 
	static void dfs(int idx, int sheepCnt, int wolfCnt, List<Integer> nextPos) {
		if (info[idx] == 0) sheepCnt++;
		else wolfCnt++;
 
		if (wolfCnt >= sheepCnt) return;
		maxSheepCnt = Math.max(sheepCnt, maxSheepCnt);
   
		List<Integer> list = new ArrayList<>();
		list.addAll(nextPos);
        
		list.remove(Integer.valueOf(idx));
		if (childs[idx] != null) {
			for (int child : childs[idx]) {
				list.add(child);
			}
		}
        
		for (int next : list) {
			dfs(next, sheepCnt, wolfCnt, list);
		}
	}
}