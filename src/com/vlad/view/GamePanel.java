package com.vlad.view;

import com.vlad.gamelogic.Game;
import com.vlad.models.Stone;
import com.vlad.models.StoneColor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private Graphics2D g;
    private ArrayList<Stone> stones = new ArrayList<>();
    private Game game;
    private int lines;
    private int sizeBtwLine;

    public GamePanel(int lines,int sizeBtwLine,Game game){
        setSize(sizeBtwLine * lines,sizeBtwLine * lines);
        addMouseListener(new MousePosition(game));
        this.game = game;
        this.lines = lines;
        this.sizeBtwLine = sizeBtwLine;
        game.setPanel(this);
    }

    public void Board(){
        g.setColor(new Color(127,72,41));
        g.fillRect(0,0,lines*sizeBtwLine,lines*sizeBtwLine);
        g.setColor(Color.black);

        int x1= sizeBtwLine/2, y1= sizeBtwLine/2, x2= sizeBtwLine/2, y2= sizeBtwLine/2+((lines-1) * sizeBtwLine);

        for (int i = 0; i < lines;i++){
            g.drawLine(x1,y1,x2,y2);
            x1+=sizeBtwLine;
            x2+=sizeBtwLine;
        }

        x1= sizeBtwLine/2;
        y1= sizeBtwLine/2;
        x2= sizeBtwLine/2+((lines-1) * sizeBtwLine);
        y2= sizeBtwLine/2;

        for (int i = 0; i < lines;i++){
            g.drawLine(x1,y1,x2,y2);
            y1+=sizeBtwLine;
            y2+=sizeBtwLine;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        this.g = (Graphics2D) g;
        Board();
        drawStones();
    }

    public void drawStones() {
        for (Stone s : stones) {
            if (s.team != StoneColor.VOID) {
                if (s.team == StoneColor.WHITE) {
                    g.setColor(Color.white);
                }
                g.fillOval((s.x * sizeBtwLine), (s.y * sizeBtwLine), sizeBtwLine, sizeBtwLine);

                g.setColor(Color.blue);
                //g.drawString(String.valueOf(s.lives),(s.x * sizeBtwLine) + sizeBtwLine/2,(s.y * sizeBtwLine) + sizeBtwLine/2);
                //g.drawString(String.valueOf(s.group.id),(s.x * sizeBtwLine) + sizeBtwLine/2,(s.y * sizeBtwLine) + sizeBtwLine/2);
                //g.drawString(String.valueOf(s.group.lives),(s.x * sizeBtwLine) + sizeBtwLine/2,(s.y * sizeBtwLine) + sizeBtwLine/2);

                g.setColor(Color.black);
            }
        }
    }

    public void setArray(ArrayList<Stone> array){
        stones = array;
        repaint();
    }
}