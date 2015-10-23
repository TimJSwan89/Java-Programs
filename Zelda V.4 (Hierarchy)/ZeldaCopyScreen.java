// // =======================
// // Title:    Zelda2
// // Designer: Swan, Timothy
// // =======================
// import java.awt.*;
// import javax.swing.*;
// import java.applet.*;
// import java.util.*;
// import java.io.*;
// import java.awt.event.*;
// 
// public class ZeldaCopyScreen extends JApplet 
// {   
//     int key, x, y, arrowDelay, speed = 2, spaceKey, rightWall = 2500, bottomWall = 2500, screenWidth = 600, screenHeight = 600;
//     boolean upKey, downKey, leftKey, rightKey, bound = true;
//     Image image, backGround, environment, linkBody;
//     Graphics graphics, backGroundGraphics, environmentGraphics, linkGraphics;
//     Random rand = new Random();
//     ArrayList<Wall> walls = new ArrayList<Wall>();
//     ArrayList<MobileObject> blocks = new ArrayList<MobileObject>();
//     ArrayList<Rectangle> all = new ArrayList<Rectangle>();
//     Player link = new Player(105, 105, 50, 50, 0);
//     public void init() 
//     {
//         if (rightWall < screenWidth)
//             screenWidth = rightWall;
//         if (bottomWall < screenHeight)
//             screenHeight = bottomWall;
//         setSize(screenWidth, screenHeight);
//         linkBody = createImage(50, 50);
//         linkGraphics = linkBody.getGraphics();
//         Link.initialize(linkGraphics);
//         backGround = createImage(rightWall, bottomWall);        
//         backGroundGraphics = backGround.getGraphics();
//         for (int a = 0; a < 50; a++)
//             for (int b = 0; b < 50; b++) 
//             {
//                 int num = rand.nextInt(100);
//                 if (num < 0) // 10% chance
//                 {
//                     backGroundGraphics.setColor(new Color(rand.nextInt(60),rand.nextInt(60),rand.nextInt(60)));
//                     walls.add(new Wall(a * 50, b * 50, 50, 50));//rand.nextInt(21) + 30, rand.nextInt(21) + 30));
//                 }
//                 else 
//                 {
//                     backGroundGraphics.setColor(new Color(rand.nextInt(31) + 225,rand.nextInt(31) + 225,rand.nextInt(31) + 225));
//                     if (num < 20) // 10% chance
//                         blocks.add(new MobileObject(a * 50, b * 50, rand.nextInt(21) + 30, rand.nextInt(21) + 30));
//                 }
//                 backGroundGraphics.fillRect(a * 50, b * 50, 50, 50);
//             }
//         
//         for(int i = 0; i < walls.size(); i++)
//             all.add(walls.get(i));
//         for(int i = 0; i < blocks.size(); i++)
//             all.add(blocks.get(i));
//         image = createImage(getSize().width, getSize().height);
//         graphics = image.getGraphics();
//         environment = createImage(rightWall, bottomWall);
//         environmentGraphics = environment.getGraphics();
//         addKeyListener(new KeyAdapter()
//         {
//             public void keyPressed(KeyEvent evt)
//             {
//                 key = evt.getKeyCode();
//                 switch(key)
//                 {
//                     case KeyEvent.VK_UP:
//                     {
//                         upKey = true;
//                         break;
//                     }
//                     case KeyEvent.VK_DOWN:
//                     {
//                         downKey = true;
//                         break;
//                     }
//                     case KeyEvent.VK_LEFT:
//                     {
//                         leftKey = true;
//                         break;
//                     }
//                     case KeyEvent.VK_RIGHT:
//                     {
//                         rightKey = true;
//                         break;
//                     }
//                     case KeyEvent.VK_SPACE:
//                     {
//                         spaceKey = 1;
//                         break;
//                     }
//                 }
//             }
//             public void keyReleased(KeyEvent evt)
//             {
//                 key = evt.getKeyCode();
//                 switch(key)
//                 {
//                     case KeyEvent.VK_UP:
//                     {
//                         upKey = false;
//                         break;
//                     }
//                     case KeyEvent.VK_DOWN:
//                     {
//                         downKey = false;
//                         break;
//                     }
//                     case KeyEvent.VK_LEFT:
//                     {
//                         leftKey = false;
//                         break;
//                     }
//                     case KeyEvent.VK_RIGHT:
//                     {
//                         rightKey = false;
//                         break;
//                     }
//                     case KeyEvent.VK_SPACE:
//                     {
//                         spaceKey = 0;
//                         break;
//                     }
//                 }
//             }
//         });
//     }
//     public void paint(Graphics g)
//     {
//         moveAll();
//         printAll();
//         g.drawImage(image, 0, 0, null);
//         repaint();
//     }
//     public void moveAll()
//     {
//         if (upKey)
//             link.pushUp(all, speed);
//         if (downKey)
//             link.pushDown(all, speed, bottomWall);
//         if (leftKey)
//             link.pushLeft(all, speed);
//         if (rightKey)
//             link.pushRight(all, speed, rightWall);
// //         if (spaceKey == 1)
// //             {
// //                 link.shootArrow();
// //                 spaceKey = 2;
// //             }
// //         for(int i = 0; i < arrows.size(); i++)
// //             if (!arrows.get(i).moveArrow())
// //             {
// //                 arrows.remove(i);
// //                 i--;
// //             }
//     }
//     public void printAll()
//     {
//         center(link);
//         environmentGraphics = backGroundGraphics.create();
//        // environmentGraphics.drawImage(backGround, 0, 0, null);
//         environmentGraphics.setColor(Color.cyan);
//         for(int i = 0; i < blocks.size(); i++)
//             environmentGraphics.fillRect(blocks.get(i).left(), blocks.get(i).top(), blocks.get(i).width(), blocks.get(i).height());
//         Link.draw(link.getDirection(), linkGraphics);
//         environmentGraphics.drawImage(linkBody, link.left(), link.top(), link.width(), link.height(), null);
//         graphics.drawImage(environment, 0, 0, screenWidth, screenHeight, x, y, x + screenWidth, y + screenHeight, null);
//     }
//     public void center(Rectangle inRect)
//     {
//         if (bound && inRect.left() < screenWidth / 2 - inRect.width() / 2) // 275
//             x = 0;
//         else if (bound && inRect.left() > rightWall - screenWidth / 2 - inRect.width() / 2) // 2175
//                 x = rightWall - screenWidth; // 1900
//              else
//                 x = inRect.left() - screenWidth / 2 + inRect.width() / 2; // left - 275
// 
//         if (bound && inRect.top() < screenHeight / 2 - inRect.height() / 2)
//             y = 0;
//         else if (bound && inRect.top() > bottomWall - screenHeight / 2 - inRect.height() / 2)
//                 y = bottomWall - screenHeight;
//              else
//                 y = inRect.top() - screenHeight / 2 + inRect.height() / 2;
//     }
// }