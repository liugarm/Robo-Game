import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BlockNode implements RobotProgramNode {

	private List<RobotProgramNode> statements = new ArrayList<RobotProgramNode>();

	@Override
	public void execute(Robot robot, Map<Variable, Expression> variables) {
		//System.out.println("BlockNode: Execute");
		for (RobotProgramNode stmt : statements) {
			//System.out.println("BlockNode Performing: "+stmt.toString());
			stmt.execute(robot,variables);
		}
	}

	public Boolean parse(Scanner s) {
		//System.out.println("Block Node: Parse method");

		while (s.hasNext()) {
			//System.out.println("Block Node: Iterating Through");
			if(s.hasNext("}")){
				//maybe add s.next here..
				//System.out.println("Blocknode: } Found Ending");
				return true;
			}

			if(!s.hasNext("\\)|\\;|\\(|\\,")){
				StatementNode state = new StatementNode();
				state.parse(s);
				statements.add(state);

				//System.out.println("BlockNode: Added To Statements");
			}

			if(s.hasNext()){
				//System.out.println("BlockNode: Checking if scanner has next...");

				if(s.hasNext("\\,|\\;|\\)|\\(|\\{")){
					//System.out.println("BlockNode: The next read: "+s.next());
					String temp = s.next();
					
					if(temp.equals(";")){
						if(s.hasNext("\\{")){
							Parser.fail("Incorrect {",s);
						}
					}
				}
			}
			else{
				//System.out.println("BlockNode: Scanner does not have next. Returning true to end");
				return true;
			}
		}
		
		if(!s.hasNext("\\}")){
			Parser.fail("Expected }",s);
		}
		
		if(statements.size()<1){
			Parser.fail("Block statements empty.",s);
		}

		//if (statements.size() > 1) {
			//System.out.println("BlockNode: Success");
			return true;
		//}
		//System.out.println("BlockNode: Fail");
		//return false;
	}

	public String toString() {
		String string = "";
		for (RobotProgramNode robot : statements) {
			string += "" + robot.toString();
		}
		return string;
	}
}
