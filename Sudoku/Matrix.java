import java.util.*;
public class Matrix {
    public int[][] nums;
    public Random rand;
    public Matrix() {
        System.out.println();
        rand = new Random();
        nums = new int[9][9];
//         for(int i = 0; i < 9; i++) {
//             for(int j = 0; j < 9; j++)
//                 nums[i][j] = 0;
//         }
        generate(0, 0);
    }
    public void generate(int x, int y) {
        int newx = x + 1;
        int newy = y;
        if (newx == 9) {
            newx = 0;
            newy++;
        }
        for(int i = 0; i < 9; i++) {
            nums[x][y] = i;
            if (newy < 9)
                generate(newx, newy);
            else
                if (test())
                    print();
        }
    }
    public boolean test() {
        boolean[] test = new boolean[9];
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++)
                test[j] = false;
            for(int j = 0; j < 9; j++)
                test[nums[i][j]] = true;
            for(int j = 0; j < 9; j++)
                if (test[j] = false)
                    return false;
        }
        return true;
    }
    public void print() {
        System.out.println();
        for(int i = 0; i < 9; i++) {
            System.out.println();
            if (i % 3 == 0)
                System.out.println(" |---------|---------|---------|");
            for(int j = 0; j < 9; j++) {
                if (j % 3 == 0)
                    System.out.print(" | ");
                else
                    System.out.print("  ");
                System.out.print(nums[i][j]);
                if (j == 8)
                    System.out.print(" | ");
            }
        }
        System.out.print("\n |---------|---------|---------|");
    }
}