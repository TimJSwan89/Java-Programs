import java.util.ArrayList;
public class Element
{
    private double x, y, xVel = 0, yVel = 0, xAcc = 0, yAcc = 0;
    private ArrayList<Element> partners;
    private static ArrayList<Element> all = new ArrayList<Element>();
    public Element(int inx, int iny)
    {
        x = inx;
        y = iny;
        partners = new ArrayList();
        all.add(this);
    }
    public void assign(Element e) 
    {
        partners.add(e);
    }
    public void move()
    {
//         for(int i = 0; i < all.size(); i++) {
//             Element e = all.get(i);
//             if (e != this) {
//                 double xd = x - e.x();
//                 double yd = y - e.y();
//                 double h = Math.sqrt(xd * xd + yd * yd);
//                 if (h < 25) {
//                     xVel += h * .00001 * xd;
//                     yVel += h * .00001 * yd;
//                 }
//             }
//         }
        for(int i = 0; i < partners.size(); i++) {
            Element e = partners.get(i);
            double xd = x - e.x();
            double yd = y - e.y();
            double h = Math.sqrt(xd * xd + yd * yd);
            double hVel = Math.sqrt(xVel * xVel + yVel * yVel);
            xVel += (h - hVel * 400) * -0.000002 * xd;
            yVel += (h - hVel * 400) * -0.000002 * yd;
        }
        x += xVel;
        y += yVel;
    }
    public double x()
    {
        return x;
    }
    public double y()
    {
        return y;
    }
//     public void move()
//     {
//         for(int i = 0; i < all.size(); i++) {
//             Element e = all.get(i);
//             if (e != this) {
//                 double xd = x - e.x();
//                 double yd = y - e.y();
//                 double h = Math.sqrt(xd * xd + yd * yd);
//                 if (h < 25) {
//                     x += h * .01 * xd;
//                     y += h * .01 * yd;
//                 }
//             }
//         }
//         for(int i = 0; i < partners.size(); i++) {
//             Element e = partners.get(i);
//             double xd = x - e.x();
//             double yd = y - e.y();
//             double h = Math.sqrt(xd * xd + yd * yd);
//             if (h > 30) {
//                 x -= h * .001 * xd;
//                 y -= h * .001 * yd;
//             }
//         }
//     }
}
