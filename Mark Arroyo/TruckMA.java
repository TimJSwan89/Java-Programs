//Marcos Arroyo
//Lab 11 task 1
import java.io.*;
import java.util.*;
public class TruckMA{

//It takes 1 hour to deliver 5 packages
//So truck limit is 8 hours x 5 packages X 5 stops(companies)
//=200 packages in 8 hours

    private final int TRUCK_SIZE=200;
    private final double WEIGHT_LIM=300,VOLUME_LIM=400;
    private final int MAX_STOPS_HOUR=5, MAX_PACKAGES_STOP=5,MAX_WORK_HOURS=8;
    private double currentWeight,currentVolume;
    private static int id;
    private int uniqueID,numPacks;
    private PackageMA[] packages=new PackageMA[TRUCK_SIZE];

    public TruckMA()
    {
        setID();
    }
    public void setID()
    {   uniqueID=id;
        id++;
    }
    public int getID()
    {   return  uniqueID;   }
    public int getSize()
    {   return TRUCK_SIZE;  }
    public boolean isFull()
    {
        if(currentWeight==WEIGHT_LIM || currentVolume==VOLUME_LIM || numPacks==TRUCK_SIZE)
            return true;
        else
            return false;
    }
    private boolean updateWeight(double newWeight)
    {   boolean updated=false;
        double testInput=currentWeight+newWeight;
        if(testInput<=WEIGHT_LIM){
            currentWeight+=newWeight;
            updated=!updated;
        }
        return updated;
    }
    private boolean updateVolume(double newVolume)
    {   boolean updated=false;
        double testInput=currentVolume+newVolume;
        if(testInput<=VOLUME_LIM){
            currentVolume+=newVolume;
            updated=!updated;
        }
        return updated;
    }      
    public boolean loadPackage(PackageMA newPack)
    {   boolean loaded=false;
        double weight=newPack.getWeight();
        double volume=newPack.getVolume();
        if(updateWeight(weight)&&updateVolume(volume)&&numPacks<TRUCK_SIZE){
            packages[numPacks]=copy(newPack);
            numPacks++;loaded=!loaded;
        }
        return loaded;
    }
    protected PackageMA copy(PackageMA newPack)
    {   if(newPack==null)
            return null;
        
        boolean special=newPack.isSpecial();
        int tempid=newPack.getID();
        PackageMA newPackage;
        if(special){
            SpecialPackage sPack=(SpecialPackage)newPack;
            newPackage=new SpecialPackage(newPack.getDate(),
            newPack.getCompanyName(),newPack.getDeliveryZone().toString(),
            newPack.getWeight(),newPack.getVolume(),sPack.getDeliveryTime());
            newPackage.setID(tempid);
        }
        else{
            newPackage=new RegularPackage(newPack.getDate(),
            newPack.getCompanyName(),newPack.getDeliveryZone().toString(),
            newPack.getWeight(),newPack.getVolume());
            newPackage.setID(tempid);
        }
        return newPackage;
    }
    public String toString()
    {   String poly="";
        for(int w=0;w<numPacks;w++)
            poly+=packages[w].toString()+"\n";
        return poly;
    }
    public PackageMA getPackage(int index)
    {
        return copy(packages[index]);
    }
}
            
        
        
        
        