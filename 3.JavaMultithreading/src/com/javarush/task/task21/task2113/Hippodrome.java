package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hippodrome {
    public static Hippodrome game;

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    private List<Horse> horses;
    public Horse getWinner(){
        return  horses.stream().max(Horse::compare).get();
    }

    public void printWinner(){
        System.out.printf("Winner is %s!", getWinner().getName());
    }
    public void run() {
        for (int i = 0; i < 100; i++) {
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void move() {
        for (Horse horse :
                horses) {
            horse.move();
        }
    }

    public void print() {
        for (Horse horse :
                horses) {
            horse.print();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public static void main(String[] args) {
        List<Horse> horses = new ArrayList<>();
        Collections.addAll(horses, new Horse("Angela", 3, 0), new Horse("Kljacha", 3, 0), new Horse("Loshadka", 3, 0));
        game = new Hippodrome(horses);
        game.run();
        game.printWinner();
    }
}
