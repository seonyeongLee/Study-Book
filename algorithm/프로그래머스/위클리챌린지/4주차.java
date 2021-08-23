/*
* [4주차] https://programmers.co.kr/learn/courses/30/lessons/84325
*/
import java.util.*;

class Solution {
    public String solution(String[] table, String[] languages, int[] preference) {
        String answer = "";
        
        Map<String, Integer> hm = new HashMap<String, Integer>();
        for(int i=0; i<table.length; i++) {
            String[] row = table[i].split(" ");            
            String type = row[0];
            
            for(int j=1; j<row.length; j++) {
                String language = row[j];
                for(int k=0; k<languages.length; k++) {
                    if(language.equals(languages[k])) {
                        if(hm.get(type) == null) {
                            hm.put(type, preference[k] * (6-j));
                        } else {
                            int val = hm.get(type) + ( preference[k] * (6-j) );
                            hm.put(type, val);
                        }
                    }
                }
            }  
        }
        
        int maxVal = -1;
        List<String> keySetList = new ArrayList<>(hm.keySet());
        Collections.sort(keySetList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        
        for (String key : keySetList) {
            System.out.println(key +"/"+ hm.get(key));
            if(hm.get(key) > maxVal) {
                answer = key;
                maxVal = hm.get(key);
            }
        }
        
        return answer;
    }
}

class Solution2 {
    public String solution(String[] table, String[] languages, int[] preference) {
        String answer = "";
        int score=-1;
        for(String str : table){
            String[] t = str.split(" ");
            String tname = t[0];
            int tscore = 0;
            for(int i=0;i<languages.length;i++){
                int idx = Arrays.asList(t).indexOf(languages[i]);
                if(idx>-1) tscore+=preference[i]*(6-idx);
            }
            if(score ==tscore && answer.compareTo(tname)>0) answer=tname;
            if(score<tscore){
                score =tscore;
                answer= tname;
            }
        }
        return answer;
    }
}
