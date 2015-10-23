import java.util.ArrayList;
public class Node {
    String data;
    ArrayList children;
    ArrayList parents;
    static ArrayList all = new ArrayList(10);
    public Node() {
        System.out.println("Enter first node: ");
        data = Keyboard.readString();
        initialize();
        all.add(this);
    }
    public Node(String newData) {
        this(newData, null);
    }
    public Node(String newData, Node... parent) {
        data = newData;
        initialize();
        if (parent != null)
            for(int i = 0; i < parent.length; i++)
                parents.add(parent[i]);
    }
    public void initialize() {
        children = new ArrayList(1);
        parents = new ArrayList(1);
    }
    public static Node createNode(String newData, Node... parent) {
        Node element = null;
        for(int i = 0; i < all.size(); i++)
            if (((Node) all.get(i)).data.equals(newData)) {
                element = (Node) all.get(i);
                if (parent != null)
                    element.addParent(parent);
                break;
            }
        if (element == null) {
            element = new Node(newData, parent);
            all.add(element);
        }
        return element;
    }
    public Node menu() {
        print();
        System.out.println("Commands: \n1. Add child\n" + 
        "2. Delete child\n3. Goto child\n4. Goto parent\n5. Save\n6. Load and mix" +
        "\n7. Clear\n8. Print Descendants\n9. Print Ancestors\n10. Exit");
        int choice = Keyboard.readInt();
        switch(choice) {
            case 1:
                addChild();
            break;
            case 2:
                
            break;
            case 3:
                printChildren();
                return (Node) children.get(Keyboard.readInt());
            case 4:
                printParents();
                return (Node) parents.get(Keyboard.readInt());
            case 5:
                System.out.println("Filename? : ");
                FileOut file = new FileOut(Keyboard.readString());
                file.write(all.size());
                for(int i = 0; i < all.size(); i++)
                    file.write(((Node) all.get(i)).data);
                Node node;
                for(int i = 0; i < all.size(); i++) {
                    node = (Node) all.get(i);
                    file.write(node.children.size());
                    for(int j = 0; j < node.children.size(); j++)
                        file.write(all.indexOf((Node)node.children.get(j)));
                }
                file.done();
                break;
            case 6:
                load();
                break;
            case 8:
                printDecendants();
                break;
            case 9:
                printAncestors();
                break;
            case 10:
                System.exit(0);
        }
        return this;
    }
    public void addChild() {
        System.out.print("Enter child title: \n" + 
            children.size() + ". ");
        Node element = createNode(Keyboard.readString(), this);
        children.add(element);
    }
    public void addChild(Node child) {
        children.add(child);
    }
    public void addParent(Node... parent) {
        for(int i = 0; i < parent.length; i++)
            parents.add((Node)parent[i]);
    }
    public void printParents() {
        System.out.println(parents());
    }
    public void printChildren() {
        System.out.println(children());
    }
    public void print() {
        System.out.println(this);
    }
    public String parents() {
        StringBuilder sb = new StringBuilder(data + ", [parents]: \n");
        for(int i = 0; i < parents.size(); i++)
            sb.append(i + ". " + ((Node)parents.get(i)).data + "\n");
        return sb.toString();
    }
    public String children() {
        StringBuilder sb = new StringBuilder(data + ", [children]: \n");
        for(int i = 0; i < children.size(); i++)
            sb.append(i + ". " + ((Node)children.get(i)).data + "\n");
        return sb.toString();
    }
    public String toString() {
        return parents() + "\n" + children();
    }
    public void printAncestors() {
        printFamily(true);
    }
    public void printDecendants() {
        printFamily(false);
    }
    private void printFamily(boolean AnotD) {
        ArrayList alreadyDone = new ArrayList(all.size());
        System.out.println(printFamily(alreadyDone, new StringBuilder("\n"), 0, AnotD).toString());
    }
    private StringBuilder printFamily(ArrayList aD, StringBuilder string, int indent, boolean AnotD) {
        for(int i = 0; i < indent; i++)
            string.append(" ");
        string.append(data + "\n");
        if (!aD.contains(this)) {
            aD.add(this);
            if (AnotD)
                for(int i = 0; i < parents.size(); i++)
                    string = ((Node) parents.get(i)).printFamily(aD, string, indent + 1, AnotD);
            else
                for(int i = 0; i < children.size(); i++)
                    string = ((Node) children.get(i)).printFamily(aD, string, indent + 1, AnotD);
        }
        return string;
    }
    public static void load() {
        System.out.println("Filename? : ");
        FileIn file = new FileIn(Keyboard.readString());
        int size = file.readInt();
        ArrayList temp = new ArrayList(size);
        for(int i = 0; i < size; i++)
            temp.add(createNode(file.read(), null));
        Node node;
        for(int i = 0; i < size; i++) {
            int j = file.readInt();
            node = (Node) temp.get(i);
            Node node2;
            for(int k = 0; k < j; k++) {
                node2 = (Node) temp.get(file.readInt());
                if (!node.children.contains(node2)) {
                    node.addChild(node2);
                    node2.addParent(node);
                }
            }
        }
        file.done();
    }
}