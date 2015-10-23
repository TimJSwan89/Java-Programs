public class Info {
    public static void main() {
        System.out.println("Welcome to the organizer built by Timothy Swan.\n" + 
        "Please enter the title of your database");
        Inter main = new Inter(Keyboard.readString(), null);
        Inter current = main;
        do {
            Inter mother = current.getMother();
            System.out.println("===================\n" + 
            current.toString(1) + "\n===================\nCommands: \n" +  
            "1. Reprint the single level subfiles of \"" + current + "\" \n" +
            "2. Print subfiles of \"" + current + "\" to specified level \n" +
            "3. Print all subfiles of \"" + current + "\" \n" + 
            "4. Rename \"" + current + "\" \n" + 
            "5. Add file to \"" + current + "\"\n" + 
            "6. Delete a subfile of \"" + current + "\" \n" + 
            "7. Select a subfile of \"" + current + "\" \n" + 
            "8. " + ((mother == null) ? "N/A (no parent file)" : "Select \"" + mother + "\"") + "\n" +
            "9. Exit program\n" + "-------------------");
            switch(Keyboard.readInt()) {
                case(1): {
                    System.out.println(current.toString(1));
                } break;
                case(2): {
                    System.out.println("Enter maximum file level");
                    System.out.println(current.toString(Keyboard.readInt()));
                } break;
                case(3): {
                    System.out.println(current.toString(-1));
                } break;
                case(4): {
                    System.out.println("Enter new filename");
                    current.changeTitle(Keyboard.readString());
                } break;
                case(5): {
                    System.out.println("Enter title of file");
                    current.add(Keyboard.readString());
                } break;
                case(6): {
                    System.out.println("Enter the number of item to delete.");
                    current.remove(Keyboard.readInt() - 1);
                } break;
                case(7): {
                    System.out.println("Enter the number of file to select.");
                    Inter temp = current.getFile(Keyboard.readInt() - 1);
                    if (temp != null)
                        current = temp;
                } break;
                case(8): {
                    if (mother == null)
                        System.out.println("No parent file avaliable");
                    else
                        current = mother;
                } break;
                case(9): {
                    return;
                }
                default: {
                    System.out.println("Input error: Please enter the number of an avaliable selection.");
                } break;
            }
        } while(true);
    }
}