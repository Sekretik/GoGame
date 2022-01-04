package com.vlad.gamelogic;

import com.vlad.models.History;
import com.vlad.models.Stone;
import com.vlad.models.StoneColor;
import com.vlad.models.StoneStatus;
import com.vlad.view.GamePanel;
import com.vlad.view.MainWindow;

import java.util.ArrayList;

public class Game {

    private GamePanel panel;
    private ArrayList<Stone> stones = new ArrayList<>();
    private int lines;
    private int turn = 0;
    History history = new History();

    public Game(int lines){
        this.lines = lines;
        ArrayList<Stone> voidStones = new ArrayList<>();
        for(int i = 0; i < lines; i ++){
           voidStones.add(new Stone(i,-1, StoneColor.VOID));
           voidStones.add(new Stone(i,lines,StoneColor.VOID));
           voidStones.add(new Stone(-1,i,StoneColor.VOID));
           voidStones.add(new Stone(lines,i,StoneColor.VOID));
       }
       stones.addAll(voidStones);

       new MainWindow(lines,this);
    }

    public void setPanel(GamePanel panel){
       this.panel = panel;
    }

    public void addStone(int x,int y){
        x = (x - x%50)/50;
        y = (y - y%50)/50;

        if(x >= 0 && y >= 0 && x <= (lines-1) && y <= (lines-1) && !isFieldOccupied(x,y)) {
            turn += 1;

            StoneColor color;
            if(turn%2==1){
                color = StoneColor.BLACK;
            }else color = StoneColor.WHITE;

            Stone stone = new Stone(x, y, color);
            stones.add(stone);
            stone.group.id = turn;

            history.add(new ArrayList<>());
            history.get(turn).addAll(stones);

            if(turn > 2 && history.isEqual(turn,turn-2)){
                turn -=1;
                history.back();
                stones = new ArrayList<>(history.get(turn));
            }
        }
        chekLives();
        panel.setArray(stones);
    }

    private void chekLives(){
        for(Stone s1 : stones) {
            for (Stone s2 : stones) {
                if (s1.y - 1 == s2.y && s1.x == s2.x) {
                    s1.neighbors[0] = s2;
                    s2.neighbors[2] = s1;
                    unnamed(s1,s2);
                }
                if (s1.x - 1 == s2.x && s1.y == s2.y) {
                    s1.neighbors[1] = s2;
                    s2.neighbors[3] = s1;
                    unnamed(s1,s2);
                }
                if (s1.y + 1 == s2.y && s1.x == s2.x) {
                    s1.neighbors[2] = s2;
                    s2.neighbors[0] = s1;
                    unnamed(s1,s2);
                }
                if (s1.x + 1 == s2.x && s1.y == s2.y) {
                    s1.neighbors[3] = s2;
                    s2.neighbors[1] = s1;
                    unnamed(s1,s2);
                }
            }
            s1.chekLives();
        }
        for (Stone s1:stones) {
            if(s1.team != StoneColor.VOID) s1.group.chekLives();
            if(s1.group.lives <= 0 && s1.team != StoneColor.VOID && s1.group.id != turn) s1.status = StoneStatus.DEAD;
        }
        for (int i = 0; i < stones.size(); i++) {
            if(stones.get(i).status == StoneStatus.DEAD){
                for(int j = 0; j < stones.get(i).neighbors.length; j++){
                    if(stones.get(i).neighbors[j] != null) {
                        for (int k = 0; k < stones.get(i).neighbors[j].neighbors.length; k++) {
                            if (stones.get(i).neighbors[j].neighbors[k] == stones.get(i)) {
                                stones.get(i).neighbors[j].neighbors[k] = null;
                            }
                        }
                    }
                }
                stones.remove(i);
                i-=1;
            }
        }
    }

    private void unnamed(Stone s1, Stone s2){
        if(s1.team == s2.team){
            s1.setGroup(s2.group, new ArrayList<>());
        }
    }

    private boolean isFieldOccupied(int x, int y){
       boolean bool = false;
        for (Stone s : stones) {
            if(s.x == x && s.y == y){
                bool = true;
            }
        }
       return bool;
    }
}
