public class MemoryTest {
    int[][][] bob = new int[500][500][100];
    MemoryTest mother;
    public MemoryTest(MemoryTest memTest) {
        mother = memTest;
    }
    public void changeMother(MemoryTest inMom) {
        mother = inMom;
    }
}