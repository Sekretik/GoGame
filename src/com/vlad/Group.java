package com.vlad;

import java.util.ArrayList;

public class Group {
    public ArrayList<Stone> stones = new ArrayList<>();
    public int lives;
    public int id;

    public void chekLives(){
        lives = 0;
        for (Stone s:stones) {
            lives += s.lives;
        }
    }
}
