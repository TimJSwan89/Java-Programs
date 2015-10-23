import java.util.*;
import javax.swing.*;
public class Reader {
    public static void main() {
        String input, output = "Enter a sentence here:";
        JOptionPane pane = new JOptionPane();
        do {
            input = pane.showInputDialog(output);
            output = "";
            if (input != null) {
                StringTokenizer strTok = new StringTokenizer(input);
                while (strTok.hasMoreTokens()) {
                    String word = strTok.nextToken();
                    if (word.length() > 3)
                        word = word.charAt(0) + 
                        scramble(word.substring(1, word.length() - 1)) + // true) + 
                        word.charAt(word.length() - 1);
                    output += word + " ";
                }
            }
        } while (input != null);
    }
    public static String scramble(String word) { //, boolean length) {
        return toString(randomOrder(toCharArray(word))); //, length));
    }
    public static String toString(char[] list) {
        String string = "";
        for(int i = 0; i < list.length; i++)
            string += list[i];
        return string;
    }
    public static char[] toCharArray(String string) {
        char[] list = new char[string.length()];
        for(int i = 0; i < string.length(); i++)
            list[i] = string.charAt(i);
        return list;
    }
    public static char[] randomOrder(char[] list) { //, boolean length) {
        char[] mixed = new char[list.length];
        for(int i = 0; i < list.length; i++)
            mixed[i] = list[i];
        Random rand = new Random();
        for(int i = list.length - 1; i > 0; i--) {
            int randomInt = rand.nextInt(i + 1); //length ? list.length : i + 1);
            char temp = mixed[i];
            mixed[i] = mixed[randomInt];
            mixed[randomInt] = temp;
        }
        return mixed;
    }
//     public static void test() {
//         ArrayList<String> bob = new ArrayList<String>();
//         int[] num = new int[4 * 3 * 2 * 1];
//         for(int i = 0; i < 2147000000; i++) {
//             String cat = scramble("1234", true);
//             int index = bob.indexOf(cat);
//             if (index == -1)
//                 bob.add(cat);
//             else
//                 num[index]++;
//         }
//         for(int i = 0; i < bob.size(); i++)
//             System.out.print(bob.get(i) + "\t");
//         System.out.println();
//         for(int i = 0; i < num.length; i++)
//             System.out.print(num[i] + "\t");
//    }
}