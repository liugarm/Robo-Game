import java.util.Map;
import java.util.Scanner;

public class SensorNode implements RobotProgramNode {

	private Sensors node;

	@Override
	public void execute(Robot robot, Map<Variable, Expression> variables) {
		//if(node!=null){
		System.out.println("SensorNode Performing: Node evaluate");
		node.evaluate(robot,variables);
		//}
	}

	public Boolean parse(Scanner s) {

		while (Parser.gobble(Parser.OPENPAREN, s) || Parser.gobble(Parser.CLOSEPAREN, s) || Parser.gobble(Parser.OPENBRACE, s) || Parser.gobble(Parser.CLOSEBRACE, s) || Parser.gobble(Parser.COMMA, s)) {

			Parser.gobble(Parser.OPENPAREN, s);
			Parser.gobble(Parser.CLOSEPAREN, s);
			Parser.gobble(Parser.OPENBRACE, s);
			Parser.gobble(Parser.CLOSEBRACE, s);
			Parser.gobble(Parser.COMMA, s);
		}

		if (s.hasNext("fuelLeft")) {
			FuelLeft fuelLeft = new FuelLeft();
			node = fuelLeft;
			System.out.println("Sensor Node: Fuel left");
		}
		if (s.hasNext("oppLR")) {
			OpponentLeftRight oppLR = new OpponentLeftRight();
			node = oppLR;
			System.out.println("Sensor Node: oppLR");
		}
		if (s.hasNext("oppFB")) {
			OpponentFrontBack oppFB = new OpponentFrontBack();
			node = oppFB;
			System.out.println("Sensor Node: oppFB");
		}
		if (s.hasNext("numBarrels")) {
			NumberBarrels numBarrels = new NumberBarrels();
			node = numBarrels;
			System.out.println("Sensor Node: numBarrels)");
		}
		if (s.hasNext("barrelLR")) {
			BarrelLeftRight barrelLR = new BarrelLeftRight();
			node = barrelLR;
			System.out.println("Sensor Node: barrelLR");

			System.out.println("Sensor Node: Next: " + s.next());

			System.out.println("Sensor Node: Checking after barrelLR");
			if (s.hasNext("\\(")) {
				System.out.println("Sensor Node: Found (");
				System.out.println("Sensor Node: Found " + s.next());

				if (s.hasNext("-?[1-9][0-9]*|0")) {
					System.out.println("Sensor Node: Number found");
					barrelLR.setExpression(new Number(s.nextInt()));
					System.out.println("Sensor Node: Number successfully set onto barrelLR");
				} else if (s.hasNext("add")) {
					System.out.println("Sensor Node: Found Add");
					AdditionOperator add = new AdditionOperator();
					add.parse(s);
					barrelLR.setExpression(add);
					System.out.println("Sensor Node: NEXT: " + s.next());
				} else if (s.hasNext("sub")) {
					System.out.println("Sensor Node: Found Sub");
					SubtractionOperator sub = new SubtractionOperator();
					sub.parse(s);
					barrelLR.setExpression(sub);
					System.out.println("Sensor Node: NEXT: " + s.next());
				} else if (s.hasNext("div")) {
					System.out.println("Sensor Node: Found Div");
					DivisionOperator div = new DivisionOperator();
					div.parse(s);
					barrelLR.setExpression(div);
					System.out.println("Sensor Node: NEXT: " + s.next());
				} else if (s.hasNext("mul")) {
					System.out.println("Sensor Node: Found Mul");
					MultiplicationOperator mul = new MultiplicationOperator();
					mul.parse(s);
					barrelLR.setExpression(mul);
					System.out.println("Sensor Node: NEXT: " + s.next());
				} else if (s.hasNext("fuelLeft")) {
					System.out.println("Sensor Node: Found fuelLeft");
					FuelLeft fuelLeft = new FuelLeft();
					barrelLR.setExpression(fuelLeft);
					System.out.println("Sensor Node: NEXT: " + s.next());
				} else if (s.hasNext("oppLR")) {
					System.out.println("Sensor Node: Found oppLR");
					OpponentLeftRight oppLR = new OpponentLeftRight();
					barrelLR.setExpression(oppLR);
					System.out.println("Sensor Node: NEXT: " + s.next());
				} else if (s.hasNext("oppFB")) {
					System.out.println("Sensor Node: Found oppFB");
					OpponentFrontBack oppFB = new OpponentFrontBack();
					barrelLR.setExpression(oppFB);
					System.out.println("Sensor Node: NEXT: " + s.next());
				} else if (s.hasNext("numBarrels")) {
					System.out.println("Sensor Node: Found numBarrels");
					NumberBarrels numBarrels = new NumberBarrels();
					barrelLR.setExpression(numBarrels);
					System.out.println("Sensor Node: NEXT: " + s.next());
				} else if (s.hasNext("barrelLR")) {
					System.out.println("Sensor Node: Found barrelLR");
					BarrelLeftRight barrelLR2 = new BarrelLeftRight();
					barrelLR2.parse(s);
					barrelLR.setExpression(barrelLR2);
					System.out.println("Sensor Node: NEXT: " + s.next());
				} else if (s.hasNext("barrelFB")) {
					System.out.println("Sensor Node: Found barrelFB");
					BarrelFrontBack barrelFB = new BarrelFrontBack();
					barrelFB.parse(s);
					barrelLR.setExpression(barrelFB);
					System.out.println("Sensor Node: NEXT: " + s.next());
				} else if (s.hasNext("wallDist")) {
					System.out.println("Sensor Node: Found wallDist");
					WallDistance wallDist = new WallDistance();
					barrelLR.setExpression(wallDist);
					System.out.println("Sensor Node: NEXT: " + s.next());
				}
				else  if(s.hasNext("\\$[A-Za-z][A-Za-z0-9]*")){
					System.out.println("Sensor Node: Found Assignment Number/Letter");
					AssignmentNode assig = new AssignmentNode();
					assig.parse(s);
					barrelLR.setExpression(assig);
					System.out.println("Sensor Node: NEXT: "+s.next());
				}
				else{
					Parser.fail("Found bracket after move. Expected an argument for move.",s);
				}
			} else {
				System.out.println("Sensor Node : barrelLR: Went into the else but nothing happens here");
				//System.out.println("Sensor Node: Next: "+s.next());
			}
		}
		if (s.hasNext("barrelFB")) {
			BarrelFrontBack barrelFB = new BarrelFrontBack();
			node = barrelFB;
			System.out.println("Sensor Node: barrelFB");

			System.out.println("Sensor Node: Next: " + s.next());

			System.out.println("Sensor Node: Checking after barrelFB");
			if (s.hasNext("\\(")) {
				System.out.println("Sensor Node: Found (");
				System.out.println("Sensor Node: Found " + s.next());

				if (s.hasNext("-?[1-9][0-9]*|0")) {
					System.out.println("Sensor Node: Number found");
					barrelFB.setExpression(new Number(s.nextInt()));
					System.out.println("Sensor Node: Number successfully set onto barrelFB");
				} else if (s.hasNext("add")) {
					System.out.println("Sensor Node: Found Add");
					AdditionOperator add = new AdditionOperator();
					add.parse(s);
					barrelFB.setExpression(add);
					System.out.println("Sensor Node: NEXT: " + s.next());
				} else if (s.hasNext("sub")) {
					System.out.println("Sensor Node: Found Sub");
					SubtractionOperator sub = new SubtractionOperator();
					sub.parse(s);
					barrelFB.setExpression(sub);
					System.out.println("Sensor Node: NEXT: " + s.next());
				} else if (s.hasNext("div")) {
					System.out.println("Sensor Node: Found Div");
					DivisionOperator div = new DivisionOperator();
					div.parse(s);
					barrelFB.setExpression(div);
					System.out.println("Sensor Node: NEXT: " + s.next());
				} else if (s.hasNext("mul")) {
					System.out.println("Sensor Node: Found Mul");
					MultiplicationOperator mul = new MultiplicationOperator();
					mul.parse(s);
					barrelFB.setExpression(mul);
					System.out.println("Sensor Node: NEXT: " + s.next());
				} else if (s.hasNext("fuelLeft")) {
					System.out.println("Sensor Node: Found fuelLeft");
					FuelLeft fuelLeft = new FuelLeft();
					barrelFB.setExpression(fuelLeft);
					System.out.println("Sensor Node: NEXT: " + s.next());
				} else if (s.hasNext("oppLR")) {
					System.out.println("Sensor Node: Found oppLR");
					OpponentLeftRight oppLR = new OpponentLeftRight();
					barrelFB.setExpression(oppLR);
					System.out.println("Sensor Node: NEXT: " + s.next());
				} else if (s.hasNext("oppFB")) {
					System.out.println("Sensor Node: Found oppFB");
					OpponentFrontBack oppFB = new OpponentFrontBack();
					barrelFB.setExpression(oppFB);
					System.out.println("Sensor Node: NEXT: " + s.next());
				} else if (s.hasNext("numBarrels")) {
					System.out.println("Sensor Node: Found numBarrels");
					NumberBarrels numBarrels = new NumberBarrels();
					barrelFB.setExpression(numBarrels);
					System.out.println("Sensor Node: NEXT: " + s.next());
				} else if (s.hasNext("barrelLR")) {
					System.out.println("Sensor Node: Found barrelLR");
					BarrelLeftRight barrelLR = new BarrelLeftRight();
					barrelFB.setExpression(barrelLR);
					System.out.println("Sensor Node: NEXT: " + s.next());
				} else if (s.hasNext("barrelFB")) {
					System.out.println("Sensor Node: Found barrelFB");
					BarrelFrontBack barrelFB2 = new BarrelFrontBack();
					barrelFB.setExpression(barrelFB2);
					System.out.println("Sensor Node: NEXT: " + s.next());
				} else if (s.hasNext("wallDist")) {
					System.out.println("Sensor Node: Found wallDist");
					WallDistance wallDist = new WallDistance();
					barrelFB.setExpression(wallDist);
					System.out.println("Sensor Node: NEXT: " + s.next());
				}
				else  if(s.hasNext("\\$[A-Za-z][A-Za-z0-9]*")){
					System.out.println("Sensor Node: Found Assignment Number/Letter");
					AssignmentNode assig = new AssignmentNode();
					assig.parse(s);
					barrelFB.setExpression(assig);
					System.out.println("Sensor Node: NEXT: "+s.next());
				}
				else{
					Parser.fail("Found bracket after barrelLR. Expected argument.",s);
				}
			} else {
				System.out.println("Sensor Node : barrelFB: Went into the else but nothing happens here");
				//System.out.println("Sensor Node: Next: "+s.next());
			}
		}
		if (s.hasNext("wallDist")) {
			WallDistance wallDist = new WallDistance();
			node = wallDist;
			System.out.println("Sensor Node: wallDist");
		}

		if (node == null) {
			System.out.println("Sensor Node: Node was null returning false");
			Parser.fail("Sensor node was null.",s);
			return false;
		}

		System.out.println("Sensor Node: Node was NOT null. Returning true");

		return true;
	}

	public String toString() {
		return "" + node;
	}

}
