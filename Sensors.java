import java.util.Map;



public interface Sensors {

	public int evaluate(Robot r, Map<Variable, Expression> variables);

}
