import javax.swing.JApplet;

import java.util.Random;
import java.util.ArrayList;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Execution extends JApplet {
    public static Random rand;
    public static int xSize = 600, ySize = 600;
    public static int width = 600, height = 600;
//     public static ArrayList<Rectangle> rectangle = new ArrayList<Rectangle>();
    public static ArrayList<Cell> cells = new ArrayList<Cell>();
    public static ArrayList<Food> food = new ArrayList<Food>();
    
    public static int chanceOfNewFood = 10;
    
    Image image;
    Graphics graphics;
    public void init() {
        rand = new Random();
        setSize(xSize, ySize);
        image = createImage(xSize, ySize);
        graphics = image.getGraphics();
        
        cells.add(new Cell(300, 300));
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
        for(int i = 0; i < cells.size(); i++) {
            g.setColor(Color.blue);
            g.fillOval((int)cells.get(i).x(), (int)cells.get(i).y(), 10, 10);
        }
        for(int i = 0; i < food.size(); i++) {
            g.setColor(Color.black);
            g.fillRect((int)food.get(i).x(), (int)food.get(i).y(), 5, 5);
        }
    }
    public static void move() {
        int filledCells;
        for(int i = 0; i < cells.size(); i++)
            filledCells += cells.get(i).filled();
        double chance = filledCells;
        if (rand.nextInt(75) > chance)
            food.add(newFood(rand.nextInt(width), rand.nextInt(height)));
    }
    public static void makeFood(int x, int y) {
        
    }
}