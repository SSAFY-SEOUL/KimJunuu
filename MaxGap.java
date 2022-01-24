package algorithm6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * BOJ : 10819번 차이를 최대로
 * N개의 정수로 이루어진 배열 A의 정수의 순서를 적절히 바꿔서 다음 식의 최댓값을 구하는 프로그램
 * | A[0] - A[1] | + | A[1] - A[2] |  + .... + | A[N-2] - A[N-1] |
 */

public class MaxGap {
	
	static int arr[];
	static int N;
	static int max;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		makeArray();
		perm(0);
		printResult(max);
	}

	static void makeArray() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		String[] strArray = str.split(" ");
		arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(strArray[i]);
		}		
	}
		
	static void perm(int depth) {
		if( depth == N) {
			sum();
			return ;
		}
		
		for(int i = depth; i< N; i++) {
			swap(i, depth);
			perm(depth + 1);
			swap(i, depth);
		}
		
	}
	
	static void sum() {
		int sum = 0;
		for(int i=0; i<N-1; i++) {
			sum += Math.abs(arr[i] - arr[i+1]);
		}
		if (sum > max) {
			max = sum;
		}
	}
	
	static void swap(int num1, int num2) {
		int temp = arr[num2];
		arr[num2] = arr[num1];
		arr[num1] = temp;
	}
	
	static void printResult(int result) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));		
		bw.write(result + "\n");						
		bw.flush();
		bw.close();
	}
}
