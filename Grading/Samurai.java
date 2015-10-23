 

//Import libraries needed by the program
import java.io.IOException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;



/** --------------------------------------------- 
 * Samurai.java
 *      Play the game of Samurai Sudoku, similar
 *      to that seen online at:
 *          http://www.killer-samurai.com/
 *          
 * Class: CS 201, Spring 2008
 * System: Eclipse, Windows
 *
 * @author Noah Martin
 * @version February, 2008
 * 
 * 
 * ----------------------------------------------
 */
public class Samurai implements MouseListener
{
    // Instance variables
	Board theBoard;                                 // array of BoardSquares
	Square theSquare;
	int clickedXPos, clickedYPos, clicked;
	private int rows;
	private int cols;

	//----------------------------------------------------------------------------------------
    // main() - startup main loop.    
    //           "throws IOException" is needed to implement reading from a file
    public static void main(String[] args) throws IOException
    {
        Samurai SamuraiGameInstance = new Samurai();
        SamuraiGameInstance.doIt();
        Canvas.getCanvas().setMouseListener(SamuraiGameInstance); 
    }
    
    
    //----------------------------------------------------------------------------------------
    // doIt() - display identifying information and run main part of program
    //      
    //      "throws IOException" is needed to implement reading from a file
    void doIt() throws IOException 
    {
    	// Display identifying information
        System.out.println( "Author: Noah Martin \n" +
                            "Program 1: Samurai Sudoku \n" +
                            "Cs 201, Spring 2008 \n" +
                            "February 11, 2007 \n");
        
        // Display Instructions
        System.out.println( "Welcome to Samurai Sudoku \n" + 
        		"The goal of the game is to fill in the 5 9x9 grids \n" +
        		"so that every row, every column, and every 3x3 box \n" +
        		"contains the digits 1 through 9 only one time each. \n" +
        		"Click on a square the number of times you want the digit \n"+
        		"to be.  If a digit is used more than once you will see it \n" +
        		"as the squares with errors will change colors.");

    	// create the board, initializing all pieces to their default settings
        theBoard = new Board();   
                
     // set up various pieces and "walls" at different spots on the board
        setBoardConfiguration();

    }//end method doIt()
   
    
	private void setBoardConfiguration() throws IOException{
        // declare variables used in reading from file
        //Scanner inFile = new Scanner(new File("sample1.input"));
        int location;
        
      
     // set the color and label for this piece (convert displayCharacter to a String)
       // theBoard.getPiece( location).setColorAndLabel( "cyan", "" ); 
	}

// checks to see if each row uses each number once
	//check_rows(){
	//for (int i=0; i<clicked; i++){
		
    //}}

	
//	checks to see if each column uses each number once
//	check_cols(){
//	}
	
	
//checks to see if each 3 x 3 grid uses each number once	
//	check_3x3(){
//	}


	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		int xPos = arg0.getX(); //gets x position when clicked
		int yPos = arg0.getY(); //gets y position when clicked
		
		// check to see if x,y within ranges corresponding to squares on board
		this.clickedXPos = (xPos - 10)/24;
	    this.clickedYPos = (yPos - 34)/24;
	
	    
	    int index = clickedXPos + clickedYPos*9;
	    
	  //Sets the label of the piece to correspond (just a test to see if working)
	  // theBoard.getPiece(index).setColorAndLabel("blue", "");
	    
	    //gets the number of times clicked on the corresponding square
	    int clicked=arg0.getClickCount();
	    
	    //if clicked more than 9 times than gives a 9 since were only using numbers 1-9
	    if (clicked >9){
	    	clicked =9;
	    }
	    	
	   	// loop used to set the squares color and label corresponding to # of times clicked  
	    for (int i=0; i<clicked; i++){
	    	theBoard.getPiece(index).setColorAndLabel("blue", "" + clicked);
	    }
	    
	    
}


	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	}

