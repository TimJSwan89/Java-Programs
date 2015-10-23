public class Drive {
    public static void main() {
        System.out.println("Memory Test:");
        for(int i = 0; i < 1000000; i++) {
            MemoryTest mem = new MemoryTest(null);
            MemoryTest mum = new MemoryTest(mem);
            mem.changeMother(mum);
        }
    }
}