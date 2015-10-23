import javax.swing.JOptionPane;

//import java.awt.*;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;

//import javax.swing.*;
import javax.swing.JApplet;

//import java.util.*;
import java.util.Random;
import java.util.ArrayList;

//import java.awt.event.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class LevelEditor extends JApplet {
    
    public void init() {
        
    }
    public void paint(Graphics g) {
        
    }
    public static void write() {
        
    }
    public static void read() {
        System.out.println("Enter file name to read from.");
        try {
            FileReader file = new FileReader(Keyboard.readString());
            BufferedReader fileIn = new BufferedReader(file);
            
        } catch(FileNotFoundException e) {
            System.out.println("That file was not found.");
        }
    }
}