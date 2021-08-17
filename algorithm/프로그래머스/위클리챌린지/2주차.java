/* 
  2주차
  https://programmers.co.kr/learn/courses/30/lessons/83201
*/

class Solution {
    public String solution(int[][] scores) {
        String answer = "";
        
        for(int j = 0; j < scores.length; j++){
            int selfScore = scores[j][j];
            int count = scores.length;
            
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            boolean flag = true;
            int summary = 0;
            
            for(int i = 0; i < scores.length; i++){
                int score = scores[i][j];
                
                if( i != j && selfScore == score ){
                    flag = false;
                }
                
                min = Math.min(min, score);
                max = Math.max(max, score);
                
                summary += score;
            }
            
            if( flag && (min == selfScore || max == selfScore) ){
                count--;
                summary -= selfScore;
            }
            
            answer += this.getJumsu((double) summary / (double) count);
        }
        
        return answer;
    }
    
    public String getJumsu(double avgScore) {
        if(avgScore >= 90)  return "A";
        if(avgScore >= 80)  return "B";
        if(avgScore >= 70)  return "C";
        if(avgScore >= 50)  return "D";
        
        return "F";
    }
}
