package homework;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


public class Work1 {

    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, CloneNotSupportedException {

        //测试多线程
        Th1 t1 = new Th1();
        t1.start();
        Th2 t2 = new Th2();
        t2.start();

        //测试序列化
        Fourth f1 = Fourth.Four.INSTANCE.getFourth();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("doubleLock_obj"));
        oos.writeObject(f1);
        File file = new File("doubleLock_obj");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Fourth f2 = (Fourth) ois.readObject();
        System.out.println(f1);
        System.out.println(f2);
        System.out.println(f1 == f2);

        //测试反射
        Constructor<Fifth> constructor=Fifth.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Fourth f3=Fourth.Four.INSTANCE.getFourth();
        Fourth f4=Fourth.Four.INSTANCE.getFourth();
        /*Fifth f1 = constructor.newInstance();
        Fifth f2 = constructor.newInstance();*/
        System.out.println(f3);
        System.out.println(f4);
        System.out.println(f3==f4);

        //测试克隆
        Fourth f5 = Fourth.Four.INSTANCE.getFourth();
        Fourth f6 = (Fourth) f5.clone();
        System.out.println(f5==f6);

    }

}
class Th1 extends Thread{
    @Override
    public void run() {
        Fourth f1 = Fourth.Four.INSTANCE.getFourth();
        System.out.println(f1);
    }
}

class Th2 extends Thread{
    @Override
    public void run() {
        Fourth f2 = Fourth.Four.INSTANCE.getFourth();
        System.out.println(f2);
    }
}
//防多线程
class First{

    private First(){

    }
    private static First first;

    public static First getFirst() {
        if (first == null) {
            synchronized (First.class) {
                if (first == null) {
                    first = new First();
                    return first;
                }
            }
        }
        return first;
    }
}
//防多线程、序列化
class Second implements Serializable{

    private Second(){

    }
    private static Second second;

    public static Second getSecond() {
        if (second == null) {
            synchronized (Second.class) {
                if (second == null) {
                    second = new Second();
                    return second;
                }
            }
        }
        return second;
    }

    private Object readResolve(){
        return second;
    }
    
}
//防序列化、反射
class Third implements Serializable{

    private static Third third = new Third();
    private Third(){
        if(third != null) {

            throw new RuntimeException();

        }
    }
    public static Third getThird() {
        if (third == null) {
            synchronized (Third.class) {
                if (third == null) {
                    third = new Third();
                    return third;
                }
            }
        } 
        return third;
    }

    private Object readResolve(){
        return third;
    }

}
//枚举防多线程、序列化、反射、克隆
class Fourth implements Serializable,Cloneable{
    private static Fourth fourth;
    enum Four{
        INSTANCE;
        
        Four() {
            fourth = new Fourth();
        }
        public Fourth getFourth(){
            return fourth;
        }

    }
    private Object readResolve(){
        return fourth;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("cloning");
    }
}

class Fifth  implements Serializable,Cloneable{

    private Fifth() {

    }
    private static class Inner{
        private static Fifth inner = new Fifth();
    }
    public static Fifth getFifth(){
        return Inner.inner;
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("cloning");
    }
    private Object readResolve(){
        return Inner.inner;
    }
}


