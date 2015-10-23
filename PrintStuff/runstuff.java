public class runstuff extends Thread {
    runstuff other;
    int p;
    boolean wait;
//     static runstuff s;
    static String s;
    public runstuff(runstuff otherOne, int p) {
        other = otherOne;
        this.p = p;
    }
    public void run() {
        method(p);
    }
    public static void main() {
        System.out.println("oo");
//         s = new runstuff(null, 0);
        s = "blob";
        runstuff bob = new runstuff(null, 1);
        Thread bobby = bob;
        
        runstuff jerry = new runstuff(bob, -1);
        Thread jerrie = jerry;
        
        bob.other = jerry;
        
        bobby.start();
        jerrie.start();
        w(0);
//         for(int i = 0; i < 10000; i++) { 
//             other.notify();
//             Thread.currentThread().wait();
//             System.out.println(i);
//         }
    }
    public synchronized void method(int j) {
        for(int i = 0; i < 10000; i++) {
            synchronized (s) {
                try {
                    s.notify();
                    s.wait();
                } catch (InterruptedException e) {
                    System.out.println("...");
                }
            }
            System.out.println(i * j);
        }
    }
    public static void w(long time) {
        try {
            Thread.currentThread().sleep(time);
        } catch (InterruptedException e) {
        }
    }
}