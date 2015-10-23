import java.util.ArrayList;
import java.util.StringTokenizer;
public class Driv {
    public static void main() {
        ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        do {
            System.out.println("Nouns are made up.\n" + 
            "Verbs: is, are\n" + 
            "Enter a statement.");
            String input = Keyboard.readString();
            boolean question = (input.charAt(input.length() - 1) == '?');
            if (question)
                input = input.substring(0, input.length() - 1);
            StringTokenizer strTok = new StringTokenizer(input);
            boolean firstNoun = true;
            String nounOne = "";
            String nounTwo = "";
            while(strTok.hasMoreTokens()) {
                String word = strTok.nextToken();
                if (word.equalsIgnoreCase("is") || word.equalsIgnoreCase("are"))
                    firstNoun = false;
                else
                    if (firstNoun)
                        nounOne += word + " ";
                    else
                        nounTwo += word + " ";
            }
            nounOne = nounOne.trim();
            nounTwo = nounTwo.trim();
            debug("nounOne: \"" + nounOne + "\"");
            debug("nounTwo: \"" + nounTwo + "\"");
            if (question) {
                for(int i = 0; i < list.size(); i++)
                    if (nounOne.equalsIgnoreCase(list.get(i).get(0)))
                        for(int j = 1; j < list.get(i).size(); j++)
                            System.out.println(list.get(i).get(j));
            }
            else {
                int temp1 = -1;
                int temp2 = -1;
                for(int i = 0; i < list.size(); i++) {
                    if (nounOne.equalsIgnoreCase(list.get(i).get(0)));
                        temp1 = i;
                    if (nounTwo.equalsIgnoreCase(list.get(i).get(0)));
                        temp2 = i;
                }
                if (temp1 == -1) {
                    ArrayList<String> bob = new ArrayList<String>();
                    list.add(bob);
                    temp1 = list.size() - 1;
                    if (list.get(temp1) == bob)
                        System.out.println("smart");
                    list.get(temp1).add(nounOne);
                }
                if (temp2 == -1) {
                    list.add(new ArrayList<String>());
                    temp2 = list.size() - 1;
                    list.get(temp2).add(nounTwo);
                }
                list.get(temp1).add(list.get(temp2).get(0));
                list.get(temp2).add(list.get(temp1).get(0));
            }
            for(int i = 0; i < list.size(); i++)
                for(int j = 0; j < list.get(i).size(); j++)
                    debug("list.get(" + i + ").get(" + j + ") = " + list.get(i).get(j));
        } while(true);
    }
    public static void debug(String in) {
            System.out.println("  Debug: " + in);
    }
}