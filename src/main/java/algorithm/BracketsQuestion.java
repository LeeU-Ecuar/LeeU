package algorithm;

/**
 * 括号有效配对是指︰
 * 1.任何一个左括号都能找到和其正确配对的右括号
 * 2.任何一个右括号都能找到和其正确配对的左括号
 * 例：
 * 有效的:(())、()()、(()())等
 * 无效的:(()、)(等
 * 问题一∶  怎么判断一个括号字符串有效?
 * 问题二︰ 如果一个括号字符串无效，返回至少填几个字符能让其整体有效
 * 问题三： 返回最长的有效括号字符串长度
 * 问题四： 给定一个有效括号字符串，返回最深的括号层数
 */
public class BracketsQuestion {
    public static void main(String[] args) {

        /*System.out.println(isValid("(())".toCharArray()));
        System.out.println(isValid("(()))".toCharArray()));
        System.out.println(isValid(")()(".toCharArray()));
        System.out.println(isValid("".toCharArray()));
        System.out.println(isValid("(".toCharArray()));

        System.out.println(isValidNum("))((".toCharArray()));
        System.out.println(isValidNum("(()))".toCharArray()));
        System.out.println(isValidNum(")()(".toCharArray()));
        System.out.println(isValidNum("".toCharArray()));
        System.out.println(isValidNum("(".toCharArray()));

        System.out.println(validMAxLength("))((".toCharArray()));
        System.out.println(validMAxLength("(()))".toCharArray()));
        System.out.println(validMAxLength(")()(".toCharArray()));
        System.out.println(validMAxLength("".toCharArray()));
        System.out.println(validMAxLength("(".toCharArray()));*/

        System.out.println(deep("(()((())))".toCharArray()));


    }

    // 问题一∶  怎么判断一个括号字符串有效?
    public static boolean isValid(char[] arr){
        int valid = 0;
        if(arr==null || arr.length==0) return false;
        for (int i = 0; i < arr.length; i++) {
            if(valid<0) return false;
            if('('==(arr[i])) valid++;
            else valid--;
        }
        return valid==0?true:false;
    }

    // 问题二︰ 如果一个括号字符串无效，返回至少填几个字符能让其整体有效
    public static int isValidNum(char[] arr){
        if(arr==null || arr.length==0) return 0;
        int valid = 0 , result = 0;
        for (int i = 0; i < arr.length; i++) {
            if(valid<0){
                result++;
                valid=0;
            }
            if('('==(arr[i])){
                valid++;
            }
            else valid--;
        }
        return (valid==-1?1:valid)+result;
    }

    // 问题三： 返回最长的有效括号字符串长度
    public static int validMAxLength(char[] arr){
        //计算每一个右括号往前数共有多少个符合要求的字符串
        if(arr==null || arr.length<2){
            return 0;
        }
        int[] dp = new int[arr.length];
        int pre=0,ml=0;

        for (int i = 1; i < arr.length; i++) {
            if(arr[i]==')'){
                pre = i-dp[i-1] -1;  //与arr[i]配对的左括号的位置 pre
                if(pre>=0 && arr[pre]=='('){
                    dp[i]=dp[i-1]+2+(pre>0?dp[pre-1]:0);
                }
            }
            ml = Math.max(ml,dp[i]);
        }
        return ml;
    }

    // 问题四： 给定一个有效括号字符串，返回最深的括号层数
    public static int deep(char[] arr){
        if(arr==null){
            return 0;
        }
        int deep = 0, maxDeep = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]=='('){
                deep++;
            }else{
                deep--;
            }
            maxDeep = Math.max(maxDeep,deep);
        }
        return maxDeep;
    }

}
