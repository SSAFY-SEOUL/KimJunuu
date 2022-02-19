package day0219;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos {
	int y;
	int x;

	public Pos(int y, int x) {
		super();
		this.y = y;
		this.x = x;
	}

	@Override
	public String toString() {
		return "Pos [y=" + y + ", x=" + x + "]";
	}

}

public class Main_3055_탈출 {

	static char[][] board;
	static boolean[][] visited;
	// 상하좌우
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static final int DIRECTION = 4;
	static int R, C, move;
	static Pos detination;
	// 현재 물이 퍼져 나가야 하는 currentWaterQueue
	static Queue<Pos> currentWaterQueue = new LinkedList<Pos>();
	// 다음 물이 퍼져 나가야 하는 nextWaterQueue
	static Queue<Pos> nextWaterQueue = new LinkedList<Pos>();
	// 현재 고슴도치의 임시위치
	static Queue<Pos> currentHedgehogQueue = new LinkedList<Pos>();
	// 다음 고슴도치의 임시위치
	static Queue<Pos> nextHedgehogQueue = new LinkedList<Pos>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		board = new char[R][C];
		// visited 대신에 X,*,.으로 구분하면서 맵을 채워가자
		visited = new boolean[R][C];

		// S 는 고슴도치 위치
		// D 는 비버의 굴 위치
		// * 은 물
		// X 는 바위
		// . 비어있음
		for (int y = 0; y < R; y++) {
			String temp = br.readLine();
			for (int x = 0; x < C; x++) {
				board[y][x] = temp.charAt(x);
				if (board[y][x] == 'S') {
					currentHedgehogQueue.add(new Pos(y, x));
					visited[y][x] = true;
				}
				if (board[y][x] == 'D') {
					detination = new Pos(y, x);
				}
				if (board[y][x] == '*') {
					currentWaterQueue.add(new Pos(y, x));
				}
			}
		}
		while (true) {
			waterBFS();
			if (hedgehogBFS()) {
				break;
			}
		}

	}

	private static void waterBFS() {
		while (!currentWaterQueue.isEmpty()) {
			Pos temp = currentWaterQueue.poll();
			int y = temp.y;
			int x = temp.x;

			for (int k = 0; k < DIRECTION; k++) {
				int tempY = y + dy[k];
				int tempX = x + dx[k];
				if (!isPosValid(tempY, tempX)) {
					continue;
				}
				if (!isWaterFeatureValid(tempY, tempX)) {
					continue;
				}
				board[tempY][tempX] = '*';
				nextWaterQueue.add(new Pos(tempY, tempX));
			}
		}
		// CQ -> A
		// NQ -> B
		currentWaterQueue = nextWaterQueue;
		// CQ -> B
		// 메모리 leak이 발생하진 않을까?
		nextWaterQueue = new LinkedList<Pos>();
		// NQ -> C

	}

	private static boolean hedgehogBFS() {
		while (!currentHedgehogQueue.isEmpty()) {
			Pos temp = currentHedgehogQueue.poll();
			int y = temp.y;
			int x = temp.x;

			for (int k = 0; k < DIRECTION; k++) {
				int tempY = y + dy[k];
				int tempX = x + dx[k];
				if (!isPosValid(tempY, tempX)) {
					continue;
				}
				if (!isHedgehogFeatureValid(tempY, tempX)) {
					continue;
				}
				if (visited[tempY][tempX]) {
					continue;
				}
				visited[tempY][tempX] = true;
				nextHedgehogQueue.add(new Pos(tempY, tempX));
			}
		}
		// 고슴도치가 이동할 수 없다면? nextHedgehogQueue == empty
		if (nextHedgehogQueue.isEmpty()) {
			System.out.println("KAKTUS");
			return true;
		}
		move++;
		for (Pos e : nextHedgehogQueue) {
			if (e.x == detination.x && e.y == detination.y) {
				System.out.println(move);
				return true;
			}
		}
		// CQ -> A
		// NQ -> B
		currentHedgehogQueue = nextHedgehogQueue;
		// CQ -> B
		// 메모리 leak이 발생하진 않을까?
		nextHedgehogQueue = new LinkedList<Pos>();
		return false;
	}

	private static boolean isPosValid(int tempY, int tempX) {
		return (tempY >= 0 && tempX >= 0 && tempY < R && tempX < C);
	}

	private static boolean isWaterFeatureValid(int tempY, int tempX) {
		char feature = board[tempY][tempX];
		return feature != 'X' && feature != '*' && feature != 'D';
	}

	private static boolean isHedgehogFeatureValid(int tempY, int tempX) {
		char feature = board[tempY][tempX];
		return feature != 'X' && feature != '*';
	}

}
