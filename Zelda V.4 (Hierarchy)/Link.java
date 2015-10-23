import java.awt.*;
import javax.swing.*;
import java.applet.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;
public class Link //extends JApplet
{
    private static ArrayList<Graphics> linkMan = new ArrayList<Graphics>();
    private static Graphics gr;
    public static void initialize(Graphics gp)
    {
        gp.clearRect(0,0,50,50);
        linkMan.add(gp.create());
        gr = linkMan.get(0);
        int[] x = {25, 41, 41, 50, 45, 41, 41, 16, 16,  5,  0, 16, 16};
        int[] y = { 0, 18, 25, 35, 40, 35, 45, 45, 35, 40, 35, 25, 18};
        gr.setColor(Color.green);
        gr.fillPolygon(x, y, 13);
        gr.setColor(new Color(100,100,100));
        gr.fillRect(12, 45, 11, 5);
        gr.fillRect(27, 45, 11, 5);
        
        linkMan.add(gr.create());
        gr = linkMan.get(1);
        gr.setColor(Color.black);
        gr.fillOval(18,20,4,4);
        gr.fillOval(28,20,4,4);
        
        linkMan.add(gp.create());
        gr = linkMan.get(2);
        int[] x2 = {25, 41, 41, 16, 16,  5,  0, 16, 16};
        int[] y2 = { 0, 18, 45, 45, 35, 40, 35, 25, 18};
        gr.setColor(Color.green);
        gr.fillPolygon(x2, y2, 9);  
        gr.setColor(Color.black);
        gr.fillOval(18,20,4,4);
        gr.setColor(new Color(100,100,100));
        gr.fillRect(12, 45, 11, 5);
        //gr.fillRect(27, 45, 11, 5);
        
        linkMan.add(gp.create());
        gr = linkMan.get(3);
        int[] x3 = {25, 41, 41, 50, 45, 41, 41, 16, 16};
        int[] y3 = { 0, 18, 25, 35, 40, 35, 45, 45, 18};
        gr.setColor(Color.green);
        gr.fillPolygon(x3, y3, 9);  
        gr.setColor(Color.black);
        gr.fillOval(28,20,4,4);
        gr.setColor(new Color(100,100,100));
        //gr.fillRect(12, 45, 11, 5);
        gr.fillRect(27, 45, 11, 5);
    }
    public static Graphics draw(int inFacing, Graphics blank)
    {
        Random rand = new Random();
        blank.clearRect(0,0,50,50);
        gr = blank;
        if (inFacing == 0)
        {
            int[] x = {25, 41, 41, 50, 45, 41, 41, 16, 16,  5,  0, 16, 16};
            for(int i = 0; i < x.length; i++)
                x[i] = x[i] + rand.nextInt(3) - 1;
            int[] y = { 0, 18, 25, 35, 40, 35, 45, 45, 35, 40, 35, 25, 18};
            for(int i = 0; i < y.length; i++)
                y[i] = y[i] + rand.nextInt(3) - 1;
            gr.setColor(Color.green);
            gr.fillPolygon(x, y, 13);
            gr.setColor(new Color(100,100,100));
            gr.fillRect(12, 45, 11, 5);
            gr.fillRect(27, 45, 11, 5);
            //return gr;
        } else if (inFacing == 1)
        {
            int[] x = {25, 41, 41, 50, 45, 41, 41, 16, 16,  5,  0, 16, 16};
            for(int i = 0; i < x.length; i++)
                x[i] = x[i] + rand.nextInt(3) - 1;
            int[] y = { 0, 18, 25, 35, 40, 35, 45, 45, 35, 40, 35, 25, 18};
            for(int i = 0; i < y.length; i++)
                y[i] = y[i] + rand.nextInt(3) - 1;
            gr.setColor(Color.green);
            gr.fillPolygon(x, y, 13);
            gr.setColor(new Color(100,100,100));
            gr.fillRect(12, 45, 11, 5);
            gr.fillRect(27, 45, 11, 5);
            gr.setColor(Color.black);
            gr.fillOval(18,20,4,4);
            gr.fillOval(28,20,4,4);
            //return gr;
        } else if (inFacing == 2)
        {
            int[] x2 = {25, 41, 41, 16, 16,  5,  0, 16, 16};
            for(int i = 0; i < x2.length; i++)
                x2[i] = x2[i] + rand.nextInt(3) - 1;
            int[] y2 = { 0, 18, 45, 45, 35, 40, 35, 25, 18};
            for(int i = 0; i < y2.length; i++)
                y2[i] = y2[i] + rand.nextInt(3) - 1;
            gr.setColor(Color.green);
            gr.fillPolygon(x2, y2, 9);  
            gr.setColor(Color.black);
            gr.fillOval(18,20,4,4);
            gr.setColor(new Color(100,100,100));
            gr.fillRect(12, 45, 11, 5);
            //return gr;
        } else
        {
            int[] x3 = {25, 41, 41, 50, 45, 41, 41, 16, 16};
            for(int i = 0; i < x3.length; i++)
                x3[i] = x3[i] + rand.nextInt(3) - 1;
            int[] y3 = { 0, 18, 25, 35, 40, 35, 45, 45, 18};
            for(int i = 0; i < y3.length; i++)
                y3[i] = y3[i] + rand.nextInt(3) - 1;
            gr.setColor(Color.green);
            gr.fillPolygon(x3, y3, 9);  
            gr.setColor(Color.black);
            gr.fillOval(28 ,20 ,4,4);
            gr.setColor(new Color(100,100,100));
            gr.fillRect(27, 45, 11, 5);
            //return gr;
        }
        //gr.setColor(Color.black);
        //gr.drawRect(0,0,50,50);
        return gr;
    }
    public static void /* Graphics */ draw(Graphics la, int inFacing)
    {
        la.clearRect(0,0,50,50);
        la = linkMan.get(inFacing).create();
        //return linkMan.get(inFacing);
    }
}