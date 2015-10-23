
public abstract class Animal {
	protected int age;
	public abstract String makeNoise();
	public int getAge() {
		return age;
	}
	public String toString() {
		return "Animal \nAge: " + getAge() + " \nNoise: " + makeNoise();
	}
}