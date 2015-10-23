import java.util.StringTokenizer;
public class Interface2 {
    final static String[] commands = {"Add", "Edit", "Move", "Remove", 
        "Sort", "View"}; 
    final static String[] files = {"Calendar", "Checklist", "Contacts"}; 
    final static String help = "Help";
    public static void main() {
        System.out.println("Organizer\tCreator: Timothy Swan\n" + 
        "Please enter a command or type \"" + help + "\" for permissible input."); 
        String input = Keyboard.readString(); 
        StringTokenizer strTok = new StringTokenizer(input);
        while (strTok.hasMoreTokens()) { 
            String word = strTok.getNextToken(); 
            if (word.equalsIgnoreCase(help)) {
                System.out.println("Commands:"); 
                for(int i = 0; i < commands.length; i++)
                    System.out.println(commands[i]);
                System.out.println("Files:");
                for(int i = 0; i < files.length; i++)
                    System.out.println(files[i]);
            } 
//             for(int i = 0; i < commands.length; i++) 
//                 if (word.equalsIgnoreCase(commands[i])) 
//                     
        }
    }
}