package day0226;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1244_스위치켜고끄기 {

	static class Student {
		int gender;
		int switchNumber;

		public Student(int gender, int switchNumber) {
			super();
			this.gender = gender;
			this.switchNumber = switchNumber;
		}

		@Override
		public String toString() {
			return "Student [gender=" + gender + ", switchNumber=" + switchNumber + "]";
		}

	}

	static final int MAN = 1;
	static final int WOMAN = 2;
	static int[] switchConditions;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int switchCount = Integer.parseInt(br.readLine());

		switchConditions = new int[switchCount];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < switchCount; i++) {
			switchConditions[i] = Integer.parseInt(st.nextToken());
		}

		int studentCount = Integer.parseInt(br.readLine());
		Student[] students = new Student[studentCount];
		for (int i = 0; i < studentCount; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int gender = Integer.parseInt(st.nextToken());
			int switchNumber = Integer.parseInt(st.nextToken());
			students[i] = new Student(gender, switchNumber);
		}

		// 메인 로직
		for (int i = 0; i < studentCount; i++) {
			int gender = students[i].gender;
			int switchNumber = students[i].switchNumber;

			switch (gender) {
			case MAN:
				manChangeSwitch(switchNumber);
				break;

			case WOMAN:
				womanChangeSwitch(switchNumber);
				break;
			}

		}
		StringBuilder sb = new StringBuilder();
		int count =0;
		for(int i=0; i< switchConditions.length; i++) {
			count++;	
			sb.append(switchConditions[i] + " ");
			if(count%20 ==0) {
				sb.append("\n");
			}
		}
		System.out.print(sb);

	}

	private static void manChangeSwitch(int switchNumber) {
		for (int i = 0; i < switchConditions.length; i++) {
			if ((i + 1) % switchNumber == 0) {
				changeSwitch(i);
			}
		}
	}

	private static void womanChangeSwitch(int switchNumber) {

		switchNumber--;
		int k = 0;
		changeSwitch(switchNumber);

		while (true) {

			k++;
			int tempLeft = switchNumber - k;
			int tempRight = switchNumber + k;

			if (!isValid(tempRight, tempLeft)) {
				break;
			}
			if (!isSymmetry(tempRight, tempLeft)) {
				break;
			}

			changeSwitch(tempRight);
			changeSwitch(tempLeft);

		}
	}

	private static void changeSwitch(int i) {
		switchConditions[i] = switchConditions[i] > 0 ? 0 : 1;
	}

	private static boolean isValid(int tempRight, int tempLeft) {
		return (tempRight < switchConditions.length && tempLeft >= 0);
	}

	private static boolean isSymmetry(int tempRight, int tempLeft) {
		return (switchConditions[tempLeft] == switchConditions[tempRight]);
	}

}
