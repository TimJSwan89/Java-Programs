//Marcos Arroyo
//Final project cs116: loading class
import java.io.*;
import java.util.*;
public class LoadingAM{

    public static LargeTruckAM regularA=new LargeTruckAM();public static LargeTruckAM regularB=new LargeTruckAM();
    public static LargeTruckAM regularC=new LargeTruckAM();public static LargeTruckAM regularD=new LargeTruckAM();
    public static LargeTruckAM regularE=new LargeTruckAM();public static LargeTruckAM regularF=new LargeTruckAM();
    public static LargeTruckAM regularG=new LargeTruckAM();public static LargeTruckAM regularH=new LargeTruckAM();
    public static LargeTruckAM regularI=new LargeTruckAM();public static LargeTruckAM regularJ=new LargeTruckAM();    
    
    public static TruckMA specialA=new TruckMA(); public static TruckMA specialB=new TruckMA();
    public static TruckMA specialC=new TruckMA(); public static TruckMA specialD=new TruckMA();
    public static TruckMA specialE=new TruckMA(); public static TruckMA specialF=new TruckMA();
    public static TruckMA specialG=new TruckMA(); public static TruckMA specialH=new TruckMA();
    
    public static void loadRegularTrucks(ArrayList<PackageMA> packages)
    {
            boolean stop=false;
            boolean onceA=false;boolean onceB=false;boolean onceC=false;
            boolean onceD=false;boolean onceE=false;boolean onceF=false;
            boolean onceG=false;boolean onceH=false;boolean onceI=false;
            boolean onceJ=false;
     while(packages.size()!=0){
            DeliveryZone currentZone=packages.get(0).getDeliveryZone();
          if(!regularA.isFull() && !onceA){
            while(!regularA.isFull() && packages.size()!=0 && !stop){
                    if(currentZone.compareZones(packages.get(0).getDeliveryZone())!=0)
                        stop=true;
                    else regularA.loadPackage(packages.remove(0));
                
                }
            int count=0;int hour=1;int stopCount=0;int zoneCount=0;    
            
            ArrayList<String> adjacentZones=DeliveryZone.getAdjacents(currentZone);
            DeliveryZone adjacentZone=packages.get(0).getDeliveryZone();
            
            while(!regularA.isFull() && packages.size()!=0 && count<packages.size() 
                    && zoneCount<=adjacentZones.size() && stopCount<regularA.getStops()){
                    boolean adjacentsLeft=DeliveryZone.isAdjacent(currentZone,packages.get(count).getDeliveryZone());     
                    if(adjacentsLeft){
                      if(adjacentZone.compareZones(packages.get(count).getDeliveryZone())==0){  
                        regularA.loadPackage(packages.remove(count),hour,stopCount);
                        count--;stopCount++;}count++;}
                      else{
                        zoneCount++;adjacentZone=packages.get(count).getDeliveryZone();
                        stopCount=0;hour++;
                       }
                    }
                    onceA=true;
                    System.out.println(!regularA.isFull());
                    System.out.println(packages.size()!=0);
                    System.out.println(count<packages.size());
                    System.out.println(zoneCount<adjacentZones.size());
                    System.out.println(stopCount<regularA.getStops());
                }
          if(!regularB.isFull() && onceA && packages.size()!=0){stop=false;currentZone=packages.get(0).getDeliveryZone();  
                while(!regularB.isFull() && packages.size()!=0 && !stop){
                    if(currentZone.compareZones(packages.get(0).getDeliveryZone())!=0)
                        stop=true;
                    else regularB.loadPackage(packages.remove(0));}
                int count=0;
                while(!regularB.isFull() && packages.size()!=0 && count<packages.size()){
                    boolean adjacentsLeft=DeliveryZone.isAdjacent(currentZone,packages.get(count).getDeliveryZone());     
                    if(adjacentsLeft){   
                        regularB.loadPackage(packages.remove(count));
                        count--;}count++;
                    }
                    onceB=true;
                }
          if(!regularC.isFull() && onceB && packages.size()!=0){stop=false;currentZone=packages.get(0).getDeliveryZone();  
                while(!regularC.isFull() && packages.size()!=0 && !stop){
                    if(currentZone.compareZones(packages.get(0).getDeliveryZone())!=0)
                        stop=true;
                    else regularC.loadPackage(packages.remove(0));}
                int count=0;
                while(!regularC.isFull() && packages.size()!=0 && count<packages.size()){
                    boolean adjacentsLeft=DeliveryZone.isAdjacent(currentZone,packages.get(count).getDeliveryZone());     
                    if(adjacentsLeft){   
                        regularC.loadPackage(packages.remove(count));
                        count--;}count++;
                    }
                    onceC=true;
                }
          if(!regularD.isFull() && onceC && packages.size()!=0){stop=false;currentZone=packages.get(0).getDeliveryZone();  
                while(!regularD.isFull() && packages.size()!=0 && !stop){
                    if(currentZone.compareZones(packages.get(0).getDeliveryZone())!=0)
                        stop=true;
                    else regularD.loadPackage(packages.remove(0));}
                int count=0;
                while(!regularD.isFull() && packages.size()!=0 && count<packages.size()){
                    boolean adjacentsLeft=DeliveryZone.isAdjacent(currentZone,packages.get(count).getDeliveryZone());     
                    if(adjacentsLeft){   
                        regularD.loadPackage(packages.remove(count));
                        count--;}count++;
                    }
                    onceD=true;
                }       
          if(!regularE.isFull() && onceD && packages.size()!=0){stop=false;currentZone=packages.get(0).getDeliveryZone();  
                while(!regularE.isFull() && packages.size()!=0 && !stop){
                    if(currentZone.compareZones(packages.get(0).getDeliveryZone())!=0)
                        stop=true;
                    else regularE.loadPackage(packages.remove(0));}
                int count=0;
                while(!regularE.isFull() && packages.size()!=0 && count<packages.size()){
                    boolean adjacentsLeft=DeliveryZone.isAdjacent(currentZone,packages.get(count).getDeliveryZone());     
                    if(adjacentsLeft){   
                        regularE.loadPackage(packages.remove(count));
                        count--;}count++;
                    }
                    onceE=true;
                }
          if(!regularF.isFull() && onceE && packages.size()!=0){stop=false;currentZone=packages.get(0).getDeliveryZone();  
                while(!regularF.isFull() && packages.size()!=0 && !stop){
                    if(currentZone.compareZones(packages.get(0).getDeliveryZone())!=0)
                        stop=true;
                    else regularF.loadPackage(packages.remove(0));}
                int count=0;
                while(!regularF.isFull() && packages.size()!=0 && count<packages.size()){
                    boolean adjacentsLeft=DeliveryZone.isAdjacent(currentZone,packages.get(count).getDeliveryZone());     
                    if(adjacentsLeft){   
                        regularF.loadPackage(packages.remove(count));
                        count--;}count++;
                    }
                    onceF=true;
                }       
          if(!regularG.isFull() && onceF && packages.size()!=0){stop=false;currentZone=packages.get(0).getDeliveryZone();  
                while(!regularG.isFull() && packages.size()!=0 && !stop){
                    if(currentZone.compareZones(packages.get(0).getDeliveryZone())!=0)
                        stop=true;
                    else regularG.loadPackage(packages.remove(0));}
                int count=0;
                while(!regularG.isFull() && packages.size()!=0 && count<packages.size()){
                    boolean adjacentsLeft=DeliveryZone.isAdjacent(currentZone,packages.get(count).getDeliveryZone());     
                    if(adjacentsLeft){   
                        regularG.loadPackage(packages.remove(count));
                        count--;}count++;
                    }
                    onceG=true;
                }
          if(!regularH.isFull() && onceG && packages.size()!=0){stop=false;currentZone=packages.get(0).getDeliveryZone();  
                while(!regularH.isFull() && packages.size()!=0 && !stop){
                    if(currentZone.compareZones(packages.get(0).getDeliveryZone())!=0)
                        stop=true;
                    else regularH.loadPackage(packages.remove(0));}
                int count=0;
                while(!regularH.isFull() && packages.size()!=0 && count<packages.size()){
                    boolean adjacentsLeft=DeliveryZone.isAdjacent(currentZone,packages.get(count).getDeliveryZone());     
                    if(adjacentsLeft){   
                        regularH.loadPackage(packages.remove(count));
                        count--;}count++;
                    }
                    onceH=true;
                }       
          if(!regularI.isFull() && onceH && packages.size()!=0){stop=false;currentZone=packages.get(0).getDeliveryZone();  
                while(!regularI.isFull() && packages.size()!=0 && !stop){
                    if(currentZone.compareZones(packages.get(0).getDeliveryZone())!=0)
                        stop=true;
                    else regularI.loadPackage(packages.remove(0));}
                int count=0;
                while(!regularI.isFull() && packages.size()!=0 && count<packages.size()){
                    boolean adjacentsLeft=DeliveryZone.isAdjacent(currentZone,packages.get(count).getDeliveryZone());     
                    if(adjacentsLeft){   
                        regularI.loadPackage(packages.remove(count));
                        count--;}count++;
                    }
                    onceI=true;
                }
          if(!regularJ.isFull() && onceI && packages.size()!=0){stop=false;currentZone=packages.get(0).getDeliveryZone();  
                while(!regularJ.isFull() && packages.size()!=0 && !stop){
                    if(currentZone.compareZones(packages.get(0).getDeliveryZone())!=0)
                        stop=true;
                    else regularJ.loadPackage(packages.remove(0));}
                int count=0;
                while(!regularJ.isFull() && packages.size()!=0 && count<packages.size()){
                    boolean adjacentsLeft=DeliveryZone.isAdjacent(currentZone,packages.get(count).getDeliveryZone());     
                    if(adjacentsLeft){   
                        regularJ.loadPackage(packages.remove(count));
                        count--;}count++;
                    }
                    onceJ=true;
                }       
                
            }          
          
    }
    
    public static void loadSpecialTrucks(ArrayList<PackageMA> packages)
    {   
            boolean stop=false;
            boolean onceA=false;boolean onceB=false;boolean onceC=false;
            boolean onceD=false;boolean onceE=false;boolean onceF=false;
            boolean onceG=false;boolean onceH=false;
         while(packages.size()!=0){   
            DeliveryZone currentZone=packages.get(0).getDeliveryZone();
         if(!specialA.isFull() && !onceA){   
            while(!specialA.isFull() && packages.size()!=0 && !stop){
                    if(currentZone.compareZones(packages.get(0).getDeliveryZone())!=0)
                        stop=true;
                    else specialA.loadPackage(packages.remove(0));}
            int count=0;
            while(!specialA.isFull() && packages.size()!=0 && count<packages.size()){
                    boolean adjacentsLeft=DeliveryZone.isAdjacent(currentZone,packages.get(count).getDeliveryZone());     
                    if(adjacentsLeft){   
                        specialA.loadPackage(packages.remove(count));
                        count--;}count++;
                    }
                    onceA=true;
                }
             
         if(!specialB.isFull() && onceA){stop=false;currentZone=packages.get(0).getDeliveryZone();  
                while(!specialB.isFull() && packages.size()!=0 && !stop){
                    if(currentZone.compareZones(packages.get(0).getDeliveryZone())!=0)
                        stop=true;
                    else specialB.loadPackage(packages.remove(0));}
                int count=0;
                while(!specialB.isFull() && packages.size()!=0 && count<packages.size()){
                    boolean adjacentsLeft=DeliveryZone.isAdjacent(currentZone,packages.get(count).getDeliveryZone());     
                    if(adjacentsLeft){   
                        specialB.loadPackage(packages.remove(count));
                        count--;}count++;
                    }
                    onceB=true;
                }
             
         if(!specialC.isFull() && onceB && packages.size()!=0){stop=false;currentZone=packages.get(0).getDeliveryZone();  
                while(!specialC.isFull() && packages.size()!=0 && !stop){
                    if(currentZone.compareZones(packages.get(0).getDeliveryZone())!=0)
                        stop=true;
                    else specialC.loadPackage(packages.remove(0));}
                int count=0;
                while(!specialC.isFull() && packages.size()!=0 && count<packages.size()){
                    boolean adjacentsLeft=DeliveryZone.isAdjacent(currentZone,packages.get(count).getDeliveryZone());     
                    if(adjacentsLeft){   
                        specialC.loadPackage(packages.remove(count));
                        count--;}count++;
                    }
                    onceC=true;
                }
            
         if(!specialD.isFull() && onceC  && packages.size()!=0){stop=false;currentZone=packages.get(0).getDeliveryZone();   
                while(!specialD.isFull() && packages.size()!=0 && !stop){
                    if(currentZone.compareZones(packages.get(0).getDeliveryZone())!=0)
                        stop=true;
                    else specialD.loadPackage(packages.remove(0));}
                int count=0;
                while(!specialD.isFull() && packages.size()!=0 && count<packages.size()){
                    boolean adjacentsLeft=DeliveryZone.isAdjacent(currentZone,packages.get(count).getDeliveryZone());     
                    if(adjacentsLeft){   
                        specialD.loadPackage(packages.remove(count));
                        count--;}count++;
                    }
                    onceD=true;
                }
             
         if(!specialE.isFull() && onceD && packages.size()!=0){stop=false;currentZone=packages.get(0).getDeliveryZone();  
                while(!specialE.isFull() && packages.size()!=0 && !stop){
                    if(currentZone.compareZones(packages.get(0).getDeliveryZone())!=0)
                        stop=true;
                    else specialE.loadPackage(packages.remove(0));}
                int count=0;
                while(!specialE.isFull() && packages.size()!=0 && count<packages.size()){
                    boolean adjacentsLeft=DeliveryZone.isAdjacent(currentZone,packages.get(count).getDeliveryZone());     
                    if(adjacentsLeft){   
                        specialE.loadPackage(packages.remove(count));
                        count--;}count++;
                    }
                    onceE=true;
                }
            
         if(!specialF.isFull() && onceE && packages.size()!=0){stop=false;currentZone=packages.get(0).getDeliveryZone();   
                while(!specialF.isFull() && packages.size()!=0 && !stop){
                    if(currentZone.compareZones(packages.get(0).getDeliveryZone())!=0)
                        stop=true;
                    else specialF.loadPackage(packages.remove(0));}
                int count=0;
                while(!specialF.isFull() && packages.size()!=0 && count<packages.size()){
                    boolean adjacentsLeft=DeliveryZone.isAdjacent(currentZone,packages.get(count).getDeliveryZone());     
                    if(adjacentsLeft){   
                        specialF.loadPackage(packages.remove(count));
                        count--;}count++;
                    }
                    onceF=true;
                }        
              
         if(!specialG.isFull() && onceF && packages.size()!=0){stop=false;   
                while(!specialG.isFull() && packages.size()!=0 && !stop){
                    if(currentZone.compareZones(packages.get(0).getDeliveryZone())!=0)
                        stop=true;
                    else specialG.loadPackage(packages.remove(0));}
                int count=0;
                while(!specialG.isFull() && packages.size()!=0 && count<packages.size()){
                    boolean adjacentsLeft=DeliveryZone.isAdjacent(currentZone,packages.get(count).getDeliveryZone());     
                    if(adjacentsLeft){   
                        specialG.loadPackage(packages.remove(count));
                        count--;}count++;
                    }
                    onceG=true;
                }     
              
         if(!specialH.isFull() && onceG && packages.size()!=0){stop=false;   
                while(!specialH.isFull() && packages.size()!=0 && !stop){
                    if(currentZone.compareZones(packages.get(0).getDeliveryZone())!=0)
                        stop=true;
                    else specialH.loadPackage(packages.remove(0));}
                int count=0;
                while(!specialH.isFull() && packages.size()!=0 && count<packages.size()){
                    boolean adjacentsLeft=DeliveryZone.isAdjacent(currentZone,packages.get(count).getDeliveryZone());     
                    if(adjacentsLeft){   
                        specialH.loadPackage(packages.remove(count));
                        count--;}count++;
                    }
                    onceH=true;
                }     
            }
      }
    }