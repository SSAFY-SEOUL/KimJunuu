package day0211;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_10163_색종이 {
	static final int BOARD_SIZE = 1001;	
	static int[] result;

	public static void main(String[] args) throws NumberFormatException, IOException {

		int board[][] = new int[BOARD_SIZE][BOARD_SIZE];
		int area = 0;

		setBoardInfo(board);
		area = getArea(board, area);
		printArea(area);
		
	}

	private static void setBoardInfo(int[][] board) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		result = new int[num];
		for (int t = 1; t <= num; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int left = Integer.parseInt(st.nextToken());
			int bottom = Integer.parseInt(st.nextToken());
			int width = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());
			
			//n번 색종이는 board에 n으로 표시
			//자연스럽게 뒤에 오는 색종이가 앞에오는 색종이의 값을 덮어버림			
			for (int i = left; i < left + width; i++) {
				for (int j = bottom; j < bottom + height; j++) {
					board[i][j] = t;
				}
			}

		}
	}

	private static int getArea(int[][] board, int area) {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				int temp = board[i][j];
				//0이라면 빈칸이므로 continue
				if (temp == 0) {
					continue;
				}
				//0이 아니라면 result[ index ] 배열에 +1 시켜줌
				// n번 색종이의 면적은 board에 n으로 표기되어 있음				
				//count되는 숫자만큼 넓이를 의미합니다.
				//temp-1을 넣어줬으므로 1번 색종이의 면적은 result[0]에 담김
				result[temp-1]++;
			}
		}
		return area;
	}

	private static void printArea(int area) {
		StringBuilder sb = new StringBuilder();
		for(int i : result) {
			sb.append(i + "\n");			
		}
		System.out.println(sb.toString());
	}
}
