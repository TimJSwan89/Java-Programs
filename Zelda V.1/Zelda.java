import java.awt.*;
import javax.swing.*;
import java.applet.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;

public class Zelda extends JApplet
{   
    int key, x = 275, y = 275, xScroll, yScroll, attack;
    boolean upKey, downKey, leftKey, rightKey, spaceKey;
    Image image, mapImage;
    Graphics graphics, mapGraphics;
    Random rand = new Random();
    int[][] map = new int[50][50];
    public void init()
    {
        setSize(600,600);
        mapImage = createImage(2500, 2500);        
        mapGraphics = mapImage.getGraphics();
        for (int a = 0; a < 50; a++)
            for (int b = 0; b < 50; b++)
            {
                map[a][b] = rand.nextInt(10);
                if (map[a][b] == 0)
                    mapGraphics.setColor(Color.black);
                else
                    mapGraphics.setColor(Color.white);
                mapGraphics.fillRect(a * 50,b * 50,50,50);    
            }                
        image = createImage(getSize().width, getSize().height);
        graphics = image.getGraphics();
    }
    public void paint(Graphics g)
    {
        getKey();
        moveAll();
        checkAll();
        printAll();
        g.drawImage(image, 0, 0, null);
        repaint();
    }
    public void moveAll()
    {
        // -----------
        // Moves Link:
        // -----------
        int xOld = x;
        int yOld = y;
        if (upKey)
            if (yScroll > 0 && y < 275)
                yScroll = yScroll - 1;
            else
                y = y - 1;
        if (downKey)
            if (yScroll < 1900 && y > 275)
                yScroll = yScroll + 1;
            else
                y = y + 1;
        if (leftKey)
            if (xScroll > 0 && x < 275)
                xScroll = xScroll - 1;
            else
                x = x - 1;
        if (rightKey)
            if (xScroll < 1900 && x > 275)
                xScroll = xScroll + 1;
            else
                x = x + 1;
        if (attack == 0)
        {
            if (spaceKey)
                attack = 12;
        } else
            attack = attack - 1;
        //if (map[x][y] == 0)
        //{
        //    x = xOld;
        //    y = yOld;
        //}
        // -------------
        // Moves arrows:
        // -------------
                
        // --------------
        // Moves enemies:
        // --------------

    }
    public void checkAll()
    {
        
    }
    public void printAll()
    {
        graphics.drawImage(mapImage,0,0,599,599,xScroll,yScroll,xScroll + 599,yScroll + 599,null);
        graphics.setColor(Color.orange);
        graphics.fillRect(x,y,50,50);
    }
    public void getKey()
    {
        addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent evt)
            {
                key = evt.getKeyCode();
                switch(key)
                {
                    case KeyEvent.VK_UP:
                    {
                        upKey = true;
                        break;
                    }
                    case KeyEvent.VK_DOWN:
                    {
                        downKey = true;
                        break;
                    }
                    case KeyEvent.VK_LEFT:
                    {
                        leftKey = true;
                        break;
                    }
                    case KeyEvent.VK_RIGHT:
                    {
                        rightKey = true;
                        break;
                    }
                    case KeyEvent.VK_P:
                    {
                        rightKey = true;
                        break;
                    }
                    case KeyEvent.VK_SPACE:
                    {
                        spaceKey = true;
                        break;
                    }
                }
            }
            public void keyReleased(KeyEvent evt)
            {
                key = evt.getKeyCode();
                switch(key)
                {
                    case KeyEvent.VK_UP:
                    {
                        upKey = false;
                        break;
                    }
                    case KeyEvent.VK_DOWN:
                    {
                        downKey = false;
                        break;
                    }
                    case KeyEvent.VK_LEFT:
                    {
                        leftKey = false;
                        break;
                    }
                    case KeyEvent.VK_RIGHT:
                    {
                        rightKey = false;
                        break;
                    }
                    case KeyEvent.VK_P:
                    {
                        rightKey = false;
                        break;
                    }
                    case KeyEvent.VK_SPACE:
                    {
                        spaceKey = false;
                        break;
                    }
                }
            }
        });
    }
}