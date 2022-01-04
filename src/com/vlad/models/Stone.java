package com.vlad.models;

import java.util.ArrayList;

public class Stone {
    public int x;
    public int y;
    public StoneColor team;
    public StoneStatus status = StoneStatus.UNCHECKED;
    public Stone[] neighbors = new Stone[4];
    public Group group = new Group();
    public int lives = 4;

    public Stone(int x, int y,StoneColor team) {
        this.x = x;
        this.y = y;
        this.team = team;
        this.group.stones.add(this);
        for(int i = 0; i < 4;i++){
            neighbors[i] = null;
        }
    }

    public void chekLives(){
        lives = 4;
        for (Stone s:neighbors) {
            if(s != null) lives -= 1;
        }
    }

    public void setGroup(Group g, ArrayList<Stone> history){
        history.add(this);
        group = g;
        if(!g.stones.contains(this)) g.stones.add(this);
        for (Stone n:neighbors) {
            if(n != null && n.team == this.team && !history.contains(n)) n.setGroup(g,history);
        }
    }
}
