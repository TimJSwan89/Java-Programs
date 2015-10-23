 

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

/**
 * Program: #2, Infinite Play History
 * CS 201, 3/17/2008
 * 
 * Class that extends a JLabel to add logic to a cell
 * @author Fred Kilbourn
 */
public class Cell extends JLabel {
    private final int left;
    private final int right;
    private final TicTacToe ttt;
    
    /**
     * Constructs our cell, stores position info and reference to the board
     * @param left
     * @param right
     * @param ttt
     */
    public Cell( int left, int right, TicTacToe ttt )
    {
	//store info
	this.left = left;
	this.right = right;
	this.ttt = ttt;
	
	//decorate and init cell
	this.setBackground( Color.WHITE );
	this.setOpaque( true );
	this.setText("");
	this.setHorizontalAlignment(JLabel.CENTER);
	
	//add mouse listener for click
	this.addMouseListener( new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent evt) {
                cellClicked();
            }
	} );
    }
    
    /**
     * Handle mouse clicks here
     */
    private void cellClicked()
    {
	//call new move on board for this cell
	this.ttt.newMove( this.left, this.right );
    }
}
