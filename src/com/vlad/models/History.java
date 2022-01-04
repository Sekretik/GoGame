package com.vlad.models;

import java.util.ArrayList;

public class History {
    private ArrayList<ArrayList<Stone>> history = new ArrayList<>();

    public ArrayList<Stone> get(int turn){
        return history.get(turn-1);
    }

    public void add(ArrayList<Stone> stones){
        history.add(stones);
    }

    public void back(){
        history.remove(history.size()-1);
    }

    public boolean isEqual(int turnA, int turnB){
        boolean bool = true;
        for (int i = 0; i < history.get(turnA-1).size();i++){
            if(history.get(turnA-1).size() != history.get(turnB-1).size() || (history.get(turnA-1).get(i).x != history.get(turnB-1).get(i).x && history.get(turnA-1).get(i).y != history.get(turnB-1).get(i).y)){
                bool = false;
            }
        }
        return bool;
    }
}
