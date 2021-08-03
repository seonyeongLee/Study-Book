/* 
  1주차
  https://programmers.co.kr/learn/courses/30/lessons/82612 
*/

class Solution {
    public long solution(int price, int money, int count) {
        long answer = money;

        for (int cnt = 0; cnt < count; ++cnt) {
            answer -= (price * (cnt + 1));
        }

        return (answer > 0 ? 0 : -answer);
    }
    
}
