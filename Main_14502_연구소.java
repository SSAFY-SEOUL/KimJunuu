package day0216;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14502_연구소 {
	static List<int[]> emptySpace = new ArrayList<int[]>();
	static List<int[]> virusList = new ArrayList<int[]>();
	static int WALL_COUNT = 3;
	static int[][] labBoard;
	static int[][] copyLabBoard;
	static int maxSafeArea = Integer.MIN_VALUE;
	static int N, M;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 세로크기 N, 가로크기M
		labBoard = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				labBoard[i][j] = Integer.parseInt(st.nextToken());
				if (labBoard[i][j] == 0) {
					emptySpace.add(new int[] { j, i });
				}
				if (labBoard[i][j] == 2) {
					virusList.add(new int[] { j, i });
				}
			}
		}
		//inputTest();
		// 조합을 위한 배열
		int[][] combinatedArray = new int[WALL_COUNT][2];
		combination(0, 0, combinatedArray);
		System.out.println(maxSafeArea);
	}

	private static void inputTest() {
		for (int[] e : emptySpace) {
			System.out.println(e[1] + " " + e[0]);
		}
		System.out.println(emptySpace.size());
		
		for(int [] e : virusList) {
			System.out.println(e[1] + " " + e[0]);
		}
		System.out.println(virusList.size());
	}

	private static void combination(int cnt, int start, int[][] combinatedArray) {
		if (cnt == WALL_COUNT) {
			// BFS
			int tempSafeArea = getSafeArea(combinatedArray);
			maxSafeArea = maxSafeArea > tempSafeArea ? maxSafeArea : tempSafeArea;
			return;
		}
		for (int i = start; i < emptySpace.size(); i++) {
			//x
			combinatedArray[cnt][1] = emptySpace.get(i)[1];
			//y
			combinatedArray[cnt][0] = emptySpace.get(i)[0];
			combination(cnt + 1, i + 1, combinatedArray);
		}

	}

	private static int getSafeArea(int[][] combinatedArray) {
		int safeArea = 0;
		copyLabBoard = new int[N][M];
		// 연구실 복사
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copyLabBoard[i][j] = labBoard[i][j];
			}
		}

		// 벽 생성
		for (int i = 0; i < 3; i++) {
			copyLabBoard[combinatedArray[i][1]][combinatedArray[i][0]] = 1;
		}
				
		// 바이러스 위치 탐색
		for (int i = 0; i < virusList.size(); i++) {
			BFS(virusList.get(i));
		}

		// 안전구역 count
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copyLabBoard[i][j] != 0) {
					continue;
				}
				safeArea++;
			}
		}

		return safeArea;
	}

	private static void BFS(int[] virus) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(virus);

		while (!q.isEmpty()) {
			int x = q.peek()[0];
			int y = q.peek()[1];
			q.poll();

			for (int k = 0; k < 4; k++) {
				int next_y = y + dy[k];
				int next_x = x + dx[k];

				if (next_y >= 0 && next_x >= 0 && next_y < N && next_x < M) {
					if (copyLabBoard[next_y][next_x] == 0) {
						copyLabBoard[next_y][next_x] = 2;
						q.add(new int[] { next_x, next_y });
					}
				}
			}
		}
	}
}
