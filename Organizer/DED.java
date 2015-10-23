//Mark Arroyo
//Encrypt

import java.util.*;
import java.io.*;
public class DED
{   
    public static void main(String[]args)
    {
    Scanner scan = new Scanner(System.in);    
    
    int key=0;
    
    while(key!=3)
    {
    menu();
    key=Integer.parseInt(scan.nextLine());
    
    switch(key)
    {
    case 1:    
    Encrypt nul = new Encrypt();
    nul.write(1,new String(""));
    break;
    
    case 2:
    Encrypt lun = new Encrypt();
    lun.read();
    break;
    
    case 3:
    System.out.println("Bye");
    break;
    default:
    System.out.println("Invalid input");
    
   }
   }
   } 
   
   private static void menu()
   {
       System.out.println("1)Write to file");
       System.out.println("2)Decrypt data file");
       System.out.println("3)Quit)");
   }
    
}
