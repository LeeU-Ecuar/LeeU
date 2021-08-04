package test;

import java.util.Arrays;
import java.util.HashMap;

public class Test {
    public static void main(String[] args) {

        int[] ary = new int[4];
        System.out.println(Arrays.toString(ary));

        int[] ary2 = {0,1,2};
        System.out.println(Arrays.toString(ary2));

        int[] ints = Arrays.copyOf(ary2, 5);
        System.out.println(Arrays.toString(ints));

        System.arraycopy(ary2,0,ary,1,3);

        System.out.println(Arrays.toString(ary));
    }
}
