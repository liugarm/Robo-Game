import java.util.Map;
import java.util.Scanner;

public interface Condition {

	public boolean evaluate(Robot r,Map<Variable,Expression> variables);
	public boolean parse(Scanner s);

}
