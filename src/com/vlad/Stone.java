package com.vlad;

import java.util.ArrayList;

public class Stone {
    private int x;
    private int y;
    private StoneColor team;
    private StoneStatus status = StoneStatus.UNCHECKED;
    public Stone[] neighbors = new Stone[4];

    public Stone(int x, int y,StoneColor team) {
        this.x = x;
        this.y = y;
        this.team = team;
        for(int i = 0; i < 4;i++){
            neighbors[i] = null;
        }
    }

    public void checkStatus(ArrayList<Stone> theWay){
        StoneStatus status = StoneStatus.DEAD;
        theWay.add(this);
        for(Stone stone : neighbors){
            if(stone == null){
                status = StoneStatus.ALIVE;
            }else if(stone.getTeam() != StoneColor.VOID) {
                if (stone.getTeam() == team && !theWay.contains(stone)) {
                    stone.checkStatus(theWay);
                    status = stone.getStatus();
                } else if (stone.getTeam() != team && !theWay.contains(stone)) {
                    stone.checkStatus(theWay);
                }
            }
        }
        this.status = status;
    }

    public StoneStatus getStatus() {
        return status;
    }

    public void setStatus(StoneStatus status) {
        this.status = status;
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
