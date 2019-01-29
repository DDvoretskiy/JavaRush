package com.javarush.task.task18.task1812;

import java.io.*;

/*
Расширяем AmigoOutputStream
*/

public class QuestionFileOutputStream implements AmigoOutputStream {
    private AmigoOutputStream file;

    public QuestionFileOutputStream(AmigoOutputStream file) {
        this.file = file;
    }

    @Override
    public void flush() throws IOException {
        file.flush();
    }

    @Override
    public void write(int b) throws IOException {
        file.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        file.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        file.write(b, off, len);
    }

    @Override
    public void close() throws IOException {
        System.out.println("Вы действительно хотите закрыть поток? Д/Н");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String res = bufferedReader.readLine();
        if (res.equals("Д")) file.close();
        bufferedReader.close();

    }

}

