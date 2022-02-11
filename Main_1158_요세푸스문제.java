package day0210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1158_요세푸스문제 {

	static int N;
	static int K;

	public static void main(String[] args) throws IOException {

		inputSolutionInfo();
		StringBuilder sb = getJosephusPermutation();
		printJosephusPermutation(sb);

	}

	private static void inputSolutionInfo() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	}

	private static StringBuilder getJosephusPermutation() {
		List<Integer> peopleList = new LinkedList<Integer>();
		StringBuilder sb = new StringBuilder();

		sb.append("<");
		for (int i = 0; i < N; i++) {
			peopleList.add(i + 1);
		}

		int cnt = 0;
		int index = -1;
		
		while (!peopleList.isEmpty()) {
			
			//세 구문이 cnt % k ==0일때 까지 반복
			int listSize = peopleList.size();
			index = (index + 1) % listSize;			
			cnt++;

			if (cnt % K != 0) {
				continue;
			}
			//<3, 6, 2, 7, 5, 1, 4>
			//<2, 4, 1, 3, 5>

			
			//k=3이라면 cnt=3 index=3						
			if (peopleList.size() == 1) {
				sb.append(peopleList.remove(index) + ">\n");
				continue;
			}
			sb.append(peopleList.remove(index) + ", ");
			index--;
			
			
			
			
		}
		return sb;
	}

	private static void printJosephusPermutation(StringBuilder sb) {
		System.out.println(sb.toString());
	}
}
