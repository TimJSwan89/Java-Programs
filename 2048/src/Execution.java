import java.util.ArrayList;
import java.util.Random;
public class Execution {
    public static void main(String[] args) {
        boolean auto = true;
        int[][] board = new int[4][4];
        int[][][] undo = new int[10][4][4];
        int undoLoadIndex = 0;
        int undoSaveIndex = 0;
        setZero(board);
        printBoard(board);
        getAndPrint(board);
        getAndPrint(board);
        while(true) {
            int filled = checkFilled(board);
            int depth = filled / 3 + 1;
            if (getTotalScore(board) > 3000)
                depth++;
            if (getTotalScore(board) > 6000)
                depth++;
            System.out.println("Filled: "+filled+". Search depth: "+depth+".");
            float[] check = optD(board, depth);
            System.out.println("Moved "+direction(check)+" <=============|");
            move(board, check);
            if (!auto) {
            undoLoadIndex++;
            if (undoLoadIndex > 9)
                undoLoadIndex = 0;
            if (undoLoadIndex == undoSaveIndex)
                undoSaveIndex++;
            if (undoSaveIndex > 9)
                undoSaveIndex = 0;
            undo[undoLoadIndex] = copy(board);
            }
            printBoard(board);
            if (! auto) {
            while (getAndPrint(board)) {
                if (undoSaveIndex == undoLoadIndex)
                    System.out.println("No more undo's available.");
                else {
                    board = undo[undoLoadIndex];
                    undoLoadIndex--;
                    if (undoLoadIndex < 0)
                        undoLoadIndex = 9;
                    printBoard(board);
                    int in = 0;
                    while (in != 4) {
                        System.out.println("(0: Right) (1: Up) (2: Left) (3: Down) (4: Continue) (5: Reenter board)");
                        in = Keyboard.readInt();
                        if (in == 0)
                            moveRight(board);
                        if (in == 1)
                            moveUp(board);
                        if (in == 2)
                            moveLeft(board);
                        if (in == 3)
                            moveDown(board);
                        if (in == 5)
                            for (int i = 0; i < 4; i++)
                                for (int j = 0; j < 4; j++)
                                    board[i][j] = Keyboard.readInt();
                        if (in < 0 || in > 5)
                            System.out.println("Please Enter [0-5]");
                        else
                            if (in != 4)
                                printBoard(board);
                    }
                }
            }
            }
            if (auto)
                autoGetAndPrint(board);
        }
    }
    public static float[] optD(int[][] board, int depth) { //want max space or min fill
        float[] retVal = new float[2];
        if (depth == 0) {
            retVal[0] = checkSpace(board);
            return retVal;
        } else {
            int[][] leftBoard  = copy(board);
            int[][] rightBoard = copy(board);
            int[][] upBoard    = copy(board);
            int[][] downBoard  = copy(board);
            float l = 0, r = 0, u = 0, d = 0;
            if (moveLeft(leftBoard))
                l = optI(leftBoard , depth - 1);
            if (moveRight(rightBoard))
                r = optI(rightBoard, depth - 1);
            if (moveUp(upBoard))
                u = optI(upBoard   , depth - 1);
            if (moveDown(downBoard))
                d = optI(downBoard , depth - 1);
            if (l > r) {
                if (l > u) {
                    if (l > d) {// l
                        retVal[0] = l;
                        retVal[1] = 2;
                    } else {    // d
                        retVal[0] = d;
                        retVal[1] = 3;
                    }
                } else {
                    if (u > d) {// u
                        retVal[0] = u;
                        retVal[1] = 1;
                    } else {    // d
                        retVal[0] = d;
                        retVal[1] = 3;
                    }
                }
            } else {
                if (r > u) {
                    if (r > d) {// r
                        retVal[0] = r;
                        retVal[1] = 0;
                    } else {    // d
                        retVal[0] = d;
                        retVal[1] = 3;
                    }
                } else {
                    if (u > d) {// u
                        retVal[0] = u;
                        retVal[1] = 1;
                    } else {    // d
                        retVal[0] = d;
                        retVal[1] = 3;
                    }
                }
            }
            return retVal;
        }
    }
    public static float optI(int[][] board, int depth) { //want min space or max fill
        // We want this to contain the base case since it is pointless to test
        // fullness with all filling possibilities after the deepest tested move.
        if (depth == 0)
            return checkSpace(board);
        //int[][] twoInstance  = new int[4][4];
        //int[][] fourInstance = new int[4][4];
        //setZero(twoInstance);
        //setZero(fourInstance);
        float two = 0;
        float four = 0;
        int total = 0;
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                if (board[i][j] == 0) {
                    total++;
                    int[][] twoInstance = copy(board);
                    twoInstance[i][j] = 2;
                    float[] returningTwo = optD(twoInstance, depth);
                    //if (returningTwo[0] < likelinessTwo)
                    //    likelinessTwo = returningTwo[0];
                    two += returningTwo[0];
                    int[][] fourInstance = copy(board);
                    fourInstance[i][j] = 4;
                    float[] returningFour = optD(fourInstance, depth);
                    //if (returningFour[0] < likelinessFour)
                    //    likelinessFour = returningFour[0];
                    four += returningFour[0];
                }
        float likelinessTwo = (total == 0) ? 0 : (((float) two) / total);
        float likelinessFour = (total == 0) ? 0 : (((float) four) / total);
        return likelinessTwo * (((float) 7)/9) + likelinessFour * (((float) 2)/9);
    }
    public static void printLine(int kind) {
        if (kind == 1)
            System.out.println("------------------------------------");
        else if (kind == 2)
            System.out.println("************************************");
        else
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                    "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                    "____________________________________");
    }
    public static void setZero(int[][] board) {
        for(int i = 0; i < 4; i++)
            for(int j = 0; j< 4; j++)
                board[i][j] = 0;
    }
    public static void printBoard(int[][] board) {
        System.out.println(" ___________________");
        for(int i = 0; i < 4; i++) {
            System.out.print("|");
            for(int j = 0; j< 4; j++) {
                int a = board[i][j];
                System.out.print((a == 0 ? " " : a));
                if (a < 1000)
                    System.out.print(" ");
                if (a < 100)
                    System.out.print(" ");
                if (a < 10)
                    System.out.print(" ");
                System.out.print("|");
            }
            System.out.println("\n ___________________");
        }
    }
    public static boolean getNewSquare(int[][] board) {
        System.out.println("Enter new square position (1-16):");
        System.out.println("[Begin with a '4' if it has value 4.]");
        System.out.println("Enter 0 to undo last move.");
        int value = 2;
        int input = Keyboard.readInt();
        if (input == 0) {
            return true;
        }
        if (input > 16) {
            if (input >= 400)
                input -= 400;
            else
                input -= 40;
            value = 4;
        }
        input -= 1;
        int row = input / 4;
        int col = input % 4;
        board[row][col] = value;
        return false;
    }
    public static boolean getAndPrint(int[][] board) {
        boolean undo = getNewSquare(board);
        if (!undo)
            printBoard(board);
        return undo;
    }
    public static void autoGetAndPrint(int[][] board) {
        autoGetNewSquare(board);
        printBoard(board);
    }
    public static void autoGetNewSquare(int[][] board) {
        Random rand = new Random();
        int value = rand.nextInt(9);
        if (value > 1) {
            value = 2;
        } else {
            value = 4;
        }
        int[] selection = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        int input = 0;
        for(int i = 15; i >= 0; i--) {
            input = rand.nextInt(i + 1);
            int temp = selection[i];
            selection[i] = selection[input];
            selection[input] = temp;
        }
        int row = 0, col = 0;
        int i = 0;
        for ( ; i < 16; i++) {
            row = selection[i] / 4;
            col = selection[i] % 4;
            System.out.print("["+row+", "+col+"], ");
            if (board[row][col] == 0) {
                System.out.println("placed a '"+value+"'.");
                break;
            }
        }
        if (i == 16) {
            System.out.println("Game Over.");
            System.exit(0);
        }
        board[row][col] = value;
    }
    public static boolean moveLeft(int[][] board) {
        boolean globalChange = false;
        boolean[] combined = {false, false, false, false};
        for(int i = 0; i < 4; i++) {
            boolean change;
            do {
                change = false;
                for(int j = 1; j < 4; j++) {
                    if (board[i][j] != 0) {
                        if (board[i][j-1] == 0) {
                            board[i][j-1] = board[i][j];
                            board[i][j] = 0;
                            change = true;
                        } else if(board[i][j-1] == board[i][j] && !combined[i]) {
                            board[i][j] = 0;
                            board[i][j-1] *= 2;
                            combined[i] = true;
                            change = true;
                        }
                    }
                }
                globalChange = (globalChange || change);
            } while (change);
        }
        return globalChange;
    }
    public static boolean moveRight(int[][] board) {
        boolean globalChange = false;
        boolean[] combined = {false, false, false, false};
        for(int i = 0; i < 4; i++) {
            boolean change;
            do {
                change = false;
                for(int j = 2; j >= 0; j--) {
                    if (board[i][j] != 0) {
                        if (board[i][j+1] == 0) {
                            board[i][j+1] = board[i][j];
                            board[i][j] = 0;
                            change = true;
                        } else if(board[i][j+1] == board[i][j] && !combined[i]) {
                            board[i][j] = 0;
                            board[i][j+1] *= 2;
                            combined[i] = true;
                            change = true;
                        }
                    }
                }
                globalChange = (globalChange || change);
            } while (change);
        }
        return globalChange;
    }
    public static boolean moveDown(int[][] board) {
        boolean globalChange = false;
        boolean[] combined = {false, false, false, false};
        for(int j = 0; j < 4; j++) {
            boolean change;
            do {
                change = false;
                for(int i = 2; i >= 0; i--) {
                    if (board[i][j] != 0) {
                        if (board[i+1][j] == 0) {
                            board[i+1][j] = board[i][j];
                            board[i][j] = 0;
                            change = true;
                        } else if(board[i+1][j] == board[i][j] && !combined[j]) {
                            board[i][j] = 0;
                            board[i+1][j] *= 2;
                            combined[j] = true;
                            change = true;
                        }
                    }
                }
                globalChange = (globalChange || change);
            } while (change);
        }
        return globalChange;
    }
    public static boolean moveUp(int[][] board) {
        boolean globalChange = false;
        boolean[] combined = {false, false, false, false};
        for(int j = 0; j < 4; j++) {
            boolean change;
            do {
                change = false;
                for(int i = 1; i < 4; i++) {
                    if (board[i][j] != 0) {
                        if (board[i-1][j] == 0) {
                            board[i-1][j] = board[i][j];
                            board[i][j] = 0;
                            change = true;
                        } else if(board[i-1][j] == board[i][j] && !combined[j]) {
                            board[i][j] = 0;
                            board[i-1][j] *= 2;
                            combined[j] = true;
                            change = true;
                        }
                    }
                }
                globalChange = (globalChange || change);
            } while (change);
        }
        return globalChange;
    }
    public static int checkSpace(int[][] board) {
        int count = 0;
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                if (board[i][j] == 0)
                    count++;
        return count;
    }
    public static int getTotalScore(int[][] board) {
        int count = 0;
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                count += board[i][j];
        return count;
    }
    public static int checkFilled(int[][] board) {
        return 16 - checkSpace(board);
    }
    public static int[][] copy(int[][] board) {
        int[][] newBoard = new int[4][4];
        for(int i = 0; i < 4; i++)
            for(int j = 0; j< 4; j++)
                newBoard[i][j] = board[i][j];
        return newBoard;
    }
    public static String direction(float[] in) {
        if (in[1] == 0)
            return "Right";
        if (in[1] == 1)
            return "Up";
        if (in[1] == 2)
            return "Left";
        if (in[1] == 3)
            return "Down";
        return "Direction DNE Error for string";
    }
    public static void move(int[][] board, float[] in) {
        if (in[1] == 0)
            moveRight(board);
        if (in[1] == 1)
            moveUp(board);
        if (in[1] == 2)
            moveLeft(board);
        if (in[1] == 3)
            moveDown(board);
        if (in[1] != 0 && in[1] != 1 && in[1] != 2 && in[1] != 3)
            System.out.println("Direction DNE error for move() in[1] = "+in[1]);
    }
}