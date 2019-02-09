package com.javarush.task.task32.task3206;

import java.lang.reflect.Proxy;
import java.util.Arrays;

/*
Дженерики для создания прокси-объекта
*/
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        test(solution.getProxy(Item.class));                        //true false false
        test(solution.getProxy(Item.class, Small.class));           //true false true
        test(solution.getProxy(Item.class, Big.class, Small.class));//true true true
        test(solution.getProxy(Big.class, Small.class));            //true true true т.к. Big наследуется от Item
        test(solution.getProxy(Big.class));                         //true true false т.к. Big наследуется от Item
    }

    public <T extends Item> T getProxy(Class<T> clazz, Class<?>... clazzInterfaces) {
        ClassLoader classLoader = clazz.getClassLoader();
        Class<?>[] interfaces = Arrays.copyOf(clazzInterfaces, clazzInterfaces.length + 1);
        interfaces[clazzInterfaces.length] = clazz;
        return (T) Proxy.newProxyInstance(classLoader,interfaces,new ItemInvocationHandler());
    }

    private static void test(Object proxy) {
        boolean isItem = proxy instanceof Item;
        boolean isBig = proxy instanceof Big;
        boolean isSmall = proxy instanceof Small;

        System.out.format("%b %b %b\n", isItem, isBig, isSmall);
    }
}