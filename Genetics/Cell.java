public class Cell {
	private int x, y, filled;
	public Cell(int x, int y)
	{
	    this.x = x;
	    this.y = y;
	    filled = 90;
	}
	public int x() {
	    return x;
	}
	public int y() {
	    return y;
	}
	public int filled() {
	    return filled;
	}
}