import java.util.Map;


/**
 * Interface for all nodes that can be executed,
 * including the top level program node
 */

interface RobotProgramNode {
	public void execute(Robot robot, Map<Variable, Expression> variables);
}
