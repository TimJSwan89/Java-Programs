import java.util.ArrayList;
public class King extends Piece {
    public King(ChessBoard board, boolean white, Location location) {
        this.board = board;
        this.white = white;
        this.location = location;
    }
    public ArrayList<Location> getPossibleMoves() {
        ArrayList<Location> test = getPossibleAttack();
        ArrayList<Piece> check = board.getPlayer(!white);
        for(int i = 0; i < test.size(); i++)
            for(int j = 0; j < check.size(); j++) {
                ArrayList<Location> checkKing;
                checkKing = check.get(j).getPossibleAttack();
                for(int k = 0; k < checkKing.size(); k++)
                    if (checkKing.get(k).equals(test.get(i))) {
                        test.remove(i);
                        i--;
                    }
            }
        return test;
    }
    public ArrayList<Location> getPossibleAttack() {
        ArrayList<Location> test = new ArrayList<Location>();
        test.add(new Location(location.getX() + 1, location.getY()));
        test.add(new Location(location.getX() - 1, location.getY()));
        test.add(new Location(location.getX(), location.getY() + 1));
        test.add(new Location(location.getX(), location.getY() - 1));
        for(int i = 0; i < test.size(); i++)
            if (!(board.isValid(test.get(i)))) {
                test.remove(i);
                i--;
            }
        return test;
    }
    public String toString() {
        return "King" + location.getY() + " " + location.getX();
    }
}