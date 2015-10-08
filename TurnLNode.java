import java.util.Map;
import java.util.Scanner;


public class TurnLNode implements RobotProgramNode{

	@Override
	public void execute(Robot robot, Map<Variable, Expression> variables) {
		robot.turnLeft();
	}

	public String toString(){
		return "turnL";
	}

}
