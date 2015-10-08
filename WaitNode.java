import java.util.Map;

public class WaitNode implements RobotProgramNode {

	private Expression expression;

	@Override
	public void execute(Robot robot, Map<Variable, Expression> variables) {
		// Number
		Number amount = null;

		// Operators
		AdditionOperator add = null;
		SubtractionOperator sub = null;
		DivisionOperator div = null;
		MultiplicationOperator mul = null;

		// Sensors
		FuelLeft fuelLeft = null;
		OpponentLeftRight oppLR = null;
		OpponentFrontBack oppFB = null;
		NumberBarrels numBarrels = null;
		BarrelLeftRight barrelLR = null;
		BarrelFrontBack barrelFB = null;
		WallDistance wallDist = null;

		// Variable
		Variable variable = null;

		if (expression instanceof Number) {
			Number number = (Number) expression;
			amount = number;
		}

		// Operators
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

		// Sensors
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

		// Execute

		// If it's a number
		if (amount != null) {
			int i = 0;
			while (i < amount.getNumber()) {
				robot.idleWait();
				i++;
			}
		}
		// If it's an Operator
		else if (add != null) {
			int n = add.evaluate(robot,variables);
			int i = 0;

			while (i < n) {
				robot.idleWait();
				i++;
			}
		} else if (sub != null) {
			int n = sub.evaluate(robot,variables);
			int i = 0;

			while (i < n) {
				robot.idleWait();
				i++;
			}
		} else if (div != null) {
			int n = div.evaluate(robot,variables);
			int i = 0;

			while (i < n) {
				robot.idleWait();
				i++;
			}
		} else if (mul != null) {
			int n = mul.evaluate(robot,variables);
			int i = 0;

			while (i < n) {
				robot.idleWait();
				i++;
			}
		}
		// If it's a Sensor
		else if (fuelLeft != null) {
			int n = fuelLeft.evaluate(robot,variables);
			int i = 0;

			while (i < n) {
				robot.idleWait();
				i++;
			}
		} else if (oppLR != null) {
			int n = oppLR.evaluate(robot,variables);
			int i = 0;

			while (i < n) {
				robot.idleWait();
				i++;
			}
		} else if (oppFB != null) {
			int n = oppFB.evaluate(robot,variables);
			int i = 0;

			while (i < n) {
				robot.idleWait();
				i++;
			}
		} else if (numBarrels != null) {
			int n = numBarrels.evaluate(robot,variables);
			int i = 0;

			while (i < n) {
				robot.idleWait();
				i++;
			}
		} else if (barrelLR != null) {
			int n = barrelLR.evaluate(robot,variables);
			int i = 0;

			while (i < n) {
				robot.idleWait();
				i++;
			}
		} else if (barrelFB != null) {
			int n = barrelFB.evaluate(robot,variables);
			int i = 0;

			while (i < n) {
				robot.idleWait();
				i++;
			}
		} else if (wallDist != null) {
			int n = wallDist.evaluate(robot,variables);
			int i = 0;

			while (i < n) {
				robot.idleWait();
				i++;
			}
		}

		// If it's a variable
		else if (variable != null) {
			//System.out.println("WaitNode: Variable");
			int n = variable.evaluate(robot,variables);
			int i = 0;

			while (i < n) {
				robot.idleWait();
				i++;
			}
		}
		// Else just move normally if expression is null
		else {
			robot.idleWait();
		}
	}

	public void setExpression(Expression exp) {
		expression = exp;
	}

	public String toString() {
		return "wait(" + expression + ")\n";
	}

}
