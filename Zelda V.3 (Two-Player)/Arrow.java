import java.awt.*;
import javax.swing.*;
import java.applet.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;
public class Arrow
{
    int x, y, speed, direction;
    public Arrow(int inX, int inY, int inSpeed, int inDirection)
    {
        direction = inDirection;
        if (direction < 2)
        {
            x = inX - 5;
            if (direction == 1)
                y = inY - 50;
            else
                y = inY;
        }
        else
        {
            if (direction == 3)
                x = inX - 50;
            else
                x = inX;
            y = inY - 5;
        }
        speed = inSpeed;
    }
    public boolean moveArrow()
    {
        switch(direction)
        {
            case 0:
            {
                if (y > 0 && Zelda.map[x/50][(y-speed)/50] >= 0 && Zelda.map[(x+9)/50][(y-speed)/50] >= 0 && Zelda.map[x/50][(y+49-speed)/50] >= 0 && Zelda.map[(x+9)/50][(y+49-speed)/50] >= 0)
                {
                    for(int i = 0; i < Zelda.blocks.size(); i++)
                        if (y > Zelda.blocks.get(i).getYDown() && y - speed <= Zelda.blocks.get(i).getYDown() && !((x + 9 < Zelda.blocks.get(i).getX()) || (x > Zelda.blocks.get(i).getXRight())))
                            if (!Zelda.blocks.get(i).moveUp(speed))
                                {
                                    Zelda.blocks.remove(i);
                                    return false;
                                }
                    y = y - speed;
                } else
                      return false;
                break;
            }
            case 1:
            {
                if (y < 2450 && Zelda.map[x/50][(y+speed)/50] >= 0 && Zelda.map[(x+9)/50][(y+speed)/50] >= 0 && Zelda.map[x/50][(y+49+speed)/50] >= 0 && Zelda.map[(x+9)/50][(y+49+speed)/50] >= 0)
                {
                    for(int i = 0; i < Zelda.blocks.size(); i++)
                        if (y + 49 < Zelda.blocks.get(i).getY() && y + 49 + speed >= Zelda.blocks.get(i).getY() && !((x + 9 < Zelda.blocks.get(i).getX()) || (x > Zelda.blocks.get(i).getXRight())))
                            if (!Zelda.blocks.get(i).moveDown(speed))
                            {
                                Zelda.blocks.remove(i);
                                return false;
                            }
                    y = y + speed;
                } else
                      return false;
                break;
            }
            case 2:
            {
                if (x > 0 && Zelda.map[(x-speed)/50][y/50] >= 0 && Zelda.map[(x+49-speed)/50][y/50] >= 0 && Zelda.map[(x-speed)/50][(y+9)/50] >= 0 && Zelda.map[(x+49-speed)/50][(y+9)/50] >= 0)
                {
                    for(int i = 0; i < Zelda.blocks.size(); i++)
                        if (x > Zelda.blocks.get(i).getXRight() && x - speed <= Zelda.blocks.get(i).getXRight() && !((y + 9 < Zelda.blocks.get(i).getY()) || (y > Zelda.blocks.get(i).getYDown())))
                            if (!Zelda.blocks.get(i).moveLeft(speed))
                            {
                                Zelda.blocks.remove(i);
                                return false;
                            }
                    x = x - speed;
                } else
                      return false;
                break;
            }
            case 3:
            {
                if (x < 2450 && Zelda.map[(x+speed)/50][y/50] >= 0 && Zelda.map[(x+49+speed)/50][y/50] >= 0 && Zelda.map[(x+speed)/50][(y+9)/50] >= 0 && Zelda.map[(x+49+speed)/50][(y+9)/50] >= 0)
                {
                    for(int i = 0; i < Zelda.blocks.size(); i++)
                        if (x + 49 < Zelda.blocks.get(i).getX() && x + 49 + speed >= Zelda.blocks.get(i).getX() && !((y + 9 < Zelda.blocks.get(i).getY()) || (y > Zelda.blocks.get(i).getYDown())))
                            if (!Zelda.blocks.get(i).moveRight(speed))
                            {
                                Zelda.blocks.remove(i);
                                return false;
                            }
                    x = x + speed;
                } else
                      return false;
                break;
            }
        }
        return true;
    }
    public void draw(Graphics g, int inX, int inY)
    {
        g.setColor(Color.black);
        if (direction < 2)
            g.fillRect(x - inX, y - inY, 10, 50);
        else
            g.fillRect(x - inX, y - inY, 50, 10);
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
}