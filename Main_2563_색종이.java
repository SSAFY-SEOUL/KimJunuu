package day0210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2563_색종이 {
	static final int BOARD_SIZE = 100;
	static final int PAPER_SIZE = 10;
	static final int checked = 1;

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
		for (int t = 0; t < num; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int left = Integer.parseInt(st.nextToken());
			int bottom = Integer.parseInt(st.nextToken());
			for (int i = left; i < left + PAPER_SIZE; i++) {
				for (int j = bottom; j < bottom + PAPER_SIZE; j++) {
					board[i][j] = checked;
				}
			}

		}
	}

	private static int getArea(int[][] board, int area) {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (board[i][j] != checked) {
					continue;
				}
				area++;
			}
		}
		return area;
	}

	private static void printArea(int area) {
		System.out.println(area);
	}
}
