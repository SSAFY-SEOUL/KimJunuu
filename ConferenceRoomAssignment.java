import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Conference implements Comparable<Conference> {
	public int startTime;
	public int finishTime;

	Conference() {
		this(0, 0);
	}

	Conference(int startTime, int finishTime) {
		this.startTime = startTime;
		this.finishTime = finishTime;
	}

	@Override
	public int compareTo(Conference c) {
		if (this.finishTime == c.finishTime) {
			return this.startTime - c.startTime;
		} else {
			return this.finishTime - c.finishTime;
		}
	}
}

public class ConferenceRoomAssignment {

	static int conferenceCount = 0;
	static int maxConferenceCount = 0;
	static List<Conference> conferenceList = new ArrayList<Conference>();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		inputConferenceInfo();
		if (isConferenceCountZero()) {
			printMaxConrenceCount();
		}
		sortConferenceList();
		getMaxConferenceCount();
		printMaxConrenceCount();

	}

	private static void inputConferenceInfo() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		conferenceCount = Integer.parseInt(br.readLine());

		for (int i = 0; i < conferenceCount; i++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str, " ");
			Conference c = new Conference(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			conferenceList.add(c);
		}
		br.close();
	}

	private static boolean isConferenceCountZero() {
		return conferenceCount == 0 ? true : false;
	}

	private static void sortConferenceList() {
		Collections.sort(conferenceList);
	}

	private static void getMaxConferenceCount() {
		int tempFinishTime = 0;

		for (Conference c : conferenceList) {
			if (c.startTime < tempFinishTime) {
				continue;
			}
			tempFinishTime = c.finishTime;
			maxConferenceCount++;
		}
	}

	private static void printMaxConrenceCount() throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(maxConferenceCount + "\n");
		bw.close();
		return;
	}

}
