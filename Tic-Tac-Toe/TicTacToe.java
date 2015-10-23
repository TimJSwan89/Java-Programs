public class TicTacToe
{
    static int[][] pieces = new int[3][3];
    static int[][] tempPieces = new int[3][3];
    static int[] choices = {1,2,3,4,5,6,7,8,9};
    static int first;
    boolean bool = true;
    public static void main(String[] args)
    {
        int start = Validation.askInt("Tic Tac Toe:\n" +
        "1. You go first.\n2. Computer goes first",1,2);
        print();
        if (start == 1)
        {
            playerTurn();
            print();
        }
        while (testGame(pieces))
        {
            //computerTurn();
            print();
            if (testGame(pieces))
            {
                playerTurn();
                print();
            }
        }
        if (first == 1)
            System.out.println("You win");
        else
            System.out.println("You lose");
    }
    public static void playerTurn()
    {
        System.out.println("You're choices are: ");
        for(int i = 0; i < choices.length; i++)
            System.out.print(choices[i] + " ");
        System.out.println();
        int selection = Validation.askInt("Enter selection.", choices);
        choices = pickSquare(selection, 1, choices, pieces);
        pieces = getPieces();
    }
    public static int[] pickSquare(int inSelection, int inNum, int[] inChoices, int[][] inPieces)
    {
        int[] temp1 = new int[inChoices.length];
        for(int i = 0; i < inChoices.length; i++)
            temp1[i] = inChoices[i];
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                tempPieces[i][j] = inPieces[i][j];
        tempPieces[(inSelection - 1) / 3][(inSelection - 1) % 3] = 1;
        for(int i = 0; i < temp1.length; i++)
            if (temp1[i] == inSelection)
                for(int j = i; j < temp1.length - 1; j++)
                    temp1[j] = temp1[j + 1];
        int[] temp2 = new int[temp1.length - 1];
        for(int i = 0; i < temp2.length; i++)
            temp2[i] = temp1[i];
        return temp2;
    }
    public static int[][] getPieces()
    {
        return tempPieces;
    }
    public static boolean testGame(int[][] inPieces)
    {
        boolean check;
        for(int i = 0; i < 3; i++)
        {
            check = true;
            first = inPieces[i][0];
            if (first != 0)
            {
                for(int j = 1; j < 3; j++)
                    if (inPieces[i][j] != first)
                        check = false;
                if (check)
                    return false;
            }
            check = true;
            first = inPieces[0][i];
            if (first != 0)
            {
                for(int j = 1; j < 3; j++)
                    if (inPieces[j][i] != first)
                        check = false;
                if (check)
                    return false;
            }
        }
        check = true;
        first = inPieces[0][0];
        if (first != 0)
        {
            for(int i = 1; i < 3; i++)
                if (inPieces[i][i] != first)
                    check = false;
            if (check)
                return false;
        }
        check = true;
        first = inPieces[2][0];
        if (first != 0)
        {
            for(int i = 1; i < 3; i++)
                if (inPieces[2 - i][i] != first)
                    check = false;
            if (check)
                return false;
        }
        if (choices.length == 0)
        {
            first = 0;
            return false;
        }
        return true;
    }
    public static void print()
    {
        System.out.println("  -------------------");
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                String character;
                switch(pieces[i][j])
                {
                    case 1:
                    {
                        character = "X";
                        break;
                    }
                    case 2:
                    {
                        character = "O";
                        break;
                    }
                    default:
                    {
                        character = " ";
                    }
                }
                System.out.print("  |  "+character);
                if (j == 2)
                    System.out.print("  |");
            }
            System.out.println("\n  -------------------");
        }
    }
    public static void computerTurn()
    {
        Simulator sim = new Simulator(pieces, choices, true);
        sim.simulate();
    }
}