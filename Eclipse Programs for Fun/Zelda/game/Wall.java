package game;

import java.util.ArrayList;
public class Wall extends Rectangle
{
    public Wall(int inX, int inY, int inHeight, int inWidth)
    {
        super(inX, inY, inHeight, inWidth);
    }
    protected int testUp(ArrayList r, int s, boolean p, int rght, int bttm, boolean wrap)
    {
        return 0;
    }
    protected int testDown(ArrayList r, int s, boolean p, int rght, int bttm, boolean wrap)
    {
        return 0;
    }
    protected int testRight(ArrayList r, int s, boolean p, int rght, int bttm, boolean wrap)
    {
        return 0;
    }
    protected int testLeft(ArrayList r, int s, boolean p, int rght, int bttm, boolean wrap)
    {
        return 0;
    }
}
