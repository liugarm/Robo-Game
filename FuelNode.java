import java.util.Map;
import java.util.Scanner;


public class FuelNode implements RobotProgramNode{

	@Override
	public void execute(Robot robot, Map<Variable, Expression> variables) {
		robot.takeFuel();
		return;
	}

	public Boolean parse(Scanner s) {
		// TODO Auto-generated method stub

		return null;
	}

	public String toString(){
		return "FuelNode";
	}

}
