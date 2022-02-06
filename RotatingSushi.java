package algorithm8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class RotatingSushi {
	static int countOfPlates, numberOfSushi, numberOfConsecutivePlates, couponNumber;
	static int maximumCountOfShsui;
	static int[] rotatingSushiBelt;
	static BufferedReader br;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		inputSushiInfo();		
		searchMaximumCountOfShsui();
		printMaximumCountOfShsui();
	}

	private static void inputSushiInfo() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		countOfPlates = Integer.parseInt(st.nextToken());
		numberOfSushi = Integer.parseInt(st.nextToken());
		numberOfConsecutivePlates = Integer.parseInt(st.nextToken());
		couponNumber = Integer.parseInt(st.nextToken());

		rotatingSushiBelt = new int[countOfPlates];

		for (int i = 0; i < countOfPlates; i++) {
			rotatingSushiBelt[i] = Integer.parseInt(br.readLine());
		}
	}

	private static void inputSushiInfoTest() {
		System.out.println(Arrays.toString(rotatingSushiBelt));
	}
	
	private static void searchMaximumCountOfShsui() {
		maximumCountOfShsui = 0;
		for(int i=0; i< countOfPlates; i++) {
			Set<Integer> set = new HashSet<Integer>();			
			for(int j=0; j< numberOfConsecutivePlates; j++) {
				set.add(rotatingSushiBelt[(i+j) % countOfPlates]);
			}
			set.add(couponNumber);			
			int temp = set.size();
			maximumCountOfShsui = maximumCountOfShsui > temp ? maximumCountOfShsui : temp;
		}
	}
	
	private static void printMaximumCountOfShsui() throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(maximumCountOfShsui + "\n");
		bw.flush();
		bw.close();		
	}
}
