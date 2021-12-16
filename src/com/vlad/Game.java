package com.vlad;

import java.util.ArrayList;

public class Game {

    private GamePanel panel;
    private ArrayList<Stone> stones = new ArrayList<>();
    private ArrayList<Stone> voidStones = new ArrayList<>();
    private int lines = 9;
    private int turn = 1;

   public Game(){
       for(int i = 0; i < lines; i ++){
           voidStones.add(new Stone(i,-1,StoneColor.VOID));
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

        StoneColor color;
        if(turn%2==1){
            color = StoneColor.BLACK;
        }else color = StoneColor.WHITE;

        if(x >= 0 && y >= 0 && x <= (lines-1) && y <= (lines-1) && !isFieldOccupied(x,y)) {
            Stone stone = new Stone(x, y, color);
            stones.add(stone);
            checkNeighbors();
            stone.checkStatus(new ArrayList<>());
            kill();
            panel.setArray(stones);
            turn += 1;
        }
    }

    private void kill() {
       for(int i =0; i< stones.size();i++){
           if(stones.get(i).getStatus() == StoneStatus.DEAD){
               stones.remove(i);
               i-=1;
           }
       }
    }

    private void checkNeighbors() {
       for(Stone s1 : stones){
           if(s1.getTeam() != StoneColor.VOID) {
               int x1 = s1.getX();
               int y1 = s1.getY();
               for (Stone s2 : stones) {
                   int x2 = s2.getX();
                   int y2 = s2.getY();

                   if (x1 - 1 == x2 && y1 == y2) {
                       s1.neighbors[1] = s2;
                   }
                   if (x1 + 1 == x2 && y1 == y2) {
                       s1.neighbors[3] = s2;
                   }
                   if (y1 - 1 == y2 && x1 == x2) {
                       s1.neighbors[0] = s2;
                   }
                   if (y1 + 1 == y2 && x1 == x2) {
                       s1.neighbors[2] = s2;
                   }
               }
           }
       }
    }

    private boolean isFieldOccupied(int x, int y){
       boolean bool = false;
        for (Stone s : stones) {
            if(s.getX() == x && s.getY() == y){
                bool = true;
            }
        }
       return bool;
    }
}
