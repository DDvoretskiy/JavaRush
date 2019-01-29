package com.javarush.task.task20.task2017;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.security.SecureRandom;

/* 
Десериализация
*/
public class Solution {
    public A getOriginalObject(ObjectInputStream objectStream) {
        A a = new A();
        try {
            a = (A) objectStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return a;
    }

    public class A implements Serializable {
    }

    public class B extends A {
        public B() {
            System.out.println("inside B");
        }
    }

    public static void main(String[] args) {

    }
}
