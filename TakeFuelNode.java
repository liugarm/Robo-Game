import java.util.Map;


public class TakeFuelNode implements RobotProgramNode{

	@Override
	public void execute(Robot robot, Map<Variable, Expression> variables) {
		//System.out.println("TakeFuelNode: Picked up Fuel Barrel");
		robot.takeFuel();
	}

	public String toString(){
		return "takeFuel";
	}

}
