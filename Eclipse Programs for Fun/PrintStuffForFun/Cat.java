
public class Cat extends Animal {
	private String favoriteToy;
	public Cat(int years, String toy) {
		age = years;
		favoriteToy = toy;
	}
	public String makeNoise() {
		return "Meow!";
	}
	public void changeToy (String newToy) {
		favoriteToy = newToy;
	}
	public String getToy() {
		return favoriteToy;
	}
	public String exchangeToy(String differentToy) {
		changeToy(differentToy);
		return getToy();
	}
	public String toString() {
		return "Cat: " + super.toString() + " \nToy: " + getToy();
	}
}
