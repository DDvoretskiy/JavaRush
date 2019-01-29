package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();
    public static Map<String, String> map = new LinkedHashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Path file = Paths.get(bufferedReader.readLine());
        List<String> string = Files.readAllLines(file);
        List<String> words = new ArrayList<>();
        for (String str :
                string) {
            for (String word :
                    str.split(" ")) {
                words.add(word);
            }
        }
        String str1 = "", str2 = "";
        for (int i = 0; i < words.size() - 1; i++) {
            str1 = words.get(i);
            if (!((map.containsKey(str1))||map.containsKey(reverse(str1)))) {
                for (int j = i + 1; j < words.size(); j++) {
                    str2 = reverse(words.get(j));
                    if (str1.equals(str2)) {
                        map.put(str1, reverse(str2));
                    }
                }
            }
        }
        for (Map.Entry<String, String> fin :
                map.entrySet()) {
            Pair pair = new Pair();
            pair.first = fin.getKey();
            pair.second = fin.getValue();
            result.add(pair);
        }
        System.out.println(result);

    }
    public static String reverse(String string){
        return new StringBuilder(string).reverse().toString();
    }

    public static class Pair {
        String first;
        String second;

        public Pair() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null && second != null ? second :
                            second == null && first != null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }
}

