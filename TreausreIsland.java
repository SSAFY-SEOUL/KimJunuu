import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
   	BOJ : 2589번 보물섬
  	직사각형 모양을 가진 보물섬 지도가 존재한다.
	지도는 여러 칸으로 나뉘어 있으며 각 칸은 육지(L)나 바다(W)로 표시되어 있다.
	지도에서 이동은 상하좌우로 이웃한 육지로만 가능하며, 한 칸 이동하는 데 한 시간이 걸린다.
	보물은 서로 간에 최단 거리로 이동하는데 있어 가장 긴 시간이 걸리는 육지 두 곳에 나뉘어 묻혀있다.
	보물 지도가 주어질 때, 보물이 묻혀 있는 두 곳 간의 최단 거리로 이동하는 시간을 구하는 프로그램을 작성하라.
 */
class Point {
	int x;
	int y;
	int depth;

	// i,j 값을 넣게 되면 좌표계상으로는 y,x값이기 때문에 y,x 순서로 생성자를 만들었습니다.
	public Point(int y, int x, int depth) {
		this.x = x;
		this.y = y;
		this.depth = depth;
	}
}

public class TreasureIsland {
	static char[][] treasureBoard;
	static boolean[][] visited;
	static int maxDistance = 0;
	static int height;
	static int width;
	// 상좌우하
	static int[] dy = { -1, 0, 0, 1 };
	static int[] dx = { 0, -1, 1, 0 };
	static Queue<Point> coordQ = new LinkedList<Point>();

	public static void main(String[] args) throws IOException {
		initialize();
		searchTreasureDistance();
		printMaxDistance();
	}

	static void initialize() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		treasureBoard = new char[height][width];
		visited = new boolean[height][width];

		for (int i = 0; i < height; i++) {
			String temp = new StringTokenizer(br.readLine()).nextToken();
			for (int j = 0; j < width; j++) {
				char tempChar = temp.charAt(j);
				treasureBoard[i][j] = tempChar;
				if (tempChar == 'L') {
					visited[i][j] = true;
				}
			}
		}
		br.close();

	}

	static void initializeTest() {

		for (int i = 0; i < treasureBoard.length; i++) {
			for (int j = 0; j < treasureBoard[i].length; j++) {
				System.out.print(treasureBoard[i][j] + " ");
			}
			System.out.println();
		}

		for (int i = 0; i < visited.length; i++) {
			for (int j = 0; j < visited[i].length; j++) {
				System.out.print(visited[i][j] + " ");
			}
			System.out.println();
		}
	}

	static void searchTreasureDistance() {
		for (int i = 0; i < treasureBoard.length; i++) {
			for (int j = 0; j < treasureBoard[i].length; j++) {
				if (!(treasureBoard[i][j] == 'L')) {
					continue;
				}
				addCoordToQueue(i, j, 0);
				changeVisitedByCoord(i, j);
				BFS();
				queueClear();
				resetVisited();

			}
		}
	}

	static void addCoordToQueue(int i, int j, int depth) {
		Point tempP = new Point(i, j, depth);
		coordQ.add(tempP);

	}

	static void changeVisitedByCoord(int i, int j) {
		visited[i][j] = false;
	}

	static void BFS() {				
		while (!coordQ.isEmpty()) {
			Point tempP = coordQ.poll();
			for (int k = 0; k < 4; k++) {
				int tempI = tempP.y + dy[k];
				int tempJ = tempP.x + dx[k];
				if (!isGo(tempI, tempJ)) {
					continue;
				}
				int depth = tempP.depth+1;
				visited[tempI][tempJ] = false;
				addCoordToQueue(tempI, tempJ, depth);
				maxDistance = maxDistance > depth ? maxDistance : depth;
			}
		}
	}

	static boolean isGo(int tempI, int tempJ) {		
		return (tempI >= 0) && (tempJ >= 0) && (tempJ < width) && (tempI < height) && (visited[tempI][tempJ] == true);	
	}

	static void queueClear() {
		coordQ.clear();
	}

	static void resetVisited() {
		for (int i = 0; i < treasureBoard.length; i++) {
			for (int j = 0; j < treasureBoard[i].length; j++) {
				if (treasureBoard[i][j] == 'L') {
					visited[i][j] = true;
				}
			}
		}
	}

	static void printMaxDistance() throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(maxDistance + "\n");
		bw.flush();
		bw.close();
	}

}
