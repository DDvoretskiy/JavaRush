package com.javarush.task.task28.task2806;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* Знакомство с Executors
1. В методе main создай фиксированный пул из 5 трэдов используя класс Executors.
2. В цикле отправь на исполнение в пул 10 тасок Runnable.
3. У каждой таски в методе run вызови метод doExpensiveOperation с порядковым номером таски начиная с 1, см. пример вывода
4. Запрети добавление новых тасок на исполнение в пул (метод shutdown)
5. Дай экзэкьютору 5 секунд на завершение всех тасок (метод awaitTermination и параметр TimeUnit.SECONDS)
Не должно быть комментариев кроме приведенного output example
*/
public class Solution {
    private static volatile int id = 1;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(5);

//2 помещаем в него задачу для выполнения
        Future<String> task = service.submit(() -> {
            return "Amigo";

        });

//3 ждем пока задача выполнится
        while (!task.isDone()) {
            System.out.println("Working");;
        }

        task = service.submit(() -> {
            return "Amigo";

        });
        while (!task.isDone()) {
            System.out.println("Working");;
        }

//4 пробуем получить результат задачи
//получим или результат или исключение, если оно было при выполнении задачи
        try {
            System.out.println("Развернутая строка : " + task.get());
        } catch (Exception ie) {
            ie.printStackTrace(System.err);
        }

//5 останавливаем ThreadPool.
        service.shutdown();
    }
}