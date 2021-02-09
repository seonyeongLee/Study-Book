import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
  static int N, K;
  static int cnt=1;
  
  static Queue<Integer> circle = new LinkedList<Integer>();
  static Queue<Integer> ans = new LinkedList<Integer>();
  
  static StringBuilder output = new StringBuilder();

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    K = sc.nextInt();

    for(int i=1; i<=N; i++) {
      circle.add(i);
    }

    while(!circle.isEmpty()) {
      if(cnt == K) {
        ans.add(circle.poll());
        cnt=1;
      }else {
        circle.add(circle.poll());
        cnt++;
      }
    }

    output.append("<");
    for(int i=0; i<N-1; i++) {
      output.append(ans.poll()).append(", ");
    }
    output.append(ans.poll()).append(">");

    System.out.println(output);
  }

}

// [백준] https://www.acmicpc.net/submit/1158/26219368
