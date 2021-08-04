import java.util.Scanner;

/**
 * 编程|200分]圆的轨迹
 * 时间限制:CI/C++1秒，其他语言2秒
 * 空间限制:C/C++ 65536K，其他语言131072K64bit lO Format: %lld
 * 本题可使用本地IDE编码，不能使用本地已有代码，无跳出限制，编码后请点击"保存并调试"按钮进
 * 行代码提交。
 * 题目描述
 * 1011 12
 * 2 346 10 1415 16
 * (0.0)n= 4
 * (xc, yc)= (3.2)
 * 有n *n的方格，n>=1。每个方格的边长为1。最左下角的方格编号为1，往右依次编号+1，当最下面一排数完后，从上面一排继续从左往右编号，直到最右上角的方格编号为n*n。设横轴为x轴，纵轴为y轴，编号1的方格左下角点的坐标(x, y)为(0,0)。
 * 现有一个圆，圆心坐标为(xc, yc)，圆的半径为r。其中xc，yc和r均为整数，并且r > =1。此圆的轨迹可能会划过一些方格内部，当给定n，xc，yc和r时，要求找出此圆划过的所有方格，输出方格编号，以升序排列。当没有划过任何方格时，输出-1。
 * 注:当圆仅划过某个方格的边或角，没有进入方格内部时，不计入该方格。
 * 收起答题卡√
 * 已答未答当前
 * 例1
 * 例2
 * 输入描述:
 * 输入4个整数，以空格分隔，分别为n，xc, yc,r。其中n , r >= 1。
 * 输出描述:
 * 升序排列的方格编号，以空格分隔。最后一个编号右侧没有空格，也没有换行符。当没有空格划过时，输出-1。
 * 示例1输入输出示例仅供调试,后台判题数据—般不包含示例
 * 输入
 * 复制
 * 输出
 * 复制
 * 23 4 6 10 14 15 16
 */
public class honerTest2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String next = scanner.nextLine();
        String res = excuteMethod(next);
        System.out.println(res);

        scanner.close();


    }

    private static String excuteMethod(String next) {
        if(next==null && next.length()==0) return "-1";

        //4个整数，n r >=1
        String[] strs = next.split(" ");
        int n = Integer.parseInt(strs[0]);
        int xc = Integer.parseInt(strs[1]);
        int yc = Integer.parseInt(strs[2]);
        int r = Integer.parseInt(strs[3]);

        String result = "";

        //遍历格子，距离圆心距离值在范围内，则被划过
        for (int i = 1; i <= n*n; i++) {
            //右上
            int p = i%n;
            int x = p==0?n:p;
            int y = (p==0?0:1)+(i/n);

            int es = 0;
            boolean inner0 = tets(x,y,xc,yc,r);
            if(tets1(x,y,xc,yc,r)) es++;
            //右下
            y--;
            boolean inner1 = tets(x,y,xc,yc,r);
            if(tets1(x,y,xc,yc,r)) es++;
            //左下
            x--;
            boolean inner2 = tets(x,y,xc,yc,r);
            if(tets1(x,y,xc,yc,r)) es++;
            //左上
            y++;
            boolean inner3 = tets(x,y,xc,yc,r);
            if(tets1(x,y,xc,yc,r)) es++;
            //判断是否会被划过
            result+=( (inner0&&inner1&&inner2&&inner3)||(!inner0&&!inner1&&!inner2&&!inner3)||(es==1&&((inner0&&inner1&&inner2)||(inner0&&inner1&&inner3)||(inner3&&inner1&&inner2))) )?"":(i+" ");
        }
        return "".equals(result)?"-1":result.substring(0,result.length()-1);
    }

    private static boolean tets(int x,int y,int xc,int yc,int r){
        return (x - xc) * (x - xc) + (y - yc) * (y - yc) < r * r;
    }

    private static boolean tets1(int x,int y,int xc,int yc,int r){
        return (x - xc) * (x - xc) + (y - yc) * (y - yc) == r * r;
    }

}
