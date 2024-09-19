package main;

import java.awt.*;

public abstract class Block {
    int currentState = 0;
    Color color;
    char id = ' ';
    int posX;
    int posY;

    int[][] states;

    Block(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;

    }
    public void changeState() {
        if(this.currentState == this.getStates().length-1) {
            this.currentState = 0;
            return;
        }
        this.currentState++;
    }
    public  Color getColor() {
        return this.color;
    }
    public int[][] getStates() {
        return this.states;
    }
    public char getId() {
        return this.id;
    }

}

class LineBlock extends Block {
    Color color = Color.CYAN;
    char id = 'L';
    int[][] states = {
            {0, 0, 0, -1, 0, -2, 0, -3},
            {0, 0, -1, 0, -2, 0, -3, 0}
    };
    LineBlock(int posX, int posY) {
        super(posX, posY);
    }
    @Override
    public int[][] getStates() {
        return this.states;
    }
    @Override
    public  Color getColor() {
        return this.color;
    }
    @Override
    public char getId() {
        return this.id;
    }
}
