# N으로 표현
```JAVA
import java.util.*;

class Solution {
  static int answer = 9;

  public int solution(int N, int number) {
    calc(0, 0, N, number);

    return answer > 8 ? -1 : answer;
  }

  private void calc(int cnt, int number, int N, int target) {
    if(cnt > 8) {
      return;
    }

    if(number == target) {
      answer = Math.min(answer, cnt);
      return;
    }

    for(int i=1; i<9-cnt; i++) {
      calc(cnt+i, number+getNum(i, N), N, target);
      calc(cnt+i, number-getNum(i, N), N, target);
      calc(cnt+i, number/getNum(i, N), N, target);
      calc(cnt+i, number*getNum(i, N), N, target);
    }

  }

  private int getNum(int digit, int N) {
    int result = 0;
    for(int i=0; i<digit;i++) {
      result += Math.pow(10,i) * N;
    }

    return result;
  }
}
```

[프로그래머스] https://programmers.co.kr/learn/courses/30/lessons/42895
