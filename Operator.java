import java.util.Map;
import java.util.Scanner;


public interface Operator {

	public boolean parse(Scanner s);
	public int evaluate(Robot robot, Map<Variable, Expression> variables);

}
