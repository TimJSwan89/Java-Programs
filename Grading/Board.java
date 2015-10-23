 

/** --------------------------------------------- 
 * Board.java
 *      Play the game of Ricochet Robots, similar
 *      to that seen online at:
 *          http://www.braunston.com/kevin/rrobot/rrobot.html
 *          
 * Class: CS 102, Fall 2007
 * Lab: Englebert Humberdink, Mon. 5:00 AM
 * System: BlueJ 2.0.5, jsdk 1.5, Windows XP
 *
 * @author  Dale Reed
 * @version October 13, 2007
 * ----------------------------------------------
 */

// Import libraries needed by the program
import java.util.Scanner;         // used for console input
import java.io.*;                 // used for reading from a file


public class Board
{
    // constants used in setting size of pieces
    static final int pieceSize = 20;        // size of each piece on a side
    static final int pieceSpace = 4;        // space between pieces
    static final int topOffset = 10;        // offset at top of board
    static final int leftOffset = 10;       // offset at left of board
    static final String defaultColor = "gray";  // default board piece color
    static final int BoardSize = 9;        // size (on a side) of a board
    
    // Instance variables 
    BoardSquare[] theBoard;                         // array of BoardSquares   

    //----------------------------------------------------------------------------------------
    // Constructors
    public Board()
    {
        // create the board array
        theBoard = new BoardSquare[ BoardSize * BoardSize]; 
        
        // build the board, initializing each piece to default values
        // declare variables whose values accumulate as the board is built
        int currentX = leftOffset;
        int currentY = topOffset;
        // variables used to determine if boundary lines are shown
        boolean leftBoundary, aboveBoundary, rightBoundary, belowBoundary;
        for( int i=0; i<BoardSize*BoardSize; i++) {
            // make a new BoardSquare
            // Determine whether or not a boundary line should be shown in each direction
            leftBoundary = isItABoundary( "left", i);
            aboveBoundary = isItABoundary( "above", i);
            rightBoundary = isItABoundary( "right", i);
            belowBoundary = isItABoundary( "below", i);

            // now make the square, including this boundary display information
            theBoard[ i] = new BoardSquare(  currentX, currentY, pieceSize, defaultColor, "", i,
                                            leftBoundary, aboveBoundary, rightBoundary, belowBoundary);
            // now update position values in preparation for next piece
            if( (i+1)%BoardSize == 0) {
                // starting a new row
                currentX = leftOffset;
                currentY += pieceSize + pieceSpace;
            }
            else {
                // the usual case, not starting a new row
                currentX += pieceSize + pieceSpace;
            }
        }//end for( int i=0...
        
    }//end method doIt()
    
    
    // See if a particular boundary should be shown.  Possible direction values are
    // "left",  "above",  "right",  "below".  Boundary values should be set
    // appropriately for those pieces whose index value places them on the edge.
    boolean isItABoundary( String direction, int index)
    {
        boolean theReturnValue = false;
        
        if( direction.equals("left")) {
            if( (index % BoardSize) == 0) {
                theReturnValue = true;   
            }
            if( (index % BoardSize) == 3) {
                theReturnValue = true;   
            }
            if( (index % BoardSize) == 6) {
                theReturnValue = true;   
            }
        }
        else if( direction.equals("above")) {
            if( index < BoardSize) {
                theReturnValue = true; 
            }
        }
        else if( direction.equals("right")) {
            if( ((index+1) % BoardSize) == 0) {
                theReturnValue = true;   
            }
        }
        else if( direction.equals("below")) {
            if( index >= (BoardSize * BoardSize) - BoardSize) {
                theReturnValue = true;
            }
               if (index>17 && index<27){
               theReturnValue=true;
               }
               if (index>44 && index<54){
                   theReturnValue=true;
                   }
        }
        else {
            // sanity check, should never execute this code
            System.out.println("*** Error: boundary value incorrect.  Exiting...");
            System.exit( -1);
        }
        
        return theReturnValue;
    }//end method isItABoundary()
    
    
    // getPiece()
    //      return a particular piece from the Board
    public BoardSquare getPiece( int index)
    {
        return theBoard[ index];   
    }
    
    // getDefaultColor()
    public String getDefaultColor()
    {
        return defaultColor;   
    }
    
    //getBoardSize()
    public int getBoardSize()
    {
        return BoardSize;   
    }
    
    
    
}//end class Board
