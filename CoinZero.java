package algorithm8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class CoinZero {

	static int kindOfCoin;
	static int sumOfValues;
	static int coinCount;
	static int[] coinArray;

	public static void main(String[] args) throws IOException {

		inputCoinInfo();
		getCoinCount();
		printCoinCount();

	}

	private static void inputCoinInfo() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		kindOfCoin = Integer.parseInt(st.nextToken());
		sumOfValues = Integer.parseInt(st.nextToken());
		coinCount = 0;
		coinArray = new int[kindOfCoin];
		for (int i = 0; i < kindOfCoin; i++) {
			coinArray[i] = Integer.parseInt(br.readLine());
		}
	}

	private static void getCoinCount() {
		while (sumOfValues != 0) {
			for (int i = kindOfCoin - 1; i >= 0; i--) {
				if (sumOfValues < coinArray[i]) {
					continue;
				}
				sumOfValues -= coinArray[i];
				coinCount++;
				kindOfCoin = i + 1;
				break;
			}

		}
	}

	private static void printCoinCount() throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(coinCount + "\n");
		bw.flush();
		bw.close();
	}

}
