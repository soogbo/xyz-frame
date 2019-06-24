package xyz.java.main.algorithm;

/**
 * 每次你只能爬一步或者两步, 共有n层,有多少种不同的方法爬到楼顶部
 *  ,爬楼梯问题其实质就是斐波那契数列！
 * 
 * @author Administrator
 *
 */
public class ClimbTheStairs {
    private static int total;

        // 递归调用
    public static int fib01(int n) {
        if (n == 1 || n == 2)
            total = n;
        else
            total = fib01(n - 2) + fib01(n - 1);
        return total;
    }

    // 三目运算符
    public static int fib02(int n) {
        return (n == 1 || n == 2) ? n : fib02(n - 2) + fib02(n - 1);
    }

    // 备忘录法
    public static int dfs(int n, int[] array) {
        if (array[n] != 0) {
            return array[n];
        } else {
            array[n] = dfs(n - 1, array) + dfs(n - 2, array);
            return array[n];
        }
    }

    public static int fib03(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1 || n == 2) {
            return n;
        } else {
            int[] array = new int[n + 1];
            array[1] = 1;
            array[2] = 2;
            return dfs(n, array);
        }
    }
    // 动态规划法 (利用数组来存储)

    public static int fib04(int n) {
        if (n == 0)
            return 1;
        int[] array = new int[n + 1];
        array[0] = 1;
        array[1] = 1;
        for (int i = 2; i <= n; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array[n];
    }
    // 状态压缩法(又称滚动数组、滑动窗口，用于优化动态规划法的空间复杂度)

    public static int fib05(int n) {
        if (n == 1 || n == 0)
            return 1;
        n = n - 1;
        int result = 0;
        int zero = 1;
        int first = 1;
        while (n > 0) {
            result = zero + first;
            zero = first;
            first = result;
            n--;
        }
        return result;
    }
    // 斐波那契数列的通项公式

    public static int fib06(int n) {
        if (n == 0)
            return 1;
        if (n == 1 || n == 2)
            return n;
        int result = (int) Math.floor(1 / Math.sqrt(5) * (Math.pow((1 + Math.sqrt(5)) / 2, n + 1) - Math.pow((1 - Math.sqrt(5)) / 2, n + 1)));
        return result;
    }

    public static void main(String[] args) {
        int num01 = fib01(7);
        System.out.println(num01);
        int num02 = fib02(8);
        System.out.println(num02);
        int num03 = fib03(0);
        System.out.println(num03);
        int num04 = fib04(8);
        System.out.println(num04);
        int num05 = fib05(8);
        System.out.println(num05);
        int num06 = fib06(2);
        System.out.println("用斐波那契数列的通项公式:" + num06);
    }

}