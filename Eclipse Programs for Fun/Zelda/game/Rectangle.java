package game;

import java.util.ArrayList;
/**
 * This class has abstract methods for the <code> Wall </code>
 * and <code> MobileObject </code> Classes. It provides simple
 * access to the single coordinate values to the sides of the
 * rectangle. It also requires all <code> Rectangles </code> with test methods
 * for simulating movement and determining the maximum distance
 * able to move according to the distance passed as a parameter. <p>
 * copyright&copy; Timothy Swan <br>
 * <b> <u> Not to be distributed </u> </b>
 * @author Timothy Swan
 * @version 4.0
 * @see Wall
 * @see MobileObject
 */
public abstract class Rectangle
{
    protected int x, y, height, width;
    public Rectangle(int inX, int inY, int inHeight, int inWidth)
    {
        x = inX;
        y = inY;
        height = inHeight;
        width = inWidth;
    }
    public int left()
    {
        return x;
    }
    public int top()
    {
        return y;
    }
    public int right()
    {
        return x + width;
    }
    public int bottom()
    {
        return y + height;
    }
    public int width()
    {
        return width;
    }
    public int height()
    {
        return height;
    }
    public boolean intercepts(Rectangle inRect)
    {
        return (left() < inRect.right() && right() > inRect.left() && 
               top() < inRect.bottom() && bottom() > inRect.top());
    }
    //public abstract void remove(ArrayList... lists);
    public void remove(ArrayList... lists) {
        for(int i = 0; i < lists.length; i++)
            while (lists[i].contains(this))
                lists[i].remove(lists[i].indexOf(this));
    }
    protected abstract int testUp(ArrayList<Rectangle> r, int s, boolean p, int rght, int bttm, boolean wrap);
    protected abstract int testDown(ArrayList<Rectangle> r, int s, boolean p, int rght, int bttm, boolean wrap);
    protected abstract int testRight(ArrayList<Rectangle> r, int s, boolean p, int rght, int bttm, boolean wrap);
    protected abstract int testLeft(ArrayList<Rectangle> r, int s, boolean p, int rght, int bttm, boolean wrap);
}