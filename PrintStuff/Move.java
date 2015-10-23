 

/**
 * Program: #2, Infinite Play History
 * CS 201, 3/17/2008
 * 
 * Doubly linked list class to store info about moves
 * @author Fred Kilbourn
 */
public class Move {
    //positions of this move
    private int human_l;
    private int human_r;
    private int computer_l;
    private int computer_r;
    
    //prev and next moves
    public Move prev = null;
    public Move next = null;
    
    /**
     * Move constructor to set position info
     * @param h_l
     * @param h_r
     * @param c_l
     * @param c_r
     */
    public Move( int h_l, int h_r, int c_l, int c_r )
    {
	//store attributes
	this.human_l = h_l;
	this.human_r = h_r;
	this.computer_l = c_l;
	this.computer_r = c_r;
    }
    
    /**
     * Returns information about the positions of this move
     * @return
     */
    public int[] getMovePositions()
    {
	int[] ret = new int[]{ this.human_l, this.human_r, this.computer_l, this.computer_r };
	return ret;
    }
    
    /**
     * Returns if this is the head of the move linked list
     * The semantic used is if all positions are -1, it is the head
     * @return
     */
    public boolean isHead()
    {
	if( this.human_l == -1 & this.human_r == -1 & this.computer_l == -1 & this.computer_r == -1 )
	    return true;
	return false;
    }
}
