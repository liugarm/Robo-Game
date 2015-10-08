import java.util.Map;
import java.util.Scanner;

public class LessThanCondition implements Condition {

	private Expression argumentOne;
	private Expression argumentTwo;

	@Override
	public boolean evaluate(Robot robot,Map<Variable,Expression> variables) {
		//Result values
		Number argOneValue = null;
		Number argTwoValue = null;

		//Argument One
		//Number
		Number amountArg1 = null;

		//Operators
		AdditionOperator addArg1 = null;
		SubtractionOperator subArg1 = null;
		DivisionOperator divArg1 = null;
		MultiplicationOperator mulArg1 = null;

		//Sensors
		FuelLeft fuelLeftArg1 = null;
		OpponentLeftRight oppLRArg1 = null;
		OpponentFrontBack oppFBArg1 = null;
		NumberBarrels numBarrelsArg1 = null;
		BarrelLeftRight barrelLRArg1 = null;
		BarrelFrontBack barrelFBArg1 = null;
		WallDistance wallDistArg1 = null;

		//Variable
		Variable variable1 = null;

		if (argumentOne instanceof Number) {
			Number number = (Number) argumentOne;
			amountArg1 = number;
		}

		//Operators
		if (argumentOne instanceof AdditionOperator) {
			addArg1 = (AdditionOperator) argumentOne;
		}
		if (argumentOne instanceof SubtractionOperator) {
			subArg1 = (SubtractionOperator) argumentOne;
		}
		if (argumentOne instanceof MultiplicationOperator) {
			mulArg1 = (MultiplicationOperator) argumentOne;
		}
		if (argumentOne instanceof DivisionOperator) {
			divArg1 = (DivisionOperator) argumentOne;
		}

		//Sensors
		if (argumentOne instanceof FuelLeft) {
			fuelLeftArg1 = (FuelLeft) argumentOne;
		}
		if (argumentOne instanceof OpponentLeftRight) {
			oppLRArg1 = (OpponentLeftRight) argumentOne;
		}
		if (argumentOne instanceof OpponentFrontBack) {
			oppFBArg1 = (OpponentFrontBack) argumentOne;
		}
		if (argumentOne instanceof NumberBarrels) {
			numBarrelsArg1 = (NumberBarrels) argumentOne;
		}
		if (argumentOne instanceof BarrelLeftRight) {
			barrelLRArg1 = (BarrelLeftRight) argumentOne;
		}
		if (argumentOne instanceof BarrelFrontBack) {
			barrelFBArg1 = (BarrelFrontBack) argumentOne;
		}
		if (argumentOne instanceof WallDistance) {
			wallDistArg1 = (WallDistance) argumentOne;
		}

		// Variable
		if (argumentOne instanceof AssignmentNode) {
			//System.out.println("LessThanCondition: In ArgumentOne Variable");
			AssignmentNode temp = (AssignmentNode) argumentOne;
			// variable = (Variable) expression;
			for (Variable v : variables.keySet()) {
				//System.out.println("LessThanCondition: Searching map for argument 1 variable");
				if (v.getVariableName().equals(temp.getVariableName())) {
					//System.out.println("LessThanCondition: Variable found in map");
					variable1 = (Variable) v;
				}
			}
		}

		/*if(argumentOne instanceof AssignmentNode){
			AssignmentNode assig = (AssignmentNode) argumentOne;
			assig.execute(robot, variables);

			for(Variable v: variables.keySet()){
				if(v.getVariableName().equals(assig.getVariableName())){
					variable1 = v;
				}
			}
		}*/

		//Argument Two
		//Number
		Number amountArg2 = null;

		//Operators
		AdditionOperator addArg2 = null;
		SubtractionOperator subArg2 = null;
		DivisionOperator divArg2 = null;
		MultiplicationOperator mulArg2 = null;

		//Sensors
		FuelLeft fuelLeftArg2 = null;
		OpponentLeftRight oppLRArg2 = null;
		OpponentFrontBack oppFBArg2 = null;
		NumberBarrels numBarrelsArg2 = null;
		BarrelLeftRight barrelLRArg2 = null;
		BarrelFrontBack barrelFBArg2 = null;
		WallDistance wallDistArg2 = null;

		//Variable
		Variable variable2 = null;

		if (argumentTwo instanceof Number) {
			Number number = (Number) argumentTwo;
			amountArg2 = number;
		}

		//Operators
		if (argumentTwo instanceof AdditionOperator) {
			addArg2 = (AdditionOperator) argumentTwo;
		}
		if (argumentTwo instanceof SubtractionOperator) {
			subArg2 = (SubtractionOperator) argumentTwo;
		}
		if (argumentTwo instanceof MultiplicationOperator) {
			mulArg2 = (MultiplicationOperator) argumentTwo;
		}
		if (argumentTwo instanceof DivisionOperator) {
			divArg2 = (DivisionOperator) argumentTwo;
		}

		//Sensors
		if (argumentTwo instanceof FuelLeft) {
			fuelLeftArg2 = (FuelLeft) argumentTwo;
		}
		if (argumentTwo instanceof OpponentLeftRight) {
			oppLRArg2 = (OpponentLeftRight) argumentTwo;
		}
		if (argumentTwo instanceof OpponentFrontBack) {
			oppFBArg2 = (OpponentFrontBack) argumentTwo;
		}
		if (argumentTwo instanceof NumberBarrels) {
			numBarrelsArg2 = (NumberBarrels) argumentTwo;
		}
		if (argumentTwo instanceof BarrelLeftRight) {
			barrelLRArg2 = (BarrelLeftRight) argumentTwo;
		}
		if (argumentTwo instanceof BarrelFrontBack) {
			barrelFBArg2 = (BarrelFrontBack) argumentTwo;
		}
		if (argumentTwo instanceof WallDistance) {
			wallDistArg2 = (WallDistance) argumentTwo;
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

		/*if(argumentTwo instanceof AssignmentNode){
			AssignmentNode assig = (AssignmentNode) argumentTwo;
			assig.execute(robot, variables);

			for(Variable v: variables.keySet()){
				if(v.getVariableName().equals(assig.getVariableName())){
					variable2 = v;
				}
			}
		}*/

		//ARGUMENT ONE
		//If it's a number
		if (amountArg1 != null) {
			argOneValue = amountArg1;
		}
		//If it's an Operator
		else if (addArg1 != null) {
			argOneValue = new Number(addArg1.evaluate(robot,variables));
		} else if (subArg1 != null) {
			argOneValue = new Number(subArg1.evaluate(robot,variables));
		} else if (divArg1 != null) {
			argOneValue = new Number(divArg1.evaluate(robot,variables));
		} else if (mulArg1 != null) {
			argOneValue = new Number(mulArg1.evaluate(robot,variables));
		}
		//If it's a Sensor
		else if (fuelLeftArg1 != null) {
			argOneValue = new Number(fuelLeftArg1.evaluate(robot,variables));

		} else if (oppLRArg1 != null) {
			argOneValue = new Number(oppLRArg1.evaluate(robot,variables));

		} else if (oppFBArg1 != null) {
			argOneValue = new Number(oppFBArg1.evaluate(robot,variables));

		} else if (numBarrelsArg1 != null) {
			argOneValue = new Number(numBarrelsArg1.evaluate(robot,variables));

		} else if (barrelLRArg1 != null) {
			argOneValue = new Number(barrelLRArg1.evaluate(robot,variables));

		} else if (barrelFBArg1 != null) {
			argOneValue = new Number(barrelFBArg1.evaluate(robot,variables));

		} else if (wallDistArg1 != null) {
			argOneValue = new Number(wallDistArg1.evaluate(robot,variables));

		}
		//Variable
		else if (variable1 != null) {
			argOneValue = new Number(variable1.evaluate(robot,variables));
		}

		//ARGUMENT TWO
		//If it's a number
		if (amountArg2 != null) {
			argTwoValue = amountArg2;
		}
		//If it's an Operator
		else if (addArg2 != null) {
			argTwoValue = new Number(addArg2.evaluate(robot,variables));
		} else if (subArg2 != null) {
			argTwoValue = new Number(subArg2.evaluate(robot,variables));
		} else if (divArg2 != null) {
			argTwoValue = new Number(divArg2.evaluate(robot,variables));
		} else if (mulArg2 != null) {
			argTwoValue = new Number(mulArg2.evaluate(robot,variables));
		}
		//If it's a Sensor
		else if (fuelLeftArg2 != null) {
			argTwoValue = new Number(fuelLeftArg2.evaluate(robot,variables));

		} else if (oppLRArg2 != null) {
			argTwoValue = new Number(oppLRArg2.evaluate(robot,variables));

		} else if (oppFBArg2 != null) {
			argTwoValue = new Number(oppFBArg2.evaluate(robot,variables));

		} else if (numBarrelsArg2 != null) {
			argTwoValue = new Number(numBarrelsArg2.evaluate(robot,variables));

		} else if (barrelLRArg2 != null) {
			argTwoValue = new Number(barrelLRArg2.evaluate(robot,variables));

		} else if (barrelFBArg2 != null) {
			argTwoValue = new Number(barrelFBArg2.evaluate(robot,variables));

		} else if (wallDistArg2 != null) {
			argTwoValue = new Number(wallDistArg2.evaluate(robot,variables));
		}
		//Variable
		else if(variable2!=null){
			argTwoValue = new Number(variable2.evaluate(robot,variables));
		}


		if(argOneValue==null){
			//System.out.println("LessThanCondition: Arg1 is null");
		}
		if(argTwoValue==null){
			//System.out.println("LessThanCondition: Arg2 is null");
		}

		//System.out.println("LessThanCondition: Is " + argOneValue.getNumber() + "<" + argTwoValue.getNumber());

		if (argOneValue.getNumber() < argTwoValue.getNumber()) {
			//System.out.println("LessThanCondition: Returning true");
			return true;
		} else {
			//System.out.println("LessThanCondition: Returning false");
			return false;
		}
	}

	public boolean parse(Scanner s) {
		//System.out.println("LT: Parse");

		if (!s.hasNext("move|turnL|turnR|turnAround|shieldOn|shieldOff|takeFuel|wait|fuelLeft|oppLR|oppFB|numBarrels|barrelLR|barrelFB|wallDist|add|sub|mul|div|-?[1-9][0-9]*|0|\\$[A-Za-z][A-Za-z0-9]*")) {
			//System.out.println("LT next: " + s.next());
			s.next();
		}

		while (Parser.gobble(Parser.OPENPAREN, s) || Parser.gobble(Parser.CLOSEPAREN, s) || Parser.gobble(Parser.OPENBRACE, s) || Parser.gobble(Parser.CLOSEBRACE, s) || Parser.gobble(Parser.COMMA, s)) {

			Parser.gobble(Parser.OPENPAREN, s);
			Parser.gobble(Parser.CLOSEPAREN, s);
			Parser.gobble(Parser.OPENBRACE, s);
			Parser.gobble(Parser.CLOSEBRACE, s);
			Parser.gobble(Parser.COMMA, s);
		}

		while (argumentOne == null || argumentTwo == null) {

			//System.out.println("LT: Looping");
			if (!s.hasNext("move|turnL|turnR|turnAround|shieldOn|shieldOff|takeFuel|wait|fuelLeft|oppLR|oppFB|numBarrels|barrelLR|barrelFB|wallDist|add|sub|mul|div|-?[1-9][0-9]*|0|\\$[A-Za-z][A-Za-z0-9]*")) {
				//System.out.println("LT next: " + s.next());
				s.next();
			}

			while (Parser.gobble(Parser.OPENPAREN, s) || Parser.gobble(Parser.CLOSEPAREN, s) || Parser.gobble(Parser.OPENBRACE, s) || Parser.gobble(Parser.CLOSEBRACE, s) || Parser.gobble(Parser.COMMA, s)) {

				Parser.gobble(Parser.OPENPAREN, s);
				Parser.gobble(Parser.CLOSEPAREN, s);
				Parser.gobble(Parser.OPENBRACE, s);
				Parser.gobble(Parser.CLOSEBRACE, s);
				Parser.gobble(Parser.COMMA, s);
			}

			if (s.hasNext("fuelLeft")) {
				//System.out.println("LT: fuelLeft");

				while (Parser.gobble(Parser.OPENPAREN, s) || Parser.gobble(Parser.CLOSEPAREN, s) || Parser.gobble(Parser.OPENBRACE, s) || Parser.gobble(Parser.CLOSEBRACE, s) || Parser.gobble(Parser.COMMA, s)) {

					Parser.gobble(Parser.OPENPAREN, s);
					Parser.gobble(Parser.CLOSEPAREN, s);
					Parser.gobble(Parser.OPENBRACE, s);
					Parser.gobble(Parser.CLOSEBRACE, s);
					Parser.gobble(Parser.COMMA, s);
				}

				if (argumentOne == null) {
					argumentOne = new FuelLeft();
					//System.out.println("fuelLeft NEXT: " + s.next());
					s.next();

					if(!s.hasNext("\\,|\\)|\\(")){
						Parser.fail("LessThanCondition expected ,",s);
					}
					//System.out.println("fuelleft NEXT: " + s.next());
					s.next();
				} else if (argumentTwo == null) {
					argumentTwo = new FuelLeft();
				}
			}
			if (s.hasNext("oppLR")) {
				//System.out.println("LT: oppLR");

				while (Parser.gobble(Parser.OPENPAREN, s) || Parser.gobble(Parser.CLOSEPAREN, s) || Parser.gobble(Parser.OPENBRACE, s) || Parser.gobble(Parser.CLOSEBRACE, s) || Parser.gobble(Parser.COMMA, s)) {

					Parser.gobble(Parser.OPENPAREN, s);
					Parser.gobble(Parser.CLOSEPAREN, s);
					Parser.gobble(Parser.OPENBRACE, s);
					Parser.gobble(Parser.CLOSEBRACE, s);
					Parser.gobble(Parser.COMMA, s);
				}

				if (argumentOne == null) {
					argumentOne = new OpponentLeftRight();
					//System.out.println("LT oppLR NEXT: " + s.next());
					s.next();

					if(!s.hasNext("\\,|\\)|\\(")){
						Parser.fail("LessThanCondition expected ,",s);
					}

					//System.out.println("LT oppLR NEXT: " + s.next());
					s.next();
				} else if (argumentTwo == null) {
					argumentTwo = new OpponentLeftRight();
				}
			}
			if (s.hasNext("oppFB")) {
				//System.out.println("LT: oppFB");

				while (Parser.gobble(Parser.OPENPAREN, s) || Parser.gobble(Parser.CLOSEPAREN, s) || Parser.gobble(Parser.OPENBRACE, s) || Parser.gobble(Parser.CLOSEBRACE, s) || Parser.gobble(Parser.COMMA, s)) {

					Parser.gobble(Parser.OPENPAREN, s);
					Parser.gobble(Parser.CLOSEPAREN, s);
					Parser.gobble(Parser.OPENBRACE, s);
					Parser.gobble(Parser.CLOSEBRACE, s);
					Parser.gobble(Parser.COMMA, s);
				}

				if (argumentOne == null) {
					argumentOne = new OpponentFrontBack();
					//System.out.println("LT oppFB NEXT: " + s.next());
					s.next();

					if(!s.hasNext("\\,|\\)|\\(")){
						Parser.fail("LessThanCondition expected ,",s);
					}

					//System.out.println("LT oppFB NEXT: " + s.next());
					s.next();
				} else if (argumentTwo == null) {
					argumentTwo = new OpponentFrontBack();
				}
			}
			if (s.hasNext("numBarrels")) {

				//System.out.println("LT: numBarrels");

				if (argumentOne == null) {
					argumentOne = new NumberBarrels();
					//System.out.println("LT numBarrels NEXT: " + s.next());
					s.next();

					if(!s.hasNext("\\,|\\)|\\(")){
						Parser.fail("LessThanCondition expected ,",s);
					}

					//System.out.println("LT numBarrels NEXT: " + s.next());
					s.next();
				} else if (argumentTwo == null) {
					argumentTwo = new NumberBarrels();
				}

				while (Parser.gobble(Parser.OPENPAREN, s) || Parser.gobble(Parser.CLOSEPAREN, s) || Parser.gobble(Parser.OPENBRACE, s) || Parser.gobble(Parser.CLOSEBRACE, s) || Parser.gobble(Parser.COMMA, s)) {

					Parser.gobble(Parser.OPENPAREN, s);
					Parser.gobble(Parser.CLOSEPAREN, s);
					Parser.gobble(Parser.OPENBRACE, s);
					Parser.gobble(Parser.CLOSEBRACE, s);
					Parser.gobble(Parser.COMMA, s);
				}
			}
			if (s.hasNext("barrelLR")) {

				//System.out.println("LT: barrelLR");
				BarrelLeftRight barrelLR = new BarrelLeftRight();
				barrelLR.parse(s);

				if (argumentOne == null) {
					argumentOne = barrelLR;
					//System.out.println("LT barrelLR NEXT: " + s.next());

					if(!s.hasNext("\\,|\\)|\\(")){
						Parser.fail("LessThanCondition expected ,",s);
					}

					//System.out.println("LT barrelLR NEXT: " + s.next());
					s.next();
				} else if (argumentTwo == null) {
					argumentTwo = barrelLR;
				}

				while (Parser.gobble(Parser.OPENPAREN, s) || Parser.gobble(Parser.CLOSEPAREN, s) || Parser.gobble(Parser.OPENBRACE, s) || Parser.gobble(Parser.CLOSEBRACE, s) || Parser.gobble(Parser.COMMA, s)) {

					Parser.gobble(Parser.OPENPAREN, s);
					Parser.gobble(Parser.CLOSEPAREN, s);
					Parser.gobble(Parser.OPENBRACE, s);
					Parser.gobble(Parser.CLOSEBRACE, s);
					Parser.gobble(Parser.COMMA, s);
				}
			}
			if (s.hasNext("barrelFB")) {

				//System.out.println("LT: barrelFB");
				
				BarrelFrontBack barrelFB = new BarrelFrontBack();
				barrelFB.parse(s);

				if (argumentOne == null) {
					argumentOne = barrelFB;
					//System.out.println("LT barrelFB NEXT: " + s.next());

					if(!s.hasNext("\\,|\\)|\\(")){
						Parser.fail("LessThanCondition expected ,",s);
					}

					//System.out.println("LT barrelFB NEXT: " + s.next());
					s.next();
				} else if (argumentTwo == null) {
					argumentTwo = barrelFB;
				}

				while (Parser.gobble(Parser.OPENPAREN, s) || Parser.gobble(Parser.CLOSEPAREN, s) || Parser.gobble(Parser.OPENBRACE, s) || Parser.gobble(Parser.CLOSEBRACE, s) || Parser.gobble(Parser.COMMA, s)) {

					Parser.gobble(Parser.OPENPAREN, s);
					Parser.gobble(Parser.CLOSEPAREN, s);
					Parser.gobble(Parser.OPENBRACE, s);
					Parser.gobble(Parser.CLOSEBRACE, s);
					Parser.gobble(Parser.COMMA, s);
				}
			}
			if (s.hasNext("wallDist")) {

				//System.out.println("LT: wallDist");

				if (argumentOne == null) {
					argumentOne = new WallDistance();
					//System.out.println("LT wallDIST NEXT: " + s.next());

					if(!s.hasNext("\\,|\\)|\\(")){
						Parser.fail("LessThanCondition expected ,",s);
					}

					//System.out.println("LT wallDIST NEXT: " + s.next());
					s.next();
				} else if (argumentTwo == null) {
					argumentTwo = new WallDistance();
				}

				while (Parser.gobble(Parser.OPENPAREN, s) || Parser.gobble(Parser.CLOSEPAREN, s) || Parser.gobble(Parser.OPENBRACE, s) || Parser.gobble(Parser.CLOSEBRACE, s) || Parser.gobble(Parser.COMMA, s)) {

					Parser.gobble(Parser.OPENPAREN, s);
					Parser.gobble(Parser.CLOSEPAREN, s);
					Parser.gobble(Parser.OPENBRACE, s);
					Parser.gobble(Parser.CLOSEBRACE, s);
					Parser.gobble(Parser.COMMA, s);
				}
			}

			if (s.hasNext("-?[1-9][0-9]*|0")) {
				//System.out.println("LT: Number");

				if (argumentOne == null) {
					argumentOne = new Number(s.nextInt());
					//System.out.println("LT Number NEXT: " + s.next());
					s.next();

					if(!s.hasNext("\\,|\\)|\\(")){
						Parser.fail("LessThanCondition expected ,",s);
					}

					//System.out.println("LT Number NEXT: " + s.next());
					s.next();
				} else if (argumentTwo == null) {
					argumentTwo = new Number(s.nextInt());
				}
			}

			//EXPR Operators
			if (s.hasNext("add")) {
				//System.out.println("LT: Add");

				if (argumentOne == null) {
					AdditionOperator add = new AdditionOperator();
					add.parse(s);
					argumentOne = add;

					if(!s.hasNext("\\,|\\)|\\(")){
						Parser.fail("addLessThanCondition expected , ",s);
					}

				} else if (argumentTwo == null) {
					AdditionOperator add = new AdditionOperator();
					add.parse(s);
					argumentTwo = add;
				}
			}
			if (s.hasNext("sub")) {
				//System.out.println("LT: Subtract");

				if (argumentOne == null) {
					SubtractionOperator sub = new SubtractionOperator();
					sub.parse(s);
					argumentOne = sub;

					if(!s.hasNext("\\,|\\)|\\(")){
						Parser.fail("subLessThanCondition expected ,",s);
					}

				} else if (argumentTwo == null) {
					SubtractionOperator sub = new SubtractionOperator();
					sub.parse(s);
					argumentTwo = sub;
				}
			}
			if (s.hasNext("div")) {
				//System.out.println("LT: Division");

				if (argumentOne == null) {
					DivisionOperator div = new DivisionOperator();
					div.parse(s);
					argumentOne = div;

					if(!s.hasNext("\\,|\\)|\\(")){
						Parser.fail("divLessThanCondition expected ,",s);
					}

				} else if (argumentTwo == null) {
					DivisionOperator div = new DivisionOperator();
					div.parse(s);
					argumentTwo = div;
				}
			}
			if (s.hasNext("mul")) {
				//System.out.println("LT: Multiply");

				if (argumentOne == null) {
					MultiplicationOperator mul = new MultiplicationOperator();
					mul.parse(s);
					argumentOne = mul;

					if(!s.hasNext("\\,|\\)|\\(")){
						Parser.fail("mulLessThanCondition expected ,",s);
					}

				} else if (argumentTwo == null) {
					MultiplicationOperator mul = new MultiplicationOperator();
					mul.parse(s);
					argumentTwo = mul;
				}
			}

			//Assignment
			if (s.hasNext("\\$[A-Za-z][A-Za-z0-9]*")) {
				//System.out.println("LT: Assignment");

				if (argumentOne == null) {
					AssignmentNode assig = new AssignmentNode();
					assig.parse(s);
					argumentOne = assig;

					if(!s.hasNext("\\,|\\)|\\(")){
						Parser.fail("numLessThanCondition expected ,",s);
					}

				} else if (argumentTwo == null) {
					AssignmentNode assig = new AssignmentNode();
					assig.parse(s);
					argumentTwo = assig;
				}
			}

		}

		if (argumentOne != null && argumentTwo != null) {
			return true;
		}

		Parser.fail("LessThanCondition failed. Argument 1 and/or 2 were null.", s);

		return false;
	}

	public String toString() {
		return "lt" + "(" + argumentOne + "," + argumentTwo + ")";
	}

}
