package com.javarush.task.task35.task3513;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    int score, maxTile;
    private boolean isSaveNeeded = true;
    private Stack<Integer> previousScores = new Stack<>();
    private Stack<Tile[][]> previousStates = new Stack<>();

    private void saveState(Tile[][] gameTiles) {
        int score2push = this.score;
        previousScores.push(score2push);
        Tile[][] tempTiles = new Tile[4][4];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                tempTiles[i][j] = new Tile(gameTiles[i][j].value);
            }
        }
        previousStates.push(tempTiles);
        isSaveNeeded = false;
    }

    public void rollback() {
        if (!previousStates.isEmpty() && !previousScores.isEmpty()) {
            this.score = previousScores.pop();
            this.gameTiles = previousStates.pop();
        }
    }

    public Model() {
        resetGameTiles();
        this.score = 0;
        this.maxTile = 2;
    }

    private void addTile() {
        int value = Math.random() < 0.9 ? 2 : 4;
        ArrayList<Tile> emptyTiles = getEmptyTiles();
        if (emptyTiles.size() != 0) {
            int number = (int) (emptyTiles.size() * Math.random());
            emptyTiles.get(number).value = value;
        }
    }

    private ArrayList<Tile> getEmptyTiles() {
        ArrayList<Tile> list = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].isEmpty()) {
                    list.add(gameTiles[i][j]);
                }
            }
        }
        return list;
    }

    void resetGameTiles() {
        this.gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                this.gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean change = false;
        for (int k = 0; k < tiles.length - 1; k++) {
            for (int i = 0; i < tiles.length - 1; i++) {
                if (tiles[i].isEmpty() && !tiles[i + 1].isEmpty()) {
                    change = true;
                    tiles[i] = tiles[i + 1];
                    tiles[i + 1] = new Tile();
                }
            }
        }
        return change;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean change = false;
        for (int i = 0; i < tiles.length - 1; i++) {
            if (tiles[i].value == tiles[i + 1].value && !tiles[i].isEmpty()) {
                change = true;
                tiles[i].value *= 2;
                tiles[i + 1] = new Tile();
                maxTile = maxTile > tiles[i].value ? maxTile : tiles[i].value;
                score += tiles[i].value;
                compressTiles(tiles);
            }
        }
        return change;
    }

    public void left() {
        if (isSaveNeeded) saveState(this.gameTiles);
        boolean isChanged = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i])) {
                isChanged = true;
            }
        }
        if (isChanged) {
            addTile();
            isSaveNeeded = true;
        }


    }

    private void rotateToRight() {
        Tile tmp;
        for (int i = 0; i < FIELD_WIDTH / 2; i++) {
            for (int j = i; j < FIELD_WIDTH - 1 - i; j++) {
                tmp = gameTiles[i][j];
                gameTiles[i][j] = gameTiles[FIELD_WIDTH - j - 1][i];
                gameTiles[FIELD_WIDTH - j - 1][i] = gameTiles[FIELD_WIDTH - i - 1][FIELD_WIDTH - j - 1];
                gameTiles[FIELD_WIDTH - i - 1][FIELD_WIDTH - j - 1] = gameTiles[j][FIELD_WIDTH - i - 1];
                gameTiles[j][FIELD_WIDTH - i - 1] = tmp;
            }
        }
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public boolean canMove() {
        if (!getEmptyTiles().isEmpty()) return true;

        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 1; j < gameTiles.length; j++) {
                if (gameTiles[i][j].value == gameTiles[i][j - 1].value)
                    return true;
            }
        }
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 1; j < gameTiles.length; j++) {
                if (gameTiles[j][i].value == gameTiles[j - 1][i].value) return true;
            }
        }
        return false;
    }

    void right() {
        saveState(this.gameTiles);
        rotateToRight();
        rotateToRight();
        left();
        rotateToRight();
        rotateToRight();
    }

    void up() {
        saveState(this.gameTiles);
        rotateToRight();
        rotateToRight();
        rotateToRight();
        left();
        rotateToRight();
    }

    void down() {
        saveState(this.gameTiles);
        rotateToRight();
        left();
        rotateToRight();
        rotateToRight();
        rotateToRight();
    }

    public void randomMove() {
        switch (((int) (Math.random() * 100)) % 4) {
            case 0:
                left();
                break;
            case 1:
                up();
                break;
            case 2:
                right();
                break;
            case 3:
                down();
                break;
        }
    }

    public boolean hasBoardChanged() {
        int old = 0, nnew = 0;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                old += this.gameTiles[i][j].value;
                nnew += this.previousStates.peek()[i][j].value;
            }
        }
        return old != nnew ? true : false;
    }

    public MoveEfficiency getMoveEfficiency(Move move) {
        MoveEfficiency moveEfficiency;
        move.move();
        if (hasBoardChanged()) moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);
        else moveEfficiency = new MoveEfficiency(-1, 0, move);
        rollback();

        return moveEfficiency;
    }

    public void autoMove() {
        PriorityQueue<MoveEfficiency> queue = new PriorityQueue(4, Collections.reverseOrder());
        queue.add(getMoveEfficiency(this::left));
        queue.add(getMoveEfficiency(this::right));
        queue.add(getMoveEfficiency(this::up));
        queue.add(getMoveEfficiency(this::down));
        Move move = queue.peek().getMove();
        move.move();
    }
}
