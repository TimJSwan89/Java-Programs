import java.util.ArrayList;
public abstract class Piece {
    boolean white;
    ChessBoard board;
    Location location;
    public ArrayList<Location> getPossibleMoves() {
        ArrayList<Location> attacks = getPossibleAttack();
        for(int i = 0; i < attacks.size(); i++) {
            Piece test = board.getPiece(attacks.get(i));
            if (test != null && test.isWhite() == white) {
                attacks.remove(i);
                i--;
            }
        }
        return attacks;
    }
//     public ArrayList<Location> getPossibleMoves() {
//         ArrayList<Location> attacks = getPossibleAttack();
//         for(Location loc : attacks) {
//             Piece test = board.getPiece(loc);
//             if (test != null && test.isWhite() == white) {
//                 attacks.remove(loc);
//                 //i--;
//             }
//         }
//         return attacks;
//     }
    public ArrayList<Location> getPossibleAmbush(ArrayList<Location> moves) {
        ArrayList<Location> ambushes = new ArrayList<Location>();
        ArrayList<Piece> enemies = board.getPlayer(!white);
        for(int i = 0; i < enemies.size(); i++) {
            ArrayList<Location> attacks = enemies.get(i).getPossibleAttack();
            for(int j = 0; j < attacks.size(); j++)
                for(int k = 0; k < moves.size(); k++)
                    if (attacks.get(j).equals(moves.get(k))) {
                        ambushes.add(moves.get(k));
                        moves.remove(k);
                    }
        }
        return ambushes;
    }
    public String toString() {
        return ((white) ? "White" : "Black");
    }
    public boolean isWhite() {
        return white;
    }
    public Location getLocation() {
        return location;
    }
    public void changeLocation(Location newLoc) {
        location = newLoc;
    }
    public abstract ArrayList<Location> getPossibleAttack();
//     {
//         return getPossibleMoves();
//     }
    
//     public boolean testSpot(Location loc) {
//         if (!board.isValid(loc))
//             return false;
//         else
//             if (board.getPiece(loc) == null)
//                 return true;
//             else
//                 if (board.getPiece(loc).isWhite() != white)
//                     return false;
//                 else
//                     return true;
//     }
// The Previous method which is now commented should
// equal to the following one:
    public boolean testMove(Location loc) {
        return (board.isValid(loc) && 
        (board.getPiece(loc) == null || 
        board.getPiece(loc).isWhite() != white));
    }
}