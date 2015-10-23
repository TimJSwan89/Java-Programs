import java.util.ArrayList;
public class Knight extends Piece {
    public Knight(ChessBoard board, boolean white, Location location) {
        this.board = board;
        this.white = white;
        this.location = location;
    }
    public ArrayList<Location> getPossibleAttack() {
        ArrayList<Location> test = new ArrayList<Location>();
        test.add(new Location(location.getX() + 2, location.getY() + 1));
        test.add(new Location(location.getX() + 2, location.getY() - 1));
        test.add(new Location(location.getX() - 2, location.getY() + 1));
        test.add(new Location(location.getX() - 2, location.getY() - 1));
        test.add(new Location(location.getX() + 1, location.getY() + 2));
        test.add(new Location(location.getX() + 1, location.getY() - 2));
        test.add(new Location(location.getX() - 1, location.getY() + 2));
        test.add(new Location(location.getX() - 1, location.getY() - 2));
        for(int i = 0; i < test.size(); i++)
            if (!board.isValid(test.get(i))) {
                test.remove(i);
                i--;
            }
        return test;
    }
    public String toString() {
        return "Knight";
    }
}