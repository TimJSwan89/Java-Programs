import java.util.Random;
public class Add {
    static int additions = 0;
    static int high;
    static int[] bob;
    public static void main() {
        Random rand = new Random();
        if (false) {
            high = (int) Math.pow(2, 23);
            bob = new int[high]; 
            for(int i = 0; i < high; i++)
                bob[i] = rand.nextInt(100) - 50;
            int j = 0;
            for(int i = 1; i < high; i *= 2) {
                j++;
                System.out.print("n = 2 ^ " + j + " = " + i + ": ");
                additions = 0;
                add(0, i - 1);
                System.out.println(additions + " additions");
            }
        } else {
            long time, time2;
            int j = 0;
            high = (int) Math.pow(2, 40);
            bob = new int[1];
            for(int i = 1; i < high; i *= 2) {
                j++;
                System.out.print("n = 2 ^ " + j + " = " + i + " time: ");
                time = System.currentTimeMillis();
                add2(0, i - 1);
                time2 = System.currentTimeMillis();
                time = time2 - time;
                System.out.println(time + " = 2 ^ " + Math.log(time) / Math.log(2) + " ratio = " + ((time == 0) ? "N/A" : i / time));
            }
        }
    }
    public static int add(int low, int high) {
        if (high == low)
            return bob[high];
        else {
            int mid = (low + high) / 2;
            additions++;
            return add(low, mid) + add(mid + 1, high);
        }
    }
    public static int add2(int low, int high) {
        if (high == low)
            return bob[0];
        else {
            int mid = (low + high) / 2;
            additions++;
            return add2(low, mid) + add2(mid + 1, high);
        }
    }
}