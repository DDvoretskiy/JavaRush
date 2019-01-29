package com.javarush.task.task20.task2009;

import java.io.*;

/*
Как сериализовать static?
*/
public class Solution {
    public static class ClassWithStatic implements Serializable{
        public static String staticString = "This is a static test string";
        public int i;
        public int j;
        public static void SerializeStaticState(ObjectOutputStream outputStream) throws IOException{
            outputStream.writeObject(staticString);
        }
        public static void DeserializeStaticState(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException{
            staticString = (String) objectInputStream.readObject();
        }

        @Override
        public String toString() {
            return "i= "+i+"\n"+"j= "+j+"\n"+"staticString= "+staticString;
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ClassWithStatic classWithStatic = new ClassWithStatic();
        classWithStatic.i = 1;
        classWithStatic.j = 35;
        ClassWithStatic.staticString = "Статическая переменная";
        System.out.println(classWithStatic);
        FileOutputStream byteArrayOutputStream = new FileOutputStream("d:/test1");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

        objectOutputStream.writeObject(classWithStatic);
        ClassWithStatic.SerializeStaticState(objectOutputStream);
        byteArrayOutputStream.close();
        objectOutputStream.close();
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("d:/test1"));
        ClassWithStatic readClassWithStatic = (ClassWithStatic) objectInputStream.readObject();
        ClassWithStatic.DeserializeStaticState(objectInputStream);


        System.out.println(readClassWithStatic);
    }
}
