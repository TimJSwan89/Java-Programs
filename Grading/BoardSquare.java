 



/**
 * Write a description of class BoardSquare here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BoardSquare
{
    Square theSquare;       // the square itself
    // sometimes there are lines on some of the sides
    MyRectangle lineLeft=null, lineAbove=null, lineRight=null, lineBelow=null; 
    int index;              // array index, used to correspond Robot positions with underlying
                            // BoardSquare positions.
    
    // constants
    static final int lineOffset = 3;    // line offset 
    
    // Constructors
    BoardSquare (int x, int y, int size, String color, String label, int index,
                 boolean thereIsLineLeft, boolean thereIsLineAbove, 
                 boolean thereIsLineRight, boolean thereIsLineBelow)
    {
         // create the basic square
         theSquare = new Square( x, y, size, color, true, label);
         // store the index
         this.index = index;
         // set any surrounding lines
         setLines( thereIsLineLeft, thereIsLineAbove, thereIsLineRight, thereIsLineBelow);
    }
    
    
    // allow setting the lines surrounding a square
    void setLines( boolean thereIsLineLeft, boolean thereIsLineAbove, 
                   boolean thereIsLineRight, boolean thereIsLineBelow)
    {
        // retrieve the x, y and size values from the Square
        int x = theSquare.getX();
        int y = theSquare.getY();
        int size = theSquare.getSize();
        
        // reset to actual "lines" as indicated by parameters
        if( thereIsLineLeft) {
            this.lineLeft = new MyRectangle(x-lineOffset, y-lineOffset, 2, size+(lineOffset*2), "black", true);
        }
        if( thereIsLineAbove) {
            this.lineAbove = new MyRectangle(x-lineOffset, y-lineOffset, size+(lineOffset*2), 2, "black", true);
        }
        if( thereIsLineRight) {
            this.lineRight = new MyRectangle(x+size+1, y-lineOffset, 2, size+(lineOffset*2),  "black", true);
        }
        if( thereIsLineBelow) {
            this.lineBelow = new MyRectangle(x-lineOffset, y+size+1, size+(lineOffset*2), 2,  "black", true);
        }   
    }
    
    
    // set color and letter label
    void setColorAndLabel( String color, String label)
    {
        theSquare.setColor( color);
        theSquare.setLabel( label);
    }
    
    // return the square, to allow extracting location information
    Square getSquare()
    {
        return theSquare;   
    }
    
    
    // return the BoardSquare index
    int getIndex()
    {
        return index;   
    }

    
    // return the BoardSquare index
    void setIndex(int newIndex)
    {
        index = newIndex;   
    }
    
    // Find out if there is a wall in the indicated direction.  There
    // is a neighbor if the "line" field is not null.  
    boolean hasWall( char direction)
    {
        boolean returnValue = false;    // the default return value assumes there is no wall
        
        switch (direction) {
            case 'L': if( this.lineLeft != null) returnValue = true; break;  
            case 'U': if( this.lineAbove != null) returnValue = true; break;    
            case 'R': if( this.lineRight != null) returnValue = true; break;    
            case 'D': if( this.lineBelow != null) returnValue = true; break;  
            default: System.out.println("*** Error, invalid direction in method hasWall(). Exiting...\n");
                     System.exit( -1);      // exit the program
        }  
        
        return returnValue;
    }
       
}//end class BoardSquare
