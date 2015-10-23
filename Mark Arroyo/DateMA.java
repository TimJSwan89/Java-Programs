  

/* A simple Date class
   Anderson, Franceschi
*/
//marcos arroyo
//Lab 2 Task 2
public class DateMA
{
  private int month,day,year;
  private int calcMonth,calcDay,calcYear,comb;

  public DateMA( )
  {
    setDate( 1, 1, 2000 );
  }
  public DateMA( int mm, int dd, int yyyy )
  {
    setDate( mm, dd, yyyy );
  }
  /* accessor methods */
  int getMonth( ) { return month; }
  int getDay( )   { return day; }
  int getYear( )  { return year; }

  public void setMonth( int mm )
  {
      if(DataConfirmation.isPositive(mm))
        month = ( mm >= 1 && mm <= 12 ? mm : 1 );
      else month=1;  
  }
  public void setDay( int dd )
  {
      if(DataConfirmation.isPositive(dd)){
          int [] validDays = { 0,  31, 29, 31, 30,
                         31, 30, 31, 31, 30,
                         31, 30, 31 };
          day = ( dd >= 1 && dd <= validDays[month] ? dd : 1 );
        }
      else day=1;  
  }
  public void setYear( int yyyy )
  { if(DataConfirmation.isPositive(yyyy))
        year = yyyy;
    else
        year=2000;
  }
  public void setDate( int mm, int dd, int yyyy )
  {
    setYear(yyyy);
    setMonth( mm );
    setDay( dd );
  }

  public String toString( )
  {
    return month + "/" + day + "/" + year;
  }

  /** equals
  *  @param   d  Date object to compare to this
  *  @return  true if d is equal to this
  *           false, otherwise
  */
  public boolean equals( DateMA d )
  {
    if ( month == d.month
         && day == d.day
         && year == d.year )
      return true;
    else
      return false;
  }
  
  public boolean leapYear()
  { boolean arb=false;
      if(year%4==0){
        arb=true;  
        if(year%100==0){
            arb=false;
            if(year%400==0)
                arb=true;}  
            }
        return arb;
  }
  
  public int dayOfWeek()
  {  String tempYear=""+year;
     String cutTwo=tempYear.substring(2);
     calcYear=Integer.parseInt(cutTwo);
     calcYear+=Math.floor(calcYear/4);
     if(month==1)
        calcMonth=6;
     else if(month==2)
          calcMonth=2;
     else if(month==3)
          calcMonth=2;
     else if(month==4)
          calcMonth=5;
     else if(month==5)
          calcMonth=0;
     else if(month==6)
          calcMonth=3;
     else if(month==7)
          calcMonth=5;
     else if(month==8)
          calcMonth=1;
     else if(month==9)
          calcMonth=4;
     else if(month==10)
          calcMonth=6;
     else if(month==11)
          calcMonth=2;
     else
         calcMonth=4;
           comb=calcYear+calcMonth+day;    
           int tempNum=comb/7;
           comb=comb-(tempNum*7);
           return comb;}

           public int compareTo( DateMA d )
           {
               if (year < d.year)
               return -1;
               else if (year==d.year && month < d.month)
               return -1;
               else if (year==d.year && month==d.month && day<d.day)
               return -1;
               else if (year==d.year && month==d.month && day==d.day)
               return 0;
               else return 1;
            }
        }