package day0226;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2116_주사위쌓기 {

	static class Dice {
		int[] numbers;

		public Dice(int[] numbers) {
			super();
			this.numbers = numbers;
		}

		@Override
		public String toString() {
			return "Dice [numbers=" + Arrays.toString(numbers) + "]";
		}

	}

	static final int DICE_NUMBER = 6;
	static Dice[] dices;
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int diceCount = Integer.parseInt(br.readLine());

		StringTokenizer st = null;
		dices = new Dice[diceCount];
		for (int i = 0; i < diceCount; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int[] tempNumbers = new int[DICE_NUMBER];
			for (int j = 0; j < DICE_NUMBER; j++) {
				tempNumbers[j] = Integer.parseInt(st.nextToken());
			}
			dices[i] = new Dice(tempNumbers);			
		}

		for (int downSideIndex = 0; downSideIndex < DICE_NUMBER; downSideIndex++) {
			// 1번째 주사위 아랫면 지정
			
			int upSideNum = findUpSideNum(0,downSideIndex);
			int downSideNum = dices[0].numbers[downSideIndex];			
			int tempResult = 0;
			//윗면 아랫면 제외하고 최댓값 탐색
			int sideMaxNum = findSideMaxNum(upSideNum, downSideNum);
			tempResult += sideMaxNum;			
			
			// 1번째 주사위 위로 순서대로 주사위 쌓기
			for (int diceNum = 1; diceNum < diceCount; diceNum++) {				
				
				for (int k = 0; k < DICE_NUMBER; k++) {
					if (dices[diceNum].numbers[k] == upSideNum) {
						// 규칙에 맞는 수를 찾으면 해당 위치 주사위 반대면으로 upSideNum을 Update
						upSideNum = findUpSideNum(diceNum,k);
						downSideNum = dices[diceNum].numbers[k];
						break;
					}
				}
				// 아랫면 윗면 찾았으니까 두 면 제외하고 최대값탐색
				
				sideMaxNum = findSideMaxNum(upSideNum, downSideNum);
				tempResult += sideMaxNum;
			}

			result = Math.max(result, tempResult);

		}
		System.out.println(result);

	}

	private static int findSideMaxNum(int upSideNum, int downSideNum) {
		int sideMaxNum = Integer.MIN_VALUE;
		for (int k = 1; k <= DICE_NUMBER; k++) {
			if (k == upSideNum || k == downSideNum) {
				continue;
			}
			sideMaxNum = Math.max(sideMaxNum, k);
		}
		return sideMaxNum;
	}

	private static int findUpSideNum(int diceNum, int downSideIndex) {

		int upSideNum = 0;
		// A B C D E F 입력
		// A==F와 대칭 / B == D와 대칭 / C==E와 대칭
		switch (downSideIndex) {
		case 0:// A
			upSideNum = dices[diceNum].numbers[5];
			break;
		case 1:// B
			upSideNum = dices[diceNum].numbers[3];
			break;
		case 2:// C
			upSideNum = dices[diceNum].numbers[4];
			break;
		case 3:// D
			upSideNum = dices[diceNum].numbers[1];
			break;
		case 4:// E
			upSideNum = dices[diceNum].numbers[2];
			break;
		case 5:// F
			upSideNum = dices[diceNum].numbers[0];
			break;
		}
		return upSideNum;
	}

}
