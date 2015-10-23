// =======================
// Title:    Colors
// Designer: Swan, Timothy
// =======================
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JApplet;
import java.util.Random;
import java.util.ArrayList;

public class Colors extends JApplet {
    int[][][] cols;
    Random rand;
    double[] arbit = {-0.1, 0, 0.1};
    public void init() {
        cols = new int[200][200][3];
        rand = new Random();
        for(int i = 0; i < 200; i++)
            for(int j = 0; j < 200; j++)
               for(int k = 0; k < 3; k++)
                    cols[i][j][k] = 255;
    }
    public void paint(Graphics g) {
        setCol();
        for(int i = 0; i < 200; i++)
            for(int j = 0; j < 200; j++) {
                g.setColor(new Color(cols[i][j][0], cols[i][j][1], cols[i][j][2]));
                g.fillRect(i * 1, j * 1, 1, 1);
            }
        for(int i = 0; i < 100000; i++);
        repaint();
    }
    public void setCol() {
        for(int i = 0; i < 200; i++)
            for(int j = 0; j < 200; j++)
                for(int k = 0; k < 3; k++) {
                    double avg;
                    if (i == 0 && j == 0)
                        avg = (cols[0][1][k] + cols[1][0][k]) / 2.0;
                    else if (i == 0 && j == 199)
                        avg = (cols[1][199][k] + cols[0][198][k]) / 2.0;
                        else if (i == 199 && j == 0)
                            avg = (cols[198][0][k] + cols[199][1][k]) / 2.0;
                            else if (i == 199 && j == 199)
                                avg = (cols[198][199][k] + cols[199][198][k]) / 2.0;
                                else if (i == 0)
                                    avg = (cols[0][j + 1][k] + cols[0][j - 1][k] + cols[1][j][k]) / 3.0;
                                    else if (i == 199)
                                        avg = (cols[199][j + 1][k] + cols[199][j - 1][k] + cols[198][j][k]) / 3.0;
                                        else if (j == 0)
                                            avg = (cols[i + 1][0][k] + cols[i - 1][0][k] + cols[i][1][k]) / 3.0;
                                            else if (j == 199)
                                                avg = (cols[i + 1][199][k] + cols[i - 1][199][k] + cols[i][198][k]) / 3.0;
                                                else
                                                    avg = (cols[i + 1][j][k] + cols[i - 1][j][k] + cols[i][j + 1][k] + cols[i][j - 1][k]) / 4.0;
                    int col = (int) (avg + rand.nextInt(3) - 1 + .5 + arbit[k]);
                    if (col > 230) {
                        col -= 2;
                        arbit[k] = -0.1;
                    }
                    if (col < 200) {
                        col += 2;
                        arbit[k] = 0.1;
                    }
                    cols[i][j][k] = col;
                }
    }
}