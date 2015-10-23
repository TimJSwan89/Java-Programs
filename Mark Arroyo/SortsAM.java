//Marcos Arroyo
//CS116 final project labs
import java.io.*;
import java.util.*;
public class SortsAM{

        private static ArrayList<PackageMA> specialsToBeSorted=new ArrayList<PackageMA>();
        private static ArrayList<PackageMA> regularsToBeSorted=new ArrayList<PackageMA>();
        private static DeliveryZone[] testMethod=new DeliveryZone[234];

        public static ArrayList<PackageMA> sortPackages(ArrayList<String[]> packages)
        {//splits packages into 2 arraylists, regular and specials
            for(int c=0;c<packages.size();c++){
                String[] currentPackage=packages.get(c);
                String date=currentPackage[3];
                String[] brokenDate=date.split("/");
                int mm=Integer.parseInt(brokenDate[0]);
                int dd=Integer.parseInt(brokenDate[1]);
                int yyyy=Integer.parseInt(brokenDate[2]);
                int weight=Integer.parseInt(currentPackage[4]);
                int volume=Integer.parseInt(currentPackage[5]);
                
                DateMA currentDate=new DateMA(mm,dd,yyyy);
                if(!currentPackage[0].equalsIgnoreCase("S"))
                {
                    RegularPackage tempReg=new RegularPackage(currentDate,
                    currentPackage[1],currentPackage[2],weight,volume);
                    regularsToBeSorted.add(tempReg);
                }
                else
                {
                    SpecialPackage tempSpec=new SpecialPackage(currentDate,
                    currentPackage[1],currentPackage[2],weight,volume,
                    Integer.parseInt(currentPackage[currentPackage.length-1]));
                    specialsToBeSorted.add(tempSpec);
                }
            }
                return regularsToBeSorted;
        }
        public static ArrayList<PackageMA> getSpecials()
        {   return specialsToBeSorted;  }
        private static DeliveryZone[] getAllZones(ArrayList<PackageMA> specials)
        {
            TruckMA tempTruck=new TruckMA();
            TruckMA tempTruck2=new TruckMA();
            TruckMA tempTruck3=new TruckMA();
            
            int count=0;//keeps track of amount of new zones inside of the list of zones(allZones)
            DeliveryZone[] allZones=new DeliveryZone[234];
            int[][] countZones=new int[234][1];//first dim=maximum amount of zones
                                                //second dim=amount of that kind of zone in the zones list
            boolean isInList=false;
            int oldPackIndex=0;

            for(int g=0;g<specials.size();g++)
            {
                //checks every zone to see if they equal the zone of the current package in specials list
                for(int d=0;d<count && !isInList;d++){
                    if(allZones[d].compareZones(specials.get(g).getDeliveryZone())==0)
                    {
                        isInList=true;
                        oldPackIndex=d;
                    }
                }
                if(!isInList && count<allZones.length)    
                {   allZones[count]=new DeliveryZone(specials.get(g).getDeliveryZone().toString());
                    countZones[count][0]++;
                    count++;
                }
                else//else reached implies the package zone is in the list, just add one to count of that zone
                {   countZones[oldPackIndex][0]++;
                    isInList=false;
                }
                
            }   
            return allZones;
        }
        private static DeliveryZone[] sortZones(ArrayList<PackageMA> specials)
        {
            DeliveryZone[] zones=getAllZones(specials);
            int amountZones=0;
            int d=0;
            while(zones[d]!=null)
            {amountZones++;d++;}
            
            for(int e=0;e<amountZones-1;e++)
            {
                for(int x=0;x<amountZones-e-1;x++)
                    if(zones[x].compareZones(zones[x+1])==-1)
                    {
                        DeliveryZone temp=zones[x+1];
                        zones[x+1]=zones[x];
                        zones[x]=temp;
                    }
            }
            return zones;
        }
        public static ArrayList<PackageMA> sortPacksByZone(ArrayList<PackageMA> unsortedPacks)
        {
            DeliveryZone[] sortedZones=sortZones(unsortedPacks);
            ArrayList<PackageMA> sortedPacks=new ArrayList<PackageMA>(unsortedPacks.size());
            
            for(int t=0;t<sortedZones.length && sortedZones[t]!=null;t++)
            {
                for(int j=0;j<unsortedPacks.size() && unsortedPacks.get(j)!=null;j++)
                {
                    if(unsortedPacks.get(j).getDeliveryZone().compareZones(sortedZones[t])==0)
                        sortedPacks.add(unsortedPacks.get(j));
                }
            }
            return sortedPacks;
        }
       
        
        public static ArrayList<PackageMA> sortPacksTime(ArrayList<PackageMA> unsortedPacks)
        {
            int x=unsortedPacks.size();
            for(int y=0;y<x-1;y++)
            {
                         for(int z=0;z<x-y-1;z++)
                         {
                             SpecialPackage tempSpec1=(SpecialPackage)unsortedPacks.get(z);
                             SpecialPackage tempSpec2=(SpecialPackage)unsortedPacks.get(z+1);
                        
                             if(tempSpec1.getDeliveryTime()>tempSpec2.getDeliveryTime())
                             {
                             PackageMA temp=unsortedPacks.get(z+1);
                             unsortedPacks.set(z+1,unsortedPacks.get(z));
                             unsortedPacks.set(z,temp);
                             }
                         }
                         
                    }
             return unsortedPacks;
        }
        
        
        
    }