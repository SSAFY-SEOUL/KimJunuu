package day0208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class Main_10830_행렬제곱 {

	final static int MOD = 1000;
	static long repeatCount;
	static int matrix[][];
	static int result[][];
	static int origin[][];
	static int N;

	public static void main(String[] args) throws IOException {

		inputMartixInfo();
		calculateMartix(repeatCount);
		printResult();

	}

	private static void inputMartixInfo() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		repeatCount = Long.parseLong(st.nextToken());
		matrix = new int[N][N];
		result = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
			result[i][i] = 1;
		}
	}

	private static void calculateMartix(long cnt) {
		// 필요한 시간복잡도 = O(log(N))
		/*
		 * while (repeatCount > 0) { if (repeatCount % 2 == 1) { result =
		 * multiplyMartix(result, matrix); } matrix = multiplyMartix(matrix, matrix);
		 * repeatCount /= 2; }
		 */
		if (cnt == 1) {
			result = multiplyMartix(result, matrix);
			return;
		}

		calculateMartix(cnt / 2);

		// 짝수
		if (cnt % 2 == 0) {
			result = multiplyMartix(result, result);
			return;
		}
		// 홀수
		int[][] temp = multiplyMartix(result, matrix);
		result = multiplyMartix(result, temp);
		return;

	}

	// 두 행렬a와 b에 대한 곱 연산 : a x b
	static int[][] multiplyMartix(int[][] a, int[][] b) {
		int result[][] = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					result[i][j] += (a[i][k] * b[k][j]);
					result[i][j] %= MOD;

				}
			}
		}
		return result;
	}

	static void printResult() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(result[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}

}
