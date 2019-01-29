package com.javarush.task.task25.task2508;

public class TaskManipulator implements Runnable, CustomThreadManipulator {
    private Thread threadManipulated;

    @Override
    public void start(String threadName) {
        this.threadManipulated = new Thread(this,threadName);
        this.threadManipulated.start();


    }

    @Override
    public void stop() {
        this.threadManipulated.interrupt();
    }

    @Override
    public void run() {
        try {
            while (!threadManipulated.isInterrupted()) {

                System.out.println(Thread.currentThread().getName());
                Thread.sleep(100);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
