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
    int key, x, y, arrowDelay, speed = 1, spaceKey;
    boolean upKey, downKey, leftKey, rightKey;// spaceKey;
    Image image, mapImage, linkBody;
    Graphics graphics, mapGraphics, linkGraphics;
    Random rand = new Random();
    static int[][] map = new int[50][50];
    //static ArrayList<Being> enemies = new ArrayList<Being>();
    static ArrayList<Being> blocks = new ArrayList<Being>();
	static ArrayList<Arrow> arrows = new ArrayList<Arrow>();
    Being link = new Being(0,0,50,50);
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
        mapGraphics = mapImage.getGraphics();
        for (int a = 0; a < 50; a++)
            for (int b = 0; b < 50; b++)
            {
                int num = rand.nextInt(100);
                if (num < 10 && (a != 1 && b != 1)) // 10% chance
                {
                    mapGraphics.setColor(new Color(rand.nextInt(60),rand.nextInt(60),rand.nextInt(60)));
                    map[a][b] = -1;
                }
                else if (num < 11 && (a != 1 && b != 1)) // 1% chance
                     {
                         mapGraphics.setColor(new Color(rand.nextInt(60) + 20,rand.nextInt(60) + 20,rand.nextInt(60) + 20));
                         map[a][b] = -2;
                         //enemies.add(new Being(a*50,b*50,rand.nextInt(21)+30,rand.nextInt(21)+30));
                     }
                     else if (num < 20) // 8% chance
                          {
                              mapGraphics.setColor(new Color(rand.nextInt(31)+225,rand.nextInt(31)+225,rand.nextInt(31)+225));
                              map[a][b] = 1;
                              blocks.add(new Being(a*50,b*50,rand.nextInt(21)+30,rand.nextInt(21)+30));
                          }
                          else
                          {
                              mapGraphics.setColor(new Color(rand.nextInt(31)+225,rand.nextInt(31)+225,rand.nextInt(31)+225));
                              map[a][b] = 0;
                          }
                mapGraphics.fillRect(a * 50,b * 50,50,50);
            }
        // --------------------------------------------
        // Image to be drawn on, then copied to screen:
        // --------------------------------------------
        image = createImage(getSize().width, getSize().height);
        graphics = image.getGraphics();
    }
    public void paint(Graphics g)
    {
        getKey();
        moveAll();
        printAll();       
        g.drawImage(image, 0, 0, null);
        //g.drawImage(image,0,0,599,299,0,0,599,599,null);
        //g.drawImage(image,0,300,599,599,0,0,599,599,null);
        repaint();
    }
    public void moveAll()
    {
        // -----------
        // Moves Link:
        // -----------
        if (upKey)
            link.moveUp(speed);
        if (downKey)
            link.moveDown(speed);
        if (leftKey)
            link.moveLeft(speed);
        if (rightKey)
            link.moveRight(speed);
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
        //graphics.setColor(Color.orange);
        //graphics.fillRect(link.getX()-x,link.getY()-y,link.getWidth(),link.getHeight());
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
    }
}