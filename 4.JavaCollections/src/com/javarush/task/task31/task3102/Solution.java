package com.javarush.task.task31.task3102;


import java.io.IOException;
        import java.nio.file.*;
        import java.nio.file.attribute.BasicFileAttributes;

class MyFileVisitor extends SimpleFileVisitor {
    public FileVisitResult visitFile(Path path,
                                     BasicFileAttributes fileAttributes) {
        System.out.println("file name:" + path.getFileName());
        return FileVisitResult.CONTINUE;
    }

    public FileVisitResult preVisitDirectory(Path path,
                                             BasicFileAttributes fileAttributes) {
        System.out.println("Directory name:" + path);
        return FileVisitResult.CONTINUE;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException{

        Path pathSource = Paths.get("C:\\JavaRushTasks\\out");
        System.out.println(pathSource.toString());
        try {
            Files.walkFileTree(pathSource, new MyFileVisitor());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}