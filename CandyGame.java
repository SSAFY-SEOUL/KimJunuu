import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * BOJ : 3085번 사탕 게임
 * N x N 크기에 사탕이 채워져 있다.
 * 사탕의 색은 같지 않을 수 있다.
 * 사탕의 색이 다른 인접한 두 칸을 골라서 교환할 수 있다.
 * 교환은 단 한번만 이루어진다.
 * 이때 사탕이 같은 색인 가장 긴 연속 부분을 출력한다.
 */
public class CandyGame {
	static int n;
	static char[][] candyBoard;
	// 위, 오른쪽, 아래, 왼쪽
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static int maxCandyCount;

	public static void main(String[] args) throws IOException {
		inputCandyInfo();
		searchMaxCandyCount();
		printMaxCandyCount();
	}

	private static void inputCandyInfo() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		candyBoard = new char[n][n];
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < str.length(); j++) {
				candyBoard[i][j] = str.charAt(j);
			}
		}
	}

	private static void searchMaxCandyCount() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < 4; k++) {
					int tempY = i + dy[k];
					int tempX = j + dx[k];
					if(tempX < 0 || tempX >= n || tempY <0 || tempY >=n) {
						continue;
					}
					swap(i, j, tempY, tempX);
					countLongestCandy(i, j, tempY, tempX);
					//swap 되돌리기
					swap(i, j, tempY, tempX);				
				}
			}
		}
	}

	private static void swap(int i, int j, int tempY, int tempX) {
		char tempCandy = candyBoard[i][j];
		candyBoard[i][j] = candyBoard[tempY][tempX];
		candyBoard[tempY][tempX] = tempCandy;
	}

	private static void countLongestCandy(int i, int j, int tempY, int tempX) {
		// 바꿨을때 어느쪽이 최대인지 모르기 때문에 4번 검사해야 함.
		searchRow(i);
		searchRow(tempY);
		searchColumn(j);
		searchColumn(tempX);
	}

	private static void searchColumn(int fixedValue) {
		char temp = candyBoard[0][fixedValue];
		int tempMax = 1;
		for (int k = 1; k < n; k++) {
			if (candyBoard[k][fixedValue] == temp) {
				tempMax++;
			} else {
				temp = candyBoard[k][fixedValue];
				maxCandyCount = maxCandyCount > tempMax ? maxCandyCount : tempMax;
				tempMax = 1;
			}
		}
		maxCandyCount = maxCandyCount > tempMax ? maxCandyCount : tempMax;
	}

	private static void searchRow(int fixedValue) {
		int tempMax = 1;
		char temp = candyBoard[fixedValue][0];
		for (int k = 1; k < n; k++) {
			if (candyBoard[fixedValue][k] == temp) {
				tempMax++;
			} else {
				temp = candyBoard[fixedValue][k];
				maxCandyCount = maxCandyCount > tempMax ? maxCandyCount : tempMax;
				tempMax = 1;
			}
		}
		maxCandyCount = maxCandyCount > tempMax ? maxCandyCount : tempMax;
	}
	
	private static void printMaxCandyCount() throws IOException{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(maxCandyCount + "\n");
		bw.flush();
		bw.close();
	}

}
