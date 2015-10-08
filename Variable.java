import java.util.Map;

public class Variable implements Expression {

	private String variableName;
	private Expression exp;
	private boolean argument;

	public Variable(String variableName) {
		this.variableName = variableName;
	}

	public int evaluate(Robot robot, Map<Variable, Expression> variables) {

		Expression expression = null;

		for (Variable v : variables.keySet()) {
			if (v.getVariableName().equals(variableName)) {
				if (variables.get(v) == null) {
					//System.out.println("Variable: Assigning null to expression.");
				} else {
					//System.out.println("Variable: Assigning " + variables.get(v).toString() + " to expression.");
				}

				expression = variables.get(v);
			}
		}

		//Answer
		int result = 0;

		if (expression != null) {

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

			if (expression instanceof Number) {
				Number number = (Number) expression;
				amount = number;
			}

			//Operators
			if (expression instanceof AdditionOperator) {
				add = (AdditionOperator) expression;
			}
			if (expression instanceof SubtractionOperator) {
				sub = (SubtractionOperator) expression;
			}
			if (expression instanceof MultiplicationOperator) {
				mul = (MultiplicationOperator) expression;
			}
			if (expression instanceof DivisionOperator) {
				div = (DivisionOperator) expression;
			}

			//Sensors
			if (expression instanceof FuelLeft) {
				fuelLeft = (FuelLeft) expression;
			}
			if (expression instanceof OpponentLeftRight) {
				oppLR = (OpponentLeftRight) expression;
			}
			if (expression instanceof OpponentFrontBack) {
				oppFB = (OpponentFrontBack) expression;
			}
			if (expression instanceof NumberBarrels) {
				numBarrels = (NumberBarrels) expression;
			}
			if (expression instanceof BarrelLeftRight) {
				barrelLR = (BarrelLeftRight) expression;
			}
			if (expression instanceof BarrelFrontBack) {
				barrelFB = (BarrelFrontBack) expression;
			}
			if (expression instanceof WallDistance) {
				wallDist = (WallDistance) expression;
			}

			//Variable
			if (expression instanceof AssignmentNode) {
				AssignmentNode temp = (AssignmentNode) expression;
				for (Variable v : variables.keySet()) {
					if (v.getVariableName().equals(temp.getVariableName())) {
						variable = (Variable) expression;
					}
				}
			}

			//Execute

			//If it's a number
			if (amount != null) {
				//System.out.println("Variable: Number");
				result = amount.getNumber();
			}
			//If it's an Operator
			else if (add != null) {
				//System.out.println("Variable: Add");
				result = add.evaluate(robot, variables);

			} else if (sub != null) {
				//System.out.println("Variable: Sub");
				result = sub.evaluate(robot, variables);

			} else if (div != null) {
				//System.out.println("Variable: Div");
				result = div.evaluate(robot, variables);

			} else if (mul != null) {
				//System.out.println("Variable: Mul");
				result = mul.evaluate(robot, variables);

			}
			//If it's a Sensor
			else if (fuelLeft != null) {
				//System.out.println("Variable: FuelLeft");
				result = fuelLeft.evaluate(robot, variables);

			} else if (oppLR != null) {
				//System.out.println("Variable: OppLR");
				result = oppLR.evaluate(robot, variables);

			} else if (oppFB != null) {
				//System.out.println("Variable: OppFB");
				result = oppFB.evaluate(robot, variables);

			} else if (numBarrels != null) {
				//System.out.println("Variable: NumBarrels");
				result = numBarrels.evaluate(robot, variables);

			} else if (barrelLR != null) {
				//System.out.println("Variable: BarrelLR");
				result = barrelLR.evaluate(robot, variables);

			} else if (barrelFB != null) {
				//System.out.println("Variable: BarrelFB");
				result = barrelFB.evaluate(robot, variables);

			} else if (wallDist != null) {
				//System.out.println("Variable: WallDist");
				result = wallDist.evaluate(robot, variables);
			}

			//If it's a variable
			else if (variable != null) {
				//System.out.println("Variable: Variable");
				result = variable.evaluate(robot, variables);
			}
		}

		//System.out.println("Variable Value: " + result);

		return result;
	}

	public void setExpression(Expression v) {
		exp = v;
	}

	public Expression getExpression() {
		return exp;
	}

	public void setArgument(boolean b) {
		argument = b;
	}

	public String getVariableName() {
		return variableName;
	}

	public String toString() {
		if (exp != null && argument == false) {
			return variableName + " = " + exp.toString() + "\n";
		} else if (argument == false) {
			return variableName + " = 0\n";
		} else {
			return variableName;
		}
	}

}
