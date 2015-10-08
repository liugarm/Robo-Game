import java.util.Map;



public class OpponentLeftRight implements Sensors, Expression{

	public int evaluate(Robot r, Map<Variable, Expression> variables){
		//System.out.println("OpponentLeftRight: Returning "+r.getOpponentLR());
		return r.getOpponentLR();
	}

	public String toString(){
		return "oppLR";
	}

}
