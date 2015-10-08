import java.util.Map;



public class NumberBarrels implements Sensors, Expression{

	public int evaluate(Robot r, Map<Variable, Expression> variables){
		return r.numBarrels();
	}

	public String toString(){
		return "numBarrels";
	}

}
