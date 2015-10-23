public class Validation
{
    private static boolean lines = true;
    private static boolean asterisks = true;
    public void setLines(boolean inLines)
    {
        lines = inLines;
    }
    public boolean toggleLines()
    {
        lines = !(lines);
        return lines;
    }
    public void setAsterisks(boolean inAsterisks)
    {
        asterisks = inAsterisks;
    }
    public boolean toggleAsterisks()
    {
        asterisks = !(asterisks);
        return asterisks;
    }
    public static String askString(String inQuestion, String... inAnswers)
    {
        boolean notAnswer = true;
        String answer;
        printLines();
        do
        {
            System.out.println(inQuestion);
            answer = Keyboard.readString();
            for(int i = 0; i < inAnswers.length; i++)
                if (answer.equals(inAnswers[i]))
                    notAnswer = false;
        } while(notAnswer);
        printAsterisks();
        return answer;
    }
    public static int askInt(String inQuestion, int... inAnswers)
    {
        boolean notAnswer = true;
        int answer;
        printLines();
        do
        {
            System.out.println(inQuestion);
            answer = Keyboard.readInt();
            for(int i = 0; i < inAnswers.length; i++)
                if (answer == inAnswers[i])
                    notAnswer = false;
        } while(notAnswer);
        printAsterisks();
        return answer;
    }
    public static int askIntRange(String inQuestion, int inLower, int inUpper)
    {
        int answer;
        printLines();
        do
        {
            System.out.println(inQuestion);
            answer = Keyboard.readInt();
        } while(answer < inLower || answer > inUpper);
        printAsterisks();
        return answer;
    }
    public static double askDouble(String inQuestion, double... inAnswers)
    {
        boolean notAnswer = true;
        double answer;
        printLines();
        do
        {
            System.out.println(inQuestion);
            answer = Keyboard.readDouble();
            for(int i = 0; i < inAnswers.length; i++)
                if (answer == inAnswers[i])
                    notAnswer = false;
        } while(notAnswer);
        printAsterisks();
        return answer;
    }
    public static char askChar(String inQuestion, char... inAnswers)
    {
        boolean notAnswer = true;
        char answer;
        printLines();
        do
        {
            System.out.println(inQuestion);
            answer = Keyboard.readChar();
            for(int i = 0; i < inAnswers.length; i++)
                if (answer == inAnswers[i])
                    notAnswer = false;
        } while(notAnswer);
        printAsterisks();
        return answer;
    }
    public static void printAsterisks()
    {
        if (asterisks)
            System.out.println("************************************");
    }
    public static void printLines()
    {
        if (lines)
            System.out.println("||||||||||||||||||||||||||||||||||||");
    }
}