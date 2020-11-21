package bag;

public class Bag {
	private String name;
	private int sauce;
	private int mushroom;
	private int paprika;
	private int onion;
	private int pepperoni;
	private int cheese;
	
	public Bag(String name) {
		this.name = name;
		sauce = 0;
		mushroom = 0;
		paprika = 0;
		onion = 0;
		pepperoni = 0;
		cheese = 0;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSauce() {
		return sauce;
	}
	public void setSauce(int sauce) {
		this.sauce = sauce;
	}
	public int getMushroom() {
		return mushroom;
	}
	public void setMushroom(int mushroom) {
		this.mushroom = mushroom;
	}
	public int getPaprika() {
		return paprika;
	}
	public void setPaprika(int paprika) {
		this.paprika = paprika;
	}
	public int getOnion() {
		return onion;
	}
	public void setOnion(int onion) {
		this.onion = onion;
	}
	public int getPepperoni() {
		return pepperoni;
	}
	public void setPepperoni(int pepperoni) {
		this.pepperoni = pepperoni;
	}	
}