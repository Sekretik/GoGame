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
        if(x >= 0 && y >= 0 && x <= (lines-1) && y <= (lines-1) && !isFieldOccupied(x,y)) {
            Stone stone = new Stone(x, y, color);
            stones.add(stone);
            panel.setArray(stones);
            chekLives(stone);
            turn += 1;
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

    public void chekLives(Stone stone){
        for (Stone s1 : stones) {
            s1.setLives(4);
            for (Stone s2 : stones) {
                if(s1.getX() == s2.getX()-1 && s1.getY() == s2.getY()) {
                    s1.setLives(s1.getLives()-1);
                }
                if(s1.getX() == s2.getX()+1 && s1.getY() == s2.getY()) {
                    s1.setLives(s1.getLives()-1);
                }
                if(s1.getY() == s2.getY()-1 && s1.getX() == s2.getX()) {
                    s1.setLives(s1.getLives()-1);
                }
                if(s1.getY() == s2.getY()+1 && s1.getX() == s2.getX()) {
                    s1.setLives(s1.getLives()-1);
                }
            }
        }
        for(int i = 0;i< stones.size();i++){
            if(stones.get(i).getLives()<=0 && stone != stones.get(i)){
                stones.remove(i);
                i-=1;
            }
        }
    }
}
