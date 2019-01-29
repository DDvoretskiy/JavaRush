package com.javarush.task.task20.task2002;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or adjust outputStream/inputStream according to your file's actual location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File fileName = new File("d:/1.txt");
            OutputStream outputStream = new FileOutputStream(fileName);
            InputStream inputStream = new FileInputStream(fileName);

            JavaRush javaRush = new JavaRush();

            User user1 = new User();
            user1.setFirstName("Alex");
            user1.setLastName("Ilyenko");
            user1.setBirthDate(new Date(1989, 3, 15));
            user1.setMale(true);
            user1.setCountry(User.Country.UKRAINE);

            User user2 = new User();
            user2.setFirstName("Bart");
            user2.setBirthDate(new Date(1989, 3, 15));
            user2.setCountry(User.Country.OTHER);

            User user3 = new User();
            user3.setLastName("Jolie");
            user3.setMale(false);

            javaRush.users.add(user1);
            javaRush.users.add(user2);
            javaRush.users.add(user3);
         //   System.out.println(User.Country.valueOf("Ukraine"));
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            for (int i = 0; i < 3; i++) {
                System.out.println(javaRush.users.get(i).equals(loadedObject.users.get(i)));
            }



            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            PrintWriter printWriter = new PrintWriter(outputStream);
            if (!users.isEmpty()) {
                for (User user :
                        users) {
                    printWriter.println("@");
                    if (user.getFirstName() != null)
                        printWriter.println(user.getFirstName());
                    else printWriter.println("-");
                    if (user.getLastName() != null)
                        printWriter.println(user.getLastName());
                    else printWriter.println("-");
                    if (user.getBirthDate() != null)
                        printWriter.println(user.getBirthDate().getTime());
                    else printWriter.println("-");
                    if (user.isMale())
                        printWriter.println("male");
                    else printWriter.println("-");
                    if (user.getCountry() != null)
                        printWriter.println(user.getCountry().getDisplayName());
                    else printWriter.println("-");
                }
            }
            printWriter.close();
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            User user;
            while ((line = bufferedReader.readLine())!=null) {
                user = new User();
                line = bufferedReader.readLine();
                if (!line.equals("-")) user.setFirstName(line);
                line = bufferedReader.readLine();
                if (!line.equals("-")) user.setLastName(line);
                line = bufferedReader.readLine();
                if (!line.equals("-")) user.setBirthDate(new Date(Long.parseLong(line)));
                line = bufferedReader.readLine();
                if (line.equals("male")) user.setMale(true);
                line = bufferedReader.readLine();
                if (!line.equals("-")) user.setCountry(User.Country.valueOf(line.toUpperCase()));
                users.add(user);
            }
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
