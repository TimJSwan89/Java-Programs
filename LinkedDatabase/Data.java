import java.util.ArrayList;
public class Data {
    private String name;
    private ArrayList<Data> immediates;
    public Data(String title) {
        name = title;
        immediates = new ArrayList<Data>();
    }
    public Data get(int i) {
        return immediates.get(i);
    }
    public void add(Data link) {
        immediates.add(link);
    }
    public void bind(Data link) {
        add(link);
        link.add(this);
    }
    public String toString() {
        return name;
    }
    public String immediates(boolean numbered) {
        String string = name;
        string += " immediates:";
        if (immediates.size() < 1)
            return string + "\n\t-- no immediates --";
        for(int i = 0; i < immediates.size(); i++) {
            string += "\n\t" + (numbered ? i + 1 + ". " : "") + immediates.get(i);
        }
        return string;
    }
    public String descendants() {
        ArrayList<Data> masterList = new ArrayList<Data>();
        String masterDescendants = "";
        int indentation = 0;
        return descendants(masterList, masterDescendants, indentation);
    }
    private String descendants(ArrayList<Data> list, String descendants, int indent) {
        list.add(this);
        String indenting = "";
        for(int i = 0; i < indent; i++)
            indenting += "  ";
        descendants += indenting + name + "\n";
        for(int i = 0; i < immediates.size(); i++) {
            if (!list.contains(immediates.get(i)))
                descendants = immediates.get(i).descendants(list, descendants, indent + 1);
        }
        return descendants;
    }
    public int size() {
        return immediates.size();
    }
}