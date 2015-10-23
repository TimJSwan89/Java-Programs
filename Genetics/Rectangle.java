public class Rectangle {
	private int width, height;
	private ArrayList<Cell> cells = new ArrayList<Cell>();
	private ArrayList<Food> food = new ArrayList<Food>();
	public Rectangle(int x, int y)
	{
	    this.width = x;
	    this.height = y;
	}
	public int width() {
	    return width;
	}
	public int height() {
	    return height;
	}
}