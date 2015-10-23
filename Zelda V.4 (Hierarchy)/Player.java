import java.util.ArrayList;
public class Player extends MobileObject
{
    int direction;
    public Player(int inX, int inY, int inHeight, int inWidth, int inDirection)
    {
        super(inX, inY, inHeight, inWidth);
        direction = inDirection;
    }
    public int getDirection()
    {
        return direction;
    }
    public int pushRight(ArrayList inRects, int speed, int rightWall, int bottomWall, boolean wrap)
    {
        direction = 3;
        return super.pushRight(inRects, speed, rightWall, bottomWall, wrap);
    }
    public int pushUp(ArrayList inRects, int speed, int rightWall, int bottomWall, boolean wrap)
    {
        direction = 0;
        return super.pushUp(inRects, speed, rightWall, bottomWall, wrap);
    }
    public int pushLeft(ArrayList inRects, int speed, int rightWall, int bottomWall, boolean wrap)
    {
        direction = 2;
        return super.pushLeft(inRects, speed, rightWall, bottomWall, wrap);
    }
    public int pushDown(ArrayList inRects, int speed, int rightWall, int bottomWall, boolean wrap)
    {
        direction = 1;
        return super.pushDown(inRects, speed, rightWall, bottomWall, wrap);
    }
    public int pushDirection(ArrayList inRects, int speed, int rightWall, int bottomWall, boolean wrap) {
        if (direction == 0)
            return pushUp(inRects, speed, rightWall, bottomWall, wrap);
        else if (direction == 1)
                return pushDown(inRects, speed, rightWall, bottomWall, wrap);
            else if (direction == 2)
                    return pushLeft(inRects, speed, rightWall, bottomWall, wrap);
                else
                    return pushRight(inRects, speed, rightWall, bottomWall, wrap);
    }
}