import java.util.Map;
import java.util.Scanner;

public class BarrelLeftRight implements Sensors, Expression {

	private Expression expression;

	@Override
	public int evaluate(Robot r, Map<Variable, Expression> variables) {

		if (expression != null) {
			Number result = null;

			// Argument One
			// Number
			// int amountArg1 = -1;
			Number amountArg1 = null;

			// Operators
			AdditionOperator addArg1 = null;
			SubtractionOperator subArg1 = null;
			DivisionOperator divArg1 = null;
			MultiplicationOperator mulArg1 = null;

			// Sensors
			FuelLeft fuelLeftArg1 = null;
			OpponentLeftRight oppLRArg1 = null;
			OpponentFrontBack oppFBArg1 = null;
			NumberBarrels numBarrelsArg1 = null;
			BarrelLeftRight barrelLRArg1 = null;
			BarrelFrontBack barrelFBArg1 = null;
			WallDistance wallDistArg1 = null;

			// Variable
			Variable variable = null;

			if (expression instanceof Number) {
				Number number = (Number) expression;
				amountArg1 = number;
				//System.out.println("BarrelLR : Number1");
			}

			// Operators
			if (expression instanceof AdditionOperator) {
				addArg1 = (AdditionOperator) expression;
				//System.out.println("BarrelLR : Add1");
			}
			if (expression instanceof SubtractionOperator) {
				subArg1 = (SubtractionOperator) expression;
				//System.out.println("BarrelLR : Sub1");
			}
			if (expression instanceof MultiplicationOperator) {
				mulArg1 = (MultiplicationOperator) expression;
				//System.out.println("BarrelLR : Mul1");
			}
			if (expression instanceof DivisionOperator) {
				divArg1 = (DivisionOperator) expression;
				//System.out.println("BarrelLR : Div1");
			}

			// Sensors
			if (expression instanceof FuelLeft) {
				fuelLeftArg1 = (FuelLeft) expression;
				//System.out.println("BarrelLR : FuelLeft1");
			}
			if (expression instanceof OpponentLeftRight) {
				oppLRArg1 = (OpponentLeftRight) expression;
				//System.out.println("BarrelLR : oppLR1");
			}
			if (expression instanceof OpponentFrontBack) {
				oppFBArg1 = (OpponentFrontBack) expression;
				//System.out.println("BarrelLR : oppFB1");
			}
			if (expression instanceof NumberBarrels) {
				numBarrelsArg1 = (NumberBarrels) expression;
				//System.out.println("BarrelLR : numBarrels1");
			}
			if (expression instanceof BarrelLeftRight) {
				barrelLRArg1 = (BarrelLeftRight) expression;
				//System.out.println("BarrelLR : barrelLR1");
			}
			if (expression instanceof BarrelFrontBack) {
				barrelFBArg1 = (BarrelFrontBack) expression;
				//System.out.println("BarrelLR : barrelFB1");
			}
			if (expression instanceof WallDistance) {
				wallDistArg1 = (WallDistance) expression;
				//System.out.println("BarrelLR : wallDist1");
			}

			// Variable
			if (expression instanceof AssignmentNode) {
				AssignmentNode temp = (AssignmentNode) expression;
				// variable = (Variable) expression;
				for (Variable v : variables.keySet()) {
					if (v.getVariableName().equals(temp.getVariableName())) {
						variable = (Variable) v;
					}
				}
			}

			// If it's a number
			if (amountArg1 != null) {
				result = amountArg1;
			}
			// If it's an Operator
			else if (addArg1 != null) {
				result = new Number(addArg1.evaluate(r, variables));
			} else if (subArg1 != null) {
				result = new Number(subArg1.evaluate(r, variables));
			} else if (divArg1 != null) {
				result = new Number(divArg1.evaluate(r, variables));
			} else if (mulArg1 != null) {
				result = new Number(mulArg1.evaluate(r, variables));
			}
			// If it's a Sensor
			else if (fuelLeftArg1 != null) {
				result = new Number(fuelLeftArg1.evaluate(r, variables));

			} else if (oppLRArg1 != null) {
				result = new Number(oppLRArg1.evaluate(r, variables));

			} else if (oppFBArg1 != null) {
				result = new Number(oppFBArg1.evaluate(r, variables));

			} else if (numBarrelsArg1 != null) {
				result = new Number(numBarrelsArg1.evaluate(r, variables));

			} else if (barrelLRArg1 != null) {
				result = new Number(barrelLRArg1.evaluate(r, variables));

			} else if (barrelFBArg1 != null) {
				result = new Number(barrelFBArg1.evaluate(r, variables));

			} else if (wallDistArg1 != null) {
				result = new Number(wallDistArg1.evaluate(r, variables));

			}
			// If it's a variable
			else if (variable != null) {
				//System.out.println("BarrelLeftRight: Variable");
				result = new Number(variable.evaluate(r, variables));
			}

			//System.out.println("BarrelLeftRight: Has an integer value");
			return r.getBarrelLR(result.getNumber());
		}

		//System.out.println("BarrelLeftRight: Returning:"+ r.getClosestBarrelLR());
		return r.getClosestBarrelLR();
	}
	
	public boolean parse(Scanner s){
		//System.out.println("BarrelLR: Parse");
		if(!s.hasNext("\\,")){
			//System.out.println("BarrelLR Next: "+s.next());
			s.next();
		}
		
		if(s.hasNext("\\(")){
			//System.out.println("BarrelLeftRight: Found (");
			//System.out.println("BarrelLeftRight: Found "+s.next());
			s.next();

			if(s.hasNext("-?[1-9][0-9]*|0")){
				//System.out.println("BarrelLeftRight: Number found");
				expression = new Number(s.nextInt());
				//System.out.println("BarrelLeftRight: Number successfully set onto Move");
				return true;
			}
			else if(s.hasNext("add")){
				//System.out.println("BarrelLeftRight: Found Add");
				AdditionOperator add = new AdditionOperator();
				add.parse(s);
				expression = add;
				//System.out.println("BarrelLeftRight: NEXT: "+s.next());
				s.next();
				return true;
			}
			else if(s.hasNext("sub")){
				//System.out.println("BarrelLeftRight: Found Sub");
				SubtractionOperator sub = new SubtractionOperator();
				sub.parse(s);
				expression = sub;
				//System.out.println("BarrelLeftRight: NEXT: "+s.next());
				s.next();
				return true;
			}
			else if(s.hasNext("div")){
				//System.out.println("BarrelLeftRight: Found Div");
				DivisionOperator div = new DivisionOperator();
				div.parse(s);
				expression = div;
				//System.out.println("BarrelLeftRight: NEXT: "+s.next());
				s.next();
				return true;
			}
			else if(s.hasNext("mul")){
				//System.out.println("BarrelLeftRight: Found Mul");
				MultiplicationOperator mul = new MultiplicationOperator();
				mul.parse(s);
				expression = mul;
				//System.out.println("BarrelLeftRight: NEXT: "+s.next());
				s.next();
				return true;
			}
			else if(s.hasNext("fuelLeft")){
				//System.out.println("BarrelLeftRight: Found fuelLeft");
				FuelLeft fuelLeft = new FuelLeft();
				expression = fuelLeft;
				//System.out.println("BarrelLeftRight: NEXT: "+s.next());
				s.next();
				return true;
			}
			else if(s.hasNext("oppLR")){
				//System.out.println("BarrelLeftRight: Found oppLR");
				OpponentLeftRight oppLR = new OpponentLeftRight();
				expression = oppLR;
				//System.out.println("BarrelLeftRight: NEXT: "+s.next());
				s.next();
				return true;
			}
			else if(s.hasNext("oppFB")){
				//System.out.println("BarrelLeftRight: Found oppFB");
				OpponentFrontBack oppFB = new OpponentFrontBack();
				expression = oppFB;
				//System.out.println("BarrelLeftRight: NEXT: "+s.next());
				s.next();
				return true;
			}
			else if(s.hasNext("numBarrels")){
				//System.out.println("BarrelLeftRight: Found numBarrels");
				NumberBarrels numBarrels = new NumberBarrels();
				expression = numBarrels;
				//System.out.println("BarrelLeftRight: NEXT: "+s.next());
				s.next();
				return true;
			}
			else if(s.hasNext("barrelLR")){
				//System.out.println("BarrelLeftRight: Found barrelLR");
				BarrelLeftRight barrelLR = new BarrelLeftRight();
				barrelLR.parse(s);
				expression = barrelLR;
				//System.out.println("BarrelLeftRight: NEXT: "+s.next());
				s.next();
				return true;
			}
			else if(s.hasNext("barrelFB")){
				//System.out.println("BarrelLeftRight: Found barrelFB");
				BarrelFrontBack barrelFB = new BarrelFrontBack();
				barrelFB.parse(s);
				expression = barrelFB;
				//System.out.println("BarrelLeftRight: NEXT: "+s.next());
				s.next();
				return true;
			}
			else if(s.hasNext("wallDist")){
				//System.out.println("BarrelLeftRight: Found wallDist");
				WallDistance wallDist = new WallDistance();
				expression = wallDist;
				//System.out.println("BarrelLeftRight: NEXT: "+s.next());
				s.next();
				return true;
			}
			else if(s.hasNext("\\$[A-Za-z][A-Za-z0-9]*")){
				//System.out.println("BarrelLeftRight: Found Assignment Letter/Number");
				AssignmentNode assig = new AssignmentNode();
				assig.parse(s);
				expression = assig;
				//System.out.println("BarrelLeftRight: NEXT: " + s.next());
				s.next();
				return true;
			}
		}
		
		return false;
	}

	public void setExpression(Expression e) {
		expression = e;
	}

	public String toString() {
		if (expression == null) {
			return "BarrelLR";
		} else {
			return "BarrelLR(" + expression.toString() + ")";
		}
	}

}
