import java.util.Map;
import java.util.Scanner;

public class ActionNode implements RobotProgramNode {

	private RobotProgramNode node;

	@Override
	public void execute(Robot robot, Map<Variable, Expression> variables) {
		//System.out.println("ActionNode Performing: "+node.toString());
		//if (node != null) {
			node.execute(robot,variables);
		//}
	}

	public Boolean parse(Scanner s) {
		while (Parser.gobble(Parser.OPENPAREN, s) || Parser.gobble(Parser.CLOSEPAREN, s) ||
				Parser.gobble(Parser.OPENBRACE, s) || Parser.gobble(Parser.CLOSEBRACE, s) || Parser.gobble(Parser.COMMA, s)) {

			Parser.gobble(Parser.OPENPAREN, s);
			Parser.gobble(Parser.CLOSEPAREN, s);
			Parser.gobble(Parser.OPENBRACE, s);
			Parser.gobble(Parser.CLOSEBRACE, s);
			Parser.gobble(Parser.COMMA, s);
		}

		if (s.hasNext("move")) {
			MoveNode move = new MoveNode();
			node = move;

			//System.out.println("ActionNode: Next: "+s.next());
			s.next();

			//System.out.println("ActionNode: Checking after Move");
			if(s.hasNext("\\(")){
				//System.out.println("ActionNode: Found (");
				//System.out.println("ActionNode: Found "+s.next());
				s.next();

				if(s.hasNext("-?[1-9][0-9]*|0")){
					//System.out.println("ActionNode: Number found");
					move.setExpression(new Number(s.nextInt()));
					//System.out.println("ActionNode: Number successfully set onto Move");
				}
				else if(s.hasNext("add")){
					//System.out.println("ActionNode: Found Add");
					AdditionOperator add = new AdditionOperator();
					add.parse(s);
					move.setExpression(add);
					//System.out.println("ActionNode: NEXT: "+s.next());
					s.next();
				}
				else if(s.hasNext("sub")){
					//System.out.println("ActionNode: Found Sub");
					SubtractionOperator sub = new SubtractionOperator();
					sub.parse(s);
					move.setExpression(sub);
					//System.out.println("ActionNode: NEXT: "+s.next());
					s.next();
				}
				else if(s.hasNext("div")){
					//System.out.println("ActionNode: Found Div");
					DivisionOperator div = new DivisionOperator();
					div.parse(s);
					move.setExpression(div);
					//System.out.println("ActionNode: NEXT: "+s.next());
					s.next();
				}
				else if(s.hasNext("mul")){
					//System.out.println("ActionNode: Found Mul");
					MultiplicationOperator mul = new MultiplicationOperator();
					mul.parse(s);
					move.setExpression(mul);
					//System.out.println("ActionNode: NEXT: "+s.next());
					s.next();
				}
				else if(s.hasNext("fuelLeft")){
					//System.out.println("ActionNode: Found fuelLeft");
					FuelLeft fuelLeft = new FuelLeft();
					move.setExpression(fuelLeft);
					//System.out.println("ActionNode: NEXT: "+s.next());
					s.next();
				}
				else if(s.hasNext("oppLR")){
					//System.out.println("ActionNode: Found oppLR");
					OpponentLeftRight oppLR = new OpponentLeftRight();
					move.setExpression(oppLR);
					//System.out.println("ActionNode: NEXT: "+s.next());
					s.next();
				}
				else if(s.hasNext("oppFB")){
					//System.out.println("ActionNode: Found oppFB");
					OpponentFrontBack oppFB = new OpponentFrontBack();
					move.setExpression(oppFB);
					//System.out.println("ActionNode: NEXT: "+s.next());
					s.next();
				}
				else if(s.hasNext("numBarrels")){
					//System.out.println("ActionNode: Found numBarrels");
					NumberBarrels numBarrels = new NumberBarrels();
					move.setExpression(numBarrels);
					//System.out.println("ActionNode: NEXT: "+s.next());
					s.next();
				}
				else if(s.hasNext("barrelLR")){
					//System.out.println("ActionNode: Found barrelLR");
					BarrelLeftRight barrelLR = new BarrelLeftRight();
					barrelLR.parse(s);
					move.setExpression(barrelLR);
					//System.out.println("ActionNode: NEXT: "+s.next());
					s.next();
				}
				else if(s.hasNext("barrelFB")){
					//System.out.println("ActionNode: Found barrelFB");
					BarrelFrontBack barrelFB = new BarrelFrontBack();
					barrelFB.parse(s);
					move.setExpression(barrelFB);
					//System.out.println("ActionNode: NEXT: "+s.next());
					s.next();
				}
				else if(s.hasNext("wallDist")){
					//System.out.println("ActionNode: Found wallDist");
					WallDistance wallDist = new WallDistance();
					move.setExpression(wallDist);
					//System.out.println("ActionNode: NEXT: "+s.next());
					s.next();
				}
				else if(s.hasNext("\\$[A-Za-z][A-Za-z0-9]*")){
					//System.out.println("ActionNode: Found Assignment Letter/Number");
					AssignmentNode assig = new AssignmentNode();
					assig.parse(s);
					move.setExpression(assig);
					//System.out.println("ActionNode: NEXT: " + s.next());
					s.next();
				}
			}
			else{
				//System.out.println("ActionNode : Move: Went into the else but nothing happens here");
				//System.out.println("ActionNode: Next: "+s.next());
			}

		}
		if (s.hasNext("turnL")) {
			TurnLNode turnLeft = new TurnLNode();
			node = turnLeft;
			//System.out.println("ActionNode: "+s.next());
			s.next();

		}
		if (s.hasNext("turnR")) {
			TurnRNode turnRight = new TurnRNode();
			node = turnRight;
			//System.out.println("ActionNode: "+s.next());
			s.next();
			
			if(!s.hasNext("\\;")){
				Parser.fail("Expected ;", s);
			}

		}
		if (s.hasNext("wait")) {
			WaitNode wait = new WaitNode();
			node = wait;

			//System.out.println("ActionNode: Next: "+s.next());
			s.next();

			//System.out.println("ActionNode: Checking after Wait");
			if(s.hasNext("\\(")){
				//System.out.println("ActionNode: Found (");
				//System.out.println("ActionNode: Found "+s.next());
				s.next();

				if(s.hasNext("-?[1-9][0-9]*|0")){
					//System.out.println("ActionNode: Number found");
					wait.setExpression(new Number(s.nextInt()));
					//System.out.println("ActionNode: Number successfully set onto Wait");
				}
				else if(s.hasNext("add")){
					//System.out.println("ActionNode: Found Add");
					AdditionOperator add = new AdditionOperator();
					add.parse(s);
					wait.setExpression(add);
					//System.out.println("ActionNode: NEXT: "+s.next());
					s.next();
				}
				else if(s.hasNext("sub")){
					//System.out.println("ActionNode: Found Sub");
					SubtractionOperator sub = new SubtractionOperator();
					sub.parse(s);
					wait.setExpression(sub);
					//System.out.println("ActionNode: NEXT: "+s.next());
					s.next();
				}
				else if(s.hasNext("div")){
					//System.out.println("ActionNode: Found Div");
					DivisionOperator div = new DivisionOperator();
					div.parse(s);
					wait.setExpression(div);
					//System.out.println("ActionNode: NEXT: "+s.next());
					s.next();
				}
				else if(s.hasNext("mul")){
					//System.out.println("ActionNode: Found Mul");
					MultiplicationOperator mul = new MultiplicationOperator();
					mul.parse(s);
					wait.setExpression(mul);
					//System.out.println("ActionNode: NEXT: "+s.next());
					s.next();
				}
				else if(s.hasNext("fuelLeft")){
					//System.out.println("ActionNode: Found fuelLeft");
					FuelLeft fuelLeft = new FuelLeft();
					wait.setExpression(fuelLeft);
					//System.out.println("ActionNode: NEXT: "+s.next());
					s.next();
				}
				else if(s.hasNext("oppLR")){
					//System.out.println("ActionNode: Found oppLR");
					OpponentLeftRight oppLR = new OpponentLeftRight();
					wait.setExpression(oppLR);
					//System.out.println("ActionNode: NEXT: "+s.next());
					s.next();
				}
				else if(s.hasNext("oppFB")){
					//System.out.println("ActionNode: Found oppFB");
					OpponentFrontBack oppFB = new OpponentFrontBack();
					wait.setExpression(oppFB);
					//System.out.println("ActionNode: NEXT: "+s.next());
					s.next();
				}
				else if(s.hasNext("numBarrels")){
					//System.out.println("ActionNode: Found numBarrels");
					NumberBarrels numBarrels = new NumberBarrels();
					wait.setExpression(numBarrels);
					//System.out.println("ActionNode: NEXT: "+s.next());
					s.next();
				}
				else if(s.hasNext("barrelLR")){
					//System.out.println("ActionNode: Found barrelLR");
					BarrelLeftRight barrelLR = new BarrelLeftRight();
					wait.setExpression(barrelLR);
					//System.out.println("ActionNode: NEXT: "+s.next());
					s.next();
				}
				else if(s.hasNext("barrelFB")){
					//System.out.println("ActionNode: Found barrelFB");
					BarrelFrontBack barrelFB = new BarrelFrontBack();
					wait.setExpression(barrelFB);
					//System.out.println("ActionNode: NEXT: "+s.next());
					s.next();
				}
				else if(s.hasNext("wallDist")){
					//System.out.println("ActionNode: Found wallDist");
					WallDistance wallDist = new WallDistance();
					wait.setExpression(wallDist);
					//System.out.println("ActionNode: NEXT: "+s.next());
					s.next();
				}
				else if(s.hasNext("\\$[A-Za-z][A-Za-z0-9]*")){
					//System.out.println("ActionNode: Found Assignment Letter/Number");
					AssignmentNode assig = new AssignmentNode();
					assig.parse(s);
					wait.setExpression(assig);
					//System.out.println("ActionNode: NEXT: " + s.next());
					s.next();
				}
			}
			else{
				//System.out.println("ActionNode: Next: "+s.next());
				//System.out.println("ActionNode : Wait: Went into the else but nothing happens here");
			}
		}
		if (s.hasNext("takeFuel")) {
			TakeFuelNode takeFuel = new TakeFuelNode();
			node = takeFuel;
			//System.out.println("ActionNode: "+s.next());
			s.next();
		}
		if (s.hasNext("turnAround")) {
			TurnAroundNode TANode = new TurnAroundNode();
			node = TANode;
			//System.out.println("ActionNode: "+s.next());
			s.next();
		}
		if (s.hasNext("shieldOn")) {
			ShieldOnNode shieldOn = new ShieldOnNode();
			node = shieldOn;
			//System.out.println("ActionNode: "+s.next());
			s.next();
		}
		if (s.hasNext("shieldOff")) {
			ShieldOnNode shieldOff = new ShieldOnNode();
			node = shieldOff;
			//System.out.println("ActionNode: "+s.next());
			s.next();
		}

		if (node == null) {
			//System.out.println("ActionNode: Node was null returning false");
			Parser.fail("Node was empty.", s);
			return false;
		}

		//System.out.println("ActionNode: Node was NOT null. Returning true");
		return true;
	}

	public String toString() {
		if(node!=null){
			return ""+node;
		}
		return "";
	}

}
