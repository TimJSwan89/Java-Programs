import java.util.ArrayList;
public class ArrayListTester {
    public static void main() {
        ArrayList<String> bob = new ArrayList<String>();
        bob.add("first");
        bob.add("second");
        bob.add("third");
        for(int i = 0; i < bob.size(); i++)
            System.out.println(bob.get(i));
        }
}