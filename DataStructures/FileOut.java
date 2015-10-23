import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
public class FileOut {
    BufferedWriter fileOut;
    public FileOut(String name) {
        try {
            FileWriter file = new FileWriter(name);
            fileOut = new BufferedWriter(file);
        } catch (IOException e) {
            error();
        }
    }
    public void write(int write) {
        if (fileOut != null) {
            try {
                fileOut.write(Integer.toString(write));
                fileOut.newLine();
            } catch (IOException e) {
                error();
            }
        }
    }
    public void write(String write) {
        if (fileOut != null) {
            try {
                fileOut.write(write);
                fileOut.newLine();
            } catch (IOException e) {
                error();
            }
        }
    }
    public void error() {
        System.out.println("Error in class FileOut.");
    }
    public void done() {
        try {
            fileOut.close();
        } catch(IOException e) {
            error();
        }
    }
}