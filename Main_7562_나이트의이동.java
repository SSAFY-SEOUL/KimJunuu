package day0208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Knight {
	int currentY;
	int currentX;
	int moveCount;

	Knight(int currnetY, int currentX, int moveCount) {
		this.currentY = currnetY;
		this.currentX = currentX;
		this.moveCount = moveCount;
	}
}

class Destination {
	int destinationX;
	int destinationY;

	Destination(int destinationY, int destinationX) {
		this.destinationY = destinationY;
		this.destinationX = destinationX;
		
	}
}

public class Main_7562_나이트의이동 {

	// 좌상2개 , 우상 2개, 우하 2개, 우좌 2개
	static int[] dy = { -1, -2, -2, -1, 2, 1, 2, 1 };
	static int[] dx = { -2, -1, 1, 2, 1, 2, -1, -2 };
	static final int SEARCH_COUNT = 8;

	static int size;
	static int[][] chessBoard;
	static boolean[][] visited;
	static Queue<Knight> queue = new LinkedList<Knight>();

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int t = 0; t < testCase; t++) {
			// 보드 초기화
			size = Integer.parseInt(br.readLine());
			chessBoard = new int[size][size];
			visited = new boolean[size][size];

			// 나이트 위치
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			Knight knight = new Knight(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);

			// 목적지
			st = new StringTokenizer(br.readLine(), " ");
			Destination destination = new Destination(Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));

			// 메인로직 BFS
			queue.add(knight);
			visited[knight.currentY][knight.currentX] = true;

			while (!queue.isEmpty()) {
				
				Knight tempKnight = queue.poll();
				if (isArrived(tempKnight, destination)) {
					sb.append(tempKnight.moveCount + "\n");
					break;
				}

				tempKnight.moveCount++;

				for (int i = 0; i < SEARCH_COUNT; i++) {
					int tempY = tempKnight.currentY + dy[i];
					int tempX = tempKnight.currentX + dx[i];
					if (!isValid(tempY, tempX)) {
						continue;
					}
					queue.add(new Knight(tempY, tempX, tempKnight.moveCount));
					visited[tempY][tempX] = true;
				}
			}
			queue.clear();
		}

		System.out.print(sb.toString());

	}

	// 나이트의 목적지 도착 검사
	static boolean isArrived(Knight k, Destination d) {
		return (k.currentX == d.destinationX && k.currentY == d.destinationY);
	}

	// 유효성 검사 : 배열 index + visited
	static boolean isValid(int tempY, int tempX) {
		return (tempY >= 0 && tempX >= 0 && tempY <= size-1 && tempX <= size-1 && visited[tempY][tempX] != true);
	}

}
