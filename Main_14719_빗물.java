package day0222;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14719_빗물 {
	private static int H, W;
	private static int[] blockArray;
	private static int left, right, center;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");		
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		blockArray = new int[W];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i=0; i<W; i++) {
			blockArray[i] = Integer.parseInt(st.nextToken());
		}
		
		left = center = right = 0;
		
		//5 4 2 3 4 6
		
		//가운데 기준으로 왼쪽 오른쪽 큰 친구를 찾는다.
		for (int i=1; i<W-1; i++) {
			left = right = 0;
			//i기준 왼쪽 중 제일 높은 친구 찾기
			for (int j=0; j<i; j++) {
				left = Math.max(left, blockArray[j]);
			}
			
			//i기준 오른쪽 중 제일 높은 친구 찾기
			for (int j=i+1; j<W; j++) {
				right = Math.max(right, blockArray[j]);
			}
			
			//현재 block이 left와 right보다 작으면 더해주기
			if (blockArray[i] < left && blockArray[i] < right) {
				center += Math.min(left, right) - blockArray[i];
			}
			
		}
		
		System.out.println(center);
	}
	
	
}