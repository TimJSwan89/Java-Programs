import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
public class Filer {
    BufferedWriter fileOut;
    BufferedReader fileIn;
    public Filer(String name) {
        System.out.println("Hello");
        System.out.println("Hello");
        System.out.println("Hello");
        try {
            FileWriter file = new FileWriter(name);
            fileOut = new BufferedWriter(file);
        } catch (IOException e) {
        }
        try {
            FileReader file = new FileReader(name);
            fileIn = new BufferedReader(file);
        } catch (IOException e) {
        }
    }
    public void write(int write) {
        if (fileOut != null) {
            try {
                fileOut.write(write);
                fileOut.newLine();
            } catch (IOException e) {
            }
        }
    }
    public void write(String write) {
        if (fileOut != null) {
            try {
                fileOut.write(write);
                fileOut.newLine();
            } catch (IOException e) {
            }
        }
    }
    public String read() {
        String string = "";
        if (fileIn != null) {
            try {
                string = fileIn.readLine();
            } catch (IOException e) {
            }
        }
        return string;
    }
}