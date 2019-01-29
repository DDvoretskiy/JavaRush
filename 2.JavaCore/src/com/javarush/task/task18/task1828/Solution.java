package com.javarush.task.task18.task1828;

/* 
Прайсы 2
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {

    public static void main(String[] args) throws Exception {
        int id = 0;
        String idstr = "";
        ArrayList<String> massiv = new ArrayList<String>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String filename = bufferedReader.readLine();
        bufferedReader.close();
        BufferedReader fileInputStream = new BufferedReader(new FileReader(new File(filename)));
        while (fileInputStream.ready()) {
            massiv.add(fileInputStream.readLine());
        }
        fileInputStream.close();
        if (args[0].equals("-d")) {
            massiv.remove(getId(massiv, args[1]));
        } else if (args[0].equals("-u")) {
            int i;
            if ((  i = getId(massiv, args[1])) >= 0){
                massiv.set(i, (new StringBuilder().append(Spaces(args[1], 8)).append(Spaces(args[2], 30)).append(Spaces(args[3], 8)).append(Spaces(args[4], 4)).toString()));
            }
        }

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(filename)));
        for (String s :
                massiv) {
            bufferedWriter.write(s + "\n");
        }
        bufferedWriter.close();
    }

    public static int getId(ArrayList<String> list, String str) {
        int id = 0, numb = -1;
        for (String s :
                list) {
            if (s.startsWith(str)) {
                numb = id;
            }
            id++;
        }
        return numb;
    }

    public static String Spaces(String inName, int count) {
        String trueName;
        if (inName.length() > count)
            trueName = inName.substring(0, count);
        else {
            String s = "";
            for (int i = 0; i < (count - inName.length()); i++)
                s = s + " ";
            trueName = inName + s;
        }
        return trueName;
    }

}
