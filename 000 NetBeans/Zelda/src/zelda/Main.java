package zelda;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
/**
 *
 * @author Timothy Swan
 */
public class Main extends JFrame {
    private static final String gameFolder = "Zelda Saved Games";
    private static String[] fileNames;
    private static String fileName;
    private static JFrame frame;
    private static JButton ok;
    private static Zelda zeldaPanel;
    private static JMenuItem pause;
    public static void main(String[] args) {
        setGameFolder();
        frame = new JFrame();
        Container container = frame.getContentPane();
        zeldaPanel = new Zelda();
        JMenuBar bar = new JMenuBar();
        JMenu game = new JMenu("Game");
        game.setMnemonic('g');
        JMenuItem restart = new JMenuItem("New Game");
        restart.addActionListener(new ActionAdapter() {
            public void actionPerformed(ActionEvent e) {
                if (fileName != null)
                    
                System.out.println("Restart ********");
            }
        });
        game.add(restart);
        
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(new ActionAdapter() {
            public void actionPerformed(ActionEvent e) {
                save(false);
            }
        });
        game.add(save);
        
        JMenuItem saveAs = new JMenuItem("Save As...");
        saveAs.addActionListener(new ActionAdapter() {
            public void actionPerformed(ActionEvent e) {
                save(true);
            }
        });
        game.add(saveAs);
        
        JMenuItem load = new JMenuItem("Load");
        load.addActionListener(new ActionAdapter() {
            public void actionPerformed(ActionEvent e) {
                load(false);
            }
        });
        game.add(load);
        
        JMenuItem loadFrom = new JMenuItem("Load From...");
        loadFrom.addActionListener(new ActionAdapter() {
            public void actionPerformed(ActionEvent e) {
                load(true);
            }
        });
        game.add(loadFrom);
        
        JMenuItem exit = new JMenuItem("Quit");
        exit.addActionListener(new ActionAdapter() {
            public void actionPerformed(ActionEvent e) {
                quit();
            }
        });
        game.add(exit);
        
        bar.add(game);
        
        pause = new JMenuItem("Pause");
        pause.setMnemonic('p');
        pause.addActionListener(new ActionAdapter() {
            public void actionPerformed(ActionEvent e) {
                if (pause.getText().equals("Pause")) {
                    zeldaPanel.setRun(false);
                    pause.setText("Unpause");
                } else {
                    zeldaPanel.setRun(true);
                    zeldaPanel.repaint();
                    pause.setText("Pause");
                }
            }
        });
        zeldaPanel.setPauseListener(pause.getActionListeners()[0]);
        bar.add(pause);
        
        frame.setJMenuBar(bar);
        frame.setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);
        container.add(zeldaPanel);
        frame.pack();
        zeldaPanel.initialize(frame);
        frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                quit();
            }
        });
        frame.setVisible(true);
    }
    private static boolean save(boolean as) {
        if (as)
            fileName = selectFile(true); //JOptionPane.showInputDialog("Enter save name", fileName);
        if (fileName != null) {
            zeldaPanel.save(gameFolder + "\\" + fileName + ".txt");
            return true;
        } else
            return false;
    }
    private static void load(boolean from) {
        if (from)
            fileName = selectFile(false); //JOptionPane.showInputDialog("Enter load name", fileName);
        if (fileName != null)
            zeldaPanel.load(gameFolder + "\\" + fileName + ".txt");
    }
    private static String selectFile(boolean create) {
        setGameFolder();
//        String[] bob = new String[100];
//        for(int i = 0; i < 100; i++)
//            bob[i] = Integer.toString(i);
//        JOptionPane.showOptionDialog(null, "message", "title", 
//                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
//                null, bob, "0"); //fileNames, fileName);
//        return "";
        JFrame popUp = new JFrame();
        JComboBox selector = new JComboBox(fileNames);
        ok = new JButton();
        ok.addActionListener(new ActionAdapter() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("notify");
                pause.notify();
            }
        });
        popUp.getContentPane().add(selector);
        popUp.pack();
        popUp.setVisible(true);
        try {
            synchronized (pause) {
                
            pause.wait(1000);}
        } catch(InterruptedException e) {
        }
        return (String) selector.getSelectedItem();
    }
    private static void quit() {
        if (finalSaveOption("Quit"))
            System.exit(0);
    }
    private static boolean finalSaveOption(String purpose) {
        int option = JOptionPane.showConfirmDialog(null, 
                "Save current game?", purpose, JOptionPane.YES_NO_CANCEL_OPTION);
        if (option == JOptionPane.YES_OPTION)
            return save(false);
        else if (option == JOptionPane.NO_OPTION)
                return true;
            else
                return false;
    }
    private static void setGameFolder() {
        File file = new File(gameFolder);
        file.mkdir();
        fileNames = file.list();
    }
    private static class ActionAdapter implements ActionListener {
        public void actionPerformed(ActionEvent e) {}
    }
}
