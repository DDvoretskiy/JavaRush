package com.javarush.task.task23.task2305;

/* 
Inner
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution[] array = new Solution[2];
        Solution solution = null;
        for (int i = 0; i < 2; i++) {
            solution = new Solution();
            solution.innerClasses[0] = solution.new InnerClass();
            solution.innerClasses[1] = solution.new InnerClass();
            array[i] = solution;
        }
            return array;
        }

    public static void main(String[] args) {

    }
}
