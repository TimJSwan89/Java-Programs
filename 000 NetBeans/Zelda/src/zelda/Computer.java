package zelda;
import java.util.ArrayList;
import java.util.Random;
public class Computer extends MobileObject {
    int direction;
    
    // I chose to make the following variables global in order to conserve
    // time over memory space
    Random rand;
    ArrayList<Integer> tries;
    boolean trying;
    
    public Computer(int inX, int inY, int inWidth, int inHeight, int inDir)
    {
        super(inX, inY, inWidth, inHeight);
        direction = inDir;
        rand = new Random();
    }
    public int testUp(ArrayList<Rectangle> inRects, int distance, boolean push, int rightWall, int bottomWall, boolean wrap)
    {
        if (direction == 1)
            randomDirection(0, 2, 3);
        return super.testUp(inRects, distance, push, rightWall, bottomWall, wrap);
    }
    public int testDown(ArrayList<Rectangle> inRects, int distance, boolean push, int rightWall, int bottomWall, boolean wrap)
    {
        if (direction == 0)
            randomDirection(1, 2, 3);
        return super.testDown(inRects, distance, push, rightWall, bottomWall, wrap);
    }
    public int testLeft(ArrayList<Rectangle> inRects, int distance, boolean push, int rightWall, int bottomWall, boolean wrap)
    {
        if (direction == 3)
            randomDirection(0, 1, 2);
        return super.testLeft(inRects, distance, push, rightWall, bottomWall, wrap);
    }
    public int testRight(ArrayList<Rectangle> inRects, int distance, boolean push, int rightWall, int bottomWall, boolean wrap)
    {
        if (direction == 2)
            randomDirection(0, 1, 3);
        return super.testRight(inRects, distance, push, rightWall, bottomWall, wrap);
    }
    public void randomDirection(int... dirs)
    {
        direction = dirs[rand.nextInt(dirs.length)];
    }
    public int move(ArrayList<Rectangle> inRects, int distance, int rightWall, int bottomWall, boolean wrap)
    {
        int moved;
        trying = true;
        do {
            switch(direction) {
                case 0: {
                    moved = pushUp(inRects, distance, rightWall, bottomWall, wrap);
                    break;
                }
                case 1: {
                    moved = pushDown(inRects, distance, rightWall, bottomWall, wrap);
                    break;
                }
                case 2: {
                    moved = pushLeft(inRects, distance, rightWall, bottomWall, wrap);
                    break;
                }
                default: {
                    moved = pushRight(inRects, distance, rightWall, bottomWall, wrap);
                    break;
                }
            }
//            Alternate "if" approach rather than "switch" approach
//            if (direction == 0)
//                moved = pushUp(inRects, distance, rightWall, bottomWall, wrap);
//            else if (direction == 1)
//                    moved = pushDown(inRects, distance, rightWall, bottomWall, wrap);
//                else if (direction == 2)
//                        moved = pushLeft(inRects, distance, rightWall, bottomWall, wrap);
//                    else
//                        moved = pushRight(inRects, distance, rightWall, bottomWall, wrap);
            if (moved == 0) {
                if (trying) {
                    tries = new ArrayList<Integer>();
                    tries.add(0);
                    tries.add(1);
                    tries.add(2);
                    tries.add(3);
                    trying = false;
                }
                tries.remove(tries.indexOf(direction));
                direction = (int) tries.get(rand.nextInt(tries.size()));
            }
        } while(moved == 0 && tries.size() > 0);
        return moved;
    }
    public int getDirection() {
        return direction;
    }
}