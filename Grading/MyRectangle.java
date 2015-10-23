 

import java.awt.*;
import javax.swing.*;   // for input dialog

/**
 * A rectangle that can be manipulated and that draws itself on a canvas.
 * Note that the class "Rectangle" is already defined, so we needed to 
 * choose a different name.  This is almost identical to the Square class,
 * except size is changed to width and height in several places.
 * 
 * @author (Dale Reed) 
 * @version (9/1/2003)
 */
public class MyRectangle
{
    private int width;
    private int height;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;
    private String label;

    /**
     * Create a new MyRectangle at default position with default color.
     */
    public MyRectangle()
    {
        xPosition = 20;
        yPosition = 20;
        width = 10;
        height = 250;
        color = "red";
        isVisible = false;
        label = "";
    }
    
    /**
     * Create a new MyRectangle with supplied position, size, color, and visibility
     */
    public MyRectangle( int x, int y, int theWidth, int theHeight, 
                        String theColor, boolean visibility)
    {
        xPosition = x;
        yPosition = y;
        width = theWidth;
        height = theHeight;
        color = theColor;
        isVisible = visibility;
        label = "";
        draw();
    }

    /**
     * Create a new MyRectangle with supplied position, size, color, Visibility,
     * and label.
     */
    public MyRectangle( int x, int y, int theWidth, int theHeight, 
                        String theColor, boolean visibility, String label)
    {
        xPosition = x;
        yPosition = y;
        width = theWidth;
        height = theHeight;
        color = theColor;
        isVisible = visibility;
        this.label = label;
        draw();
    }
    /**
     * Return the xPosition
     */
    public int getXPosition()
    {
        return xPosition;
    }

    /**
     * Make this MyRectangle visible. If it was already visible, do nothing.
     */
    public void makeVisible()
    {
        isVisible = true;
        draw();
    }
    
    /**
     * Make this MyRectangle invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible()
    {
        erase();
        isVisible = false;
    }
    

    /**
     * Slowly move the MyRectangle either horizontally or vertically by 'distance' pixels,
     * depending on the direction given.
     */
    public void slowMove(String distanceString, String direction)
    {
        int distance;
        
        // Convert distance from string to int, 
        distance = Integer.parseInt( distanceString);
        
        /* Negate position change, depending on direction entered. 
         * These if statement could be written as two separate statements 
         */ 
        if ( direction.equals("U") || direction.equals("L")) {
            distance = -distance;   
        }
        // Convert position change from number of squares to pixels (1 square = 40 pixels)
        distance = distance * 40;
       
        if (direction.equals("U") || direction.equals("D")) {
           slowMoveVertical( distance);   
        }
        else if (direction.equals("L") || direction.equals("R")) {
           slowMoveHorizontal( distance); 
        }
        else
           JOptionPane.showMessageDialog(null, "Invalid direction in method slowMove()");

    }

    /**
     * Slowly move the MyRectangle horizontally by 'distance' pixels.
     */
    public void slowMoveHorizontal(int distance)
    {
        int delta;

        if(distance < 0) 
        {
            delta = -1;
            distance = -distance;
        }
        else 
        {
            delta = 1;
        }

        for(int i = 0; i < distance; i++)
        {
            xPosition += delta;
            draw();
        }
    }

    /**
     * Slowly move the MyRectangle vertically by 'distance' pixels.
     */
    public void slowMoveVertical(int distance)
    {
        int delta;

        if(distance < 0) 
        {
            delta = -1;
            distance = -distance;
        }
        else 
        {
            delta = 1;
        }

        for(int i = 0; i < distance; i++)
        {
            yPosition += delta;
            draw();
        }
    }

    /*
     * Draw the MyRectangle with current specifications on screen.
     */
    private void draw()
    {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color,
                        new Rectangle(xPosition, yPosition, width, height),
                        label);
           // canvas.wait(0);
        }
    }

    /*
     * Erase the MyRectangle on screen.
     */
    private void erase()
    {       
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
}

