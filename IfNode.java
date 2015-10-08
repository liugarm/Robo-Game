import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class IfNode implements RobotProgramNode {

	//If
	private RobotProgramNode block;
	private ConditionNode conditionNode;

	//Else If
	private List<BlockNode> elseIfBlocks = new ArrayList<BlockNode>();
	private List<ConditionNode> elseIfConditions = new ArrayList<ConditionNode>();

	//Else
	private RobotProgramNode elseBlock;


	@Override
	public void execute(Robot robot, Map<Variable, Expression> variables) {
		//System.out.println("IfNode Performing: ConditionNode Evaluate");
		//System.out.println("IfNode Performing: Condition: "+conditionNode.toString());

		boolean elseIfFailed = true;

		if (conditionNode.evaluate(robot,variables)) {
			//System.out.println("IfNode Performing: Condition Evaluate If True");
			block.execute(robot,variables);
			elseIfFailed = false;
		}
		else if(elseIfBlocks!=null){
			//System.out.println("IfNode Performing: Evaluating Else If's ... Size: "+elseIfBlocks.size());

			for(int i=0;i<elseIfBlocks.size();i++){
				//System.out.println("IfNode Performing: Iterating through else if's");
				if(elseIfConditions.get(i).evaluate(robot,variables)){
					//System.out.println("IfNode Performing: Else If No."+i+" true. Executing block.");
					elseIfBlocks.get(i).execute(robot,variables);
					elseIfFailed = false;
					break;
				}
			}
		}

		if(elseIfFailed && elseBlock!=null){
			//System.out.println("IfNode Performing: Condition Evaluate Else True");
			elseBlock.execute(robot,variables);
		}
	}

	public Boolean parse(Scanner s) {
		//System.out.println("If Node: " + s.next());
		s.next();
		
		if(!s.hasNext("\\(")){
			Parser.fail("Expected (",s);
		}

		ConditionNode condition = new ConditionNode();
		condition.parse(s);
		conditionNode = condition;

		/*if(!s.hasNext("\\)")){
			Parser.fail("If expected )",s);
		}*/
		
		if(!s.hasNext("if")){
			//System.out.println("If Node:" +s.next());
			s.next();
		}

		BlockNode blockNode = new BlockNode();
		blockNode.parse(s);
		block = blockNode;

		if(!s.hasNext("\\}")){
			Parser.fail("Expected } at the end of if block.",s);
		}
		//System.out.println("If Node after block: "+s.next());
		s.next();

		while(s.hasNext("elif")){
			//System.out.println("If Node: Else If");
			//System.out.println("If Node: Sucking in Else If "+s.next());
			s.next();

			ConditionNode elseIfCond = new ConditionNode();
			elseIfCond.parse(s);
			elseIfConditions.add(elseIfCond);

			if(!s.hasNext("\\)")){
				Parser.fail("If Node expected ) after else if condition.",s);
			}

			//System.out.println("If Node: Else if: "+s.next());
			s.next();


			BlockNode elseIfBlck = new BlockNode();
			elseIfBlck.parse(s);
			elseIfBlocks.add(elseIfBlck);

			if(!s.hasNext("\\}")){
				Parser.fail("Expected } end of else if.",s);
			}
			//System.out.println("If Node: Sucking in bracket after else if: "+s.next());
			s.next();
		}

		if(s.hasNext("else")){
				//System.out.println("If Node: Else");
				//System.out.println("if Node: Sucking in else: "+s.next());
				s.next();

				BlockNode elseBlck = new BlockNode();
				elseBlck.parse(s);
				elseBlock = elseBlck;

				if(!s.hasNext("\\}")){
					Parser.fail("Expected } end of else. ",s);
				}
				//System.out.println("If Node: Sucking in bracket: "+s.next());
				s.next();
		}

		return true;
	}

	public String toString() {
		if(elseBlock!=null && elseIfBlocks!=null){
			String elseIf = "";

			for(int i=0;i<elseIfBlocks.size();i++){
				elseIf+="\nelif("+elseIfConditions.get(i).toString()+"){\n"+elseIfBlocks.get(i).toString()+"\n}";
			}

			return "\n\nif(" + conditionNode + ")" + "{\n" + block.toString() + "\n}\n"+elseIf+"\nelse{\n"+elseBlock.toString()+"\n}";
		}
		else if(elseIfBlocks==null && elseBlock!=null){
			return "\n\nif(" + conditionNode + ")" + "{\n" + block.toString() + "\n}\nelse{\n"+elseBlock.toString()+"\n}";
		}
		else{
			return "\n\nif(" + conditionNode + ")" + "{\n" + block.toString() + "\n}";
		}
	}

}
