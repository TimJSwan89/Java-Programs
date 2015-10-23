public class Location {
    int x, y;
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public boolean equals(Location loc) {
        return (loc.getX() == x && loc.getY() == y);
    }
}