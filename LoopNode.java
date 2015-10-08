import java.util.Map;
import java.util.Scanner;

public class LoopNode implements RobotProgramNode {

	private BlockNode block;

	@Override
	public void execute(Robot robot, Map<Variable, Expression> variables) {
		//System.out.println("Loop Performing: While (true)");
		while (true) {
			block.execute(robot,variables);
		}
	}

	public String toString() {
		return "\nloop{\n" + block.toString() + "\n}";
	}

	public Boolean parse(Scanner s) {
		s.next();

		while (Parser.gobble(Parser.OPENPAREN, s) || Parser.gobble(Parser.CLOSEPAREN, s) || Parser.gobble(Parser.OPENBRACE, s) || Parser.gobble(Parser.CLOSEBRACE, s) || Parser.gobble(Parser.COMMA, s)) {

			Parser.gobble(Parser.OPENPAREN, s);
			Parser.gobble(Parser.CLOSEPAREN, s);
			Parser.gobble(Parser.OPENBRACE, s);
			Parser.gobble(Parser.CLOSEBRACE, s);
			Parser.gobble(Parser.COMMA, s);
		}

		BlockNode blockNode = new BlockNode();
		if (!blockNode.parse(s)) {
			return false;
		}
		//blockNode.parse(s);
		//System.out.println("Loop: Finished Parsing Block");
		block = blockNode;

		return true;
	}

}
