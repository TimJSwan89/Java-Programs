import java.util.ArrayList;
public class MobileObject extends Rectangle {
    public MobileObject(int inX, int inY, int inHeight, int inWidth) {
        super(inX, inY, inHeight, inWidth);
    }
    
//     This method returns the positive remainder of two values
//     NOTE: This is NOT equal to "Absolute Value of (coordinate % bound)"
//     consider it a homemade modulation operator. It is not used in the 
//     recursive methods below for program speed purposes. It can easily
//     be replaced by normal additon/sutraction in this class.

//     public static int PR(int coordinate, int bound) {
//         while(coordinate < 0)
//             coordinate += bound;
//         coordinate %= bound;
//         while(coordinate > bound)
//             coordinate -= bound;
//         return coordinate;
//     }
    public int pushUp(ArrayList<Rectangle> inRects, int distance, int rightWall, int bottomWall, boolean wrap)
    {
        return testUp(inRects, testUp(inRects, distance, false, rightWall, bottomWall, wrap), true, rightWall, bottomWall, wrap);
    }
    protected int testUp(ArrayList<Rectangle> inRects, int distance, boolean push, int rightWall, int bottomWall, boolean wrap)
    {
        for(int i = distance; i > 0; i--) {
            if (wrap || top() - i >= 0) {
                int test = i;
                for(Rectangle rect : inRects)
                {
                    int bottom = rect.bottom();
                    if (bottom > top())
                        bottom -= bottomWall;
                    int left = rect.left();
                    int right = rect.right();
                    if (left > right()) {
                        left -= rightWall;
                        right -= rightWall;
                    } 
                    else if (right < left()) {
                        left += rightWall;
                        right += rightWall;
                    }
                    if (left() < right && right() > left && 
                    top() >= bottom && top() - test < bottom)
                    {
                        int spaceBetween = top() - bottom;
                        int freeMove = rect.testUp(inRects, test - spaceBetween, push, rightWall, bottomWall, wrap) + spaceBetween;
                        if (freeMove < test)
                            test = freeMove;
                    }
                }
                if (push) {
                    y -= distance;
                    if (y < 0)
                        y += bottomWall;
                }
                return test;
            }
        }
        return 0;
    }
    public int pushLeft(ArrayList<Rectangle> inRects, int distance, int rightWall, int bottomWall, boolean wrap)
    {
        return testLeft(inRects, testLeft(inRects, distance, false, rightWall, bottomWall, wrap), true, rightWall, bottomWall, wrap);
    } 
    protected int testLeft(ArrayList<Rectangle> inRects, int distance, boolean push, int rightWall, int bottomWall, boolean wrap)
    {
        for(int i = distance; i > 0; i--) {
            if (wrap || left() - i >= 0) {
                    int test = i;
                    for(Rectangle rect : inRects)
                    {
                        int right = rect.right();
                        if (right > left())
                            right -= rightWall;
                        int top = rect.top();
                        int bottom = rect.bottom();
                        if (top > bottom()) {
                            top -= bottomWall;
                            bottom -= bottomWall;
                        } 
                        else if (bottom < top()) {
                            top += bottomWall;
                            bottom += bottomWall;
                        }
                        if (top() < bottom && bottom() > top && 
                        left() >= right && left() - test < right)
                        {
                            int spaceBetween = left() - right;
                            int freeMove = rect.testLeft(inRects, test - spaceBetween, push, rightWall, bottomWall, wrap) + spaceBetween;
                            if (freeMove < test)
                                test = freeMove;
                        }
                    }
                    if (push) {
                        x -= distance;
                        if (x < 0)
                            x += rightWall;
                    }
                    return test;
                }
            }
        return 0;
    }
    public int pushRight(ArrayList<Rectangle> inRects, int distance, int rightWall, int bottomWall, boolean wrap)
    {
        return testRight(inRects, testRight(inRects, distance, false, rightWall, bottomWall, wrap), true, rightWall, bottomWall, wrap);
    }
    protected int testRight(ArrayList<Rectangle> inRects, int distance, boolean push, int rightWall, int bottomWall, boolean wrap)
    {
        for(int i = distance; i > 0; i--) {
            if (wrap || right() + i <= rightWall) {
                int test = i;
                for(Rectangle rect : inRects)
                {
                    int left = rect.left();
                    if (left < right())
                        left += rightWall;
                    int top = rect.top();
                    int bottom = rect.bottom();
                    if (top > bottom()) {
                        top -= bottomWall;
                        bottom -= bottomWall;
                    } 
                    else if (bottom < top()) {
                        top += bottomWall;
                        bottom += bottomWall;
                    }
                    if (top() < bottom && bottom() > top && 
                    right() <= left && right() + test > left)
                    {
                        int spaceBetween = left - right();
                        int freeMove = rect.testRight(inRects, test - spaceBetween, push, rightWall, bottomWall, wrap) + spaceBetween;
                        if (freeMove < test)
                            test = freeMove;
                    }
                }
                if (push) {
                    x += distance;
                    if (x >= rightWall)
                        x -= rightWall;
                }
                return test;
            }
        }
        return 0;
    }
    public int pushDown(ArrayList<Rectangle> inRects, int distance, int rightWall, int bottomWall, boolean wrap)
    {
        return testDown(inRects, testDown(inRects, distance, false, rightWall, bottomWall, wrap), true, rightWall, bottomWall, wrap);
    }
    protected int testDown(ArrayList<Rectangle> inRects, int distance, boolean push, int rightWall, int bottomWall, boolean wrap)
    {
        for(int i = distance; i > 0; i--) {
            if (wrap || bottom() + i <= bottomWall) {
                int test = i;
                for(Rectangle rect : inRects)
                {
                    int top = rect.top();
                    if (top < bottom())
                        top += bottomWall;
                    int left = rect.left();
                    int right = rect.right();
                    if (left > right()) {
                        left -= rightWall;
                        right -= rightWall;
                    } 
                    else if (right < left()) {
                        left += rightWall;
                        right += rightWall;
                    }
                    if (left() < right && right() > left && 
                    bottom() <= top && bottom() + test > top)
                    {
                        int spaceBetween = top - bottom();
                        int freeMove = rect.testDown(inRects, test - spaceBetween, push, rightWall, bottomWall, wrap) + spaceBetween;
                        if (freeMove < test)
                            test = freeMove;
                    }
                }
                if (push) {
                    y += distance;
                    if (y >= bottomWall)
                        y -= bottomWall;
                }
                return test;
            }
        }
        return 0;
    }
}