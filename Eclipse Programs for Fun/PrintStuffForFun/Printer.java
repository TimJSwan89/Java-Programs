
public class Printer {
	static Animal[] pets;
	public static void main(String[] args) {
		pets = new Animal[4];
		
		Dog dog1 = new Dog(5, 2);
		System.out.println("First dog makes this noise:");
		System.out.println(dog1.makeNoise() + "\n");
		
		Cat kitty1 = new Cat(3, "Scratching Post");
		System.out.println("Cat's favorite toy:");
		System.out.println(kitty1.getToy() + "\n");
		
		GermanShepherd dog2 = new GermanShepherd(6, 3, 34);
		System.out.println("German shepherd got groomed " +
				dog2.getNumOfGrooms() + " times.\n");
		
		pets[0] = dog1;
		pets[1] = kitty1;
		pets[2] = dog2;
		pets[3] = new Cat(4, "Fur Brush");
		System.out.println(pets[3].toString() + "\n");
		printAnimals();
	}
	public static void printAnimals() {
		for(int i = 0; i < pets.length; i++)
			System.out.println(pets[i] + "\n");
	}
	public void groomShepherdNumberOfTimes(GermanShepherd doggy, int num) {
		for(int i = 0; i < num; i++)
			doggy.goGetAGroom();
	}
	public void groomShepherdNumberOfTimes(int num, GermanShepherd doggy) {
		if (num > 0) {
			groomShepherdNumberOfTimes(num - 1, doggy);
			doggy.goGetAGroom();
		} else
			return;
	}
}
