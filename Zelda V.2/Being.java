import java.awt.*;
import javax.swing.*;
import java.applet.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;

public class Being
{
    private int x, y, facing = 1, height, width;
    public Being (int inX, int inY)
    {
        x = inX;
        y = inY;
        height = 49;
        width = 49;
    }
    public Being (int inX, int inY, int inHeight, int inWidth)
    {
        x = inX;
        y = inY;
        height = inHeight - 1;
        width = inWidth - 1;
    }
    public boolean moveUp(int speed)
    {
        facing = 0;
        if (y > 0 && Zelda.map[x/50][(y-speed)/50] >= 0 && Zelda.map[(x+width)/50][(y-speed)/50] >= 0 && Zelda.map[x/50][(y+height-speed)/50] >= 0 && Zelda.map[(x+width)/50][(y+height-speed)/50] >= 0)
        {
            for(int i = 0; i < Zelda.blocks.size(); i++)
                if (y > Zelda.blocks.get(i).getYDown() && y - speed <= Zelda.blocks.get(i).getYDown() && !((x + width < Zelda.blocks.get(i).getX()) || (x > Zelda.blocks.get(i).getXRight())))
                    if (!Zelda.blocks.get(i).moveUp(speed))
                        return false;
            y = y - speed;
            return true;
        } else
              return false;
    }
    public boolean moveDown(int speed)
    {
        facing = 1;
        if (y < 2450 && Zelda.map[x/50][(y+speed)/50] >= 0 && Zelda.map[(x+width)/50][(y+speed)/50] >= 0 && Zelda.map[x/50][(y+height+speed)/50] >= 0 && Zelda.map[(x+width)/50][(y+height+speed)/50] >= 0)
        {
            for(int i = 0; i < Zelda.blocks.size(); i++)
                if (y + height < Zelda.blocks.get(i).getY() && y + height + speed >= Zelda.blocks.get(i).getY() && !((x + width < Zelda.blocks.get(i).getX()) || (x > Zelda.blocks.get(i).getXRight())))
                    if (!Zelda.blocks.get(i).moveDown(speed))
                        return false;
            y = y + speed;
            return true;
        } else
              return false;
    }
    public boolean moveLeft(int speed)
    {
        facing = 2;
        if (x > 0 && Zelda.map[(x-speed)/50][y/50] >= 0 && Zelda.map[(x+width-speed)/50][y/50] >= 0 && Zelda.map[(x-speed)/50][(y+height)/50] >= 0 && Zelda.map[(x+width-speed)/50][(y+height)/50] >= 0)
        {
            for(int i = 0; i < Zelda.blocks.size(); i++)
                if (x > Zelda.blocks.get(i).getXRight() && x - speed <= Zelda.blocks.get(i).getXRight() && !((y + height < Zelda.blocks.get(i).getY()) || (y > Zelda.blocks.get(i).getYDown())))
                    if (!Zelda.blocks.get(i).moveLeft(speed))
                        return false;
            x = x - speed;
            return true;
        } else
              return false;
    }
    public boolean moveRight(int speed)
    {
        facing = 3;
        if (x < 2450 && Zelda.map[(x+speed)/50][y/50] >= 0 && Zelda.map[(x+width+speed)/50][y/50] >= 0 && Zelda.map[(x+speed)/50][(y+height)/50] >= 0 && Zelda.map[(x+width+speed)/50][(y+height)/50] >= 0)
        {
            for(int i = 0; i < Zelda.blocks.size(); i++)
                if (x + width < Zelda.blocks.get(i).getX() && x + width + speed >= Zelda.blocks.get(i).getX() && !((y + height < Zelda.blocks.get(i).getY()) || (y > Zelda.blocks.get(i).getYDown())))
                    if (!Zelda.blocks.get(i).moveRight(speed))
                        return false;
            x = x + speed;
            return true;
        } else
              return false;
    }
    public int getFacing()
    {
        return facing;
    }
    public void shootArrow()
    {
        Zelda.arrows.add(new Arrow(x + width / 2, y + height / 2, 4, facing));
    }
    public int getX()
    {
        return x;
    }
    public int getXRight()
    {
        return x + width;
    }
    public int getY()
    {
        return y;
    }
    public int getYDown()
    {
        return y + height;
    }
    public int getHeight()
    {
        return height + 1;
    }
    public int getWidth()
    {
        return width + 1;
    }
}