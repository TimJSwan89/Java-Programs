public class ArbitraryStructure {
    public static void main(String[] args) {
        System.out.println();
        int in;
        do {
            System.out.println("Create new structure (1) or load (2)?");
            in = Keyboard.readInt();
        } while(in != 1 && in != 2);
        Node point, start;
        if (in == 1)
            start = new Node();
        else {
            Node.load();
            start = (Node) Node.all.get(0);
        }
        point = start.menu();
        while(point != null)
            point = point.menu();
    }
}