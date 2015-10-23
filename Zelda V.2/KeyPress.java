import java.awt.*;
import javax.swing.*;
import java.applet.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;
public class KeyPress
{
    static int key;
    static boolean upKey, downKey, leftKey, rightKey, spaceKey;
    public static void updateKeys()
    {
        addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent evt)
            {
                key = evt.getKeyCode();
                switch(key)
                {
                    case KeyEvent.VK_UP:
                    {
                        upKey = true;
                        break;
                    }
                    case KeyEvent.VK_DOWN:
                    {
                        downKey = true;
                        break;
                    }
                    case KeyEvent.VK_LEFT:
                    {
                        leftKey = true;
                        break;
                    }
                    case KeyEvent.VK_RIGHT:
                    {
                        rightKey = true;
                        break;
                    }
                    case KeyEvent.VK_SPACE:
                    {
                        spaceKey = true;
                        break;
                    }
                }
            }
            public void keyReleased(KeyEvent evt)
            {
                key = evt.getKeyCode();
                switch(key)
                {
                    case KeyEvent.VK_UP:
                    {
                        upKey = false;
                        break;
                    }
                    case KeyEvent.VK_DOWN:
                    {
                        downKey = false;
                        break;
                    }
                    case KeyEvent.VK_LEFT:
                    {
                        leftKey = false;
                        break;
                    }
                    case KeyEvent.VK_RIGHT:
                    {
                        rightKey = false;
                        break;
                    }
                    case KeyEvent.VK_SPACE:
                    {
                        spaceKey = false;
                        break;
                    }
                }
            }
        });
    }
}