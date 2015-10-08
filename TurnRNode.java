import java.util.Map;
import java.util.Scanner;


public class TurnRNode implements RobotProgramNode{

	@Override
	public void execute(Robot robot, Map<Variable, Expression> variables) {
		robot.turnRight();
	}

	public String toString(){
		return "turnR";
	}
}
