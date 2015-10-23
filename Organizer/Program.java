import javax.swing.*;
import java.awt.*;
import java.util.*;
public class Program extends JApplet {
    String write;
    int mess;
    Random r;
    Image im;
    Graphics gr;
    public void init() {
        write = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        mess = 0;
        r = new Random();
        im = createImage(/*write.length() **/ 10, 10);
        gr = im.getGraphics();
    }
    public void paint(Graphics g) {
        for(int i = 0; i < write.length(); i++)
            letter(i * 10, 10, write.charAt(i));
        g.drawImage(im, 0, 0, null);
        repaint();
    }
    public void letter(int x, int y, char l) {
        gr.setColor(Color.black);
        switch (l) {
            case 'A': {
                line(0, 9, 4, 0);
                line(9, 9, 4, 0);
                line(3, 3, 6, 3);
                break;
            }
            case 'B': {
                
                break;   
            }
            case 'E': {
                line(0, 0, 0, 9);
                break;
            }
        }
    }
    public void line(int x, int y, int x2, int y2) {
        gr.drawLine(x + r(), y + r(), x2 + r(), y2 + r());
    }
    public int r() {
        return r.nextInt(mess) - mess / 2;
    }
}