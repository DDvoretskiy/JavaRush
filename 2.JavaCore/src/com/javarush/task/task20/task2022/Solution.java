package com.javarush.task.task20.task2022;

import java.io.*;

/* 
Переопределение сериализации в потоке
*/
public class Solution implements Serializable, AutoCloseable {
    transient private FileOutputStream stream;
    private String fileName;

    public Solution(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        this.stream = new FileOutputStream(fileName);
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();

    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        stream = new FileOutputStream(this.fileName,true);
    }


    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) throws Exception{
        Solution sol = new Solution("2writeobject");
        sol.writeObject("2 write object");

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object"));
        oos.writeObject(sol);
        oos.flush();
        oos.close();

        ObjectInputStream oIS = new ObjectInputStream(new FileInputStream("object"));
        Solution loadedSol = (Solution)oIS.readObject();
        loadedSol.writeObject("5 write object");

    }
}
