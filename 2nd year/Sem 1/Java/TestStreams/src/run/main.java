package run;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class main {
    public static void main(String[] args)
    {
        List < Integer > numbers= Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14);
        Integer sum= numbers.stream()
                .filter(x->x%4!=0)
                .reduce(0,Integer::sum);
        System.out.println(sum);

    }
    public static boolean div(int x)
    {
        if(x%4==0)
            return true;
        else
            return false;
    }
}
