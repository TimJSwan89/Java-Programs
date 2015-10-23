//Marcos Arroyo
//Class that verifies valid input

public class DataConfirmation{


    public static boolean isPositive(int number)
    {   boolean is=false;
        if(number>=0)
            is=true;
        return is;
    }
    public static boolean isPositive(double number)
    {   boolean is=false;
        if(number>=0)
            is=true;
        return is;
    }
}
    