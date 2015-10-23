package mind;

public class Brain {
	public final int CONNECTIONS = 5;
	static Node[][][] b;
	public Brain() {
		initialize();
	}
	public static void initialize() {
		b = new Node[10][10][10];
		for(int i = 0; i < 10; i++)
			for(int j = 0; j < 10; j++)
				for(int k = 0; k < 10; k++)
					b[i][j][k] = new Node(5);
		
		
	}
}
