// =======================
// Title:    Zelda2
// Designer: Swan, Timothy
// =======================
import java.awt.*;
import javax.swing.*;
import java.applet.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;
/**
 * Zelda Guys is a game in which the player controls the character in
 * a bounded environment. \n
 * Controls: 
 *  Up Arrow: Moves character up
 *  Down Arrow: Moves character down
 *  Right Arrow: Moves character right
 *  Left Arrow: Moves character left
 * 
 * @author Timothy Swan
 * @version 4.0 (Hierarchy, enemies included)
 */
public class ZeldaGuys extends JApplet
{   
    int key, x, y, arrowDelay, speed = 4, rightWall = 2500, bottomWall = 2500, screenWidth = 600, screenHeight = 600;
    boolean upKey, downKey, leftKey, rightKey, spaceKey, pause = false;
    Image image, mapImage, linkBody;
    Graphics graphics, mapGraphics, linkGraphics;
    Random rand = new Random();
    ArrayList<Wall> walls = new ArrayList<Wall>();
    ArrayList<MobileObject> blocks = new ArrayList<MobileObject>();
    ArrayList<Computer> comps = new ArrayList<Computer>();
    ArrayList<Rectangle> all = new ArrayList<Rectangle>();
    ArrayList<Player> arrows = new ArrayList<Player>();
    Player link = new Player(105, 105, 50, 50, 0);
    public void init()
    {
        setSize(screenWidth, screenHeight);
        //setLocation(100, 20);
        linkBody = createImage(50, 50);
        linkGraphics = linkBody.getGraphics();
        Link.initialize(linkGraphics);
        mapImage = createImage(rightWall, bottomWall);        
        mapGraphics = mapImage.getGraphics();
        for (int a = 0; a < 50; a++)
            for (int b = 0; b < 50; b++)
            {
                int num = rand.nextInt(100);
                if (num < 10) // 10% chance
                {
                    mapGraphics.setColor(new Color(rand.nextInt(60),rand.nextInt(60),rand.nextInt(60)));
                    walls.add(new Wall(a * 50, b * 50, 50, 50));// rand.nextInt(21) + 30, rand.nextInt(21) + 30));
                }
                else
                {
                    mapGraphics.setColor(new Color(rand.nextInt(31) + 225,rand.nextInt(31) + 225,rand.nextInt(31) + 225));
                    if (num < 20) // 10% chance
                        blocks.add(new MobileObject(a * 50, b * 50, rand.nextInt(21) + 30, rand.nextInt(21) + 30));
                    else if (num < 25)
                        comps.add(new Computer(a * 50, b * 50,/* 100, 100, 0));//*/rand.nextInt(50) + 1, rand.nextInt(50) + 1, rand.nextInt(4)));
                }
                mapGraphics.fillRect(a * 50, b * 50, 50, 50);
            }
        
        for(int i = 0; i < walls.size(); i++)
            all.add(walls.get(i));
        for(int i = 0; i < blocks.size(); i++)
            all.add(blocks.get(i));
        for(int i = 0; i < comps.size(); i++)
            all.add(comps.get(i));
        all.add(link);
        image = createImage(getSize().width, getSize().height);
        graphics = image.getGraphics();
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
                    case KeyEvent.VK_SPACE:
                    {
                        spaceKey = false;
                        break;
                    }
                    case KeyEvent.VK_P:
                    {
                        pause = !pause;
                        break;
                    }
                }
            }
        });
    }
    public void paint(Graphics g)
    {
        moveAll();
        printAll();       
        g.drawImage(image, 0, 0, null);
        repaint();
        while (pause); 
    }
    public void moveAll()
    {
        if (upKey)
            link.pushUp(all, speed, rightWall, bottomWall, false);
        if (downKey)
            link.pushDown(all, speed, rightWall, bottomWall);
        if (leftKey)
            link.pushLeft(all, speed, rightWall, bottomWall);
        if (rightKey)
            link.pushRight(all, speed, rightWall, bottomWall);
        if (spaceKey) {
            Player arrow;
            if (link.getDirection() < 2)
                arrow = new Player(link.left() + link.width() / 3, link.top(), link.height(), link.width() / 3, link.getDirection());
            else
                arrow = new Player(link.left(), link.top() + link.height() / 3, link.height() / 3, link.width(), link.getDirection());
            boolean test = true;
            for(int i = 0; i < arrows.size(); i++)
                if (arrows.get(i).intercepts(arrow))
                    test = false;
            if (test) {
                all.add(arrow);
                arrows.add(arrow);
            }
        }
        
//         if (spaceKey == 1)
//             {
//                 link.shootArrow();
//                 spaceKey = 2;
//             }
//         for(int i = 0; i < arrows.size(); i++)
//             if (!arrows.get(i).moveArrow())
//                 {
//                     arrows.remove(i);
//                     i--;
//                 }
        for(int i = 0; i < comps.size(); i++)
            comps.get(i).move(all, rand.nextInt(5) + 10, rightWall, bottomWall);
        for(int i = 0; i < arrows.size(); i++)
            if (arrows.get(i).pushDirection(all, 3, rightWall, bottomWall) == 0)
                arrows.get(i).remove(all, arrows);
    }
    public void printAll()
    {
        center(link);
        graphics.drawImage(mapImage, 0, 0, 600, 600, x, y, x + 600, y + 600, null);
        
        linkGraphics = Link.draw(link.getDirection(), linkGraphics);
        graphics.drawImage(linkBody, link.left() - x, link.top() - y, link.width(), link.height(), null);
        
        graphics.setColor(Color.cyan);
        for(int i = 0; i < blocks.size(); i++)
            graphics.fillRect(blocks.get(i).left() - x, blocks.get(i).top() - y, blocks.get(i).width(), blocks.get(i).height());    
            
        //graphics.setColor(Color.red);
        for(int i = 0; i < comps.size(); i++)
        //graphics.fillRect(comps.get(i).left() - x, comps.get(i).top() - y, comps.get(i).width(), comps.get(i).height());
        graphics.drawImage(linkBody, comps.get(i).left() - x, comps.get(i).top() - y, comps.get(i).width(), comps.get(i).height(), null);
        
        graphics.setColor(Color.black);
        for(int i = 0; i < arrows.size(); i++)
            graphics.fillRect(arrows.get(i).left() - x, arrows.get(i).top() - y, arrows.get(i).width(), arrows.get(i).height);
    }
    public void center(Rectangle inRect)
    {
        if (inRect.left() < screenWidth / 2 - inRect.width() / 2) // 275
            x = 0;
        else if (inRect.left() > rightWall - screenWidth / 2 - inRect.width() / 2) // 2175
                x = rightWall - screenWidth; // 1900
             else
                x = inRect.left() - screenWidth / 2 + inRect.width() / 2; // left - 275

        if (inRect.top() < screenHeight / 2 - inRect.height() / 2)
            y = 0;
        else if (inRect.top() > bottomWall - screenHeight / 2 - inRect.height() / 2)
                y = bottomWall - screenHeight;
             else
                y = inRect.top() - screenHeight / 2 + inRect.height() / 2;
    }
}