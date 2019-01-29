package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {

    public String getPartOfName() {
        return partOfName;
    }

    public String getPartOfContent() {
        return partOfContent;
    }

    public int getMinSize() {
        return minSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    private String partOfName;
    private String partOfContent;
    private int minSize;
    private int maxSize;
    private List<Path> foundFiles = new ArrayList<>(0);

    ArrayList<Integer> options = new ArrayList<Integer>() {{
        add(null);
        add(null);
    }};

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
        options.set(0, minSize);
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
        options.set(1, maxSize);
    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }


    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        boolean isTrue = true;

        if (partOfName != null) {

            if (!file.getFileName().toString().contains(partOfName)) {
                isTrue = false;
            }
        }

        if (options.get(0) != null) {
            if (Files.size(file) < minSize)
                isTrue = false;

        }

        if (options.get(1) != null) {
            if (Files.size(file) > maxSize)
                isTrue = false;

        }

        if (partOfContent != null) {
            String s = new String(Files.readAllBytes(file));
            if (!s.contains(partOfContent))
                isTrue = false;
        }


        if (isTrue)
            foundFiles.add(file);

        return FileVisitResult.CONTINUE;
    }
}

