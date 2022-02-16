package day0215;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_11725_트리의부모찾기 {
	static int N;
	static boolean[] visited;
	static int[] parentArray;
	static List<ArrayList<Integer>> edgeList;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		inputGraph();		
		DFS(1);
		printResult();

	}

	static void inputGraph() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N + 1];
		edgeList = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < N+1; i++) {
			edgeList.add(new ArrayList<Integer>());
		}
		parentArray = new int[N + 1];
		for (int node = 0; node < N - 1; node++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int edgeA = Integer.parseInt(st.nextToken());
			int edgeB = Integer.parseInt(st.nextToken());			
			edgeList.get(edgeA).add(edgeB);
			edgeList.get(edgeB).add(edgeA);
		}

	}

	static void DFS(int node) {
		visited[node] = true;
		
		for (int e : edgeList.get(node)) {
			if (visited[e]) {
				continue;
			}
			parentArray[e] = node;
			DFS(e);
		}
	}

	

	static void printResult() throws IOException {
		StringBuilder sb = new StringBuilder();
		for (int i = 2; i < parentArray.length; i++) {
			sb.append(parentArray[i] + "\n");
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(sb.toString());
		bw.flush();

	}

}
