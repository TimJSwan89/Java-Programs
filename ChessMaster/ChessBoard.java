import java.util.ArrayList;
public class ChessBoard {
    Piece[][] board;
    ArrayList<Piece> white, black;
    public ChessBoard () {
        board = new Piece[8][8];
        white = new ArrayList<Piece>();
        black = new ArrayList<Piece>();
        for(int i = 0; i < 8; i += 7) {
            board[0][i] = new Rook(this, (i == 0) ? true : false, new Location(0, i));
            board[1][i] = new Knight(this, (i == 0) ? true : false, new Location(1, i));
            board[2][i] = new Bishop(this, (i == 0) ? true : false, new Location(2, i));
            board[3][i] = new Queen(this, (i == 0) ? true : false, new Location(3, i));
            board[4][i] = new King(this, (i == 0) ? true : false, new Location(4, i));
            board[5][i] = new Bishop(this, (i == 0) ? true : false, new Location(5, i));
            board[6][i] = new Knight(this, (i == 0) ? true : false, new Location(6, i));
            board[7][i] = new Rook(this, (i == 0) ? true : false, new Location(7, i));
        }
        for(int i = 1; i < 7; i += 5)
            for(int j = 0; j < 8; j++)
                board[j][i] = new Pawn(this, (i == 1) ? true : false, new Location(j, i));
        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 8; j++)
                    white.add(board[j][i]);
        for(int i = 6; i < 8; i++)
            for(int j = 0; j < 8; j++)
                    black.add(board[j][i]);
    }
    public ArrayList<Piece> getPlayer(boolean white) {
        if (white)
            return this.white;
        else
            return black;
    }
    public boolean isValid(Location loc) {
        return (loc.getX() >= 0 && loc.getX() < 8 && 
        loc.getY() >= 0 && loc.getY() < 8);
    }
    public void move(Location oldLoc, Location newLoc) {
        int j;
        if (newLoc.equals(oldLoc))
            j = 5 / 0;
            //throw (new Exception(""));
        board[newLoc.getX()][newLoc.getY()] = board[oldLoc.getX()][oldLoc.getY()];
        board[oldLoc.getX()][oldLoc.getY()] = null;
        getPiece(newLoc).changeLocation(newLoc);
    }
    public Piece getPiece(Location loc) {
        return getPiece(loc.getX(), loc.getY());
    }
    public Piece getPiece(int x, int y) {
        return board[x][y];
    }
}