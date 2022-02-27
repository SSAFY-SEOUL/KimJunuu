package day0227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_10158_개미 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int width = Integer.parseInt(st.nextToken());
		int height = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		int currentX = Integer.parseInt(st.nextToken());
		int currentY = Integer.parseInt(st.nextToken());

		int time = Integer.parseInt(br.readLine());

		int moveX = time % (width * 2);
		int moveY = time % (height * 2);

		int tempX = 1;
		int tempY = 1;
		// moveX > 0 || moveY > 0
		while (moveX > 0 || moveY > 0) {

			if (moveX > 0) {
				currentX += tempX;
			}
			if (moveY > 0) {
				currentY += tempY;
			}

			if (currentX == width)
				tempX = -1;
			if (currentX == 0)
				tempX = 1;
			if (currentY == height)
				tempY = -1;
			if (currentY == 0)
				tempY = 1;

			moveX--;
			moveY--;
		}

		System.out.printf(currentX + " " + currentY);
	}

}
