package day0213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_18222_투에모스문자열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long k = Long.parseLong(br.readLine());
		System.out.println(rec(k - 1));
	}

	private static int rec(long k) {
		if (k == 0)
			return 0;
		if (k == 1)
			return 1;
		if (k % 2 == 0)
			return rec(k / 2);
		return 1 - rec(k / 2);
	}

}