public class Algebra {
    public static void main() {
        System.out.println(solve(Keyboard.readString(), Keyboard.readChar()));
    }
    public static String solve(String equation, char variable) {
        String num;
        for(int i = 0; i < equation.length(); i++)
            if (new String("0123456789.").indexOf(equation.charAt(i)) != -1) {
                num = "";
                while (i < equation.length() && new String("0123456789.").indexOf(equation.charAt(i)) != -1) {
                    num += equation.charAt(i);
                    i++;
                }
            } else 
                
        return equation;
    }
}