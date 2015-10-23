import javax.swing.JApplet;

import java.util.Random;
import java.util.ArrayList;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Formation extends JApplet {
    public static Random rand;
    public static int xSize = 600, ySize = 600;
    public static ArrayList<Element> elements = new ArrayList<Element>();
    public static boolean test = false;
    Image image;
    Graphics graphics;
    public void init() {
        rand = new Random();
        setSize(xSize, ySize);
        image = createImage(xSize, ySize);
        graphics = image.getGraphics();
        if (test) {
            for(int i = 0; i < 25; i++)
                elements.add(new Element(rand.nextInt(xSize), rand.nextInt(ySize)));
                for(int i = 0; i < elements.size(); i++) {
                Element e = elements.get(i);
                if (i % 5 > 0) {
                    e.assign(elements.get(i - 1));
                    elements.get(i - 1).assign(e);
                }
                if (i > 5) {
                    e.assign(elements.get(i - 5));
                    elements.get(i - 5).assign(e);
                }
            }
        } else {
            elements.add(new Element((int)(xSize * .2), (int)(ySize * .4)));
            elements.add(new Element((int)(xSize * .8), (int)(ySize * .6)));
            elements.get(0).assign(elements.get(1));
//             elements.get(1).assign(elements.get(0));
        }
    }
    public void paint(Graphics g) {
        move();
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
        g.fillRect(0, 0, xSize, ySize);
//         g.drawLine(0, -yMinPix, XSIZE, -yMinPix);
        for(int i = 0; i < elements.size(); i++) {
            g.setColor(Color.cyan);
            g.fillOval((int)elements.get(i).x(), (int)elements.get(i).y(), 20, 20);
            g.setColor(Color.black);
            g.drawString(Integer.toString(i), (int)elements.get(i).x() + 3, (int)elements.get(i).y() + 15);
        }
    }
    public static void move() {
        for(int i = 0; i < elements.size(); i++)
            elements.get(i).move();
    }
}