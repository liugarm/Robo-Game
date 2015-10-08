import java.util.Map;
import java.util.Scanner;

public class StatementNode implements RobotProgramNode {

	private RobotProgramNode node;

	@Override
	public void execute(Robot robot, Map<Variable, Expression> variables) {
		//System.out.println("Statement Node Performing: Node Execute");
		//System.out.println("Statement Node Performing: \n" + node.toString() + "\n");
		node.execute(robot,variables);
	}

	public Boolean parse(Scanner s) {

		//System.out.println("StatementNode: Parse");

		while (Parser.gobble(Parser.OPENPAREN, s) || Parser.gobble(Parser.CLOSEPAREN, s) || Parser.gobble(Parser.OPENBRACE, s) || Parser.gobble(Parser.CLOSEBRACE, s) || Parser.gobble(Parser.COMMA, s)) {

			Parser.gobble(Parser.OPENPAREN, s);
			Parser.gobble(Parser.CLOSEPAREN, s);
			Parser.gobble(Parser.OPENBRACE, s);
			Parser.gobble(Parser.CLOSEBRACE, s);
			Parser.gobble(Parser.COMMA, s);
		}

		if (!s.hasNext("move|turnL|turnR|turnAround|shieldOn|shieldOff|takeFuel|wait|loop|if|while|else|\\$[A-Za-z][A-Za-z0-9]*")) {
			//System.out.println("StatementNode: NEXT: " + s.next());
			s.next();
		}

		if (s.hasNext("move|turnL|turnR|turnAround|shieldOn|shieldOff|takeFuel|wait")) {
			//System.out.println("Statement Node: Action");
			ActionNode action = new ActionNode();

			while (Parser.gobble(Parser.OPENPAREN, s) || Parser.gobble(Parser.CLOSEPAREN, s) || Parser.gobble(Parser.OPENBRACE, s) || Parser.gobble(Parser.CLOSEBRACE, s) || Parser.gobble(Parser.COMMA, s)) {

				Parser.gobble(Parser.OPENPAREN, s);
				Parser.gobble(Parser.CLOSEPAREN, s);
				Parser.gobble(Parser.OPENBRACE, s);
				Parser.gobble(Parser.CLOSEBRACE, s);
				Parser.gobble(Parser.COMMA, s);
			}

			if (!action.parse(s)) {
				//System.out.println("Statement Node: ACTION Failed");
				Parser.fail("Statement Node: Action Node failed.",s);
				return false;
			}
			
			//System.out.println("Statement Node: ACTION Success");
			node = action;
		} else if (s.hasNext("loop")) {
			//System.out.println("Statement Node: Loop");
			LoopNode loopNode = new LoopNode();

			while (Parser.gobble(Parser.OPENPAREN, s) || Parser.gobble(Parser.CLOSEPAREN, s) || Parser.gobble(Parser.OPENBRACE, s) || Parser.gobble(Parser.CLOSEBRACE, s) || Parser.gobble(Parser.COMMA, s)) {

				Parser.gobble(Parser.OPENPAREN, s);
				Parser.gobble(Parser.CLOSEPAREN, s);
				Parser.gobble(Parser.OPENBRACE, s);
				Parser.gobble(Parser.CLOSEBRACE, s);
				Parser.gobble(Parser.COMMA, s);
			}

			if (!loopNode.parse(s)) {
				//System.out.println("Statement Node: LOOP Failed");
				Parser.fail("Statement Node: Loop Node failed.",s);
				return false;
			}
			//System.out.println("Statement Node: LOOP Success");
			node = loopNode;
		} else if (s.hasNext("if")) {
			//System.out.println("Statement Node: If");

			IfNode ifNde = new IfNode();

			while (Parser.gobble(Parser.OPENPAREN, s) || Parser.gobble(Parser.CLOSEPAREN, s) || Parser.gobble(Parser.OPENBRACE, s) || Parser.gobble(Parser.CLOSEBRACE, s) || Parser.gobble(Parser.COMMA, s)) {

				Parser.gobble(Parser.OPENPAREN, s);
				Parser.gobble(Parser.CLOSEPAREN, s);
				Parser.gobble(Parser.OPENBRACE, s);
				Parser.gobble(Parser.CLOSEBRACE, s);
				Parser.gobble(Parser.COMMA, s);
			}

			if (!ifNde.parse(s)) {
				//System.out.println("Statement Node: IF Failed");
				Parser.fail("Statement Node: If failed.",s);
				return false;
			}
			//System.out.println("Statement Node: IF Success");
			node = ifNde;
		} else if (s.hasNext("while")) {
			//System.out.println("Statement Node: While");

			WhileNode whileNde = new WhileNode();

			while (Parser.gobble(Parser.OPENPAREN, s) || Parser.gobble(Parser.CLOSEPAREN, s) || Parser.gobble(Parser.OPENBRACE, s) || Parser.gobble(Parser.CLOSEBRACE, s) || Parser.gobble(Parser.COMMA, s)) {

				Parser.gobble(Parser.OPENPAREN, s);
				Parser.gobble(Parser.CLOSEPAREN, s);
				Parser.gobble(Parser.OPENBRACE, s);
				Parser.gobble(Parser.CLOSEBRACE, s);
				Parser.gobble(Parser.COMMA, s);
			}

			if (!whileNde.parse(s)) {
				//System.out.println("Statement Node: WHILE Failed");
				Parser.fail("Statement Node: While failed",s);
				return false;
			}
			//System.out.println("Statement Node: WHILE Success");
			node = whileNde;
		}
		else if(s.hasNext("\\$[A-Za-z][A-Za-z0-9]*")){
			//System.out.println("Statement Node: Assignment Letter/Numbers");

			AssignmentNode assig = new AssignmentNode();

			while (Parser.gobble(Parser.OPENPAREN, s) || Parser.gobble(Parser.CLOSEPAREN, s) || Parser.gobble(Parser.OPENBRACE, s) || Parser.gobble(Parser.CLOSEBRACE, s) || Parser.gobble(Parser.COMMA, s)) {

				Parser.gobble(Parser.OPENPAREN, s);
				Parser.gobble(Parser.CLOSEPAREN, s);
				Parser.gobble(Parser.OPENBRACE, s);
				Parser.gobble(Parser.CLOSEBRACE, s);
				Parser.gobble(Parser.COMMA, s);
			}

			/*if(!assig.parse(s)){
				System.out.println("Statement Node: Assignment False");
				return false;
			}*/
			boolean temp = assig.parse(s);

			//System.out.println("Statement Node: Assignment Success");
			if(temp){
				node = assig;
			}
		}

		if (node == null) {
			//System.out.println("Statement Node: Returning FALSE");
			Parser.fail("Statement Node failed. Node was null.",s);

			//System.out.println("Statement Node: Next was: "+s.next());
			//System.out.println("Statement Node: Next was: "+s.next());
			s.next();
			s.next();


			return false;
		}

		return true;
	}

	public String toString() {
		if (node != null) {
			return "" + node;
		}
		return "";
	}

}
