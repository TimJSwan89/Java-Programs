import java.awt.Graphics;
import java.awt.Color;
public class Sphere {
    double x, y, z;
//     double xAngle, yAngle, zAngle;
    double[] x1;
    double[] x2;
    double[] y1;
    double[] y2;
    double[] z1;
    double[] z2;
    int h, q, qu;
    double r;
    public Sphere(double x, double y, double z, boolean V2Pole, boolean V2) {
        int accuracy = 50;
        q = 0;
        if (V2Pole)
            for(int i = 0; i < accuracy; i++) {
                double phi = ((double)i/accuracy) * Math.PI;
                double b = (Math.sin(phi) * accuracy);
                for(int j = 0; j < b; j++)
                    q++;
            }
        else
            q = accuracy * accuracy;
        double[] x1 = new double[q];
        double[] y1 = new double[q];
        double[] z1 = new double[q];
        double p = 15;
        double theta;
        double phi;
        h = 0;
        for(int i = 0; i < accuracy; i++) {
            phi = ((double)i/accuracy) * Math.PI;
            double num = ((V2Pole) ? Math.sin(phi) : 1) * accuracy;
//             h += num;
            for(int j = 0; j < num; j++) {
                theta = (j/num) * 2 * Math.PI + (V2 ? phi : 0);
                x1[h] = p * Math.sin(phi) * Math.cos(theta);
                y1[h] = p * Math.sin(phi) * Math.sin(theta);
                z1[h] = p * Math.cos(phi);
//                 if (z1[h] > 15)
//                     h--;
                h++;
            }
        }
        qu = h;
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        move(x,y,z);
//         double[] x2 =  new double[1000];
//         this.x2 = x2;
//         double[] y2 =  new double[1000];
//         this.y2 = y2;
//         double[] z2 =  new double[1000];
//         this.z2 = z2;
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
        g.setColor(Color.black);
        g.drawString(Integer.toString(q), 10, 10);
        g.drawString(Integer.toString(h), 10, 50);
        for(int i = 0; i < h; i++) {
//             int dx1 = (int)(s * (x1[i] + x)/(z1[i] + z));
//             int dy1 = (int)(s * (y1[i] + y)/(z1[i] + z));
//             int dx2 = (int)(s * (x2[i] + x)/(z2[i] + z));
//             int dy2 = (int)(s * (y2[i] + y)/(z2[i] + z));
                
                int[] p1 = findPoint(x1[i] + x, y1[i] + y, z1[i] + z, xCam, yCam, zCam, xThetaCam, yThetaCam, s);
                if (p1 != null)
                    g.drawLine(p1[0] + 300, p1[1] + 300, p1[0] + 300, p1[1] + 300);
//          else
//         g.drawString("ERROR z <=0 ", 10, 30);
    }
        g.drawString("Drawn", 10, 20);
    
}
    public static int[] findPoint(double x, double y, double z, double xCam, double yCam, double zCam,
                          double xThetaCam, double yThetaCam, int s) {
        // Account for shift between origin and camera
        x -= xCam;
        y -= yCam;
        z -= zCam;
        
        // Account for camera rotation compared to coordinate system
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