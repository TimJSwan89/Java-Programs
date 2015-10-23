public class Driver {
    public static void main() {
        int[] a = new int[10];
        int i = 0;
        a[1] = 8;
        System.out.println(a[++i]);
        System.out.println(i);
       // Matrix m = new Matrix();
    }
    static {
        System.out.println("When does this print?");
    }
}