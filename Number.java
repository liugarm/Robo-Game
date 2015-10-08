

public class Number implements Expression{

	private int number;

	public Number(int number){
		this.number = number;
	}

	public int getNumber(){
		return number;
	}

	public String toString(){
		return ""+number;
	}

}
