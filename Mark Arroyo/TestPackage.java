//Marcos Arroyo
//Package test class

public class TestPackage{
    public static void main(String[] ponies)
    {
        
        DateMA d1=new DateMA(3,16,2007);
        DateMA d2=new DateMA(3,17,2007);
        RegularPackage p1=new RegularPackage(d1,"Orange Inc.","C2",7,30);
        RegularPackage p2=new RegularPackage(d2,"Orange Inc.","C2",1,30);
        
        SpecialPackage s1=new SpecialPackage(d1,"HAL Industries","A2",10,7,11);
        SpecialPackage s2=new SpecialPackage(d2,"HAL Industries","A1",8,30,11);
        
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(s1);
        System.out.println(s2);     
           
    }
}