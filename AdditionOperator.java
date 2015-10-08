import java.util.Map;
import java.util.Scanner;

public class AdditionOperator implements Operator, Expression {

	private Expression argumentOne;
	private Expression argumentTwo;

	@Override
	public int evaluate(Robot robot, Map<Variable, Expression> variables) {
		//System.out.println("Add: Evaluate");

		// Result values
		Number argOneValue = null;
		Number argTwoValue = null;

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
		Variable variable1 = null;

		if (argumentOne instanceof Number) {
			Number number = (Number) argumentOne;
			amountArg1 = number;
			//System.out.println("Add : Number1");
		}

		// Operators
		if (argumentOne instanceof AdditionOperator) {
			addArg1 = (AdditionOperator) argumentOne;
			//System.out.println("Add : Add1");
		}
		if (argumentOne instanceof SubtractionOperator) {
			subArg1 = (SubtractionOperator) argumentOne;
			//System.out.println("Add : Sub1");
		}
		if (argumentOne instanceof MultiplicationOperator) {
			mulArg1 = (MultiplicationOperator) argumentOne;
			//System.out.println("Add : Mul1");
		}
		if (argumentOne instanceof DivisionOperator) {
			divArg1 = (DivisionOperator) argumentOne;
			//System.out.println("Add : Div1");
		}

		// Sensors
		if (argumentOne instanceof FuelLeft) {
			fuelLeftArg1 = (FuelLeft) argumentOne;
			//System.out.println("Add : FuelLeft1");
		}
		if (argumentOne instanceof OpponentLeftRight) {
			oppLRArg1 = (OpponentLeftRight) argumentOne;
			//System.out.println("Add : oppLR1");
		}
		if (argumentOne instanceof OpponentFrontBack) {
			oppFBArg1 = (OpponentFrontBack) argumentOne;
			//System.out.println("Add : oppFB1");
		}
		if (argumentOne instanceof NumberBarrels) {
			numBarrelsArg1 = (NumberBarrels) argumentOne;
			//System.out.println("Add : numBarrels1");
		}
		if (argumentOne instanceof BarrelLeftRight) {
			barrelLRArg1 = (BarrelLeftRight) argumentOne;
			//System.out.println("Add : barrelLR1");
		}
		if (argumentOne instanceof BarrelFrontBack) {
			barrelFBArg1 = (BarrelFrontBack) argumentOne;
			//System.out.println("Add : barrelFB1");
		}
		if (argumentOne instanceof WallDistance) {
			wallDistArg1 = (WallDistance) argumentOne;
			//System.out.println("Add : wallDist1");
		}

		// Variable
		if (argumentOne instanceof AssignmentNode) {
			AssignmentNode temp = (AssignmentNode) argumentOne;
			// variable = (Variable) expression;
			for (Variable v : variables.keySet()) {
				if (v.getVariableName().equals(temp.getVariableName())) {
					variable1 = (Variable) v;
				}
			}
		}

		// Argument Two
		// Number
		// int amountArg2 = -1;
		Number amountArg2 = null;

		// Operators
		AdditionOperator addArg2 = null;
		SubtractionOperator subArg2 = null;
		DivisionOperator divArg2 = null;
		MultiplicationOperator mulArg2 = null;

		// Sensors
		FuelLeft fuelLeftArg2 = null;
		OpponentLeftRight oppLRArg2 = null;
		OpponentFrontBack oppFBArg2 = null;
		NumberBarrels numBarrelsArg2 = null;
		BarrelLeftRight barrelLRArg2 = null;
		BarrelFrontBack barrelFBArg2 = null;
		WallDistance wallDistArg2 = null;

		// Variable
		Variable variable2 = null;

		if (argumentTwo instanceof Number) {
			Number number = (Number) argumentTwo;
			amountArg2 = number;
			//System.out.println("Add : Number2");
		}

		// Operators
		if (argumentTwo instanceof AdditionOperator) {
			addArg2 = (AdditionOperator) argumentTwo;
			//System.out.println("Add : Add2");
		}
		if (argumentTwo instanceof SubtractionOperator) {
			subArg2 = (SubtractionOperator) argumentTwo;
			//System.out.println("Add : Sub2");
		}
		if (argumentTwo instanceof MultiplicationOperator) {
			mulArg2 = (MultiplicationOperator) argumentTwo;
			//System.out.println("Add : Mul2");
		}
		if (argumentTwo instanceof DivisionOperator) {
			divArg2 = (DivisionOperator) argumentTwo;
			//System.out.println("Add : Div2");
		}

		// Sensors
		if (argumentTwo instanceof FuelLeft) {
			fuelLeftArg2 = (FuelLeft) argumentTwo;
			//System.out.println("Add : fuelLeft2");
		}
		if (argumentTwo instanceof OpponentLeftRight) {
			oppLRArg2 = (OpponentLeftRight) argumentTwo;
			//System.out.println("Add : oppLR2");
		}
		if (argumentTwo instanceof OpponentFrontBack) {
			oppFBArg2 = (OpponentFrontBack) argumentTwo;
			//System.out.println("Add : oppFB2");
		}
		if (argumentTwo instanceof NumberBarrels) {
			numBarrelsArg2 = (NumberBarrels) argumentTwo;
			//System.out.println("Add : numBarrels2");
		}
		if (argumentTwo instanceof BarrelLeftRight) {
			barrelLRArg2 = (BarrelLeftRight) argumentTwo;
			//System.out.println("Add : barrelLR2");
		}
		if (argumentTwo instanceof BarrelFrontBack) {
			barrelFBArg2 = (BarrelFrontBack) argumentTwo;
			//System.out.println("Add : barrelFB2");
		}
		if (argumentTwo instanceof WallDistance) {
			wallDistArg2 = (WallDistance) argumentTwo;
			//System.out.println("Add : wallDist2");
		}

		// Variable
		if (argumentTwo instanceof AssignmentNode) {
			AssignmentNode temp = (AssignmentNode) argumentTwo;
			// variable = (Variable) expression;
			for (Variable v : variables.keySet()) {
				if (v.getVariableName().equals(temp.getVariableName())) {
					variable2 = (Variable) v;
				}
			}
		}

		// ARGUMENT ONE
		// If it's a number
		if (amountArg1 != null) {
			argOneValue = amountArg1;
		}
		// If it's an Operator
		else if (addArg1 != null) {
			argOneValue = new Number(addArg1.evaluate(robot, variables));
		} else if (subArg1 != null) {
			argOneValue = new Number(subArg1.evaluate(robot, variables));
		} else if (divArg1 != null) {
			argOneValue = new Number(divArg1.evaluate(robot, variables));
		} else if (mulArg1 != null) {
			argOneValue = new Number(mulArg1.evaluate(robot, variables));
		}
		// If it's a Sensor
		else if (fuelLeftArg1 != null) {
			argOneValue = new Number(fuelLeftArg1.evaluate(robot, variables));

		} else if (oppLRArg1 != null) {
			argOneValue = new Number(oppLRArg1.evaluate(robot, variables));

		} else if (oppFBArg1 != null) {
			argOneValue = new Number(oppFBArg1.evaluate(robot, variables));

		} else if (numBarrelsArg1 != null) {
			argOneValue = new Number(numBarrelsArg1.evaluate(robot, variables));

		} else if (barrelLRArg1 != null) {
			argOneValue = new Number(barrelLRArg1.evaluate(robot, variables));

		} else if (barrelFBArg1 != null) {
			argOneValue = new Number(barrelFBArg1.evaluate(robot, variables));

		} else if (wallDistArg1 != null) {
			argOneValue = new Number(wallDistArg1.evaluate(robot, variables));

		}
		// Variable
		else if (variable1 != null) {
			argOneValue = new Number(variable1.evaluate(robot, variables));
		}

		// ARGUMENT TWO
		// If it's a number
		if (amountArg2 != null) {
			argTwoValue = amountArg2;
		}
		// If it's an Operator
		else if (addArg2 != null) {
			argTwoValue = new Number(addArg2.evaluate(robot, variables));
		} else if (subArg2 != null) {
			argTwoValue = new Number(subArg2.evaluate(robot, variables));
		} else if (divArg2 != null) {
			argTwoValue = new Number(divArg2.evaluate(robot, variables));
		} else if (mulArg2 != null) {
			argTwoValue = new Number(mulArg2.evaluate(robot, variables));
		}
		// If it's a Sensor
		else if (fuelLeftArg2 != null) {
			argTwoValue = new Number(fuelLeftArg2.evaluate(robot, variables));

		} else if (oppLRArg2 != null) {
			argTwoValue = new Number(oppLRArg2.evaluate(robot, variables));

		} else if (oppFBArg2 != null) {
			argTwoValue = new Number(oppFBArg2.evaluate(robot, variables));

		} else if (numBarrelsArg2 != null) {
			argTwoValue = new Number(numBarrelsArg2.evaluate(robot, variables));

		} else if (barrelLRArg2 != null) {
			argTwoValue = new Number(barrelLRArg2.evaluate(robot, variables));

		} else if (barrelFBArg2 != null) {
			argTwoValue = new Number(barrelFBArg2.evaluate(robot, variables));

		} else if (wallDistArg2 != null) {
			argTwoValue = new Number(wallDistArg2.evaluate(robot, variables));

		}
		// Variable
		else if (variable2 != null) {
			argTwoValue = new Number(variable2.evaluate(robot, variables));
		}

		if (argOneValue == null) {
			//System.out.println("Add: arg1 null");
		}
		if (argTwoValue == null) {
			//System.out.println("Add: arg2 null");
		}

		//System.out.println("Addition: Adding " + argOneValue.getNumber()+ " + " + argTwoValue.getNumber());

		int result = (argOneValue.getNumber() + argTwoValue.getNumber());

		return result;
	}

	@Override
	public boolean parse(Scanner s) {
		//System.out.println("AdditionOperator: Parse");
		//System.out.println("AdditionOperator Next: " + s.next());
		s.next();

		while (argumentOne == null || argumentTwo == null) {
			//System.out.println("AdditionOperator: Loop");

			if (!s.hasNext("-?[1-9][0-9]*|0|fuelLeft|oppLR|oppFB|numBarrels|barrelLR|barrelFB|wallDist|add|mul|div|sub")) {
				//System.out.println("AdditionOperator Loop Next: " + s.next());
				s.next();
			}

			// Numbers
			if (s.hasNext("-?[1-9][0-9]*|0")) {
				//System.out.println("AdditionOperator: Number");

				if (argumentOne == null) {
					argumentOne = new Number(s.nextInt());

					if (!s.hasNext("\\,|\\)|\\(")) {
						Parser.fail("Addition Operator does not have a comma.", s);
					}
				} else if (argumentTwo == null) {
					argumentTwo = new Number(s.nextInt());
				}
			}

			// Sensors
			if (s.hasNext("fuelLeft")) {
				//System.out.println("AdditionOperator: fuelLeft");
				//System.out.println("AdditionOperator Next: " + s.next());
				s.next();

				if (argumentOne == null) {
					argumentOne = new FuelLeft();
					if (!s.hasNext("\\,|\\)|\\(")) {
						Parser.fail("Addition Operator does not have a comma.", s);
					}
				} else if (argumentTwo == null) {
					argumentTwo = new FuelLeft();
				}
			}
			if (s.hasNext("oppLR")) {
				//System.out.println("AdditionOperator: oppLR");
				//System.out.println("AdditionOperator Next: " + s.next());
				s.next();

				if (argumentOne == null) {
					argumentOne = new OpponentLeftRight();
					if (!s.hasNext("\\,|\\)|\\(")) {
						Parser.fail("Addition Operator does not have a comma.", s);
					}
				} else if (argumentTwo == null) {
					argumentTwo = new OpponentLeftRight();
				}
			}
			if (s.hasNext("oppFB")) {
				//System.out.println("AdditionOperator: oppFB");
				//System.out.println("AdditionOperator Next: " + s.next());
				s.next();

				if (argumentOne == null) {
					argumentOne = new OpponentFrontBack();
					if (!s.hasNext("\\,|\\)|\\(")) {
						Parser.fail("Addition Operator does not have a comma.", s);
					}
				} else if (argumentTwo == null) {
					argumentTwo = new OpponentFrontBack();
				}
			}
			if (s.hasNext("numBarrels")) {
				//System.out.println("AdditionOperator: numBarrels");
				//System.out.println("AdditionOperator Next: " + s.next());
				s.next();

				if (argumentOne == null) {
					argumentOne = new NumberBarrels();
					if (!s.hasNext("\\,|\\)|\\(")) {
						Parser.fail("Addition Operator does not have a comma.", s);
					}
				} else if (argumentTwo == null) {
					argumentTwo = new NumberBarrels();
				}
			}
			if (s.hasNext("barrelLR")) {
				//System.out.println("AdditionOperator: barrelLR");
				//System.out.println("AdditionOperator Next: " + s.next());
				s.next();

				BarrelLeftRight barrelLR = new BarrelLeftRight();
				barrelLR.parse(s);

				if (argumentOne == null) {
					argumentOne = barrelLR;
					if (!s.hasNext("\\,|\\)|\\(")) {
						Parser.fail("Addition Operator does not have a comma.", s);
					}
				} else if (argumentTwo == null) {
					argumentTwo = barrelLR;
				}
			}
			if (s.hasNext("barrelFB")) {
				//System.out.println("AdditionOperator: barrelFB");
				//System.out.println("AdditionOperator Next: " + s.next());
				s.next();

				BarrelFrontBack barrelFB = new BarrelFrontBack();
				barrelFB.parse(s);

				if (argumentOne == null) {
					argumentOne = barrelFB;
					if (!s.hasNext("\\,|\\)|\\(")) {
						Parser.fail("Addition Operator does not have a comma.", s);
					}
				} else if (argumentTwo == null) {
					argumentTwo = barrelFB;
				}
			}
			if (s.hasNext("wallDist")) {
				//System.out.println("AdditionOperator: wallDist");
				//System.out.println("AdditionOperator Next: " + s.next());
				s.next();

				if (argumentOne == null) {
					argumentOne = new WallDistance();
					if (!s.hasNext("\\,|\\)|\\(")) {
						Parser.fail("Addition Operator does not have a comma.", s);
					}
				} else if (argumentTwo == null) {
					argumentTwo = new WallDistance();
				}
			}

			// Operators
			if (s.hasNext("add")) {
				//System.out.println("AdditionOperator: Operator Add");
				//System.out.println("AdditionOperator Next: " + s.next());
				s.next();

				AdditionOperator add = new AdditionOperator();

				if (argumentOne == null) {
					argumentOne = add;
					if (!s.hasNext("\\,|\\)|\\(")) {
						Parser.fail("Addition Operator does not have a comma.", s);
					}
				} else if (argumentTwo == null) {
					argumentTwo = add;
				}
			}
			if (s.hasNext("sub")) {
				//System.out.println("AdditionOperator: Operator Subtraction");
				//System.out.println("AdditionOperator Next: " + s.next());
				s.next();

				SubtractionOperator sub = new SubtractionOperator();
				sub.parse(s);

				if (argumentOne == null) {
					argumentOne = sub;
					if (!s.hasNext("\\,|\\)|\\(")) {
						Parser.fail("Addition Operator does not have a comma.", s);
					}
				} else if (argumentTwo == null) {
					argumentTwo = sub;
				}
			}
			if (s.hasNext("mul")) {
				//System.out.println("AdditionOperator: Operator Multiplication");
				//System.out.println("AdditionOperator Next: " + s.next());
				s.next();

				MultiplicationOperator mul = new MultiplicationOperator();
				mul.parse(s);

				if (argumentOne == null) {
					argumentOne = mul;
					if (!s.hasNext("\\,|\\)|\\(")) {
						Parser.fail("Addition Operator does not have a comma.", s);
					}
				} else if (argumentTwo == null) {
					argumentTwo = mul;
				}
			}
			if (s.hasNext("div")) {
				//System.out.println("AdditionOperator: Operator Division");
				//System.out.println("AdditionOperator Next: " + s.next());
				s.next();

				DivisionOperator div = new DivisionOperator();
				div.parse(s);

				if (argumentOne == null) {
					argumentOne = div;
					if (!s.hasNext("\\,|\\)|\\(")) {
						Parser.fail("Addition Operator does not have a comma.", s);
					}
				} else if (argumentTwo == null) {
					argumentTwo = div;
				}
			}

			// Assignment
			if (s.hasNext("\\$[A-Za-z][A-Za-z0-9]*")) {
				//System.out.println("AdditionOperator: Assignment");
				//System.out.println("AdditionOperator Next: " + s.next());
				s.next();

				AssignmentNode assig = new AssignmentNode();
				assig.parse(s);

				if (argumentOne == null) {
					argumentOne = assig;
					if (!s.hasNext("\\,|\\)|\\(")) {
						Parser.fail("Addition Operator does not have a comma.", s);
					}
				} else if (argumentTwo == null) {
					argumentTwo = assig;
				}
			}
		}

		if (argumentOne != null && argumentTwo != null) {
			//System.out.println("AdditionOperator: Expression was not null. RETURNING TRUE.");
			return true;
		}

		//System.out.println("AdditionOperator: All were null. RETURNING FALSE.");
		Parser.fail("Addition Operator failed. Both arguments must not be null.", s);
		return false;
	}

	public String toString() {
		return "add(" + argumentOne.toString() + "," + argumentTwo.toString() + ")";
	}

}
