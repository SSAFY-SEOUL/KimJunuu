package algorithm6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class InputBox {
	static int[] boxArray;
	static int[] dp;
	static int n;
	static int maxBoxCount;

	public static void main(String[] args) throws IOException {

		inputBoxData();
		calculateMaxBoxCount();
		printMaxBoxCount();

	}

	private static void inputBoxData() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		boxArray = new int[n + 1];
		dp = new int[n + 1];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= n; i++) {
			boxArray[i] = Integer.parseInt(st.nextToken());
			dp[i] = 1;
		}

		br.close();
	}

	private static void calculateMaxBoxCount() {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= i; j++) {
				if (boxArray[i] > boxArray[j] && dp[i] < dp[j] + 1) {
					dp[i] = dp[j] + 1;
				}
			}
			maxBoxCount = maxBoxCount > dp[i] ? maxBoxCount : dp[i];
		}
	}

	private static void printMaxBoxCount() throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(maxBoxCount + "\n");
		bw.flush();
		bw.close();

	}

}
