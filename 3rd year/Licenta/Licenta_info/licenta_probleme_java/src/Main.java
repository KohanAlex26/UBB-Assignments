import java.util.ArrayList;
import java.util.List;

public class Main {

    public static boolean sequentialSearch(int [] arr,int e){
        for(int i=0;i<arr.length;i++)
            if(arr[i]==e)
                return true;
            return false;
    }

    public static boolean binarySearch(int [] arr, int e, int l, int r)
    {
        if(r>=l) {
            int m = (l + r) / 2;
            if (arr[m] == e)
                return true;
            if (e > arr[m])
                return binarySearch(arr, e, m + 1, r);
            if (e < arr[m])
                return binarySearch(arr, e, l, r - 1);
        }
        return false;
    }

    public static void selectionSort(int [] arr){
        for(int i=0;i<arr.length;i++)
        {
            int minId=i;
            for(int j=i+1;j<arr.length;j++)
            {
                if(arr[j]<arr[minId])
                    minId=j;
            }
            int aux=arr[i];
            arr[i]=arr[minId];
            arr[minId]=aux;
        }
        for(int i=0;i<arr.length;i++)
            System.out.println(arr[i]);
    }

    public static void bubbleSort(int [] arr){
        for(int i=0;i<arr.length;i++)
        {
            for(int j=0;j<arr.length-i-1;j++)
            {
                if(arr[j]>arr[j+1])
                {
                    int aux=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=aux;
                }
            }
        }
        for(int i=0;i<arr.length;i++)
            System.out.println(arr[i]);
    }

    public static void main(String[] args) {
        int[] intArray = new int[]{ 1,2,3,4,5,6,7,8,9,10 };
        System.out.println(sequentialSearch(intArray,3));
        System.out.println(binarySearch(intArray,11,0,9));

        int[] sortArray = new int[]{ 5,2,6,1,7,8,4,3 };
//        selectionSort(sortArray);
//        bubbleSort(sortArray);

        List<Integer> list=new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.set(0,23);
        System.out.println(list.get(0));

        C c=new C();
        c.print();

    }
}
