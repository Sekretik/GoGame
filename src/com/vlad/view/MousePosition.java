package com.vlad.view;

import com.vlad.gamelogic.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MousePosition implements MouseListener {
    private Game game;

    public MousePosition(Game game){
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        game.addStone(x,y);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
