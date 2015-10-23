//Marcos Arroyo
//Large truck class

public class LargeTruckAM extends TruckMA{
//     private final int TRUCK_SIZE=250;
    private final double WEIGHT_LIM=500,VOLUME_LIM=600;
    private final int MAX_STOPS_HOUR=5, MAX_PACKAGES_STOP=25,MAX_WORK_HOURS=8;
    private double currentWeight,currentVolume;
    private static int id;
    private int uniqueID,currentPack,currentHour;
    private PackageMA[][] packages=new PackageMA[MAX_WORK_HOURS][MAX_PACKAGES_STOP];
    public LargeTruckAM()
    {   super();    }
    public int getHours()
    {   return MAX_WORK_HOURS;  }
    public int getStops()
    {   return MAX_PACKAGES_STOP;   }
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
        if(updateWeight(weight)&&updateVolume(volume)&&currentHour<MAX_WORK_HOURS){
           if(currentPack<MAX_PACKAGES_STOP){ 
                packages[currentHour][currentPack]=copy(newPack);
                currentPack++;loaded=!loaded;
            }
           if(currentPack==MAX_PACKAGES_STOP){
                currentPack=0;
                currentHour++;
            }
               
        }
        return loaded;
    }
    public boolean loadPackage(PackageMA newPack,int hour,int stop)
    {   boolean loaded=false;
        double weight=newPack.getWeight();
        double volume=newPack.getVolume();
        if(updateWeight(weight)&&updateVolume(volume)&&hour<MAX_WORK_HOURS){
           if(currentPack<MAX_PACKAGES_STOP){ 
                packages[hour][stop]=copy(newPack);
                loaded=!loaded;
            }
        }
        return loaded;
    }
    public boolean isFull()
    {
        if(currentWeight==WEIGHT_LIM || currentVolume==VOLUME_LIM || currentHour==MAX_WORK_HOURS)
            return true;
        else
            return false;
    }
    public PackageMA getPackage(int hour,int stop)
    {
        return copy(packages[hour][stop]);
    }
}