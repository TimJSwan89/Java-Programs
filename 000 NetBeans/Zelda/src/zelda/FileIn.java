package zelda;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
public class FileIn {
    BufferedReader fileIn;
    public FileIn(String name) {
        try {
            FileReader file = new FileReader(name);
            fileIn = new BufferedReader(file);
        } catch (IOException e) {
            error();
        }
    }
    public String read() {
        String string = "";
        if (fileIn != null) {
            try {
                string = fileIn.readLine();
            } catch (IOException e) {
                error();
            }
        }
        return string;
    }
    public int readInt() {
        String string = read();
        return Integer.parseInt(string);
    }
    public void error() {
        System.out.println("Error in class Filer.");
    }
    public void done() {
        try {
            fileIn.close();
        } catch(IOException e) {
            error();
        }
    }
}