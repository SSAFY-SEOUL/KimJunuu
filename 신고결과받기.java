import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
    
        int[] answer = new int[id_list.length];
        Map<String, Set<String>> reportedMap = new HashMap<String, Set<String>>();
        Map<String, Integer> reportedCountMap = new HashMap<String,Integer>();
        
        //초기화
        for(String e : id_list){
            reportedCountMap.put(e,0);
            reportedMap.put(e, new HashSet<String>());
        }
        
        
        StringTokenizer st = null;
        String reporter = null;
        String reported = null;
        
        //신고된 사람들
        for(String e : report){
            st = new StringTokenizer(e, " ");
            reporter = st.nextToken();
            reported = st.nextToken();
            
            //만약 신고자 -> 신고받은사람 존재하면 -> 신고받은사람의 횟수 +1
            if(reportedMap.get(reporter).add(reported)){
      reportedCountMap.put(reported, reportedCountMap.get(reported) + 1);                      
            }
            
        }
        
        //정지 사실을 메일로 보내기 위한 작업
        for(String e : report){
            st = new StringTokenizer(e," ");
            reporter = st.nextToken();
            reported = st.nextToken();
            //신고당한 횟수가 K이상인 경우만 남기기
            if(reportedCountMap.get(reported) < k){
            reportedMap.get(reporter).remove(reported);
            }
        }
     
        for(int i=0; i < id_list.length; i++){
        answer[i] = reportedMap.get(id_list[i]).size();
        }
        
        
        return answer;
    }
}
