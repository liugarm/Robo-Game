import java.util.Map;
import java.util.Scanner;


public class ShieldOffNode implements RobotProgramNode{

	@Override
	public void execute(Robot robot, Map<Variable, Expression> variables) {
		robot.takeFuel();
	}

	public String toString(){
		return "shieldOff";
	}

}
