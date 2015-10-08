import java.util.Map;




public class FuelLeft implements Sensors, Expression{

	public int evaluate(Robot robot, Map<Variable,Expression> variables) {
		return robot.getFuel();
	}

	public String toString(){
		return "fuelLeft";
	}

}
