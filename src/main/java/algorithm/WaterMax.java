package algorithm;

/**
 * 给定一个数组arr，已知其中所有的值都是非负的，将这个数组看作一个容器，请返回容器能装多少水
 * 比如, arr.= {3，1，2，5，2，4}，根据值画出的直方图就是容器形状．该容器可以装下5格水
 * 再比如,arr = {4、5,1，3，2}.该容器可以装下2格水
 */
public class WaterMax {
    public static void main(String[] args) {
        int[] arr = {3,1,2,5,2,4};
        System.out.println(waterMax(arr));
    }

    //常规解法: 遍历数组，每个i求左右两边最大高度，看i能存多少水。 最后累加
    private static int waterMaxNormal(int[] arr) {
        return 0;
    }

    // 最优解法，双指针
    private static int waterMax(int[] arr) {
        if(arr==null || arr.length<2) return 0;

        int lenght = arr.length;
        int l = 1, r = lenght-2;  //左指针初始值为最左边的第二个数，右边同理
        int lm = arr[0], rm = arr[lenght-1];  //左max初始值为最左边的数，右边同理
        int water = 0 ;
        //左右指针往中间遍历，相碰撞时结束
        while (l<=r){
            if(lm<=rm){
                water+=Math.max(0,lm-arr[l]);
                lm = Math.max(lm,arr[l++]);
            }else{
                water+=Math.max(0,rm-arr[r]);
                rm = Math.max(rm,arr[r--]);
            }
        }
        return water;
    }
}
