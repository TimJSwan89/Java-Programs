import java.awt.*;
import javax.swing.*;
import java.applet.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;
public class Imaging extends JApplet
{
    static Image image, mapImage;
    static Graphics graphics, mapGraphics; 
    public static void createBackground()
    {
        // -------------------
        // Creates random map:
        // -------------------
        mapImage = createImag bnvcmx,z./z/.xx,cmvbbnnnnnnnnnnnnnnnnnnnnnnnb
        A';SSLKDFGHKJK e(2500, 2500);        
        mapGraphics = mapImage.getGraphics();
        for (int a = 0; a < 50; a++)
            for (int b = 0; b < 50; b++)
            {
                int num = rand.nextInt(100);
                if (num < 10) // 10% chance
                {
                    mapGraphics.setColor(Color.black);
                    map[a][b] = -1;
                }
                else if (num < 12) // 2% chance
                     {
                         mapGraphics.setColor(Color.gray);
                         map[a][b] = -2;
                     }
                     else if (num < 20) // 8% chance
                          {
                              mapGraphics.setColor(Color.white);
                              map[a][b] = 1;
                              blocks.add(new Being(a*50,b*50,1));
                          }
                          else
                          {
                              mapGraphics.setColor(Color.white);
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
}