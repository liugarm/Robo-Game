import java.util.Map;
import java.util.Scanner;


public class TurnAroundNode implements RobotProgramNode{

	@Override
	public void execute(Robot robot, Map<Variable, Expression> variables) {
		robot.turnAround();
	}

	public String toString(){
		return "turnAround";
	}
}
