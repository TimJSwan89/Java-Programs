//Marcos Arroyo
//Lab 11 task 1

public class PackageMA{
    private static int id;
    private DateMA delDate;
    protected int uniqueID;
    protected String compName;
    protected DeliveryZone zone;
    protected double weight,volume;
    private boolean special=false;
    
    public PackageMA()
    {   setID();
    }
    public void setID()
    {   uniqueID=id;
        id++;
    }
    public void setID(int newID)
    {   uniqueID=newID; }
    public void setDate(DateMA rDate)
    {   delDate=copyDate(rDate);   }
    public void setCompanyName(String compName)
    {   this.compName=compName;  }
    public void setDeliveryZone(DeliveryZone delZone)
    {   zone=new DeliveryZone(""+delZone.getASect()+delZone.getNumSect());   }
    public void setWeight(double weight)
    {   if(DataConfirmation.isPositive(weight))
            this.weight=weight;
        else this.weight=0;    
    }
    public void setVolume(double volume)
    {   if(DataConfirmation.isPositive(volume))
            this.volume=volume; 
        else this.volume=0;
    }
    public DateMA getDate()
    {   return copyDate(delDate);}
    public int getID()
    {   return uniqueID;    }
    public String getCompanyName()
    {   return compName;    }
    public DeliveryZone getDeliveryZone()
    {   return new DeliveryZone(""+zone.getASect()+zone.getNumSect()); }
    public double getWeight()
    {   return weight;  }
    public double getVolume()
    {   return volume;  }
    private DateMA copyDate(DateMA date)
    {   return new DateMA(date.getMonth(),date.getDay(),date.getYear()); }    
    public void setSpecial(boolean bo)
    {   special=bo; }
    public boolean isSpecial()
    {   return special; }
    public String toString()
    {   String s="R";
        if(this.isSpecial())s="S";
        String stf="Package: "+getID()+","+s+","+getCompanyName()+","+
        getDeliveryZone().toString()+","+getDate().toString()+","+getWeight()+","+
        getVolume();
        return stf;
    }

}