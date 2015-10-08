import java.util.Map;
import java.util.Scanner;

public class WhileNode implements RobotProgramNode {

	private RobotProgramNode block;
	private ConditionNode conditionNode;

	@Override
	public void execute(Robot robot, Map<Variable, Expression> variables) {
		//System.out.println("WhileNode Performing: While ConditionNode.evaluate(robot)");
		//System.out.println("WhileNode Performing: "+block.toString());
		while (conditionNode.evaluate(robot,variables)) {
			//System.out.println("WhileNode Iterating");
			block.execute(robot,variables);
		}
		//System.out.println("WhileNode: Ended");
	}

	public Boolean parse(Scanner s) {
		//System.out.println("WhileNode: Parse");


		//System.out.println("WhileNode: " + s.next());
		s.next();

		ConditionNode condition = new ConditionNode();
		condition.parse(s);
		conditionNode = condition;

		//System.out.println("While Node:"+s.next());
		s.next();

		BlockNode blockNode = new BlockNode();
		blockNode.parse(s);
		block = blockNode;

		//System.out.println("WhileNode: Complete. Returning True");

		if(!s.hasNext("\\}")){
			Parser.fail("While Node: Expected } at the end of block.",s);
		}

		//System.out.println("WhileNode: Sucking in bracket: "+s.next());
		s.next();
		//System.out.println("WhileNode contents: \n"+block.toString()+"\n");

		return true;
	}

	public String toString() {
		return "\nwhile(" + conditionNode + ")"+"{\n"+block.toString()+"\n}";
		//return "while(" + conditionNode + ")";
	}
}
