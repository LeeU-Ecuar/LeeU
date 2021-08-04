package algorithm;

import java.util.Arrays;
import java.util.Stack;

/**
 * 获取一个正整数数组中，每一个位置离它最近的比它小的值
 *       ---单调栈中存放int.   如果有重复，则栈中数据存放List
 */
public class DrabStack {

    public static void main(String[] args) {
        int[] arr = {1,6,5,3,4,7,5};
        int[][] ints = drabStack0(arr);
        for(int[] d : ints){
            System.out.println(""+d[0]+" "+d[1]);
        }
    }

    private static int[][] drabStack0(int[] arr) {
        if(arr==null || arr.length==0){
            return null;
        }

        int[][] res = new int[arr.length][2];

        // 指定单调栈，由小到大
        Stack<Integer> st = new Stack<Integer>();

        st.push(0);

        for (int i = 1; i <arr.length; i++) {
            while(arr[i]<arr[st.peek()]){
                // 执行弹出，并且结算
                Integer pop = st.pop();
                res[pop][0] = st.isEmpty()?-1:arr[st.peek()];
                res[pop][1] = arr[i];
            }
            st.push(i);
        }

        // 最后单调栈当中如果依然还有数据，单独处理
        while(!st.isEmpty()){
            Integer pop = st.pop();
            res[pop][0] = st.isEmpty()?-1:arr[st.peek()];
            res[pop][1] = -1;
        }

        return res;
    }


}
