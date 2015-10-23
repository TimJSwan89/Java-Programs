
public class GermanShepherd extends Dog {
	private int groomings;
	public GermanShepherd (int age, int fleas, int grooms) {
		super(age, fleas);
		groomings = grooms;
	}
	public String makeNoise() {
		return "Woof!";
	}
	public int getNumOfGrooms() {
		return groomings;
	}
	public void goGetAGroom() {
		changeGrooms(getNumOfGrooms() + 1);
	}
	private void changeGrooms(int newNumber) {
		groomings = newNumber;
	}
	public String toString() {
		return "German Shepherd: " + super.toString() + " \nGrooms: " +
				getNumOfGrooms();
	}
}
