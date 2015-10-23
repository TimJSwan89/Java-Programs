// Program designed by Timothy J Swan

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

public class Environment extends JApplet {
    boolean upKey, downKey, leftKey, rightKey, plusKey, minusKey, greaterThanKey, lessThanKey, spaceKey, moving, c, v, toggle;
    double[][] coordinates = new double[1000][3];
    Cube[] cube = new Cube[100];
    Sphere sphere, sphere2, sphere3, sphere4;
    int s = 500, key, count = 0;
    double zCube = 50;
    double xCube = 0;
    double yCube = 0;
    double xCam = 0, yCam = 0, zCam = 0, xThetaCam = 0, yThetaCam = 0;
    Image image;
    Graphics graphics;
    public void init() {
        image = createImage(600, 600);
        graphics = image.getGraphics();
        for(int i = 0; i < 100; i++) {
            cube[i] = new Cube(10, xCube, yCube + 10, zCube);
        }
        sphere = new Sphere(xCube - 15, yCube - 15, zCube, false, false);
        sphere2 = new Sphere(xCube + 15, yCube - 15, zCube, false, true);
        sphere3 = new Sphere(xCube - 15, yCube + 15, zCube, true, false);
        sphere4 = new Sphere(xCube + 15, yCube + 15, zCube, true, true);
        for(int i = 0; i < 1000; i++) {
            coordinates[i][0] = xCube;
            coordinates[i][1] = yCube;
            coordinates[i][2] = zCube;
        }
 
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
                    case KeyEvent.VK_EQUALS: {
                        plusKey = true;
                        break;
                    }
                    case KeyEvent.VK_MINUS: {
                        minusKey = true;
                        break;
                    }
                    case KeyEvent.VK_PERIOD: {
                        greaterThanKey = true;
                        break;
                    }
                    case KeyEvent.VK_COMMA: {
                        lessThanKey = true;
                        break;
                    }
                    case KeyEvent.VK_C: {
                        c = true;
                        break;
                    }
                    case KeyEvent.VK_V: {
                        v = true;
                        break;
                    }
                    case KeyEvent.VK_T: {
                        toggle = !toggle;
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
                        plusKey = false;
                        break;
                    }
                    case KeyEvent.VK_MINUS: {
                        minusKey = false;
                        break;
                    }
                    case KeyEvent.VK_PERIOD: {
                        greaterThanKey = false;
                        break;
                    }
                    case KeyEvent.VK_COMMA: {
                        lessThanKey = false;
                        break;
                    }
                    case KeyEvent.VK_C: {
                        c = false;
                        break;
                    }
                    case KeyEvent.VK_V: {
                        v = false;
                        break;
                    }
                }
            }
        });
    }
    public void paint(Graphics g) {
//         image = createImage(600, 600);
//         graphics = image.getGraphics();
//        if (moving)
        //move();
        drawings(graphics);
        move();
        g.drawImage(image, 0, 0, null);
//         for(int i = 0; i < 100000; i++)
//             for(int j = 0; j < 1000000; j++)
//                 for(int k = 0; k < 100000; k++)
                
        repaint();
    }
    public void drawings(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, 600, 600);
        if (count == 1000)
            count = 0;
        coordinates[count][0] = xCube;
        coordinates[count][1] = yCube;
        coordinates[count][2] = zCube;
        //count++;
        int j = count;
        for(int i = 0; i < 100; i++) {
            if (j >= 1000)
                j -= 1000;
            cube[i].move(coordinates[j][0], coordinates[j][1], coordinates[j][2]);
            cube[i].draw(g, s, xCam, yCam, zCam, xThetaCam, yThetaCam);
            j += 20;
        }
        if (!toggle) {
            sphere.draw(g, s, xCam, yCam, zCam, xThetaCam, yThetaCam);
            sphere2.draw(g, s, xCam, yCam, zCam, xThetaCam, yThetaCam);
            sphere3.draw(g, s, xCam, yCam, zCam, xThetaCam, yThetaCam);
            sphere4.draw(g, s, xCam, yCam, zCam, xThetaCam, yThetaCam);
        }
    }
    public void move() {
        moving = ((upKey || downKey || leftKey || rightKey || greaterThanKey || lessThanKey) && !spaceKey && !c && !v);
        if (moving)
            count++;
        if (spaceKey) {
            if (upKey)
                for(int i = 0; i < 100; i++)
                    cube[i].xRotate(-.1);
            if (downKey)
                for(int i = 0; i < 100; i++)
                    cube[i].xRotate(.1);
            if (leftKey)
                for(int i = 0; i < 100; i++)
                    cube[i].yRotate(-.1);
            if (rightKey)
                for(int i = 0; i < 100; i++)
                    cube[i].yRotate(.1);
            if (greaterThanKey)
                for(int i = 0; i < 100; i++)
                    cube[i].zRotate(.1);
            if (lessThanKey)
                for(int i = 0; i < 100; i++)
                    cube[i].zRotate(-.1);
        } else if (c) {
            if (upKey)
                yCam--;
            if (downKey)
                yCam++;
            if (leftKey)
                xCam--;
            if (rightKey)
                xCam++;
            if (greaterThanKey) {
//                 zCam -= Math.sin(/*Math.atan2(zCam, xCam) +*/ yThetaCam);
//                 xCam -= Math.cos(/*Math.atan2(zCam, xCam) + */yThetaCam);
                zCam++;
            }
            if (lessThanKey)
                zCam--;
            } else if (v) {
                if (upKey)
                for(int i = 0; i < 100; i++) {
                        double yrot = cube[i].y - yCam;
                        double zrot = cube[i].z - zCam;
                        double r = Math.sqrt(yrot * yrot + zrot * zrot);
                        double angle = Math.atan2(zrot, yrot) - 0.1;
                        cube[i].y = r * Math.cos(angle) + yCam;
                        cube[i].z = r * Math.sin(angle) + zCam;
                        for(int j = 0; j < 12; j++) {
                            yrot = cube[i].y1[j] + cube[i].y - yCam;
                            zrot = cube[i].z1[j] + cube[i].z - zCam;
                            r = Math.sqrt(yrot * yrot + zrot * zrot);
                            angle = Math.atan2(zrot, yrot) - 0.1;
                            cube[i].y1[j] = r * Math.cos(angle) - cube[i].y + yCam;
                            cube[i].z1[j] = r * Math.sin(angle) - cube[i].z + zCam;
                            
                            yrot = cube[i].y2[j] + cube[i].y - yCam;
                            zrot = cube[i].z2[j] + cube[i].z - zCam;
                            r = Math.sqrt(yrot * yrot + zrot * zrot);
                            angle = Math.atan2(zrot, yrot) - 0.1;
                            cube[i].y2[j] = r * Math.cos(angle) - cube[i].y + yCam;
                            cube[i].z2[j] = r * Math.sin(angle) - cube[i].z + zCam;
                        }
                    }
                if (downKey)
                for(int i = 0; i < 100; i++) {
                        double yrot = cube[i].y - yCam;
                        double zrot = cube[i].z - zCam;
                        double r = Math.sqrt(yrot * yrot + zrot * zrot);
                        double angle = Math.atan2(zrot, yrot) + 0.1;
                        cube[i].y = r * Math.cos(angle) + yCam;
                        cube[i].z = r * Math.sin(angle) + zCam;
                        for(int j = 0; j < 12; j++) {
                            yrot = cube[i].y1[j] + cube[i].y - yCam;
                            zrot = cube[i].z1[j] + cube[i].z - zCam;
                            r = Math.sqrt(yrot * yrot + zrot * zrot);
                            angle = Math.atan2(zrot, yrot) + 0.1;
                            cube[i].y1[j] = r * Math.cos(angle) - cube[i].y + yCam;
                            cube[i].z1[j] = r * Math.sin(angle) - cube[i].z + zCam;
                            
                            yrot = cube[i].y2[j] + cube[i].y - yCam;
                            zrot = cube[i].z2[j] + cube[i].z - zCam;
                            r = Math.sqrt(yrot * yrot + zrot * zrot);
                            angle = Math.atan2(zrot, yrot) + 0.1;
                            cube[i].y2[j] = r * Math.cos(angle) - cube[i].y + yCam;
                            cube[i].z2[j] = r * Math.sin(angle) - cube[i].z + zCam;
                        }
                    }
                if (leftKey) 
                    for(int i = 0; i < 100; i++) {
                        double xrot = cube[i].x - xCam;
                        double zrot = cube[i].z - zCam;
                        double r = Math.sqrt(xrot * xrot + zrot * zrot);
                        double angle = Math.atan2(zrot, xrot) - 0.1;
                        cube[i].x = r * Math.cos(angle) + xCam;
                        cube[i].z = r * Math.sin(angle) + zCam;
                        for(int j = 0; j < 12; j++) {
                            xrot = cube[i].x1[j] + cube[i].x - xCam;
                            zrot = cube[i].z1[j] + cube[i].z - zCam;
                            r = Math.sqrt(xrot * xrot + zrot * zrot);
                            angle = Math.atan2(zrot, xrot) - 0.1;
                            cube[i].x1[j] = r * Math.cos(angle) - cube[i].x + xCam;
                            cube[i].z1[j] = r * Math.sin(angle) - cube[i].z + zCam;
                            
                            xrot = cube[i].x2[j] + cube[i].x - xCam;
                            zrot = cube[i].z2[j] + cube[i].z - zCam;
                            r = Math.sqrt(xrot * xrot + zrot * zrot);
                            angle = Math.atan2(zrot, xrot) - 0.1;
                            cube[i].x2[j] = r * Math.cos(angle) - cube[i].x + xCam;
                            cube[i].z2[j] = r * Math.sin(angle) - cube[i].z + zCam;
                        }
                    }
                if (rightKey) 
                    for(int i = 0; i < 100; i++) {
                        double xrot = cube[i].x - xCam;
                        double zrot = cube[i].z - zCam;
                        double r = Math.sqrt(xrot * xrot + zrot * zrot);
                        double angle = Math.atan2(zrot, xrot) + 0.1;
                        
                        cube[i].x = r * Math.cos(angle) + xCam;
                        cube[i].z = r * Math.sin(angle) + zCam;
//                         double newx = r * Math.cos(angle) + xCam;
//                         double newz = r * Math.sin(angle) + zCam;

                        for(int j = 0; j < 12; j++) {
                            xrot = cube[i].x1[j] + cube[i].x - xCam;
                            zrot = cube[i].z1[j] + cube[i].z - zCam;
                            r = Math.sqrt(xrot * xrot + zrot * zrot);
                            angle = Math.atan2(zrot, xrot) + 0.1;
                            
                            cube[i].x1[j] = r * Math.cos(angle) - cube[i].x + xCam;
                            cube[i].z1[j] = r * Math.sin(angle) - cube[i].z + zCam;
//                             cube[i].x1[j] = r * Math.cos(angle) - newx + xCam;
//                             cube[i].z1[j] = r * Math.sin(angle) - newz + zCam;
                            
                            xrot = cube[i].x2[j] + cube[i].x - xCam;
                            zrot = cube[i].z2[j] + cube[i].z - zCam;
                            r = Math.sqrt(xrot * xrot + zrot * zrot);
                            angle = Math.atan2(zrot, xrot) + 0.1;
                            
                            cube[i].x2[j] = r * Math.cos(angle) - cube[i].x + xCam;
                            cube[i].z2[j] = r * Math.sin(angle) - cube[i].z + zCam;
//                             cube[i].x2[j] = r * Math.cos(angle) - newx + xCam;
//                             cube[i].z2[j] = r * Math.sin(angle) - newz + zCam;
                        }
//                         cube[i].x = newx;
//                         cube[i].z = newz;
                    }
                
//                     yThetaCam += .1;
//                 if (greaterThanKey)
//                     zCube++;
//                 if (lessThanKey)
//                     zCube--;
                } else {
                    if (upKey)
                        yCube--;
                    if (downKey)
                        yCube++;
                    if (leftKey)
                        xCube--;
                    if (rightKey)
                        xCube++;
                    if (greaterThanKey)
                        zCube++;
                    if (lessThanKey)
                        zCube--;
                }
        if (plusKey)
            s++;
        if (minusKey)
            s--;
    }
}