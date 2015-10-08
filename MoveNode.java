import java.util.Map;

public class MoveNode implements RobotProgramNode{

	private Expression expression;

	@Override
	public void execute(Robot robot, Map<Variable, Expression> variables) {
		//System.out.println("MoveNode: Execute");

		//Number
		Number amount = null;

		//Operators
		AdditionOperator add = null;
		SubtractionOperator sub = null;
		DivisionOperator div = null;
		MultiplicationOperator mul = null;

		//Sensors
		FuelLeft fuelLeft = null;
		OpponentLeftRight oppLR = null;
		OpponentFrontBack oppFB = null;
		NumberBarrels numBarrels = null;
		BarrelLeftRight barrelLR = null;
		BarrelFrontBack barrelFB = null;
		WallDistance wallDist = null;

		//Variable
		Variable variable = null;

		if(expression instanceof Number){
			Number number = (Number) expression;
			amount = number;
		}

		//Operators
		if(expression instanceof AdditionOperator){
			add = (AdditionOperator) expression;
		}
		if(expression instanceof SubtractionOperator){
			sub = (SubtractionOperator) expression;
		}
		if(expression instanceof MultiplicationOperator){
			mul = (MultiplicationOperator) expression;
		}
		if(expression instanceof DivisionOperator){
			div = (DivisionOperator) expression;
		}

		//Sensors
		if(expression instanceof FuelLeft){
			fuelLeft = (FuelLeft) expression;
		}
		if(expression instanceof OpponentLeftRight){
			oppLR = (OpponentLeftRight) expression;
		}
		if(expression instanceof OpponentFrontBack){
			oppFB = (OpponentFrontBack) expression;
		}
		if(expression instanceof NumberBarrels){
			numBarrels = (NumberBarrels) expression;
		}
		if(expression instanceof BarrelLeftRight){
			barrelLR = (BarrelLeftRight) expression;
		}
		if(expression instanceof BarrelFrontBack){
			barrelFB = (BarrelFrontBack) expression;
		}
		if(expression instanceof WallDistance){
			wallDist = (WallDistance) expression;
		}

		//Variable
		if(expression instanceof AssignmentNode){
			AssignmentNode temp = (AssignmentNode) expression;
			//variable = (Variable) expression;
			for(Variable v: variables.keySet()){
				if(v.getVariableName().equals(temp.getVariableName())){
					variable = (Variable) v;
				}
			}
		}


		//Execute

		//If it's a number
		if(amount!=null){
			//System.out.println("MoveNode: Number");
			int i = 0;
			while(i<amount.getNumber()){
				robot.move();
				i++;
			}
		}
		//If it's an Operator
		else if(add!=null){
			//System.out.println("MoveNode: Add");
			int n = add.evaluate(robot,variables);
			int i = 0;

			while(i<n){
				robot.move();
				i++;
			}
		}
		else if(sub!=null){
			//System.out.println("MoveNode: Sub");
			int n = sub.evaluate(robot,variables);
			int i = 0;

			while(i<n){
				robot.move();
				i++;
			}
		}
		else if(div!=null){
			//System.out.println("MoveNode: Div");
			int n = div.evaluate(robot,variables);
			int i = 0;

			while(i<n){
				robot.move();
				i++;
			}
		}
		else if(mul!=null){
			//System.out.println("MoveNode: Mul");
			int n = mul.evaluate(robot,variables);
			int i = 0;

			while(i<n){
				robot.move();
				i++;
			}
		}
		//If it's a Sensor
		else if(fuelLeft!=null){
			//System.out.println("MoveNode: FuelLeft");
			int n = fuelLeft.evaluate(robot,variables);
			int i = 0;

			while(i<n){
				robot.move();
				i++;
			}
		}
		else if(oppLR!=null){
			//System.out.println("MoveNode: OppLR");
			int n = oppLR.evaluate(robot,variables);
			int i = 0;

			while(i<n){
				robot.move();
				i++;
			}
		}
		else if(oppFB!=null){
			//System.out.println("MoveNode: OppFB");
			int n = oppFB.evaluate(robot,variables);
			int i = 0;

			while(i<n){
				robot.move();
				i++;
			}
		}
		else if(numBarrels!=null){
			//System.out.println("MoveNode: NumBarrels");
			int n = numBarrels.evaluate(robot,variables);
			int i = 0;

			while(i<n){
				robot.move();
				i++;
			}
		}
		else if(barrelLR!=null){
			//System.out.println("MoveNode: BarrelLR");
			int n = barrelLR.evaluate(robot,variables);
			int i = 0;

			while(i<n){
				robot.move();
				i++;
			}
		}
		else if(barrelFB!=null){
			//System.out.println("MoveNode: BarrelFB");
			int n = barrelFB.evaluate(robot,variables);
			int i = 0;

			while(i<n){
				robot.move();
				i++;
			}
		}
		else if(wallDist!=null){
			//System.out.println("MoveNode: WallDist");
			int n = wallDist.evaluate(robot,variables);
			int i = 0;

			while(i<n){
				robot.move();
				i++;
			}
		}

		//If it's a variable
		else if(variable!=null){
			//System.out.println("MoveNode: Variable");
			int n = variable.evaluate(robot,variables);
			int i = 0;

			while(i<n){
				robot.move();
				i++;
			}
		}

		//Else just move normally if expression is null
		else{
			//System.out.println("MoveNode: Move without arguments");
			robot.move();
		}
	}

	public void setExpression(Expression exp){
		expression = exp;
	}

	public String toString(){
		return "move("+expression+")\n";
	}

}
