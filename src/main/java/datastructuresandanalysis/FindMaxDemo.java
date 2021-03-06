package datastructuresandanalysis;

/**
 * Created by DreamYao on 2016/6/26.
 */
public class FindMaxDemo {

    private FindMaxDemo(){}
    /**
     * Return max item arr.
     * Precondition: arr.length>0
     */
    public static Comparable findMax(Comparable[] arr){
        int maxIndex=0;
        for (int i=0;i<arr.length;i++){
            if (arr[i].compareTo(arr[maxIndex]) > 0) {
                maxIndex=i;
            }
        }
        return arr[maxIndex];
    }
}
