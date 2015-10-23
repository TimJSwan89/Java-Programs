public class Mod {
    public static void main() {
        for(int i = 1; i < 10; i++) {
            System.out.println("shake" + shake(i));
            System.out.println(i*(i-1)/2);
        }
    }
    public static int shake(int n) {
        if (n == 1)
            return 0;
        else
            return n-1 + shake(n - 1);
    }
    public static void po() {
        
        int n = 1;
        for(int b = 0; b < 6; b++) {
            n *= 4;
        int sum = 0;
        for(int i = 0; i <= n; i++)
            for(int j = 1; j <= i * i; j++)
                if (j % i == 0)
                    for(int k = 0; k < j; k++)
                        sum++;
                        System.out.println(n);
        System.out.println(sum);
    }
    }
}