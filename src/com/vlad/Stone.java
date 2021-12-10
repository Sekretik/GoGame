package com.vlad;

public class Stone {
    private int x;
    private int y;
    private StoneColor team;
    private int lives = 4;

    public Stone(int x, int y,StoneColor team) {
        this.x = x;
        this.y = y;
        this.team = team;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public StoneColor getTeam() {
        return team;
    }

    public void setTeam(StoneColor team) {
        this.team = team;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
