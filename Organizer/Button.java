import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
public class Button {
    private static JButton button;
    private static JPanel panel;
    private static JFrame frame;
    private static String[] txt = {"DO NOT PRESS", "* Ahem *\nDO NOT PRESS",
    "Sara, is that you?!", "Fine GoodBye!"};
    public static void main() {
        int x = 0;
        button = new JButton();
        panel = new JPanel();
        frame = new JFrame();
        
        panel.add(button);
        frame.add(panel);
        frame.setVisible(true);
        do {
            button.setText(txt[x]);
            x++;
        } while(x < txt.length);
    }
}