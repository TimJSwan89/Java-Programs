package zelda;

//=======================
//Title:    Zelda2
//Designer: Swan, Timothy
//=======================
import javax.swing.JOptionPane;

//import java.awt.*;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;

//import javax.swing.*;
import javax.swing.JApplet;

//import java.util.*;
import java.util.Random;
import java.util.ArrayList;

//import java.awt.event.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//import java.io.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Zelda extends JApplet {
 // Variables to set before running:
 double chanceOfWall = 3, chanceOfBlock = 5, chanceOfGuy = 2; // These are percents
 int speed = 7, rightWall = 1200, bottomWall = 800, screenWidth = 800, screenHeight = 800;
 boolean wrap = true, levelEditor = false;
 String loadName = "", saveName = "save.txt";
 
 int key, x, sx, y, sy, arrowDelay;
 int windowWidth = screenWidth, windowHeight = screenHeight;
 int sw, sh;
 double Xscale, Yscale, zoom;
 boolean upKey, downKey, leftKey, rightKey, plusKey, minusKey, spaceKey;
 Image image, mapImage, linkBody;
 Graphics graphics, mapGraphics, linkGraphics;
 Random rand = new Random();
 ArrayList<Wall> walls;
 ArrayList<MobileObject> blocks;
 ArrayList<Player> arrows;
 ArrayList<Computer> comps;
 ArrayList<Rectangle> all;
 Player link;
 public void init() {
//      int rw = JOptionPane.showInputDialog("Length of environment? (12 - 50)");
//      if (rw < 12)
//          rw = 12;
//      if (rw > 50)
//          rw = 50;
//      rightWall = rw * 50;
//      int bw = JOptionPane.showInputDialog("Height of environment? (12 - 50)");
//      if (bw < 12)
//          bw = 12;
//      if (bw > 50)
//          bw = 50;
//      bottomWall = bw * 50;
//      int p = JOptionPane.showConfirmDialog(null, "Wrapped environment? \n(Cancel means no)");
//      if (p == JOptionPane.YES_OPTION)
//          wrap = true;
     
     //setSize(screenWidth, screenHeight);
     if (loadName.equals("")) {
         walls = new ArrayList<Wall>();
         blocks = new ArrayList<MobileObject>();
         arrows = new ArrayList<Player>();
         comps = new ArrayList<Computer>();
         all = new ArrayList<Rectangle>();
         link = new Player(0, 0, 50, 50, 0);
         setSize(screenWidth, screenHeight);
         setScale();
         linkBody = createImage(50, 50);
         linkGraphics = linkBody.getGraphics();
         Link.initialize(linkGraphics);
         mapImage = createImage(rightWall, bottomWall);        
         mapGraphics = mapImage.getGraphics();
         for (int a = 0; a < rightWall / 50; a++)
             for (int b = 0; b < bottomWall / 50; b++) {
                 double num = rand.nextDouble() * 100;
                 if (num < chanceOfWall) { // 5% chance
                     walls.add(new Wall(a * 50, b * 50, 50, 50));//rand.nextInt(21) + 30, rand.nextInt(21) + 30));
                 }
                 else  {
                     if (num < chanceOfWall + chanceOfBlock) // 15% chance
                         blocks.add(new MobileObject(a * 50, b * 50, rand.nextInt(21) + 30, rand.nextInt(21) + 30));
                     else if (num < chanceOfWall + chanceOfBlock + chanceOfGuy)
                         comps.add(new Computer(a * 50, b * 50, rand.nextInt(21) + 30, rand.nextInt(21) + 30, rand.nextInt(4)));
                 }
             }
     } else {
         load();
     }
     for (int a = 0; a < rightWall / 50; a++)
             for (int b = 0; b < bottomWall / 50; b++) {
                 mapGraphics.setColor(new Color(rand.nextInt(31) + 215,rand.nextInt(31) + 215,rand.nextInt(31) + 215));
                 mapGraphics.fillRect(a * 50, b * 50, 50, 50);
             }
     for(Wall wall : walls) {
         mapGraphics.fillRect(wall.left(), wall.top(), wall.width(), wall.height());
     }
//      mapGraphics.setColor(Color.black);
//      mapGraphics.fillRect(0, 0, rightWall, 5);
//      mapGraphics.fillRect(0, 0, 5, bottomWall);
         
     all.add(link);
     for(Rectangle wall : walls)
         all.add(wall);
     for(Rectangle block : blocks)
         all.add(block);
     for(Computer comp : comps)
         all.add(comp);
         
     image = createImage(getSize().width, getSize().height);
     graphics = image.getGraphics();
     addKeyListener(new KeyAdapter() {
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
                 case KeyEvent.VK_S: {
                     save();
                     break;
                 }
                 case KeyEvent.VK_L: {
                     load();
                     break;
                 }
                 case KeyEvent.VK_EQUALS: {
                     if (zoom >= 0)
                         zoom = -1;
                     else if (zoom > -32)
                         zoom *= 1.2;
                     break;
//                       screenWidth -= 1;
//                       screenHeight -= 1;
//                       setScale();
//                       break;
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
 }
 public void paint(Graphics g) {
     //for (int i = 0 ; i < 1000000; i ++);
     moveAll();
     //graphics = g;
     //g = graphics;
     //if ((new Random()).nextInt(200) == 0)
         
      sw = windowWidth;
      sh = windowHeight;
      windowWidth = screenWidth;
      windowHeight = screenHeight;
      setScale();
     
     printAll();  
     
     g.drawImage(image, 0, 0, /*sw, sh,*/600, 600, 0, 0, screenWidth, screenHeight, null);
     //g.drawImage(image, 0, 0, windowWidth, windowWidth, null);
      windowWidth = sw;
      windowHeight = sh;
     //g = (Graphics) image.getGraphics().create();
     //graphics = g;
//      g.clearRect(30, 0, 60, 10);
//      g.drawString("" + link.left() + " " + link.width() + " " + screenWidth + " " + x, 30, 10);
     try {
         Thread.currentThread().sleep(3);
     } catch (InterruptedException e) {
     }
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
//      upKey = downKey = leftKey = rightKey = false;
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
     for(Computer comp : comps)
         comp.move(all, 14/*rand.nextInt(5) + 10*/, rightWall, bottomWall, wrap);
         
//      if (plusKey) {
//          if (screenWidth > 0)
//              screenWidth--;
//          if (screenHeight > 0)
//              screenHeight--;
//          setScale();
////             Xscale = (double) windowWidth / screenWidth;
////             Yscale = (double) windowHeight / screenHeight;
//          //setSize(screenWidth, screenHeight);
//      }
//        if (minusKey) {
//          if (screenWidth < rightWall) {
//              screenWidth += zoom;
//              if
//          }
//          if (screenHeight < bottomWall)
//              screenHeight++;
         setScale();
//          Xscale = (double) windowWidth / screenWidth;
//          Yscale = (double) windowHeight / screenHeight;
         //setSize(screenWidth, screenHeight);
         
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
         
         for(Player arrow : arrows)
             if (arrow.pushDirection(all, 9, rightWall, bottomWall, wrap) == 0)
                 arrow.remove(all, arrows);
         
     }
//      if (spaceKey == 1)
//{
//              link.shootArrow();
//              spaceKey = 2;
//          }
//      for(int i = 0; i < arrows.size(); i++)
//          if (!arrows.get(i).moveArrow())
//{
//                  arrows.remove(i);
//                  i--;
//              }
// }
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

//          int width = (int) (XSd(block.right() - x) - XSd(block.left() - x));
//          int height = (int) (YSd(block.bottom() - x) - YSd(block.top() - x));
//          graphics.fillRect(XS(block.left() - x), YS(block.top() - y), width, height);
         
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
         graphics.fillRect(arrow.left() - x, arrow.top() - y, arrow.width(), arrow.height);
         
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
 private void save() {
     //try {
         Filer fileOut = new Filer(saveName);
         fileOut.write(speed);
         //fileOut.newLine();
         fileOut.write(rightWall);
         //fileOut.newLine();
         fileOut.write(bottomWall);
         //fileOut.newLine();
         fileOut.write(screenWidth);
         //fileOut.newLine();
         fileOut.write(screenHeight);
         //fileOut.newLine();
         fileOut.write(wrap ? "true" : "false");
         //fileOut.newLine();
         fileOut.write(link.left());
         //fileOut.newLine();
         fileOut.write(link.top());
         //fileOut.newLine();
         fileOut.write(link.width());
         //fileOut.newLine();
         fileOut.write(link.height());
         //fileOut.newLine();
         fileOut.write(link.getDirection());
         //fileOut.newLine();
         fileOut.write(walls.size());
         //fileOut.newLine();
         for(Rectangle wall : walls) {
             fileOut.write(wall.left());
             //fileOut.newLine();
             fileOut.write(wall.top());
             //fileOut.newLine();
             fileOut.write(wall.width());
             //fileOut.newLine();
             fileOut.write(wall.height());
             //fileOut.newLine();
         }
         fileOut.write(blocks.size());
         //fileOut.newLine();
         for(Rectangle block : blocks) {
             fileOut.write(block.left());
             //fileOut.newLine();
             fileOut.write(block.top());
             //fileOut.newLine();
             fileOut.write(block.width());
             //fileOut.newLine();
             fileOut.write(block.height());
             //fileOut.newLine();
         }
         fileOut.write(comps.size());
         //fileOut.newLine();
         for(Computer comp : comps) {
             fileOut.write(comp.left());
             //fileOut.newLine();
             fileOut.write(comp.top());
             //fileOut.newLine();
             fileOut.write(comp.width());
             //fileOut.newLine();
             fileOut.write(comp.height());
             //fileOut.newLine();
             fileOut.write(comp.getDirection());
             //fileOut.newLine();
         }
         fileOut.write(arrows.size());
         //fileOut.newLine();
         for(Player arrow : arrows) {
             fileOut.write(arrow.left());
             //fileOut.newLine();
             fileOut.write(arrow.top());
             //fileOut.newLine();
             fileOut.write(arrow.width());
             //fileOut.newLine();
             fileOut.write(arrow.height());
             //fileOut.newLine();
             fileOut.write(arrow.getDirection());
             //fileOut.newLine();
         }
//      } catch(IOException e) {
//          rightKey = true;
//      }
 }
 public void load() {
     try {
         FileReader file = new FileReader(loadName);
         BufferedReader fileIn = new BufferedReader(file);
         speed = Integer.parseInt(fileIn.readLine());
         rightWall = Integer.parseInt(fileIn.readLine());
         bottomWall = Integer.parseInt(fileIn.readLine());
         screenWidth = Integer.parseInt(fileIn.readLine());
         screenHeight = Integer.parseInt(fileIn.readLine());
         wrap = fileIn.readLine().equals("true");
         int x, y, width, height, dir, num;
         x = Integer.parseInt(fileIn.readLine());
         y = Integer.parseInt(fileIn.readLine());
         width = Integer.parseInt(fileIn.readLine());
         height = Integer.parseInt(fileIn.readLine());
         dir = Integer.parseInt(fileIn.readLine());
         link = new Player(x, y, width, height, dir);
         num = Integer.parseInt(fileIn.readLine()); // walls.size();
         for(int i = 0; i < num; i++) {
             x = Integer.parseInt(fileIn.readLine());
             y = Integer.parseInt(fileIn.readLine());
             width = Integer.parseInt(fileIn.readLine());
             height = Integer.parseInt(fileIn.readLine());
             walls.add(new Wall(x, y, width, height));
         }
         num = Integer.parseInt(fileIn.readLine()); // blocks.size();
         for(int i = 0; i < num; i++) {
             x = Integer.parseInt(fileIn.readLine());
             y = Integer.parseInt(fileIn.readLine());
             width = Integer.parseInt(fileIn.readLine());
             height = Integer.parseInt(fileIn.readLine());
             blocks.add(new MobileObject(x, y, width, height));
         }
         num = Integer.parseInt(fileIn.readLine()); // comps.size();
         for(int i = 0; i < num; i++) {
             x = Integer.parseInt(fileIn.readLine());
             y = Integer.parseInt(fileIn.readLine());
             width = Integer.parseInt(fileIn.readLine());
             height = Integer.parseInt(fileIn.readLine());
             dir = Integer.parseInt(fileIn.readLine());
             comps.add(new Computer(x, y, width, height, dir));
         }
         num = Integer.parseInt(fileIn.readLine()); // arrows.size();
         for(int i = 0; i < num; i++) {
             x = Integer.parseInt(fileIn.readLine());
             y = Integer.parseInt(fileIn.readLine());
             width = Integer.parseInt(fileIn.readLine());
             height = Integer.parseInt(fileIn.readLine());
             dir = Integer.parseInt(fileIn.readLine());
             arrows.add(new Player(x, y, width, height, dir));
         }
     } catch(IOException e) {
         upKey = true;
     }
 }
}