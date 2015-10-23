package mind;

public class Node {
	long set;
	Node[] connect;
	public Node(int con) {
		connect = new Node[con];
	}
}
