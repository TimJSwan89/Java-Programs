//Marcos Arroyo
//Lab 13 Dispatcher class for final project
import java.io.*;
import java.util.*;

public class DispatcherAM{
        private static Scanner scan=new Scanner(System.in);
        private static String wait;
 
        private static ArrayList<PackageMA> specialsToBeSorted=new ArrayList<PackageMA>();
        private static ArrayList<PackageMA> regularsToBeSorted=new ArrayList<PackageMA>();
        
        public static void main(String[] ponies){
            
            int count=0;
            System.out.println("Please input the file name: ");
            String temp=scan.next();
            if(!temp.equals("packages.txt") && !temp.equals("packages1.txt")){
                System.out.println("Invalid file name, "+
                "re-routed to: packages.txt");
                temp="packages.txt";
            }
            ArrayList<String[]> tempList=manageFile(temp);
            for(int x=0;x<tempList.size();x++){
                String[] array=tempList.get(x);
                for(int y=0;y<array.length;y++)
                    System.out.print(array[y]+",");
                System.out.print("\n");   count++; 
            }
            System.out.println("File read and opened, contains the following data");
            System.out.println("Number of packages: "+count);
            System.out.println("Press any key to continue");
            wait=scan.next();
            
            regularsToBeSorted=SortsAM.sortPackages(tempList);
            specialsToBeSorted=SortsAM.getSpecials();
            
            
            ArrayList<PackageMA> sortedRegs=SortsAM.sortPacksByZone(regularsToBeSorted);
            //sorting all regular packs by zone only
            ArrayList<PackageMA> sortedSpecsTime=SortsAM.sortPacksTime(specialsToBeSorted);
            ArrayList<PackageMA> sortedSpecs=SortsAM.sortPacksByZone(sortedSpecsTime);
            //sorting all the special packs in order
            System.out.println("Packages sorted into temp locations, press any key to continue");
            wait=scan.next();
//             for(int y=0;y<sortedSpecs.size();y++)
//                     System.out.println(sortedSpecs.get(y));
//             System.out.println("Special packs sorted");
//             wait=scan.next();
                       
            LoadingAM.loadSpecialTrucks(sortedSpecs);
            System.out.println("Special Truck 1");
            for(int y=0;y<LoadingAM.specialA.getSize() && LoadingAM.specialA.getPackage(y)!=null;y++)
                    System.out.println(LoadingAM.specialA.getPackage(y));
            System.out.println("Special Truck 2");
            for(int y=0;y<LoadingAM.specialB.getSize() && LoadingAM.specialB.getPackage(y)!=null;y++)
                    System.out.println(LoadingAM.specialB.getPackage(y));
            System.out.println("Special Truck 3");
            for(int y=0;y<LoadingAM.specialC.getSize() && LoadingAM.specialC.getPackage(y)!=null;y++)
                    System.out.println(LoadingAM.specialC.getPackage(y));
            System.out.println("Special packages loaded on to trucks, press any key to continue");
            wait=scan.next();
            
            for(int y=0;y<sortedRegs.size();y++)
                    System.out.println(sortedRegs.get(y));
            System.out.println("Regular packs sorted");
            wait=scan.next();
            
            LoadingAM.loadRegularTrucks(sortedRegs); 
            for(int w=0;w<LoadingAM.regularA.getHours() && LoadingAM.regularA.getPackage(w,0)!=null;w++){
                for(int x=0;x<LoadingAM.regularA.getStops() && LoadingAM.regularA.getPackage(w,x)!=null;x++)
                    System.out.println(LoadingAM.regularA.getPackage(w,x));
                System.out.println("Hour: "+(w+1));
                wait=scan.next();
            }
            System.out.println("Regular packages loaded on to trucks, press any key to continue");
            wait=scan.next();
        }     
        private static ArrayList<String[]> manageFile(String filename)
        {   
            ArrayList<String[]> listOfStrings=new ArrayList<String[]>();
            String[] toks=new String[1];
            try{
            FileReader file=new FileReader("C:\\Documents and Settings\\Administrator\\Desktop\\New Folder\\" + filename);//packages.txt
            BufferedReader read=new BufferedReader(file);
            
            while(read.ready()){
            toks=read.readLine().split(",");
            listOfStrings.add(toks);
            }
            read.close();
            }
            catch(IOException ex)
            {   System.out.print(ex+"\n");  }
            return listOfStrings;
        }
            
}
            
            
            