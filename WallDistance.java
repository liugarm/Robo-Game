import java.util.Map;



public class WallDistance implements Sensors, Expression{

	public int evaluate(Robot r, Map<Variable, Expression> variables) {
		return r.getDistanceToWall();
	}

	public String toString(){
		return "wallDist";
	}

}
