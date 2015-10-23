import java.util.ArrayList;
public class Pawn extends Piece {
    private boolean firstMove = true;
    public Pawn(ChessBoard board, boolean white, Location location) {
        this.board = board;
        this.white = white;
        this.location = location;
    }
    public ArrayList<Location> getPossibleMoves() {
        ArrayList<Location> test = new ArrayList<Location>();
        int addY;
        if (white)
            addY = 1;
        else
            addY = -1;
        test = getPossibleAttack();
        for(int i = 0; i < test.size(); i++)
            if (board.getPiece(test.get(i)) == null || 
            board.getPiece(test.get(i)).isWhite() == white) {
                test.remove(i);
                i--;
            }
        Location loc = new Location(location.getX(), location.getY() + addY);
        if (board.getPiece(loc) == null) {
            test.add(loc);
            if (firstMove) {
                loc = new Location(location.getX(), location.getY() + addY * 2);
                if (board.getPiece(loc) == null)
                    test.add(loc);
            }
        }
        return test;
    }
    public ArrayList<Location> getPossibleAttack() {
        ArrayList<Location> test = new ArrayList<Location>();
        int addY;
        if (white)
            addY = 1;
        else
            addY = -1;
        for(int i = -1; i < 2; i += 2) {
            Location loc = new Location(location.getX() + i, location.getY() + addY); 
            if (board.isValid(loc))
                test.add(loc);
        }
        return test;
    }
    public void changeLocation(Location newLoc) {
        super.changeLocation(newLoc);
        firstMove = false;
    }
    public String toString() {
        return "Pawn";
    }
}