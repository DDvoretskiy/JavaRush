package com.javarush.task.task17.task1707;


public class IMF {

    private static IMF imf;

    public static IMF getFund() {
        synchronized (IMF.class) {//add your code here - добавь код тут
            if (imf == null)
                imf = new IMF();
        }
        return imf;
    }

    private IMF() {
    }
    public void runtest(Object ob){
        test();

    }
    public void test(){
        System.out.println("test from IMF");
    }
}
