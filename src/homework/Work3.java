package homework;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Work3 {

    static Object[] arr = new Object[2];
    private static int size = arr.length;

    public static void main(String[] args) {

        add(1);
        add(2);
        add(3);
        remove(5);
        Object o = get(2);
        System.out.println(Arrays.toString(arr));
        System.out.println(o);
        set(2,5);
        System.out.println(Arrays.toString(arr));
        System.out.println(size());
        pop();
        System.out.println(Arrays.toString(arr));
    }

    public static void add(Object o){
        int num = 0;
        for (int i = 0; i < size; i++) {
            if(arr[i]!=null) {
                num++;
                if (num == size - 1) {
                    int ol = arr.length;
                    int ne = ol + (ol >> 1);
                    if (ne - size > 0)
                        size = ne;
                    arr = Arrays.copyOf(arr, ne);
                }
            }
        }
        arr[num] = o;

    }

    public static void remove(int index){
        if(index>size-1){
            System.out.println("³¬¹ýÊý×é·¶Î§");
        }else{
             int num = size - index-1;
            if (num > 0)
                System.arraycopy(arr, index+1, arr, index,
                        num);
            arr[--size] = null;
        }

    }

    public static Object get(int index){
        Object num = 0;
        num = arr[index-1];
        return num;
    }

    public static void set(int index , Object o){
        Object oo = null;
        arr[index-1] = o;

    }

    public static int size( ){
        return arr.length;
    }

    public static void push(Object o){
        int num = 0;
        for (int i = 0; i < size; i++) {
            if(arr[i]!=null) {
                num++;
                if (num == size - 1) {
                    int ol = arr.length;
                    int ne = ol + (ol >> 1);
                    if (ne - size > 0)
                        size = ne;
                    arr = Arrays.copyOf(arr, ne);
                }
            }
        }
        arr[num] = o;

    }

    public static void pop(){
        for (int i = 1; i < size; i++) {
            if(arr[size-i]!=null){
                arr[size-i] = null;
                break;
            }
        }
    }



}
