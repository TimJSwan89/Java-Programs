// =======================
// Title:    Zelda
// Designer: Swan, Timothy
// =======================
import java.awt.*;
import javax.swing.*;
import java.applet.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;

public class Zelda extends JApplet
{   
    int up =0, left=0, down=0, right=0;// temporary
    int key, x, y, arrowDelay, speed = 2, speed2 = 4, spaceKey;
    boolean upKey, downKey, leftKey, rightKey, w, a, s, d;// spaceKey;
    Image image, mapImage, linkBody, mapImage2, image2;
    Graphics graphics, mapGraphics, linkGraphics, graphics2, mapGraphics2;
    Random rand = new Random();
    static int[][] map = new int[50][50];
    //static ArrayList<Being> enemies = new ArrayList<Being>();
    static ArrayList<Being> blocks = new ArrayList<Being>();
    static ArrayList<Arrow> arrows = new ArrayList<Arrow>();
    Being link = new Being(0,0,25,25);
    Being link2 = new Being(50,50,43,39);//
    public void init()
    {
        setSize(600,600);
        linkBody = createImage(50, 50);
        linkGraphics = linkBody.getGraphics();
        Link.initialize(linkGraphics);
        //Link.draw(linkGraphics);
        // ---------------
        // Image for rock:
        // ---------------
        
        // -------------------
        // Creates random map:
        // -------------------
        mapImage = createImage(2500, 2500);  
        mapImage2 = createImage(2500, 2500);
        mapGraphics = mapImage.getGraphics();
        mapGraphics2 = mapImage2.getGraphics();
        for (int a = 0; a < 50; a++)
            for (int b = 0; b < 50; b++)
            {
                int num = rand.nextInt(100);
                if (num < 10) // 10% chance
                {
                    mapGraphics.setColor(new Color(rand.nextInt(60),rand.nextInt(60),rand.nextInt(60)));
                    mapGraphics2.setColor(new Color(rand.nextInt(60),rand.nextInt(60),rand.nextInt(60)));
                    map[a][b] = -1;
                }
                else if (num < 11) // 1% chance
                     {
                         mapGraphics.setColor(new Color(rand.nextInt(60) + 20,rand.nextInt(60) + 20,rand.nextInt(60) + 20));
                         mapGraphics2.setColor(new Color(rand.nextInt(60) + 20,rand.nextInt(60) + 20,rand.nextInt(60) + 20));
                         map[a][b] = -2;
                         //enemies.add(new Being(a*50,b*50,rand.nextInt(21)+30,rand.nextInt(21)+30));
                     }
                     else if (num < 20) // 8% chance
                          {
                              mapGraphics.setColor(new Color(rand.nextInt(31)+225,rand.nextInt(31)+225,rand.nextInt(31)+225));
                              mapGraphics2.setColor(new Color(rand.nextInt(31)+225,rand.nextInt(31)+225,rand.nextInt(31)+225));
                              map[a][b] = 1;
                              blocks.add(new Being(a*50,b*50,rand.nextInt(21)+30,rand.nextInt(21)+30));
                          }
                          else
                          {
                              mapGraphics.setColor(new Color(rand.nextInt(31)+225,rand.nextInt(31)+225,rand.nextInt(31)+225));
                              mapGraphics2.setColor(new Color(rand.nextInt(31)+225,rand.nextInt(31)+225,rand.nextInt(31)+225));
                              map[a][b] = 0;
                          }
                mapGraphics.fillRect(a * 50,b * 50,50,50);
                mapGraphics2.fillRect(a * 50,b * 50,50,50);
            }
        // --------------------------------------------
        // Image to be drawn on, then copied to screen:
        // --------------------------------------------
        image = createImage(getSize().width, getSize().height);
        image2 = createImage(getSize().width, getSize().height);
        graphics = image.getGraphics();
        graphics2 = image2.getGraphics();
        getKey();
    }
    public void paint(Graphics g)
    {
        moveAll();
        printAll();       
        //g.drawImage(image, 0, 0, null);
        g.drawImage(image,0,0,599,299,0,0,599,599,null);
        g.drawImage(image2,0,300,599,599,0,0,599,599,null);
        repaint();
    }
    public void moveAll()
    {
        // -----------
        // Moves Link:
        // -----------
//         if (up > 0)
//         {
//             up--;
//             if (up <= 0)
//                 upKey = false;
//         } else if (rand.nextInt(3) == 0 && !downKey)
//         {
//             upKey = true;
//             up = rand.nextInt(50) + 30;
//         }
//         if (down > 0)
//         {
//             down--;
//             if (down <= 0)
//                 downKey = false;
//         } else if (rand.nextInt(3) == 0 && !upKey)
//         {
//             downKey = true;
//             down = rand.nextInt(50) + 30;
//         }
//         if (right > 0)
//         {
//             right--;
//             if (right <= 0)
//                 rightKey = false;
//         } else if (rand.nextInt(3) == 0 && !leftKey)
//         {
//             rightKey = true;
//             right = rand.nextInt(50) + 30;
//         }
//         if (left > 0)
//         {
//             left--;
//             if (left <= 0)
//                 leftKey = false;
//         } else if (rand.nextInt(3) == 0 && !rightKey)
//         {
//             leftKey = true;
//             left = rand.nextInt(50) + 30;
//         }
        if (upKey)
            link.moveUp(speed * 2);
        if (downKey)
            link.moveDown(speed * 2);
        if (leftKey)
            link.moveLeft(speed);
        if (rightKey)
            link.moveRight(speed);
        if (w)
            link2.moveUp(speed2 * 2);
        if (s)
            link2.moveDown(speed2 * 2);
        if (a)
            link2.moveLeft(speed2);
        if (d)
            link2.moveRight(speed2);
//         if (rand.nextBoolean())
//             link.moveUp(speed);
//         if (rand.nextBoolean())
//             link.moveDown(speed);
//         if (rand.nextBoolean())
//             link.moveLeft(speed);
//         if (rand.nextBoolean())
//             link.moveRight(speed);
        if (spaceKey == 1)
            {
                link.shootArrow();
                spaceKey = 2;
            }
        
        // -------------
        // Moves arrows:
        // -------------
        for(int i = 0; i < arrows.size(); i++)
            if (!arrows.get(i).moveArrow())
                {
                    arrows.remove(i);
                    i--;
                }
        // --------------
        // Moves enemies:
        // --------------

    }
    public void printAll()
    {
        // -----------------
        // Draws background:
        // -----------------
        if (link.getX() < 275)
            x = 0;
        else if (link.getX() > 2175)
                x = 1900;
             else
                x = link.getX() - 275;
        if (link.getY() < 275)
            y = 0;
        else if (link.getY() > 2175)
                y = 1900;
             else
                y = link.getY() - 275;
        graphics.drawImage(mapImage,0,0,599,599,x,y,x + 599,y + 599,null);
        // -------------
        // Draws blocks:
        // -------------
        graphics.setColor(Color.cyan);
        for(int i = 0; i < blocks.size(); i++)
            graphics.fillRect(blocks.get(i).getX() - x, blocks.get(i).getY()-y,blocks.get(i).getWidth(),blocks.get(i).getHeight());
        graphics.fillRect(link2.getX() - x, link2.getY() - y, link2.getWidth(), link2.getHeight());
            // -------------
        // Draws Arrows:
        // -------------
        for(int i = 0; i < arrows.size(); i++)
            arrows.get(i).draw(graphics, x, y);
        // -----------
        // Draws Link:
        // -----------
        linkGraphics = Link.draw(link.getFacing(), linkGraphics);
        graphics.drawImage(linkBody,link.getX()-x,link.getY()-y,link.getWidth(),link.getHeight(),null);
        
        
//         linkGraphics = Link.draw(link2.getFacing(), linkGraphics);
//         //Link.draw(linkGraphics, link.getFacing());
//         graphics.drawImage(linkBody,link2.getX()-x,link2.getY()-y,link2.getWidth(),link2.getHeight(),null);
        //graphics.setColor(Color.orange);
        //graphics.fillRect(link.getX()-x,link.getY()-y,link.getWidth(),link.getHeight());
        
        
        
        
        if (link2.getX() < 275)
            x = 0;
        else if (link2.getX() > 2175)
                x = 1900;
             else
                x = link2.getX() - 275;
        if (link2.getY() < 275)
            y = 0;
        else if (link2.getY() > 2175)
                y = 1900;
             else
                y = link2.getY() - 275;
        graphics2.drawImage(mapImage2,0,0,599,599,x,y,x + 599,y + 599,null);
        // -------------
        // Draws blocks:
        // -------------
        graphics2.setColor(Color.cyan);
        for(int i = 0; i < blocks.size(); i++)
            graphics2.fillRect(blocks.get(i).getX() - x, blocks.get(i).getY()-y,blocks.get(i).getWidth(),blocks.get(i).getHeight());
        //graphics2.fillRect(link2.getX() - x, link2.getY() - y, link2.getWidth(), link2.getHeight());
            // -------------
        // Draws Arrows:
        // -------------
        for(int i = 0; i < arrows.size(); i++)
            arrows.get(i).draw(graphics2, x, y);
        // -----------
        // Draws Link:
        // -----------
        linkGraphics = Link.draw(link2.getFacing(), linkGraphics);
        graphics2.drawImage(linkBody,link2.getX()-x,link2.getY()-y,link2.getWidth(),link2.getHeight(),null);
        
        linkGraphics = Link.draw(link.getFacing(), linkGraphics);
        graphics2.drawImage(linkBody,link.getX()-x,link.getY()-y,link.getWidth(),link.getHeight(),null);
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
                    case KeyEvent.VK_SPACE:
                    {
                        spaceKey = 1;
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
                        spaceKey = 0;
                        break;
                    }
                }
            }
        });
        addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent evt)
            {
                key = evt.getKeyCode();
                switch(key)
                {
                    case KeyEvent.VK_W:
                    {
                        w = true;
                        break;
                    }
                    case KeyEvent.VK_A:
                    {
                        a = true;
                        break;
                    }
                    case KeyEvent.VK_S:
                    {
                        s = true;
                        break;
                    }
                    case KeyEvent.VK_D:
                    {
                        d = true;
                        break;
                    }
                }
            }
            public void keyReleased(KeyEvent evt)
            {
                key = evt.getKeyCode();
                switch(key)
                {
                    case KeyEvent.VK_W:
                    {
                        w = false;
                        break;
                    }
                    case KeyEvent.VK_A:
                    {
                        a = false;
                        break;
                    }
                    case KeyEvent.VK_S:
                    {
                        s = false;
                        break;
                    }
                    case KeyEvent.VK_D:
                    {
                        d = false;
                        break;
                    }
                }
            }
        });
    }
}