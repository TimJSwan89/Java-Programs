//Marcos Arroyo
//Lab 11 task 1
import java.util.*;
public class DeliveryZone{

    private char alphaSection;
    private int numericalSection;
    private static DeliveryZone[][] grid=new DeliveryZone[9][26];
    
    public DeliveryZone(String zone)
    {
        setAlphaSection(zone.charAt(0));
        setNumericalSection((Integer.parseInt(""+zone.charAt(1))));
    }
    public void setAlphaSection(char newASect)
    {   alphaSection=newASect;  }
    public void setNumericalSection(int newNumSect)
    {   numericalSection=newNumSect;    }
    public char getASect()
    {   return alphaSection;    }
    public int getNumSect()
    {   return numericalSection;    }

    public int compareZones(DeliveryZone param)
    {
        int bool=2; //1=this comes before param, 0=equal, -1=this comes after param
        if((int)this.getASect()<(int)param.getASect())
            bool=1;
        else if((int)this.getASect()==(int)param.getASect())
                if(this.getNumSect()<param.getNumSect())
                    bool=1;
                else if(this.getNumSect()==param.getNumSect())
                        bool=0;
                     else
                        bool=-1;
             else
                bool=-1;
        return bool;
    }
    public String toString()
    {
        return ""+getASect()+getNumSect();
    }
    public static DeliveryZone[][] fillGrid()
    {
        char base='A';
        for(int alpha=0;alpha<26;alpha++)
        {
            
            for(int num=0;num<9;num++)
            {    
                grid[num][alpha]=new DeliveryZone(""+base+(num+1));
            }
            base='A';
            base+=(alpha+1);
        }
        return grid;
    }
    public static ArrayList<String> getAdjacents(DeliveryZone alpha)
    {   
        grid=fillGrid();
        ArrayList<String> ads=new ArrayList<String>(10);
            if(alpha.compareZones(new DeliveryZone("A1"))==0)
                {ads.add("A2");ads.add("B1");ads.add("B2");}
            else if(alpha.compareZones(new DeliveryZone("A9"))==0)
                {ads.add("A8");ads.add("B8");ads.add("B9");}
            else if(alpha.compareZones(new DeliveryZone("Z1"))==0)
                {ads.add("Y1");ads.add("Y2");ads.add("Z2"); }  
            else if(alpha.compareZones(new DeliveryZone("Z9"))==0)
                {ads.add("Y8");ads.add("Y9");ads.add("Z8");}
                
            else if(alpha.getASect()=='A')
                {
                    char sectAlph=alpha.getASect();
                    int num=alpha.getNumSect();
                    ads.add("A"+(num-1));ads.add("A"+(num+1));
                    sectAlph+=1;
                    ads.add(""+sectAlph+(num-1));ads.add(""+sectAlph+num);ads.add(""+sectAlph+(num+1));
                }
            else if(alpha.getASect()=='Z')
                {
                    char sectAlph=alpha.getASect();
                    int num=alpha.getNumSect();
                    ads.add("Z"+(num-1));ads.add("Z"+(num+1));
                    sectAlph-=1;
                    ads.add(""+sectAlph+(num-1));ads.add(""+sectAlph+num);ads.add(""+sectAlph+(num+1));      
                }
            else if(alpha.getNumSect()==1)
                {
                    char sectAlph=alpha.getASect();
                    int num=alpha.getNumSect();
                    ads.add(""+sectAlph+(num+1));
                    sectAlph-=1;
                    ads.add(""+sectAlph+(num));ads.add(""+sectAlph+(num+1));
                    sectAlph+=2;
                    ads.add(""+sectAlph+(num));ads.add(""+sectAlph+(num+1));      
                }
            else if(alpha.getNumSect()==9)
                {
                    char sectAlph=alpha.getASect();
                    int num=alpha.getNumSect();
                    ads.add(""+sectAlph+(num-1));
                    sectAlph-=1;
                    ads.add(""+sectAlph+(num));ads.add(""+sectAlph+(num-1));
                    sectAlph+=2;
                    ads.add(""+sectAlph+(num));ads.add(""+sectAlph+(num-1));      
                }
           else
                {
                    char sectAlph=alpha.getASect();
                    int num=alpha.getNumSect();
                    ads.add(""+sectAlph+(num-1));ads.add(""+sectAlph+(num+1));
                    sectAlph-=1;
                    ads.add(""+sectAlph+(num-1));ads.add(""+sectAlph+(num));ads.add(""+sectAlph+(num+1));
                    sectAlph+=2;
                    ads.add(""+sectAlph+(num-1));ads.add(""+sectAlph+(num));ads.add(""+sectAlph+(num+1));
                }
   
        
        return ads;
    }
    public static boolean isAdjacent(DeliveryZone unknown,DeliveryZone known)
    {
//             System.out.println("Inside of the isAdjacent method, press any key to continue");
//             wait=scan.next();
            ArrayList<String> adjacentZones=DeliveryZone.getAdjacents(known);
            boolean adj=false;
            for(int f=0;f<adjacentZones.size();f++)
            {
//                 System.out.println(adjacentZones.get(f));
                if(unknown.compareZones(new DeliveryZone(adjacentZones.get(f)))==0)
                    adj=true;
            }
            return adj;
    }
            
    
}