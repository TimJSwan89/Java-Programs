import java.awt.Graphics;
import java.awt.Color;
public class Cube {
    double x, y, z;
//     double xAngle, yAngle, zAngle;
    double[] x1;
    double[] x2;
    double[] y1;
    double[] y2;
    double[] z1;
    double[] z2;
    int h;
    double r;
    public Cube(int inSide, double x, double y, double z) {
        h = inSide / 2;
        r = Math.sqrt(2) * h;
        double[] x1 = { h, h,-h,-h, h, h,-h,-h, h, h,-h,-h};
        this.x1 = x1;
        double[] y1 = { h,-h, h,-h, h, h,-h,-h, h, h,-h,-h};
        this.y1 = y1;
        double[] z1 = { h, h, h, h, h, h, h, h,-h,-h,-h,-h};
        this.z1 = z1;
        double[] x2 = { h, h,-h,-h,-h, h, h,-h,-h, h, h,-h};
        this.x2 = x2;
        double[] y2 = { h,-h, h,-h, h,-h,-h, h, h,-h,-h, h};
        this.y2 = y2;
        double[] z2 = {-h,-h,-h,-h, h, h, h, h,-h,-h,-h,-h};
        this.z2 = z2;
        move(x,y,z);
    }
    public void move(double xNew, double yNew, double zNew) {
        x = xNew;
        y = yNew;
        z = zNew;
    }
    public void xRotate(double ang) {
        rotate(ang, y1, z1);
        rotate(ang, y2, z2);
    }
    public void yRotate(double ang) {
        rotate(ang, x1, z1);
        rotate(ang, x2, z2);
    }
    public void zRotate(double ang) {
        rotate(ang, x1, y1);
        rotate(ang, x2, y2);
    }
    public void rotate(double ang, double[] cosCord, double[] sinCord) {
        for(int i = 0; i < 12; i++) {
            double r = Math.sqrt(cosCord[i] * cosCord[i] + sinCord[i] * sinCord[i]);
            double angle = Math.atan2(sinCord[i], cosCord[i]) + ang;
            cosCord[i] = r * Math.cos(angle);
            sinCord[i] = r * Math.sin(angle);
        }
    }
//  I wrote this mathod before I knew about Math.atan2().
//     public static double atan(double num, double den) {
//         return (den == 0) ? Math.PI / 2 * ((num < 0) ? -1 : 1) : Math.atan(num / den) - ((den < 0) ? Math.PI : 0);
//     }
    public void draw(Graphics g, int s, double xCam, double yCam, double zCam, double xThetaCam, double yThetaCam) {
        g.drawString(Double.toString(x1[1]), 10, 10);
        g.setColor(Color.black);
        for(int i = 0; i < 12; i++) {
//             int dx1 = (int)(s * (x1[i] + x)/(z1[i] + z));
//             int dy1 = (int)(s * (y1[i] + y)/(z1[i] + z));
//             int dx2 = (int)(s * (x2[i] + x)/(z2[i] + z));
//             int dy2 = (int)(s * (y2[i] + y)/(z2[i] + z));
                
                int[] p1 = findPoint(x1[i] + x, y1[i] + y, z1[i] + z, xCam, yCam, zCam, xThetaCam, yThetaCam, s);
                if (p1 != null) {
                    int[] p2 = findPoint(x2[i] + x, y2[i] + y, z2[i] + z, xCam, yCam, zCam, xThetaCam, yThetaCam, s);
                    if (p2 != null)
                        g.drawLine(p1[0] + 300, p1[1] + 300, p2[0] + 300, p2[1] + 300);
                }
            
        }
//         int[] p1 = findPoint(x, y, z, xCam, yCam, zCam, xThetaCam, yThetaCam, s);
//         g.drawLine(p1[0] + 300, p1[1] + 300, p1[0] + 300, p1[1] + 300);
    }
    public static int[] findPoint(double x, double y, double z, double xCam, double yCam, double zCam,
                          double xThetaCam, double yThetaCam, int s) {
        // Account for shift between origin and camera
        x -= xCam;
        y -= yCam;
        z -= zCam;
        
        // Account for camera rotation compared to coordinate system
        // Note: I haven't made this happen yet
        double xr = x; 
        double yr = y;
        double zr = z;
        
        // Account for point behind camera
        if (zr <= 0)
            return null;
        
        // Scale to 2D Screen
        int x2d = (int)(s * xr / zr);
        int y2d = (int)(s * yr / zr);
        int[] xy = {x2d, y2d};
        return xy;
    }
}