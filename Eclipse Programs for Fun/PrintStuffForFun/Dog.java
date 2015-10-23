
public class Dog extends Animal {
	private int fleas;
	public Dog(int years, int fleas) {
		age = years;
		this.fleas = fleas;
	}
	public String makeNoise() {
		return "Bark!";
	}
	public void multiplyFleas() {
		fleas = multiply(fleas, age);
	}
	private int multiply(int num, int num2) {
		return num * num2;
	}
	public int getNumberOfFleas() {
		return fleas;
	}
	public String toString() {
		return "Dog: " + super.toString() + " \nFleas: " + getNumberOfFleas();
	}
}
