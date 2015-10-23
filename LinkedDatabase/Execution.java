import java.util.ArrayList;
public class Execution {
    public static void main() {
        printLine(2);
        System.out.print("Root [data] title > ");
        Data current = new Data(Keyboard.readString());
        printLine(0);
        System.out.println(current);
        for( ; ; ) {
            printLine(1);
            int i = 0;
            while(i < 1 || i > 8) {
                System.out.print("Menu" + 
                                   "\n\t1.  Link " + current + " with [new]." + 
                                   "\n\t2.  Link " + current + " with [data]." + 
                                   "\n\t3.  Unlink " + current + " with [data]." +
                                   "\n\t4.  Combine " + current + " with [data]." + 
                                   "\n\t5.  Destroy " + current + "." +
                                   "\n\t6.  List immediates of " + current + "." +
                                   "\n\t7.  List descendants of " + current + "." +
                                   "\n\t8.  Select [data]." +
                                   "\n\t9.  Save to file." +
                                   "\n\t10. Load from file." +
                                   "\n\t11. Exit." +
                                   "\nSelect (1 - 11) > ");
                i = Keyboard.readInt();
            }
            switch(i) {
                case 1: {
                    printLine(2);
                    System.out.print("Create link > ");
                    current.bind(new Data(Keyboard.readString()));
                    break;
                }
                case 2: {
                    
                    break;
                }
                case 3: {
                    
                    break;
                }
                case 4: {
                    
                    break;
                }
                case 5: {
                    
                    break;
                }
                case 6: {
                    printLine(0);
                    System.out.println(current.immediates(false));
                    continue;
                }
                case 7: {
                    printLine(0);
                    System.out.println(current.descendants());
                    continue;
                }            
                case 8: {
                    int j = -1;
                    while (j < 0 || j >= current.size()) {
                        printLine(0);
                        System.out.println(current.immediates(true));
                        printLine(1);
                        System.out.println("Select (1 - " + current.size() + ") > ");
                        j = Keyboard.readInt() - 1;
                    }
                    current = current.get(j);
                    break;
                }
                case 9: {
                    
                    break;
                }
                case 10: {
                    
                    break;
                }
                case 11: {
                    return;
                }
            }
            printLine(0);
            System.out.println(current.immediates(false));
        }
    }
    public static void printLine(int kind) {
        if (kind == 1)
            System.out.println("------------------------------------");
        else if (kind == 2)
                System.out.println("************************************");
            else
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + 
                                   "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + 
                                   "____________________________________");
    }
}