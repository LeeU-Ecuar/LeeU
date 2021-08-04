package algorithm;

/**
 * 有一些排成一行的正方形，每个正方形已经被染成红色或者绿色。
 * 现在可以选择任意一个正方形然后用这两种颜色的任意一种进行染色.这个正方形的颜色将会被覆盖。
 * 目标是在完成染色之后,每个红色R都比每个绿色G距离最左侧更近。返回最少需要涂染几个正方形。
 * 如样例所示: s = RGRGR我们涂染之后变成RRRGG满足要求了,涂染的个数为2，且没有比这个更好的涂染方案。
 *
 * 可以全是R,也可以全是G,但是混合的必须是R在左边G在右边
 */
public class SquareColor {

    public static void main(String[] args) {
        System.out.println(num("RRRGRGGGGRG"));
    }

    private static int num(String str) {
        if(str==null || str.length()==0) return 0;
        //先获取一共有多少个G
        char[] chars = str.toCharArray();
        int right = 0;  //右边有多少R
        for (int i = 0; i < chars.length; i++) {
            if(chars[i]=='R') right++;
        }
        //遍历,当RG分界线从0位置到最终位置，各需要涂染多少次，取最小值
        int res = right; // 初始位置为字符串最左边，则为G的数量
        int left = 0;  //左边有多少G
        for (int i = 0; i < chars.length; i++) {
            if(chars[i]=='G') left++;
            if(chars[i]=='R') right--;
            res = Math.min(res, left+right);
        }
        return res;
    }


}
