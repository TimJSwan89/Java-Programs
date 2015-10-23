//Marcos Arroyo
//Lab 11 task 1

public class SpecialPackage extends PackageMA{
    private double deliveryTime;
    private static boolean special=true;
    
    public SpecialPackage(DateMA sDate,String compName,String delZone,
    double weight,double volume,double time)
    {super();setDeliveryTime(time);setDate(sDate);
     setCompanyName(compName);setDeliveryZone(new DeliveryZone(delZone));
     setWeight(weight);setVolume(volume);setSpecial(special);
    }
    
    public void setDeliveryTime(double time)
    {   deliveryTime=time;  }
    public double getDeliveryTime()
    {   return deliveryTime;    }
    public String toString()
    {   String sStr=super.toString()+","+getDeliveryTime();
        return sStr;
    }

}