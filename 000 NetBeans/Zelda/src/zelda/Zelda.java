package zelda;
/*
 * Zelda
 * Version: 5.0
 * Author: Timothy Swan
 */
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

//import java.awt.*;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;

//import javax.swing.*;
import javax.swing.*;

//import java.util.*;
import java.util.Random;
import java.util.ArrayList;

//import java.awt.*;
import java.awt.Container;

//import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.io.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

import javax.sound.sampled.*;

public class Zelda extends JPanel {
    // Variables to set before running:
    double chanceOfWall = 1, chanceOfBlock = 5, chanceOfGuy = 2; // These are percents
    static int standardSize = 50;
    int speed = (int) (7.0 / 50.0 * standardSize), rightWall = 24 * standardSize, bottomWall = 24 * standardSize, screenWidth = 12 * standardSize, screenHeight = 12 * standardSize;
    boolean wrap = true, levelEditor = false;
    String loadName = "", saveName = "save.txt";
    
    int key, x, sx, y, sy, arrowDelay;
    int windowWidth = screenWidth, windowHeight = screenHeight;
    int sw, sh;
    double Xscale, Yscale, zoom;
    boolean upKey, downKey, leftKey, rightKey, plusKey, minusKey, spaceKey, running = true;
    Clip clip;
    AudioInputStream pop;
    File soundFile;
    JFrame frame;
    ActionListener al;
    Image image, mapImage, linkBody;
    Graphics graphics, mapGraphics, linkGraphics;
    Random rand = new Random();
    ArrayList<Wall> walls;
    ArrayList<MobileObject> blocks;
    ArrayList<Player> arrows;
    ArrayList<Computer> comps;
    ArrayList<Rectangle> all;
    Player link;
    public Zelda() {
    }
//         int rw = JOptionPane.showInputDialog("Length of environment? (12 - 50)");
//         if (rw < 12)
//             rw = 12;
//         if (rw > 50)
//             rw = 50;
//         rightWall = rw * 50;
//         int bw = JOptionPane.showInputDialog("Height of environment? (12 - 50)");
//         if (bw < 12)
//             bw = 12;
//         if (bw > 50)
//             bw = 50;
//         bottomWall = bw * 50;
//         int p = JOptionPane.showConfirmDialog(null, "Wrapped environment? \n(Cancel means no)");
//         if (p == JOptionPane.YES_OPTION)
//             wrap = true;
        
        //setSize(screenWidth, screenHeight);
    
    public void initialize(JFrame inFrame) {
        frame = inFrame;
        if (loadName.equals("")) {
            walls = new ArrayList<Wall>();
            blocks = new ArrayList<MobileObject>();
            arrows = new ArrayList<Player>();
            comps = new ArrayList<Computer>();
            all = new ArrayList<Rectangle>();
            link = new Player(0, 0, standardSize, standardSize, 0);
            setSize(screenWidth, screenHeight);
            frame.setSize(screenWidth, screenHeight);
            setScale();
            linkBody = createImage(standardSize, standardSize);
            linkGraphics = linkBody.getGraphics();
            Link.initialize(linkGraphics);
            mapImage = createImage(rightWall, bottomWall);//rightWall * (wrap ? 12 : 1), bottomWall * (wrap ? 12 : 1));        
            mapGraphics = mapImage.getGraphics();
            for (int a = 0; a < rightWall / standardSize; a++)
                for (int b = 0; b < bottomWall / standardSize; b++) {
                    double num = rand.nextDouble() * 100;
                    if (num < chanceOfWall) { // 5% chance
                        walls.add(new Wall(a * standardSize, b * standardSize, standardSize, standardSize));//rand.nextInt(21) + 30, rand.nextInt(21) + 30));
                    }
                    else  {
                        if (num < chanceOfWall + chanceOfBlock) // 15% chance
                            blocks.add(new MobileObject(a * standardSize, b * standardSize, standardSize, standardSize));
                        else if (num < chanceOfWall + chanceOfBlock + chanceOfGuy)
                            comps.add(new Computer(a * standardSize, b * standardSize, rand.nextInt(21) + 30, rand.nextInt(21) + 30, rand.nextInt(4)));
                    }
                }
        } else {
            load(loadName);
        }
        for (int a = 0; a < rightWall / standardSize; a++)
                for (int b = 0; b < bottomWall / standardSize; b++) {
                    mapGraphics.setColor(new Color(rand.nextInt(31) + 215,rand.nextInt(31) + 215,rand.nextInt(31) + 215));
                    mapGraphics.fillRect(a * standardSize, b * standardSize, standardSize, standardSize);
                }
        for(Wall wall : walls) {
            mapGraphics.fillRect(wall.left(), wall.top(), wall.width(), wall.height());
        }
        if (wrap) {
            mapGraphics.drawImage(mapImage, rightWall, 0, this);
            mapGraphics.drawImage(mapImage, 0, bottomWall, this);
            mapGraphics.drawImage(mapImage, rightWall, bottomWall, this);
        }
//         mapGraphics.setColor(Color.black);
//         mapGraphics.fillRect(0, 0, rightWall, 5);
//         mapGraphics.fillRect(0, 0, 5, bottomWall);
            
        all.add(link);
        for(Rectangle wall : walls)
            all.add(wall);
        for(Rectangle block : blocks)
            all.add(block);
        for(Computer comp : comps)
            all.add(comp);
        image = createImage(rightWall, bottomWall);
        graphics = image.getGraphics();
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                key = evt.getKeyCode();
                switch(key) {
                    case KeyEvent.VK_UP: {
                        upKey = true;
                        break;
                    }
                    case KeyEvent.VK_DOWN: {
                        downKey = true;
                        break;
                    }
                    case KeyEvent.VK_LEFT: {
                        leftKey = true;
                        break;
                    }
                    case KeyEvent.VK_RIGHT: {
                        rightKey = true;
                        break;
                    }
                    case KeyEvent.VK_SPACE: {
                        spaceKey = true;
                        break;
                    }
                    case KeyEvent.VK_P: {
                        al.actionPerformed(new ActionEvent("", 0, ""));
                        break;
                    }
                    case KeyEvent.VK_S: {
                        save(saveName);
                        break;
                    }
                    case KeyEvent.VK_L: {
                        load(loadName);
                        break;
                    }
                    case KeyEvent.VK_EQUALS: {
                        if (zoom >= 0)
                            zoom = -1;
                        else if (zoom > -32)
                            zoom *= 1.2;
                        break;
//                          screenWidth -= 1;
//                          screenHeight -= 1;
//                          setScale();
//                          break;
                    }
                    case KeyEvent.VK_MINUS: {
                        if (zoom <= 0)
                            zoom = 1;
                        else if (zoom < 32)
                            zoom *= 1.2;
                        break;
                    }
                    case KeyEvent.VK_0: {
                        screenWidth = windowWidth;
                        screenHeight = windowHeight;
                        setScale();
                        break;
                    }
                }
            }
            public void keyReleased(KeyEvent evt) {
                key = evt.getKeyCode();
                switch(key) {
                    case KeyEvent.VK_UP: {
                        upKey = false;
                        break;
                    }
                    case KeyEvent.VK_DOWN: {
                        downKey = false;
                        break;
                    }
                    case KeyEvent.VK_LEFT: {
                        leftKey = false;
                        break;
                    }
                    case KeyEvent.VK_RIGHT: {
                        rightKey = false;
                        break;
                    }
                    case KeyEvent.VK_SPACE: {
                        spaceKey = false;
                        break;
                    }
                    case KeyEvent.VK_EQUALS: {
                        zoom = 0;
                        break;
                    }
                    case KeyEvent.VK_MINUS: {
                        zoom = 0;
                        break;
                    }
                }
            }
        });
        pop = null;
        try {
            soundFile = new File("pop.wav");
            pop = AudioSystem.getAudioInputStream(soundFile);

            clip = AudioSystem.getClip();
            System.out.println(AudioSystem.getClip() == clip);
            clip.open(pop);
            clip.start();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Zelda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Zelda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
        } finally {
            try {
                if (pop != null)
                    pop.close();
            } catch (IOException ex) {
//                Logger.getLogger(Zelda.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void paint(Graphics g) {
        moveAll();
        setScale();
        sw = windowWidth;
        sh = windowHeight;
      //  windowWidth = screenWidth;
      //  windowHeight = screenHeight;
        
        graphics = g;
      //  setScale();
        printAll();  // prints background to graphics according to window and screen
        windowWidth = sw;
        windowHeight = sh;
        windowWidth = frame.getContentPane().getWidth();
        windowHeight = frame.getContentPane().getHeight();
      //  g.drawImage(image, 0, 0, /*sw, sh,*/windowWidth, windowHeight, 0, 0, screenWidth, screenHeight, this);
       // System.out.println(screenWidth);
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            System.out.println("Threading error");
        }
        if (running)
            repaint();
    }
    public void moveAll() {
        if (upKey)
            link.pushUp(all, speed, rightWall, bottomWall, wrap);
        if (downKey)
           link.pushDown(all, speed, rightWall, bottomWall, wrap);
        if (leftKey)
            link.pushLeft(all, speed, rightWall, bottomWall, wrap);
        if (rightKey)
            link.pushRight(all, speed, rightWall, bottomWall, wrap);
//         upKey = downKey = leftKey = rightKey = false;
        if (zoom > 0 || screenWidth >= screenHeight) {
            screenWidth += (int) (.1 * zoom * screenWidth);
            if (screenWidth < 1)
                screenWidth = 1;
            else if (screenWidth > rightWall)
                screenWidth = rightWall;
        }
        if (zoom > 0 || screenWidth <= screenHeight) {
            screenHeight += (int) (.1 * zoom * screenHeight);
            if (screenHeight < 1)
                screenHeight = 1;
            else if (screenHeight > bottomWall)
                screenHeight = bottomWall;
        }
//        if (image.getWidth(this) != screenWidth || image.getHeight(this) != screenHeight) {
//            System.out.println(screenWidth);
//            image = createImage(screenWidth, screenHeight); /* Change to screen */
//            graphics = image.getGraphics();
//        }
    
        for(Computer comp : comps)
            comp.move(all, 14/*rand.nextInt(5) + 10*/, rightWall, bottomWall, wrap);
            
//         if (plusKey) {
//             if (screenWidth > 0)
//                 screenWidth--;
//             if (screenHeight > 0)
//                 screenHeight--;
//             setScale();
// //             Xscale = (double) windowWidth / screenWidth;
// //             Yscale = (double) windowHeight / screenHeight;
//             //setSize(screenWidth, screenHeight);
//         }
   //        if (minusKey) {
//             if (screenWidth < rightWall) {
//                 screenWidth += zoom;
//                 if
//             }
//             if (screenHeight < bottomWall)
//                 screenHeight++;
            setScale();
//             Xscale = (double) windowWidth / screenWidth;
//             Yscale = (double) windowHeight / screenHeight;
            //setSize(screenWidth, screenHeight);
            
            if (spaceKey) {
                Player arrow;
                if (link.getDirection() < 2)
                    arrow = new Player(link.left() + link.width() / 3, link.top(), link.width() / 3, link.height(), link.getDirection());
                else
                    arrow = new Player(link.left(), link.top() + link.height() / 3, link.width(), link.height() / 3, link.getDirection());
                boolean test = true;
                for(int i = 0; i < arrows.size(); i++)
                    if (arrows.get(i).intercepts(arrow))
                        test = false;
                if (test) {
                    //AudioInputStream pop = null;
                    try {
                        //soundFile = new File("pop.wav");
                        pop = AudioSystem.getAudioInputStream(soundFile);
                        System.out.println(pop.equals(AudioSystem.getAudioInputStream(soundFile)));
                        clip = AudioSystem.getClip();
                        //System.out.println(AudioSystem.getClip() == clip);
                        clip.open(pop);
                        clip.start();
                    } catch (LineUnavailableException ex) {
                        Logger.getLogger(Zelda.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedAudioFileException ex) {
                        Logger.getLogger(Zelda.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                    } finally {
                        try {
                            if (pop != null)
                                pop.close();
                        } catch (IOException ex) {
            //                Logger.getLogger(Zelda.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                    
                    
                   // play(pop);
                    all.add(arrow);
                    arrows.add(arrow);
                }
            }
            for(int i = 0; i < arrows.size(); ) {
                if (((Player)arrows.get(i)).pushDirection(all, 9, rightWall, bottomWall, wrap) == 0)
                    arrows.get(i).remove(all, arrows);
                else
                    i++;
        }
    }
    public void play(AudioInputStream ais) {
        if (ais != null) {
            try {
                clip.open(ais);
                clip.start();
            } catch (LineUnavailableException ex) {
                Logger.getLogger(Zelda.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Zelda.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void printAll() {
        center(link);
        // Draws background
        graphics.drawImage(mapImage, 0, 0, windowWidth, windowHeight, x, y, x + screenWidth, y + screenHeight, this);
        if (wrap) {
            if (x < 0) {
                graphics.drawImage(mapImage, 0, 0, -XS(x), windowHeight, x + rightWall, y, rightWall, y + screenHeight, this);
                if (y < 0) {
                    graphics.drawImage(mapImage, 0, 0, -XS(x), -YS(y), x + rightWall, y + bottomWall, rightWall, bottomWall, this);
                } else if (y > bottomWall - screenHeight) {
                    graphics.drawImage(mapImage, 0, YS(bottomWall - y), -XS(x), windowHeight, x + rightWall, 0, rightWall, y + screenHeight - bottomWall, this);
                }
            } else if (x > rightWall - screenWidth) {
                graphics.drawImage(mapImage, XS(rightWall - x), 0, windowWidth, windowHeight, 0, y, x + screenWidth - rightWall, y + screenHeight, this);
                if (y < 0) {
                    graphics.drawImage(mapImage, XS(rightWall - x), 0, windowWidth, -YS(y), 0, y + bottomWall, x + screenWidth - rightWall, bottomWall, this);
                } else if (y > bottomWall - screenHeight) {
                    graphics.drawImage(mapImage, XS(rightWall - x), YS(bottomWall - y), windowWidth, windowHeight, 0, 0, x + screenWidth - rightWall, y + screenHeight - bottomWall, this);
                }
            }
            if (y < 0) {
                graphics.drawImage(mapImage, 0, 0, windowWidth, -YS(y), x, y + bottomWall, x + screenWidth, bottomWall, this);
            } else if (y > bottomWall - screenHeight) {
                graphics.drawImage(mapImage, 0, YS(bottomWall - y), windowWidth, windowHeight, x, 0, x + screenWidth, y + screenHeight - bottomWall, this);
            }
        }
        
        // Draws Objects
        printObjects(x, y);
        if (wrap) {
            if (x < 0) {
                printObjects(x + rightWall, y);
                if (y < 0) {
                    printObjects(x + rightWall, y + bottomWall);
                } else if (y > bottomWall - screenHeight) {
                    printObjects(x + rightWall, y - bottomWall);
                }
            } else if (x > rightWall - screenWidth) {
                printObjects(x - rightWall, y);
                if (y < 0) {
                    printObjects(x - rightWall, y + bottomWall);
                } else if (y > bottomWall - screenHeight) {
                    printObjects(x - rightWall, y - bottomWall);
                }
            }
            if (y < 0) {
                printObjects(x, y + bottomWall);
            } else if (y > bottomWall - screenHeight) {
                printObjects(x, y - bottomWall);
            }
        }
    }
    public void printObjects(int x, int y) {
        graphics.setColor(Color.cyan);
        for(Rectangle block : blocks) {
           int left = XS(block.left() - x);
           int width = XS(block.right() - x) - left;
           int top = YS(block.top() - y);
           int height = YS(block.bottom() - y) - top;
           graphics.fillRect(left, top, width, height);

//             int width = (int) (XSd(block.right() - x) - XSd(block.left() - x));
//             int height = (int) (YSd(block.bottom() - x) - YSd(block.top() - x));
//             graphics.fillRect(XS(block.left() - x), YS(block.top() - y), width, height);
            
            if (block.right() > rightWall) {
                graphics.fillRect(XS(block.left() - x - rightWall), YS(block.top() - y), XS(block.width()), YS(block.height()));
                if (block.bottom() > bottomWall)
                    graphics.fillRect(XS(block.left() - x - rightWall), YS(block.top() - y - bottomWall), XS(block.width()), YS(block.height()));
            } else if (block.bottom() > bottomWall) {
                graphics.fillRect(XS(block.left() - x), YS(block.top() - y - bottomWall), XS(block.width()), YS(block.height()));
            }
        }

        for(Computer comp : comps) {
            linkGraphics = Link.draw(comp.getDirection(), linkGraphics);
            graphics.drawImage(linkBody, XS(comp.left() - x), YS(comp.top() - y), XS(comp.width()), YS(comp.height()), null);
            if (comp.right() > rightWall) {
                graphics.drawImage(linkBody, XS(comp.left() - x - rightWall), YS(comp.top() - y), XS(comp.width()), YS(comp.height()), null);
                if (comp.bottom() > bottomWall)
                    graphics.drawImage(linkBody, XS(comp.left() - x - rightWall), YS(comp.top() - y - bottomWall), XS(comp.width()), YS(comp.height()), null);
            } else if (comp.bottom() > bottomWall) {
                graphics.drawImage(linkBody, XS(comp.left() - x), YS(comp.top() - y - bottomWall), XS(comp.width()), YS(comp.height()), null);
            }
        }
        graphics.setColor(Color.black);
        for(Player arrow : arrows)
            graphics.fillRect(XS(arrow.left() - x), YS(arrow.top() - y), XS(arrow.width()), YS(arrow.height()));
            
        for(Wall block : walls) {
           int left = XS(block.left() - x);
           int width = XS(block.right() - x) - left;
           int top = YS(block.top() - y);
           int height = YS(block.bottom() - y) - top;
           graphics.fillRect(left, top, width, height);
            if (block.right() > rightWall) {
                graphics.fillRect(XS(block.left() - x - rightWall), YS(block.top() - y), XS(block.width()), YS(block.height()));
                if (block.bottom() > bottomWall)
                    graphics.fillRect(XS(block.left() - x - rightWall), YS(block.top() - y - bottomWall), XS(block.width()), YS(block.height()));
            } else if (block.bottom() > bottomWall) {
                graphics.fillRect(XS(block.left() - x), YS(block.top() - y - bottomWall), XS(block.width()), YS(block.height()));
            }
        }
        
        linkGraphics = Link.draw(link.getDirection(), linkGraphics);
        graphics.drawImage(linkBody, XS(link.left() - x), YS(link.top() - y), XS(link.width()), YS(link.height()), null);
    }
    public void center(Rectangle inRect) {
        if (wrap) {
            x = inRect.left() - screenWidth / 2 + inRect.width() / 2;
            y = inRect.top() - screenHeight / 2 + inRect.height() / 2;
        } else {
            if (inRect.left() < screenWidth / 2 - inRect.width() / 2)
                x = 0;
            else if (inRect.left() >= rightWall - screenWidth / 2 - inRect.width() / 2)
                    x = rightWall - screenWidth;
                 else
                    x = inRect.left() - screenWidth / 2 + inRect.width() / 2;
            if (inRect.top() < screenHeight / 2 - inRect.height() / 2)
                y = 0;
            else if (inRect.top() >= bottomWall - screenHeight / 2 - inRect.height() / 2)
                    y = bottomWall - screenHeight;
                 else
                    y = inRect.top() - screenHeight / 2 + inRect.height() / 2;
        }
    }
    private void setScale() {
        Xscale = (double) windowWidth / screenWidth;
        Yscale = (double) windowHeight / screenHeight;
        sx = XS(x);
        sy = YS(y);
    }
    private int XS(int screen) {
        return (int) (Xscale * screen);
    }
    private int YS(int screen) {
        return (int) (Yscale * screen);
    }
    private double XSd(int screen) {
        return Xscale * screen;
    }
    private double YSd(int screen) {
        return Yscale * screen;
    }
    public void setRun(boolean run) {
        running = run;
    }
    public void setPauseListener(ActionListener inAl) {
        al = inAl;
    }
    public void save(String saveName) {
        FileOut fileOut = new FileOut(saveName);
        fileOut.write(speed);
        fileOut.write(rightWall);
        fileOut.write(bottomWall);
        fileOut.write(screenWidth);
        fileOut.write(screenHeight);
        fileOut.write(wrap ? "true" : "false");
        fileOut.write(link.left());
        fileOut.write(link.top());
        fileOut.write(link.width());
        fileOut.write(link.height());
        fileOut.write(link.getDirection());
        fileOut.write(walls.size());
        for(Rectangle wall : walls) {
            fileOut.write(wall.left());
            fileOut.write(wall.top());
            fileOut.write(wall.width());
            fileOut.write(wall.height());
        }
        fileOut.write(blocks.size());
        for(Rectangle block : blocks) {
            fileOut.write(block.left());
            fileOut.write(block.top());
            fileOut.write(block.width());
            fileOut.write(block.height());

        }
        fileOut.write(comps.size());
        for(Computer comp : comps) {
            fileOut.write(comp.left());
            fileOut.write(comp.top());
            fileOut.write(comp.width());
            fileOut.write(comp.height());
            fileOut.write(comp.getDirection());
        }
        fileOut.write(arrows.size());
        for(Player arrow : arrows) {
            fileOut.write(arrow.left());
            fileOut.write(arrow.top());
            fileOut.write(arrow.width());
            fileOut.write(arrow.height());
            fileOut.write(arrow.getDirection());
        }
        fileOut.done();
        System.out.println("Save Complete.");
    }
    public void load(String loadName) {
        for( ; all.size() > 0 ; )
            all.get(0).remove(all, walls, blocks, comps, arrows);
        FileIn fileIn = new FileIn(loadName);
        speed = fileIn.readInt();
        rightWall = fileIn.readInt();
        bottomWall = fileIn.readInt();
        screenWidth = fileIn.readInt();
        screenHeight = fileIn.readInt();
        wrap = fileIn.read().equals("true");
        int x, y, width, height, dir, num;
        x = fileIn.readInt();
        y = fileIn.readInt();
        width = fileIn.readInt();
        height = fileIn.readInt();
        dir = fileIn.readInt();
        link = new Player(x, y, width, height, dir);
        all.add(link);
        num = fileIn.readInt(); // walls.size();
        for(int i = 0; i < num; i++) {
            x = fileIn.readInt();
            y = fileIn.readInt();
            width = fileIn.readInt();
            height = fileIn.readInt();
            Wall wall = new Wall(x, y, width, height);
            walls.add(wall);
            all.add(wall);
        }
        num = fileIn.readInt(); // blocks.size();
        for(int i = 0; i < num; i++) {
            x = fileIn.readInt();
            y = fileIn.readInt();
            width = fileIn.readInt();
            height = fileIn.readInt();
            MobileObject mobileObject = new MobileObject(x, y, width, height);
            blocks.add(mobileObject);
            all.add(mobileObject);
        }
        num = fileIn.readInt(); // comps.size();
        for(int i = 0; i < num; i++) {
            x = fileIn.readInt();
            y = fileIn.readInt();
            width = fileIn.readInt();
            height = fileIn.readInt();
            dir = fileIn.readInt();
            Computer comp = new Computer(x, y, width, height, dir);
            comps.add(comp);
            all.add(comp);
        }
        num = fileIn.readInt(); // arrows.size();
        for(int i = 0; i < num; i++) {
            x = fileIn.readInt();
            y = fileIn.readInt();
            width = fileIn.readInt();
            height = fileIn.readInt();
            dir = fileIn.readInt();
            Player player = new Player(x, y, width, height, dir);
            arrows.add(player);
            all.add(player);
        }
    }
}