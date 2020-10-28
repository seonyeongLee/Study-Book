# K번째수

```JAVA
  import java.util.*;

  class Solution {
      public int[] solution(int[] array, int[][] commands) {
          int[] answer = new int[commands.length];

          for(int i=0; i<commands.length; i++) {
              int[] newArr = Arrays.copyOfRange(array, commands[i][0] - 1, commands[i][1]);

              Arrays.sort(newArr);

              answer[i] = newArr[commands[i][2] - 1];
          }

          return answer;
      }
  }
```

[프로그래머스] https://programmers.co.kr/learn/courses/30/lessons/42748
