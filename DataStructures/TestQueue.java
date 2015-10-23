public class TestQueue {
    public static void main(String[] args) {
        Queue bob = new Queue();
        bob.add(5);
        bob.add("poop");
        System.out.println(bob.peek());
        System.out.println(bob.remove());
        System.out.println(bob.remove());
    }
}