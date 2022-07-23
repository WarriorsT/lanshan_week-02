package nature_number_divide;

import java.util.Scanner;

/**
 * 自然数拆分
 */
public class NatureNumberDivideTest {

    static int N = 9;
    static int n;
    static int[] path = new int[N];  //存储值
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        dfs(1, 0, 0);
    }

    //表示从x开始填,填的总和为y,填第z个数
    public static void dfs(int x, int y, int z){
        if(x == n) return;
        if(y == n){
           for(int i = 0; i < z; i++){
               if(i == 0){
                   System.out.print(path[i]);
               }else{
                   System.out.printf("+%d", path[i]);
               }
           }
            System.out.println();
            return;
        }

        for(int i = x; i <= n - y; i++){
            path[z] = i;
            dfs(i, y + i, z + 1);
        }
    }
    
}
