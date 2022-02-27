package day0227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_8320_직사각형을만드는방법 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int rectCount = 0;
		for(int i=1; i<=N; i++) {
			if(i<=3) {
				rectCount++;
				continue;
			}
			
			for(int j=1; j<=Math.sqrt(i); j++) {
				if(i%j !=0) {
					continue;
				}
				rectCount++;								
			}			
		}
		System.out.println(rectCount);
	}

}
