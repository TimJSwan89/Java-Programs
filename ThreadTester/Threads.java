public class Threads {
    public static void main(String[] args) {
        Thread num1 = new Thread();
        Thread num2 = new Thread();
        num1.run();
        Thread.this.interrupt();
    }
}