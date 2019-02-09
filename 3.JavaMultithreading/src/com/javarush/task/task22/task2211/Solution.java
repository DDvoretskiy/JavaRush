package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/* 
Смена кодировки
*/
public class Solution {
    static String win1251TestString = "РќР°СЂСѓС€РµРЅРёРµ РєРѕРґРёСЂРѕРІРєРё РєРѕРЅСЃРѕР»Рё?"; //only for your testing

    public static void main(String[] args) throws IOException {
      /*  Path input = Paths.get(args[0]);
        Path output = Paths.get(args[1]);
        Charset windows1251 = Charset.forName("Windows-1251");
        Charset utf8 = Charset.forName("UTF-8");
        byte[] buffer = Files.readAllBytes(input);
        String str = new String(buffer);
        buffer = str.getBytes(windows1251);
        Files.write(output,buffer);
*/
            FileInputStream inputStream=new FileInputStream(args[0]);
            FileOutputStream outputStream=new FileOutputStream(args[1]);
            byte[] buff=new byte[inputStream.available()];
            inputStream.read(buff);
            String s= new String(buff, "UTF-8");
            outputStream.write(s.getBytes("Windows-1251"));
            inputStream.close();
            outputStream.close();
        
    }
}
