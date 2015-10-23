public class Simulator
{
    int[][] pieces;
    int[] choices;
    boolean compSim;
    public Simulator(int[][] inPieces, int[] inChoices, boolean inCompSim)
    {
        pieces = inPieces;
        choices = inChoices;
        compSim = inCompSim;
    }
    public int simulate()
    {
        if (TicTacToe.testGame(pieces))
        {
            int turn;
            if (compSim)
                turn = 2;
            else
                turn = 1;
            int[] result = new int[choices.length];
            for(int i = 0; i < choices.length; i++)
            {
                int[] temp = TicTacToe.pickSquare(choices[i], turn, choices, pieces);
                int[][] tempPieces = TicTacToe.getPieces();
                Simulator sim = new Simulator(tempPieces, temp, (!compSim));
                result[i] = sim.simulate();
            }
            
            return common;
        } else
        {
            return TicTacToe.first;
        }
    }
}