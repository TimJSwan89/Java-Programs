import java.util.ArrayList;
public class Inter {
    private String title;
    private ArrayList<Inter> list;
    private Inter mother;
    public Inter(String str, Inter inter) {
        list = new ArrayList<Inter>();
        title = str;
        mother = inter;
    }
    public void changeTitle(String title) {
        this.title = title;
    }
    public Inter add(String str) {
        Inter fresh = new Inter(str, this);
        list.add(fresh);
        return fresh;
    }
    public void remove(Inter inter) {
        for(int i = 0; i < list.size(); i++)
            if (inter == list.get(i))
                list.remove(i);
    }
    public Inter remove(int index) {
        if (index >= 0 && index < list.size())
            return list.remove(index);
        return null;
    }
    public Inter getFile(int index) {
        if (index >= 0 && index < list.size())
            return list.get(index);
        return null;
    }
    public Inter getMother() {
        return mother;
    }
    public String toString() {
        return toString(0);
    }
    public String toString(int max) {
        return toString(max, 0);
    }
    public String toString(int max, int depth) {
        String retStr = title;
        if (max != 0) {
            String space = " ";
            for(int i = 0; i < depth; i++)
                space += "  ";
            for(int i = 0; i < list.size(); i++)
                retStr += "\n" + space + (i + 1) + ". " + list.get(i).toString(max - 1, depth + 1);
        }
        return retStr;
    }
}