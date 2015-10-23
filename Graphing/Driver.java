import javax.swing.JApplet;

import java.util.Random;
import java.util.ArrayList;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Driver extends JApplet {

    public static int TOTALPOINTS = 1000; // total points plotted
    public static int XSUBSECTIONS = 10, YSUBSECTIONS = 10; // plot labels
    public static double XMIN = -15, XMAX = 5, YMIN = -10, YMAX = 10; // number
    public static final int XSIZE = 600, YSIZE = 600; // pixel
    
    public static double xWidth = XMAX - XMIN, yHeight = YMAX - YMIN; // num
    public static double xRatio = (double) xWidth / XSIZE, yRatio = (double) yHeight / YSIZE; // num / pixel
    public static int xMinPix = (int)(XMIN / xRatio), yMinPix = (int)(YMIN / yRatio);
    public static double incrementer = ((double) XMAX - XMIN) / TOTALPOINTS; // number
    public static double xSubsectionSize = xWidth / XSUBSECTIONS, ySubsectionSize = yHeight / YSUBSECTIONS; // num
    
    public static boolean upKey, downKey, leftKey, rightKey, zoomIn, zoomOut;
    public static int key;
    public static Random rand;
    Image image;
    Graphics graphics;
    public void init() {
        rand = new Random();
        setSize(XSIZE, YSIZE);
        image = createImage(XSIZE, YSIZE);
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
                        break;
                    }
                    case KeyEvent.VK_S: {
                        break;
                    }
                    case KeyEvent.VK_L: {
                        break;
                    }
                    case KeyEvent.VK_EQUALS: {
                        zoomIn = true;
                        break;
                    }
                    case KeyEvent.VK_MINUS: {
                        zoomOut = true;
                        break;
                    }
                    case KeyEvent.VK_0: {
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
                        break;
                    }
                    case KeyEvent.VK_EQUALS: {
                        zoomIn = false;
                        break;
                    }
                    case KeyEvent.VK_MINUS: {
                        zoomOut = false;
                        break;
                    }
                }
            }
        });
    }
    public void paint(Graphics g) {
        control();
        draw(graphics);
        g.drawImage(image, 0, 0, null);
        Thread thread = Thread.currentThread();
        try {
            thread.sleep(5);
        } catch (InterruptedException e) {
        }
        repaint();
    }
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, XSIZE, YSIZE);
        g.setColor(Color.cyan);
        g.drawLine(0, -yMinPix, XSIZE, -yMinPix); // x-axis
        g.drawLine(-xMinPix, 0, -xMinPix, YSIZE); // y-axis
        for(int i = 0; i < XSUBSECTIONS + 1; i++) { // vertical lines along x-axis
            double x = -XMIN + i * xSubsectionSize;
            int xPix = (int)(x / xRatio);
           // (int)(i * ((double)XSIZE / XSUBSECTIONS));
            g.drawLine(xPix, -yMinPix - 5, xPix, -yMinPix + 5);
            String string = Double.toString(((double)((int) (x * 10))) / 10);
            g.drawString(string, xPix - (int)(string.length() * ((double)17 / 5)), -yMinPix - 6);
        }
        for(int i = 1; i < YSUBSECTIONS; i++) { // horizontal lines along y-axis
            int y = (int)(i * ((double)YSIZE / YSUBSECTIONS));
            g.drawLine(-xMinPix - 5, y, -xMinPix + 5, y);
            g.drawString(Integer.toString(-(int)(i * yHeight / YSUBSECTIONS + YMIN)), -xMinPix + 10, y + 4);
        }
        double x = 0;
        double[] y = new double[4];
        int xold;
        int[] yold = new int[4];
        for(double j = XMIN; j < XMAX; j += incrementer) {
            xold = (int)x;
            x = j;
            for(int i = 0; i < y.length; i++)
                yold[i] = (int)y[i];
            
//             for(int i = -30; i < 30; i++)
//                 y[i + 30] = Math.pow(x, i);
//             for(int i = -30; i < 30; i++)
//                 y[i + 90] = -Math.pow(x, i);
            y[0] = Math.pow(x, 2) + Math.pow(x, 2);
            y[1] = -Math.pow(x, 3);
            y[2] = Math.log(x);
            y[3] = Math.sqrt(x);
            
            x = x / xRatio - xMinPix;
            for(int i = 0; i < y.length; i++)
                y[i] = -y[i] / yRatio - yMinPix;
            if (j > XMIN) {
                for(int i = 0; i < y.length; i++) {
                    Color color;
                    if (i == 0)
                        color = Color.white;
                    else if (i == 1)
                            color = Color.yellow;
                        else if (i == 2)
                                color = Color.magenta;
                            else
                                color = Color.pink;
                    g.setColor(color);
                  //  if (y[i] != Integer.MAX_VALUE && y[i] != Integer.MIN_VALUE && yold[i] != Integer.MAX_VALUE && yold[i] != Integer.MIN_VALUE)
                    if (y[i] != Double.NaN && yold[i] != Integer.MIN_VALUE)
                        g.drawLine((int)x, (int)y[i], xold, yold[i]);
                }
            }
        }
    }
    public static void control() {
        if (upKey) {
            YMIN -= yHeight / 50;
            YMAX -= yHeight / 50;
        }
        if (downKey) {
            YMIN += yHeight / 50;
            YMAX += yHeight / 50;
        }
        if (leftKey) {
            XMIN -= xWidth / 50;
            XMAX -= xWidth / 50;
        }
        if (rightKey) {
            XMIN += xWidth / 50;
            XMAX += xWidth / 50;
        }
        if (zoomIn) {
            XMIN += xWidth / 50;
            XMAX -= xWidth / 50;
            YMIN += yHeight / 50;
            YMAX -= yHeight / 50;
        }
        if (zoomOut) {
            XMIN -= xWidth / 50;
            XMAX += xWidth / 50;
            YMIN -= yHeight / 50;
            YMAX += yHeight / 50;
        }
        xWidth = XMAX - XMIN;
        yHeight = YMAX - YMIN; // num
        xRatio = (double) xWidth / XSIZE;
        yRatio = (double) yHeight / YSIZE; // num / pixel
        xMinPix = (int)(XMIN / xRatio);
        yMinPix = (int)(YMIN / yRatio);
        incrementer = ((double) XMAX - XMIN) / TOTALPOINTS; // number
        xSubsectionSize = xWidth / XSUBSECTIONS;
        ySubsectionSize = yHeight / YSUBSECTIONS;
    }
}