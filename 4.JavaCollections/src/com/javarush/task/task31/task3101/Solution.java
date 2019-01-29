package com.javarush.task.task31.task3101;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
Проход по дереву файлов
*/
public class Solution extends SimpleFileVisitor<Path> {
    public static ArrayList<Path> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        final Solution solution = new Solution();
        Files.walkFileTree(Paths.get(args[0]), solution);
        Path newPath = Paths.get(args[1]+args[1]);
        String newName = "allFilesContent.txt";
        FileUtils.renameFile(newPath.toFile(), Paths.get(newName).toFile());
        Collections.sort(list, Comparator.comparing(Path::toString));
        FileOutputStream fileOutputStream = new FileOutputStream(newPath.toFile());

        for (Path file : list) {
            FileInputStream fileInputStream = new FileInputStream(file.toFile());
            while (fileInputStream.available() > 0) {
                fileOutputStream.write(fileInputStream.read());
            }
            fileOutputStream.write(System.lineSeparator().getBytes());
            fileOutputStream.flush();

            fileInputStream.close();
        }
        fileOutputStream.close();
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
        if (Files.size(path) > 50) {
            FileUtils.deleteFile(path.toFile());
        } else {
            list.add(path);
        }
        return FileVisitResult.CONTINUE;
    }

    public static void deleteFile(File file) {
        if (!file.delete()) System.out.println("Can not delete file with name " + file.getName());
    }
}
