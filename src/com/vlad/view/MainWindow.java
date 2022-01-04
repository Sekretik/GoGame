package com.vlad.view;

import com.vlad.gamelogic.Game;

import javax.swing.*;

public class MainWindow extends JFrame {

    private int sizeBtwLine = 50;

    public MainWindow(int lines, Game game){
        setFocusable(true);
        setTitle("GoGame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(sizeBtwLine * lines + 250,sizeBtwLine * lines + sizeBtwLine/2+13);
        setLocation(500,100);
        add(new GamePanel(lines,sizeBtwLine,game));
        setResizable(false);
        setVisible(true);
    }
}
