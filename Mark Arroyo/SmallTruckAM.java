//Marcos Arroyo
//Small truck class

public class SmallTruckAM extends TruckMA{
    private final int TRUCK_SIZE=150;
    private final double WEIGHT_LIM=100,VOLUME_LIM=200;
    private final int MAX_STOPSHOUR=5, MAX_PACKAGESSTOP=5,MAX_WORK_HOURS=8;
    private double currentWeight,currentVolume;
    private int uniqueID,numPacks;
    private PackageMA[] packages=new PackageMA[TRUCK_SIZE];
    public SmallTruckAM()
    {   super();    }
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
}