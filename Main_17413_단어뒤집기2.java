package day0226;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_17413_단어뒤집기2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringBuilder sb = new StringBuilder();
		StringBuilder tempSB;
		for (int i = 0; i < str.length(); i++) {
			char temp = str.charAt(i);
			
			if (temp == '<') {
				sb.append(temp);
				do {					
					i++;
					temp = str.charAt(i);
					sb.append(temp);
				} while (temp != '>');

			} else if (temp == ' ') {

				sb.append(temp);

			} else {
				tempSB = new StringBuilder();
				while (true) {
					tempSB.append(str.charAt(i));
					i++;
					if(i == str.length()) {
						break;
					}
					if (str.charAt(i) == ' ' || str.charAt(i) =='<') {
						i--;
						break;
					}
				}
				
				// 문자열 만들어짐
				// 뒤집기
				String tempStr = tempSB.toString();
				StringBuilder reverseSB = new StringBuilder();
				for (int j = tempStr.length() - 1; j >=0; j--) {
					reverseSB.append(tempStr.charAt(j));
				}
				tempStr = reverseSB.toString();
				sb.append(tempStr);
				
			}

		}
		System.out.println(sb);

	}

}
