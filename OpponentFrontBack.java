import java.util.Map;



public class OpponentFrontBack implements Sensors, Expression{

	public int evaluate(Robot r, Map<Variable, Expression> variables){
		//System.out.println("OpponentFrontBack: Returning "+r.getOpponentFB());
		return r.getOpponentFB();
	}

	public String toString(){
		return "oppFB";
	}
}
