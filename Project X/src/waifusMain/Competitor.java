package waifusMain;

public class Competitor {
	String name;
	String affilation;
	Boolean	active = true;
	
	public Competitor(String name, String affilation) {
		this.name = name;
		this.affilation = affilation;
	}
	
	void reset() {
		active = true;
	}
	
	void defeat() {
		active = false;
	}

	public void introduce() {
		System.out.println(this.name + "(" + this.affilation + ")");
	}
}
