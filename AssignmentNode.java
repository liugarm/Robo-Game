import java.util.Map;
import java.util.Scanner;

public class AssignmentNode implements RobotProgramNode, Expression {

	private Variable variable;
	private Expression expression;

	@Override
	public void execute(Robot robot, Map<Variable, Expression> variables) {
		if (expression == null) {
			//System.out.println("Assigning: " + variable.getVariableName() + " to null");
		} else {
			//System.out.println("Assigning: " + variable.getVariableName() + " to " + expression.toString());
		}

		Variable replace = null;

		for (Variable v : variables.keySet()) {
			if (v.getVariableName().equals(variable.getVariableName())) {
				replace = v;
			}
		}

		if (expression != null) {

			if (replace != null) {
				//System.out.println("AssignmentNode: Replaced");
				variables.put(replace, expression);
			} else {
				//System.out.println("AssignmentNode: Added without replace");
				variables.put(variable, expression);
			}
		}

		//Printing
		for (Variable v : variables.keySet()) {
			if (variables.get(v) != null) {
				//System.out.println("AssignmentNode has: " + v.getVariableName() + " - " + variables.get(v).toString());
			} else {
				//System.out.println("AssignmentNode has: " + v.getVariableName() + " - null");
			}
		}
	}

	public boolean parse(Scanner s) {
		//System.out.println("AssignmentNode: Parse");
		//System.out.println("AssignmentNode: Next: "+s.next());

		if(!s.hasNext("\\,|\\;|\\(|\\)|\\{|\\}")){
			variable = new Variable(s.next());
			//System.out.println("AssignmentNode: Variable Name: " + variable.getVariableName());
		}

		if (s.hasNext("\\,|\\)")) {//
			if(variable!=null){
				variable.setArgument(true);
			}
			//System.out.println("AssignmentNode: Found , returning True");
			return false;
		}

		if (!s.hasNext("\\=")) {
			Parser.fail("Assignment Node should have equals. ", s);
		}

		if (s.hasNext("\\=")) {
			//System.out.println("AssignmentNode: Next: " + s.next());
			s.next();
		} else {
			//fail("Need an equals after the variable in order to assign a value.");
		}

		//System.out.println("AssignmentNode: Checking after variable");

		if (s.hasNext("-?[1-9][0-9]*|0")) {
			//System.out.println("AssignmentNode: Number found");
			Number n = new Number(s.nextInt());
			expression = n;
			variable.setExpression(n);
			//System.out.println("AssignmentNode: Number successfully set onto variable");
		} else if (s.hasNext("add")) {
			//System.out.println("AssignmentNode: Found Add");
			AdditionOperator add = new AdditionOperator();
			add.parse(s);
			expression = add;
			variable.setExpression(add);
			//System.out.println("AssignmentNode: NEXT: " + s.next());
			s.next();
		} else if (s.hasNext("sub")) {
			//System.out.println("AssignmentNode: Found Sub");
			SubtractionOperator sub = new SubtractionOperator();
			sub.parse(s);
			expression = sub;
			variable.setExpression(sub);
			//System.out.println("AssignmentNode: NEXT: " + s.next());
			s.next();
		} else if (s.hasNext("div")) {
			//System.out.println("AssignmentNode: Found Div");
			DivisionOperator div = new DivisionOperator();
			div.parse(s);
			expression = div;
			variable.setExpression(div);
			//System.out.println("AssignmentNode: NEXT: " + s.next());
			s.next();
		} else if (s.hasNext("mul")) {
			//System.out.println("AssignmentNode: Found Mul");
			MultiplicationOperator mul = new MultiplicationOperator();
			mul.parse(s);
			expression = mul;
			variable.setExpression(mul);
			//System.out.println("AssignmentNode: NEXT: " + s.next());
			s.next();
		} else if (s.hasNext("fuelLeft")) {
			//System.out.println("AssignmentNode: Found fuelLeft");
			FuelLeft fuelLeft = new FuelLeft();
			expression = fuelLeft;
			variable.setExpression(fuelLeft);
			//System.out.println("AssignmentNode: NEXT: " + s.next());
			s.next();
		} else if (s.hasNext("oppLR")) {
			//System.out.println("AssignmentNode: Found oppLR");
			OpponentLeftRight oppLR = new OpponentLeftRight();
			expression = oppLR;
			variable.setExpression(oppLR);
			//System.out.println("AssignmentNode: NEXT: " + s.next());
			s.next();
		} else if (s.hasNext("oppFB")) {
			//System.out.println("AssignmentNode: Found oppFB");
			OpponentFrontBack oppFB = new OpponentFrontBack();
			expression = oppFB;
			variable.setExpression(oppFB);
			//System.out.println("AssignmentNode: NEXT: " + s.next());
			s.next();
		} else if (s.hasNext("numBarrels")) {
			//System.out.println("AssignmentNode: Found numBarrels");
			NumberBarrels numBarrels = new NumberBarrels();
			expression = numBarrels;
			variable.setExpression(numBarrels);
			//System.out.println("AssignmentNode: NEXT: " + s.next());
			s.next();
		} else if (s.hasNext("barrelLR")) {
			//System.out.println("AssignmentNode: Found barrelLR");
			BarrelLeftRight variable2 = new BarrelLeftRight();
			variable2.parse(s);
			expression = variable2;
			variable.setExpression(variable2);
			//System.out.println("AssignmentNode: NEXT: " + s.next());
			s.next();
		} else if (s.hasNext("barrelFB")) {
			//System.out.println("AssignmentNode: Found barrelFB");
			BarrelFrontBack barrelFB = new BarrelFrontBack();
			barrelFB.parse(s);
			expression = barrelFB;
			variable.setExpression(barrelFB);
			//System.out.println("AssignmentNode: NEXT: " + s.next());
			s.next();
		} else if (s.hasNext("wallDist")) {
			//System.out.println("AssignmentNode: Found wallDist");
			WallDistance wallDist = new WallDistance();
			expression = wallDist;
			variable.setExpression(wallDist);
			//System.out.println("AssignmentNode: NEXT: " + s.next());
			s.next();
		} else if (s.hasNext("\\$[A-Za-z][A-Za-z0-9]*")) {
			//System.out.println("AssignmentNode: Found Assignment Letter/Number");
			AssignmentNode assig = new AssignmentNode();
			expression = assig;
			assig.parse(s);
			variable.setExpression(assig);
			//System.out.println("AssignmentNode: NEXT: " + s.next());
			s.next();
		}

		if (variable.getExpression() != null) {
			//System.out.println("AssignmentNode: Variable not null. Returning True.");
			return true;
		} else {
			//System.out.println("AssignmentNode: Variable was null. Returning False.");
			//System.out.println("AssignmentNode: Next: " + s.next());
			Parser.fail("Assignment Node failed. Variable was null.", s);
			return false;
		}
	}

	public String getVariableName() {
		return variable.getVariableName();
	}

	public String toString() {
		if(variable!=null){
			return variable.toString();
		}
		else{
			return null;
		}
	}

}
