import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/*
 * BOJ 1065번 : 한수
 * 어떤 양의 정수 X의 각 자리가 등차수열을 이룬다면, 그 수를 한수라고 한다.
 * 등차수열은 연속된 두 수의 차이가 일정한 수열을 말한다.
 * N이 주어졌을 때, 1보다 크거나 같고, N보다 작거나 같은 한수의 개수를 출력하는 프로그램을 작성하시오.
 */
public class Hansu {
	static int N;
	static int count = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		inputNum();
		searchHansu();
		printResult();
	}

	static void inputNum() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		br.close();
	}

	static void searchHansu() {
		for (int i = 1; i <= N; i++) {
			if (i < 100 || isHansu(i)) {
				count++;
			}
		}
	}

	static boolean isHansu(int num) {
		ArrayList<Integer> numSplited = new ArrayList<Integer>();

		do {
			numSplited.add(num % 10);
			num /= 10;
		} while (num > 0);
		
		int gap = numSplited.get(0) - numSplited.get(1);
		for (int i = 1; i < numSplited.size() - 1; i++) {
			if (gap != numSplited.get(i) - numSplited.get(i + 1)) {
				return false;
			}
		}

		return true;
	}
	
	static void printResult() throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(count + "\n");
		bw.flush();
		bw.close();
		
	}
}
