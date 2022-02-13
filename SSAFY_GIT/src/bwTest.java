

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class bwTest{

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int iterate = 500 ;
		double startPrint = System.nanoTime();
		for(int i=0; i<iterate; i++) {
			System.out.println(1);
		}
		
		double finishPrint = System.nanoTime();
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		double startBw = System.nanoTime();
		
		for(int i=0; i<iterate; i++) {
			bw.write(1 + "\n");			
			bw.flush();			
		}
		
		double finishBw = System.nanoTime();
		
		System.out.println(finishPrint - startPrint);
		System.out.println(finishBw - startBw);
		
		
	}

}
