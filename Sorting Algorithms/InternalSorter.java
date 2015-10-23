import java.util.Random;
public class InternalSorter {
    static Random rand;
    public static void main(String[] args) {
        System.out.println("\n");
        rand = new Random();
        try {
            Thread.currentThread().sleep(500);
        } catch (InterruptedException e) {
        }
        int[] list = randomize(5000000, -100, 1300);
        int[] list1 = randomize(30000);
        int[] list2 = copy(list1);
        int[] list3 = copy(list2);
        
        System.out.println("Start Selection");
        selectionSort(list1);
        System.out.println("End Selection");
        
        System.out.println("Start Insertion");
        insertionSort(list2);
        System.out.println("End Insertion");
        
        System.out.println("Start Quick");
        quickSort(list3);
        System.out.println("End Quick");
        
        System.out.println("Start PigeonHole");
        pigeonHoleSort(list, -100, 1300);
        System.out.println("End PigeonHole");
        
        string(list1, true);
    }
    public static void quickSort(int[] list) {
        quickSort(list, 0, list.length - 1);
    }
    public static void quickSort(int[] list, int low, int high) {
        if (low + 8 > high)
            insertionSort(list, low, high);
        else {
            int middle = (low + high) / 2;
            if(list[middle] < list[low])
                swapReferences(list, low, middle);
            if(list[high] < list[low])
                swapReferences(list, low, high);
            if(list[high] < list[middle])
                swapReferences(list, middle, high);
            swapReferences(list, middle, high - 1);
            int pivot = list[high - 1];
            int i, j;
            for(i = low, j = high - 1; ; ) {
                while(list[++i] < pivot);
                while(pivot < list[--j]);
                if (i >= j)
                    break;
                swapReferences(list, i, j);
            }
            swapReferences(list, i, high - 1);
            quickSort(list, low, i - 1);
            quickSort(list, i + 1, high);
        }
    }
//     public static void mergeSort
    public static void insertionSort(int[] list) {
        insertionSort(list, 0, list.length - 1);
    }
    public static void insertionSort(int[] list, int low, int high) {
        int j, k, current;
        for(j = low + 1; j <= high; j++) {
            current = list[j];
            for(k = j; k > low && list[k - 1] > current; k--)
                list[k] = list[k - 1];
            list[k] = current;
        }
    }
    public static void selectionSort(int[] list) {
        selectionSort(list, 0, list.length - 1);
    }
    public static void selectionSort(int[] list, int low, int high) {
        int i, min, index, j;
        for(i = low; i <= high; i++) {
            min = list[i];
            index = i;
            for(j = i; j <= high; j++)
                if (list[j] < min) {
                    min = list[j];
                    index = j;
                }
            swapReferences(list, i, index);
        }
    }
    public static void pigeonHoleSort(int[] list, int min, int max) {
        int[] counter = new int[max - min + 1];
        for(int i = 0; i < list.length; i++)
            counter[list[i] - min]++;
        int k = 0;
        for(int i = 0; i < counter.length; i++)
            for(int j = 0; j < counter[i]; j++) {
                list[k] = i + min;
                k++;
            }
    }
    public static void swapReferences(int[] list, int i, int j) {
        int temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }
    public static int[] randomize(int size) {
        int[] list = new int[size];
        for(int i = 0; i < size; i++)
            list[i] = rand.nextInt();
        return list;
    }
    public static int[] randomize(int size, int lowerBound, int upperBound) {
        int[] list = new int[size];
        for(int i = 0; i < size; i++) {
            list[i] = rand.nextInt(upperBound - lowerBound);
            list[i] += lowerBound;
        }
        return list;
    }
    public static String string(int[] list) {
        return string(list, false);
    }
    public static String string(int[] list, boolean printOnFly) {
        String string = "";
        for(int i = 0; i < list.length; i++) {
            String numString = Integer.toString(list[i]);
            while (numString.length() < 11)
                numString = " " + numString;
            numString = "\n" + numString;
            if (printOnFly)
                System.out.println(numString);
            string += numString;
        }
        return string;
    }
    public static int[] copy(int[] list) {
        int[] newList = new int[list.length];
        for(int i = 0; i < list.length; i++)
            newList[i] = list[i];
        return newList;
    }
}