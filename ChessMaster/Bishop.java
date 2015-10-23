import java.util.ArrayList;
public class Bishop extends Piece {
    public Bishop(ChessBoard board, boolean white, Location location) {
        this.board = board;
        this.white = white;
        this.location = location;
    }
    public ArrayList<Location> getPossibleAttack() {
        ArrayList<Location> test = new ArrayList<Location>();
        int i = 1;
        boolean works;
        do {
            Location loc = new Location(location.getX() + i, location.getY() + i);
            works = board.isValid(loc);
            if (works) {
                test.add(loc);
                i++;
                if (board.getPiece(loc) != null)
                    works = false;
            }
        } while (works);
        i = 1;
        do {
            Location loc = new Location(location.getX() + i, location.getY() - i);
            works = board.isValid(loc);
            if (works) {
                test.add(loc);
                i++;
                if (board.getPiece(loc) != null)
                    works = false;
            }
        } while (works);
        i = 1;
        do {
            Location loc = new Location(location.getX() - i, location.getY() + i);
            works = board.isValid(loc);
            if (works) {
                test.add(loc);
                i++;
                if (board.getPiece(loc) != null)
                    works = false;
            }
        } while (works);
        i = 1;
        do {
            Location loc = new Location(location.getX() - i, location.getY() - i);
            works = board.isValid(loc);
            if (works) {
                test.add(loc);
                i++;
                if (board.getPiece(loc) != null)
                    works = false;
            }
        } while (works);
        i = 1;
        return test;
    }
    public String toString() {
        return "Bishop";
    }
}