import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JApplet;
public class BibleFormChanger {
    static ArrayList bible = new ArrayList();
    static volatile Thread timer;
    static boolean downKey;
    public static void main(String[] args) {
        read();
        write();
        show();
    }
    private static void read() {
        String[] abbBooks = {"GE", "EX", "LEV", "NU", "DT", "JOS", "JDG", "RU", "1SA", "2SA", "1KI", "2KI", "1CH",
            "2CH", "EZR", "NE", "EST", "JOB", "PS", "PR", "ECC", "SS", "ISA", "JER", "LA", "EZE", "DA", "HOS", "JOEL", 
            "AM", "OB", "JNH", "MIC", "NA", "HAB", "ZEP", "HAG", "ZEC", "MAL", "MT", "MK", "LK", "JN", "AC", "RO", 
            "1CO", "2CO", "GAL", "EPH", "PHP", "COL", "1TH", "2TH", "1TI", "2TI", "TIT", "PHM", "HEB", "JAS", "1PE", 
            "2PE", "1JN", "2JN", "3JN", "JUDE", "REV"};
        String[] fullBooks = {"Genesis", "Exodus", "Leviticus", "Numbers", "Deuteronomy", "Joshua", "Judges", "Ruth", 
            "1 Samuel", "2 Samuel", "1 Kings", "2 Kings", "1 Chronicles", "2 Chronicles", "Ezra", "Nehemiah", 
            "Esther", "Job", "Psalm", "Proverbs", "Ecclesiastes", "Song of Solomon", "Isaiah", "Jeremiah", 
            "Lamentations", "Ezekiel", "Daniel", "Hosea", "Joel", "Amos", "Obadiah", "Jonah", "Micah", "Nahum", 
            "Habbakuk", "Zephaniah", "Haggai", "Zechariah", "Malachi", "Matthew", "Mark", "Luke", "John", "Acts", 
            "Romans", "1 Corinthians", "2 Corinthians", "Galatians", "Ephesians", "Philippians", "Colossians", 
            "1 Thessalonians", "2 Thessalonians", "1 Timothy", "2 Timothy", "Titus", "Philemon", "Hebrews", "James", 
            "1 Peter", "2 Peter", "1 John", "2 John", "2 John", "Jude", "Revelation"};
        
        try {
            FileReader file = new FileReader("Copy of Bible.txt");
            BufferedReader fileIn = new BufferedReader(file);
            ArrayList<String> lines = new ArrayList<String>();
            lines.add(fileIn.readLine());
            while(lines.get(lines.size() - 1) != null) {
                lines.add(fileIn.readLine());
            }
            String book = "";
            String chapter = "";
            String verse = "";
            int bookCount = -1;
            int chapterCount = 0;
            int verseCount = 0;
            boolean textAdded = false;
            for(String line : lines) {
                int spaceCount = 0;
                while(line != null && spaceCount < line.length() && line.charAt(spaceCount) == ' ')
                    spaceCount++;
                if (line != null && line.length() - spaceCount > 0) {
                    line = line.substring(spaceCount);
                    boolean isBook = (line.length() > 4) ? ((line.substring(0, 5)).equals("Psalm")) : false;
                    int capCount;
                    String newBook = "";
                    if (isBook) {
                        newBook = "Psalm";
                        capCount = 5;
                    }
                    else {
                        capCount = 0;
                        while((line.charAt(capCount) >= 'A' && line.charAt(capCount) <= 'Z' && capCount < 5) ||
                        (line.charAt(capCount) >= '0' && line.charAt(capCount) <= '9' && capCount < 2))
                            capCount++;
                        isBook = (capCount > 1);
                        if (isBook) {
                            newBook = line.substring(0, capCount);
                            if (capCount == 5 || newBook.equals("AND") || newBook.equals("THE"))
                                isBook = false;
                        }
                        int index = 0;
                        for(int i = 0; i < abbBooks.length; i++)
                            if (newBook.equals(abbBooks[i])) {
                                index = i;
                                break;
                            }
                        newBook = fullBooks[index];
                    }
                    if (isBook) {
                        if (book == "" || !book.equals(newBook)) {
                            book = newBook;
                            bookCount++;
                            chapterCount = 0;
                            chapter = "";
                            bible.add(new ArrayList());
                            ((ArrayList)bible.get(bookCount)).add(book);
                        }
                        line = line.substring(capCount + 1);
                        int numCount = 0;
//                         System.out.println(line);
                        while(line.length() > numCount && line.charAt(numCount) >= '0' && line.charAt(numCount) <= '9')
                            numCount++;
                        
//                         System.out.println(('0' > '9') + " " + ('1' > '9'));
//                         System.out.println(numCount);
//                         System.out.println("\"" + line.substring(0, numCount) + "\"");
                        String newChapter = line.substring(0, numCount);
//                         System.out.println(newChapter + "\"\n" + bookCount + "\n" + chapterCount);                           ////////
                        if (chapter == "" || !chapter.equals(newChapter)) {
                            chapter = newChapter;
                            chapterCount++;
                            verseCount = 0;
                            verse = "";
                            ((ArrayList)bible.get(bookCount)).add(new ArrayList());
                            ((ArrayList)((ArrayList)bible.get(bookCount)).get(chapterCount)).add(chapter);
                        }
                        String newVerse;
                        if (capCount < 5) {
                            line = line.substring(numCount + 1);
                            numCount = 0;
                            while(line.charAt(numCount) >= '0' && line.charAt(numCount) <= '9')
                                numCount++;
//                             System.out.println(verseCount);
//                             System.out.println(line.substring(0, numCount));
                            newVerse = line.substring(0, numCount);
                            
//                             System.out.println("\"" + newVerse + "\"\n" + numCount);
                            line = line.substring(numCount + 1);
//                             System.out.println("\"" + line + "\"");

                        } else {
                            newVerse = "";
                            line = line.substring(numCount);
                        }
                        if (verse == "" || !verse.equals(newVerse)) {
                            verse = newVerse;
                            textAdded = false;
                            verseCount++;
//                             System.out.println(verseCount + " " + ((ArrayList)((ArrayList)bible.get(bookCount)).get(chapterCount)).size());
                            ((ArrayList)((ArrayList)bible.get(bookCount)).get(chapterCount)).add(new ArrayList());
                            ((ArrayList)((ArrayList)((ArrayList)bible.get(bookCount)).get(chapterCount)).get(verseCount)).add(verse);
//                             System.out.println(((ArrayList)((ArrayList)((ArrayList)bible.get(bookCount)).get(chapterCount)).get(verseCount)).size());
                        }
                    }
                    while(line.length() > 0) {
                        int bracketCount = 0;
                        while(line.length() > bracketCount && line.charAt(bracketCount) != '[')
                            bracketCount++;
                        if (bracketCount < line.length()) {
                            int numCount = 1;
                            while(line.charAt(bracketCount + numCount) >= '0' && line.charAt(bracketCount + numCount) <= '9')
                               numCount++;
                            verse = line.substring(bracketCount + 1, bracketCount + numCount);
//                             System.out.println(line);
                            ((ArrayList)((ArrayList)((ArrayList)bible.get(bookCount)).get(chapterCount)).get(verseCount)).add((bracketCount == 0 ? "" : line.substring(0, bracketCount - 1)));
                            verseCount++;
//                             System.out.println(verseCount + " " + ((ArrayList)((ArrayList)bible.get(bookCount)).get(chapterCount)).size());
                            ((ArrayList)((ArrayList)bible.get(bookCount)).get(chapterCount)).add(new ArrayList());
                            ((ArrayList)((ArrayList)((ArrayList)bible.get(bookCount)).get(chapterCount)).get(verseCount)).add(verse);
//                             System.out.println(((ArrayList)((ArrayList)((ArrayList)bible.get(bookCount)).get(chapterCount)).get(verseCount)).size());
                            textAdded = false;
                            line = line.substring(bracketCount + 2 + numCount);
                        } else {
                            if (!textAdded)
                                ((ArrayList)((ArrayList)((ArrayList)bible.get(bookCount)).get(chapterCount)).get(verseCount)).add("");
//                             System.out.println(((ArrayList)((ArrayList)((ArrayList)bible.get(bookCount)).get(chapterCount)).get(verseCount)).size());
//                             System.out.println(chapterCount);
                            String previous = (String)((ArrayList)((ArrayList)((ArrayList)bible.get(bookCount)).get(chapterCount)).get(verseCount)).remove(1);
                            ((ArrayList)((ArrayList)((ArrayList)bible.get(bookCount)).get(chapterCount)).get(verseCount)).add(previous + (previous.equals("") ? "" : " ") + line);
                            textAdded = true;
                            line = "";
                        }
//                         System.out.println(((ArrayList)((ArrayList)((ArrayList)bible.get(bookCount)).get(chapterCount)).get(verseCount)).size());
                    }
                    if (textAdded == false)
                        ((ArrayList)((ArrayList)((ArrayList)bible.get(bookCount)).get(chapterCount)).get(verseCount)).add("");
                }
            }
            fileIn.close();
        } catch(IOException ex) {
            System.out.println("Could not read from file");
        }
    }
    private static void write() {
        try {
            FileWriter file = new FileWriter("ProgBible.txt");
            BufferedWriter fileOut = new BufferedWriter(file);
            for(int i = 0; i < bible.size(); i++)
                for(int j = 1; j < ((ArrayList)bible.get(i)).size(); j++) { // chapters
                    for(int k = 1; k < ((ArrayList)((ArrayList)bible.get(i)).get(j)).size(); k++) { // verses
//                         System.out.println(i + " " + j + " " + k);
                        String write = (String)((ArrayList)bible.get(i)).get(0);
                        write += " ";
                        write += (String)((ArrayList)((ArrayList)bible.get(i)).get(j)).get(0);
                        String verse = (String)((ArrayList)((ArrayList)((ArrayList)bible.get(i)).get(j)).get(k)).get(0);
                        write += (verse.length() == 0) ? "" : ":";
                        write += verse;
//                         if (((ArrayList)((ArrayList)((ArrayList)bible.get(i)).get(j)).get(k)).size() > 1) {
                            write += " "; //(verse.length() == 0) ? "" : " ";
                            write += (String)((ArrayList)((ArrayList)((ArrayList)bible.get(i)).get(j)).get(k)).get(1);
//                         }
//                         fileOut.write((String)((ArrayList)bible.get(i)).get(0) + " " + (String)((ArrayList)((ArrayList)bible.get(i)).get(j)).get(0)
//                         + ":" + (String)((ArrayList)((ArrayList)((ArrayList)bible.get(i)).get(j)).get(k)).get(0) + " " + (String)((ArrayList)((ArrayList)((ArrayList)bible.get(i)).get(j)).get(k)).get(1));
                        fileOut.write(write);
                        fileOut.newLine();
//                         if (false) {
//                             fileOut.close();
//                             return;
//                         }
                    }
                }
            fileOut.close();
        } catch(IOException ex) {
            System.out.println("Could not write to file");
        }
    }
    private static void freeze(int time) {
        try {
            Thread.currentThread().sleep(time);
        } catch (InterruptedException e) {
            System.out.println("Pause Error");
            return;
        }
    }
    private static void show() {
        int written = 0;
        for(int i = 0; i < bible.size(); i++)
                for(int j = 1; j < ((ArrayList)bible.get(i)).size(); j++) { // chapters
                    for(int k = 1; k < ((ArrayList)((ArrayList)bible.get(i)).get(j)).size(); k++) { // verses
//                         String write = (String)((ArrayList)bible.get(i)).get(0);
//                         write += " ";
//                         write += (String)((ArrayList)((ArrayList)bible.get(i)).get(j)).get(0);
//                         String verse = (String)((ArrayList)((ArrayList)((ArrayList)bible.get(i)).get(j)).get(k)).get(0);
//                         write += (verse.length() == 0) ? "" : ":";
//                         write += verse;
//                         write += " ";
                        String write = (String)((ArrayList)((ArrayList)((ArrayList)bible.get(i)).get(j)).get(k)).get(1);
//                         System.out.println((String)((ArrayList)bible.get(i)).get(0) + " " + (String)((ArrayList)((ArrayList)bible.get(i)).get(j)).get(0)
//                         + ":" + (String)((ArrayList)((ArrayList)((ArrayList)bible.get(i)).get(j)).get(k)).get(0) + " " + (String)((ArrayList)((ArrayList)((ArrayList)bible.get(i)).get(j)).get(k)).get(1));
                        while(written + write.length() > 120) {
                            int m = 120 - written;
                            while(m != -1 && write.charAt(m) != ' ') {
                                m--;
                            }
                            System.out.println(write.substring(0, (m == -1) ? 0 : m));
                            write = write.substring((m == -1) ? 1 : m + 1);
                            written = 0;
                            System.out.println(downKey);
                            if (!downKey)
                                freeze(500);
                        }
                        written += write.length();
                        System.out.print(write + " ");
                        
                    }
                }
    }
    public void getkey() {
        JApplet keyJApp = new JApplet();
        keyJApp.init();
        keyJApp.addKeyListener(new KeyAdapter() {
            int key;
            public void keyPressed(KeyEvent evt) {
                key = evt.getKeyCode();
                switch(key) {
                    case KeyEvent.VK_DOWN: {
                        downKey = true;
                        break;
                    }
                }
            }
            public void keyReleased(KeyEvent evt) {
                key = evt.getKeyCode();
                switch(key) {
                    case KeyEvent.VK_DOWN: {
                        downKey = false;
                        break;
                    }
                }
            }
        });
    }
}