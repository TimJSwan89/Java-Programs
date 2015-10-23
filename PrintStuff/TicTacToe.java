 

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.SecureRandom;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

/**
 * Program: #2, Infinite Play History
 * CS 201, 3/17/2008
 * 
 * This class runs all aspects of the Tic Tac Toe game, 
 * serves as a head for the moves linked list, 
 * and references the grid of cells
 * @author Fred Kilbourn
 */
public class TicTacToe extends JFrame {
    //constants that affect the game
    private final int boardSize = 5;
    private final int cellDimension = 22;
    private final Cell[][] cells = new Cell[this.boardSize][this.boardSize];
    //track num cells (for random)
    private int num_cells = this.boardSize * this.boardSize;
    //init head of move linked list
    private Move current_move = new Move(-1, -1, -1, -1);

    /**
     * Creates instance of TicTacToe and makes it visible
     * @param args Runtime arguments
     */
    public static void main(String[] args) {
	java.awt.EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException ex) {
		} catch (InstantiationException ex) {
		} catch (IllegalAccessException ex) {
		} catch (UnsupportedLookAndFeelException ex) {
		}

		new TicTacToe().setVisible(true);
	    }
	});
    }

    /**
     * Constructs Tic Tac Toe Game
     */
    public TicTacToe() {
	//output header
	System.out.println(
		"Author: Fred Kilbourn\n" +
		"Program: #2, Infinite Play History\n" +
		"CS 201, Spring 2008");

	//setup frame
	this.setTitle("T-T-T");
	this.setLayout(new BorderLayout(0, 0));
	this.setResizable(false);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	//setup cell panel
	JPanel cellpanel = new JPanel();
	cellpanel.setLayout(new GridLayout(this.boardSize, this.boardSize, 1, 1));
	cellpanel.setBackground(Color.BLACK);
	cellpanel.setBorder(new LineBorder(Color.BLACK));
	int d = this.cellDimension * this.boardSize + this.boardSize + 1;
	cellpanel.setPreferredSize(new Dimension(d, d));
	this.add(cellpanel, BorderLayout.NORTH);

	//setup cells
	for (int x = 0; x < this.boardSize; x++) {
	    for (int y = 0; y < this.boardSize; y++) {
		cells[x][y] = new Cell(x, y, this);
		cellpanel.add(cells[x][y]);
	    }
	}

	//setup button panel
	JPanel buttonpanel = new JPanel();
	buttonpanel.setLayout(new BorderLayout(0, 0));
	this.add(buttonpanel, BorderLayout.SOUTH);

	//setup back button
	final JButton back = new JButton();
	back.setText("b");
	back.setBackground(Color.YELLOW);
	back.setPreferredSize(new Dimension(d / 2, 20));
	buttonpanel.add(back, BorderLayout.WEST);

	//setup forward button
	final JButton forward = new JButton();
	forward.setText("f");
	forward.setBackground(Color.YELLOW);
	forward.setPreferredSize(new Dimension(d / 2, 20));
	buttonpanel.add(forward, BorderLayout.EAST);

	//setup key adapter for buttons
	KeyAdapter bka = new KeyAdapter() {

	    @Override
	    public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 'b') {
		    prevMove();
		} else if (e.getKeyChar() == 'f') {
		    nextMove();
		}
	    }
	};
	back.addKeyListener(bka);
	forward.addKeyListener(bka);

	//setup mouse adapter for buttons
	MouseAdapter bma = new MouseAdapter() {

	    @Override
	    public void mouseClicked(MouseEvent e) {
		if (e.getComponent().equals(back)) {
		    prevMove();
		} else if (e.getComponent().equals(forward)) {
		    nextMove();
		}
	    }
	};
	back.addMouseListener(bma);
	forward.addMouseListener(bma);

	//pack the frame
	this.pack();
    }

    /**
     * Processes a new move from the GUI
     * @param l Left Index
     * @param r Right Index
     */
    public void newMove(int l, int r) {
	//checks if valid move
	if (isMoveValid(l, r)) {
	    //generates computers move
	    int[] comp_move = this.generateMove(l, r);
	    
	    //create new move object
	    Move m = new Move(l, r, comp_move[0], comp_move[1]);
	    
	    //update this move on the board
	    this.doMove(m);

	    //check if game over
	    int f = this.checkForFinish();
	    if( f == -1 )
	    {
		//if game on, update list with this move
		this.current_move.next = m;
		m.prev = this.current_move;
	        this.current_move = m;
	    }
	    else
	    {
		//if game over, update result
		switch( f )
		{
		    case 0:
			System.out.println( "You Win!!" );
			break;
		    case 1:
			System.out.println( "I WIN!!" );
			break;
		    case 2:
			System.out.println( "DRAW" );
			break;
		}
		//and restart the game
		this.restartGame();
	    }
	}
    }

    private void doMove(Move m) {
	//get position info from move object
	int[] p = m.getMovePositions();

	//do human move
	this.cells[p[0]][p[1]].setText("X");
	this.num_cells--;

	//do computer move (if valid)
	if (p[2] != -1 & p[3] != -1) {
	    this.cells[p[2]][p[3]].setText("O");
	    this.num_cells--;
	}
    }

    private void undoMove(Move m) {
	//get position info from move object
	int[] p = m.getMovePositions();

	//undo human move
	this.cells[p[0]][p[1]].setText("");
	this.num_cells++;

	//undo computer move (if valid)
	if (p[2] != -1 & p[3] != -1) {
	    this.cells[p[2]][p[3]].setText("");
	    this.num_cells++;
	}
    }

    private void prevMove() {
	//if not at head of move list,
	//backstep on move list and update board
	if (!this.current_move.isHead()) {
	    this.undoMove(this.current_move);
	    this.current_move = this.current_move.prev;
	}
    }

    private void nextMove() {
	//if there is a next move,
	//advance on move list and update board
	if (this.current_move.next != null) {
	    this.doMove(this.current_move.next);
	    this.current_move = this.current_move.next;
	}
    }

    private boolean isMoveValid(int l, int r) {
	//check if cell at requested space is blank
	if (this.cells[l][r].getText().equals("")) {
	    return true;
	}
	return false;
    }

    private int[] generateMove(int l, int r) {
	//init return to invalid move
	int[] ret = new int[]{-1, -1};

	//if there are cells left on the board, generate number between 0 and cells left on the board
	//subtract 1 for the pending human move
	if (this.num_cells - 1 > 0) {
	    //create java securerandom object
	    SecureRandom sr = new SecureRandom();
	    //get random integer i, 0 <= i < num_cells-1
	    int n = sr.nextInt(this.num_cells - 1);
	    //traverse the board
	    for (int x = 0; x < this.boardSize; x++) {
		for (int y = 0; y < this.boardSize; y++) {
		    //see if this space is empty and is not where the pending human move is
		    if (isMoveValid(x, y) && !(x == l & y == r)) {
			//if this is the nth valid space, return it as the computer's move
			//regardless, decrement n after we evaluate
			if (n-- == 0) {
			    ret = new int[]{x, y};
			    return ret;
			}
		    }
		}
	    }
	}
	//return move
	return ret;
    }

/**
 * Check the board to see if the game is over or not and what the result is
 * @return finish status:  -1 not finished, 0 = human win, 1 = computer win, 2 = draw
 */
    private int checkForFinish() {
	String t;
	boolean win = true;

	//check rows
	for (int x = 0; x < this.boardSize; x++) {
	    t = this.cells[x][0].getText();
	    if (!t.equals("")) {
		win = true;
		for (int y = 1; y < this.boardSize; y++) {
		    if (!t.equals(this.cells[x][y].getText())) {
			win = false;
			break;
		    }
		}
		if (win) {
		    if (t.equals("X")) {
			return 0;
		    }
		    if (t.equals("O")) {
			return 1;
		    }
		}
	    }
	}

	//check cols
	for (int x = 0; x < this.boardSize; x++) {
	    t = this.cells[0][x].getText();
	    if (!t.equals("")) {
		win = true;
		for (int y = 1; y < this.boardSize; y++) {
		    if (!t.equals(this.cells[y][x].getText())) {
			win = false;
			break;
		    }
		}
		if (win) {
		    if (t.equals("X")) {
			return 0;
		    }
		    if (t.equals("O")) {
			return 1;
		    }
		}
	    }
	}

	//check downhill diag
	t = this.cells[0][0].getText();
	if (!t.equals("")) {
	    win = true;
	    for (int x = 1; x < this.boardSize; x++) {
		if (!t.equals(this.cells[x][x].getText())) {
		    win = false;
		    break;
		}
	    }
	    if (win) {
		if (t.equals("X")) {
		    return 0;
		}
		if (t.equals("O")) {
		    return 1;
		}
	    }
	}

	//check uphill diag
	t = this.cells[this.boardSize - 1][0].getText();
	if (!t.equals("")) {
	    win = true;
	    for (int x = 1; x < this.boardSize; x++) {
		if (!t.equals(this.cells[this.boardSize - 1 - x][x].getText())) {
		    win = false;
		    break;
		}
	    }
	    if (win) {
		if (t.equals("X")) {
		    return 0;
		}
		if (t.equals("O")) {
		    return 1;
		}
	    }
	}

	//if no blank cells left, its a draw
	if (this.num_cells == 0) {
	    return 2;
	}
	
	return -1;
    }
    
    /**
     * reset the board for a new game
     */
    private void restartGame()
    {
	System.out.println( "Restarting..."  );

	//set all cells blank
	for (int x = 0; x < this.boardSize; x++) {
	    for (int y = 0; y < this.boardSize; y++) {
		cells[x][y].setText("");
	    }
	}
	//reset number of empty cells
	this.num_cells = this.boardSize * this.boardSize;
	//reset list to only have a header
	this.current_move = new Move(-1, -1, -1, -1);
    }
}
