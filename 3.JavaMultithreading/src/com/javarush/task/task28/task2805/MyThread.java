package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {
    private ThreadGroup threadGroup;
    private static AtomicInteger priorNumber = new AtomicInteger(0);

    public MyThread() {

        init();
    }

    public MyThread(Runnable target) {
        super(target);
        init();
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        this.threadGroup = group;
        init();
    }

    public MyThread(String name) {
        super(name);
        init();
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        this.threadGroup = group;
        init();
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        init();
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        this.threadGroup = group;
        init();
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        this.threadGroup = group;
        init();
    }

    private void init() {
        priorNumber.incrementAndGet();
        priorNumber.compareAndSet(11, 1);
        int newPrior = priorNumber.get();
        if (threadGroup != null && newPrior > threadGroup.getMaxPriority())
            newPrior = threadGroup.getMaxPriority();
        this.setPriority(newPrior);
    }
}
