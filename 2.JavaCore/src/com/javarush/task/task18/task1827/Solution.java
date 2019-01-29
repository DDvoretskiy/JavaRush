package com.javarush.task.task18.task1827;

/* 
Прайсы
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
        if (args[0] != "") {
            for (String s :
                    massiv) {
                if (id < Integer.valueOf(s.substring(0, 8).trim())) {
                    idstr = s.substring(0, 8);
                    id = Integer.valueOf(idstr.trim());
                }
            }
            idstr = String.valueOf(id+1) ;
            massiv.add(new StringBuilder().append(Spaces(idstr, 8)).append(Spaces(args[1], 30)).append(Spaces(args[2], 8))
                    .append(Spaces(args[3], 4)).toString());
        }
        fileInputStream.close();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(filename)));
        for (String s :
                massiv) {
            bufferedWriter.write(s+"\n");
        }
        bufferedWriter.close();
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
