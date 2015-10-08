import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ProgramNode implements RobotProgramNode {

	private List<RobotProgramNode> statements = new ArrayList<RobotProgramNode>();
	private Map<Variable, Expression> variables = new HashMap<Variable, Expression>();

	@Override
	public void execute(Robot robot, Map<Variable, Expression> variables) {
		for (RobotProgramNode stmt : statements) {
			//System.out.println("ProgramNode Performing: " + stmt.toString());
			stmt.execute(robot,this.variables);
		}
	}

	public Boolean parse(Scanner s) {
		while (s.hasNext()) {
			//System.out.println("ProgramNode: Looping Around");

			while (Parser.gobble(Parser.OPENPAREN, s) || Parser.gobble(Parser.CLOSEPAREN, s) || Parser.gobble(Parser.OPENBRACE, s) || Parser.gobble(Parser.CLOSEBRACE, s) || Parser.gobble(Parser.COMMA, s)) {

				Parser.gobble(Parser.OPENPAREN, s);
				Parser.gobble(Parser.CLOSEPAREN, s);
				Parser.gobble(Parser.OPENBRACE, s);
				Parser.gobble(Parser.CLOSEBRACE, s);
				Parser.gobble(Parser.COMMA, s);
			}

			if (s.hasNext()) {
				StatementNode state = new StatementNode();
				if (!state.parse(s)) {
					//System.out.println("ProgramNode: Statement Failed");
					return false;
				}
				//System.out.println("ProgramNode: Adding to statements");
				statements.add(state);
			}

			//System.out.println("PROGRAM NODE: CHECKING IF HAS NEXT");
			if (s.hasNext()) {
				//System.out.println("PROGRAM NODE: HAS NEXT");
				if (s.hasNext("\\}|\\;")) {
					//System.out.println("PROGRAM NODE NEXT: " + s.next());
					s.next();
				}
			}
		}

		return true;
	}

	public String toString() {
		String string = "";
		for (RobotProgramNode r : statements) {
			string += r;
		}
		return string;
	}

}
