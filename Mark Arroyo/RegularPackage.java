//Marcos Arroyo
//Lab 11 task 1

public class RegularPackage extends PackageMA{
    private static boolean special=false;
    
    public RegularPackage(DateMA sDate,String compName,String delZone,
    double weight,double volume)
    {super();setDate(sDate);setCompanyName(compName);
     setDeliveryZone(new DeliveryZone(delZone));
     setWeight(weight);setVolume(volume);setSpecial(false);
    }
    
}