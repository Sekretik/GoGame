package com.vlad;

import java.util.ArrayList;

public class Game {

    private GamePanel panel;
    private ArrayList<Stone> stones = new ArrayList<>();
    private int lines = 9;
    //-------------------------------
    private int turn = 1;//delete
    //-------------------------------^

   public Game(){
       new MainWindow(lines,this);
   }

   public void setPanel(GamePanel panel){
       this.panel = panel;
   }

    public void addStone(int x,int y){
        x = (x - x%50)/50;
        y = (y - y%50)/50;
        //-------------------------------
        StoneColor color;//delete
        if(turn%2==1){//delete
            color = StoneColor.BLACK;//delete
        }else color = StoneColor.WHITE;//delete
        //-------------------------------^
        if(x >= 0 && y >= 0 && x <= 8 && y <= 8 && !isFieldOccupied(x,y)) {
            Stone stone = new Stone(x, y, color);
            stones.add(stone);
            panel.addStone(stone);
            //-------------------------------
            turn += 1;//delete
            //-------------------------------^
        }
    }

    public boolean isFieldOccupied(int x, int y){
       boolean bool = false;
        for (Stone s : stones) {
            if(s.getX() == x && s.getY() == y){
                bool = true;
            }
        }
       return bool;
    }
}
