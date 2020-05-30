import java.util.Scanner;

public class KSubArray {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String inputLine1 = sc.nextLine();
        int n = Integer.parseInt(inputLine1.split(" ")[0]);
        int k = Integer.parseInt(inputLine1.split(" ")[1]);

        String inputLine2 = sc.nextLine();
        String[] nums = inputLine2.split(" ");
        int[] arr = new int[n];
        if(nums.length != n) {
            throw new RuntimeException("Invalid input, 'n' and 'input array' length is not equal");
        }
        for(int i=0;i<n;i++) {
            arr[i] = Integer.parseInt(nums[i]);
        }

        //validate Inputs
        if(k > arr.length || k <= 0) {
            System.out.println(String.format("%s is invalid value for number of partitions.", k));
        } else if(!ifArrayIsSorted(arr)) {
            System.out.println("The array is not sorted");
        } else {
            int[][] dp = new int[k][arr.length];

            for(int i=0;i<k;i++) {
                for (int j = 0; j < arr.length; j++) {
                    if(i == 0) {
                        dp[i][j] = arr[arr.length - 1] * arr[arr.length - 1] - arr[j] * arr[j];
                    } else {
                        dp[i][j] = -1;
                    }
                }
            }

            System.out.println(getMin(arr,0,k, dp));
        }

    }

    private static boolean ifArrayIsSorted(int[] arr) {
        for(int i=0;i<arr.length-1;i++) {
            if(arr[i] > arr[i+1]) {
                return  false;
            }
        }
        return true;
    }

    private static int getMin(int[] arr, int start, int k, int[][] dp) {

        if(dp[k-1][start] != -1) {
            return dp[k-1][start];
        }

        int min = Integer.MAX_VALUE;
        for(int i=start;i<arr.length-k+1;i++) {
            int sum = arr[i]*arr[i] - arr[start]*arr[start];
            min = Math.min(min, sum + getMin(arr, i+1, k-1, dp));

            /*System.out.print(String.format(" Min of (%s, %s) = %s", i+1, arr.length-1, ans));
            System.out.print(String.format("Sum of (%s, %s) = %s", start, i, sum));
            System.out.println(" Minimum till now is =" + min);*/
        }

        dp[k-1][start] = min;
        return min;
    }

}
