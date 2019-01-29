package com.javarush.task.task31.task3112;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Загрузчик файлов
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://www.amigo.com/ship/secretPassword.txt", Paths.get("D:/MyDownloads"));
        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }
    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        URL url=new URL(urlString);
        InputStream inputStream=url.openStream();
        Path tmp=Files.createTempFile("temp-",".tmp");
        Files.copy(inputStream,tmp);
        String fileName=urlString.substring(urlString.lastIndexOf("/"));
        String dir=downloadDirectory.toString();
        Path moveTo=Paths.get(dir+fileName);
        Files.move(tmp,moveTo);
        return Paths.get(downloadDirectory.toString()+urlString.substring(urlString.lastIndexOf("/")));
    }
}