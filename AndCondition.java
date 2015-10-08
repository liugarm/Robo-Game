import java.util.Map;
import java.util.Scanner;

public class AndCondition implements Condition {

	private Condition argumentOne;
	private Condition argumentTwo;

	@Override
	public boolean evaluate(Robot robot, Map<Variable, Expression> variables) {
		//System.out.println("And: Evaluate");

		//Result values
		boolean argOneValue = false;
		boolean argTwoValue = false;

		//Argument One
		LessThanCondition lt1 = null;
		GreaterThanCondition gt1 = null;
		EqualToCondition eq1 = null;
		AndCondition and1 = null;
		OrCondition or1 = null;
		NotCondition not1 = null;

		//Argument Two
		LessThanCondition lt2 = null;
		GreaterThanCondition gt2 = null;
		EqualToCondition eq2 = null;
		AndCondition and2 = null;
		OrCondition or2 = null;
		NotCondition not2 = null;

		if (argumentOne instanceof LessThanCondition) {
			lt1 = (LessThanCondition) argumentOne;
		} else if (argumentOne instanceof GreaterThanCondition) {
			gt1 = (GreaterThanCondition) argumentOne;
		} else if (argumentOne instanceof EqualToCondition) {
			eq1 = (EqualToCondition) argumentOne;
		} else if (argumentOne instanceof AndCondition) {
			and1 = (AndCondition) argumentOne;
		} else if (argumentOne instanceof OrCondition) {
			or1 = (OrCondition) argumentOne;
		} else if (argumentOne instanceof NotCondition) {
			not1 = (NotCondition) argumentOne;
		}

		if (argumentTwo instanceof LessThanCondition) {
			lt2 = (LessThanCondition) argumentTwo;
		} else if (argumentTwo instanceof GreaterThanCondition) {
			gt2 = (GreaterThanCondition) argumentTwo;
		} else if (argumentTwo instanceof EqualToCondition) {
			eq2 = (EqualToCondition) argumentTwo;
		} else if (argumentTwo instanceof AndCondition) {
			and2 = (AndCondition) argumentTwo;
		} else if (argumentTwo instanceof OrCondition) {
			or2 = (OrCondition) argumentTwo;
		} else if (argumentTwo instanceof NotCondition) {
			not2 = (NotCondition) argumentTwo;
		}

		//Calculate
		if (lt1 != null) {
			argOneValue = lt1.evaluate(robot,variables);
		} else if (gt1 != null) {
			argOneValue = gt1.evaluate(robot,variables);
		} else if (eq1 != null) {
			argOneValue = eq1.evaluate(robot,variables);
		} else if (and1 != null) {
			argOneValue = and1.evaluate(robot,variables);
		} else if (or1 != null) {
			argOneValue = or1.evaluate(robot,variables);
		} else if (not1 != null) {
			argOneValue = not1.evaluate(robot,variables);
		}

		if (lt2 != null) {
			argTwoValue = lt2.evaluate(robot,variables);
		} else if (gt2 != null) {
			argTwoValue = gt2.evaluate(robot,variables);
		} else if (eq2 != null) {
			argTwoValue = eq2.evaluate(robot,variables);
		} else if (and2 != null) {
			argTwoValue = and2.evaluate(robot,variables);
		} else if (or2 != null) {
			argTwoValue = or2.evaluate(robot,variables);
		} else if (not2 != null) {
			argTwoValue = not2.evaluate(robot,variables);
		}

		if (argOneValue && argTwoValue) {
			return true;
		}

		return false;
	}

	@Override
	public boolean parse(Scanner s) {
		//System.out.println("AndCondition: Parse");
		//System.out.println("AndCondition: Sucking in"+s.next());
		s.next();

		if (!s.hasNext("and|or|not|lt|gt|eq")) {
			//System.out.println("AndCondition Sucking in: " + s.next());
			s.next();
		}

		while (argumentOne == null || argumentTwo == null) {

			if (!s.hasNext("and|or|not|lt|gt|eq")) {
			//System.out.println("AndCondition: Next: " + s.next());
				s.next();
			}

			//And Or Not
			if (s.hasNext("and")) {
				//System.out.println("AndCondition: And");
				//System.out.println("AndCondition: " + s.next());
				s.next();

				if (!s.hasNext("\\(")) {
					Parser.fail("And Condition must have bracket after 'and'", s);
				}

				//System.out.println("AndCondition: " + s.next());
				s.next();

				Condition and = new AndCondition();
				and.parse(s);

				if (argumentOne == null) {
					System.out.println("AndCondition: ArgumentOne empty. Adding to ArgumentOne.");
					argumentOne = and;
				} else if (argumentOne != null) {
					System.out.println("AndCondition: ArgumentOne is not empty. Adding to ArgumentTwo.");
					argumentTwo = and;
				}
			}
			if (s.hasNext("or")) {
				//System.out.println("AndCondition: Or");
				//System.out.println("AndCondition: " + s.next());
				s.next();

				if (!s.hasNext("\\(")) {
					Parser.fail("Missing (", s);
				}

				//System.out.println("AndCondition: " + s.next());
				s.next();

				Condition or = new OrCondition();
				or.parse(s);

				if (argumentOne == null) {
					//System.out.println("AndCondition: ArgumentOne empty. Adding to ArgumentOne.");
					argumentOne = or;
				} else if (argumentOne != null) {
					//System.out.println("AndCondition: ArgumentOne is not empty. Adding to ArgumentTwo.");
					argumentTwo = or;
				}
			}
			if (s.hasNext("not")) {
				//System.out.println("AndCondition: Not");
				//System.out.println("AndCondition: " + s.next());
				s.next();

				if (!s.hasNext("\\(")) {
					Parser.fail("Missing (", s);
				}

				//System.out.println("AndCondition: " + s.next());
				s.next();

				Condition not = new OrCondition();
				not.parse(s);

				if (argumentOne == null) {
					//System.out.println("AndCondition: ArgumentOne empty. Adding to ArgumentOne.");
					argumentOne = not;
				} else if (argumentOne != null) {
					//System.out.println("AndCondition: ArgumentOne is not empty. Adding to ArgumentTwo.");
					argumentTwo = not;
				}
			}

			//lt gt eq
			if (s.hasNext("lt")) {
				//System.out.println("AndCondition: LT");
				//System.out.println("AndCondition: " + s.next());
				s.next();

				if (!s.hasNext("\\(")) {
					Parser.fail("Missing (", s);
				}

				//System.out.println("AndCondition: " + s.next());
				s.next();

				Condition lt = new LessThanCondition();
				lt.parse(s);

				if (argumentOne == null) {
					//System.out.println("AndCondition: ArgumentOne empty. Adding to ArgumentOne.");
					argumentOne = lt;
				} else if (argumentOne != null) {
					//System.out.println("AndCondition: ArgumentOne is not empty. Adding to ArgumentTwo.");
					argumentTwo = lt;
				}
			}
			if (s.hasNext("gt")) {
				//System.out.println("AndCondition: GT");
				//System.out.println("AndCondition: " + s.next());
				s.next();

				if (!s.hasNext("\\(")) {
					Parser.fail("Missing (", s);
				}

				//System.out.println("AndCondition: " + s.next());
				s.next();

				Condition gt = new GreaterThanCondition();
				gt.parse(s);

				if (argumentOne == null) {
					//System.out.println("AndCondition: ArgumentOne empty. Adding to ArgumentOne.");
					argumentOne = gt;
				} else if (argumentOne != null) {
					//System.out.println("AndCondition: ArgumentOne is not empty. Adding to ArgumentTwo.");
					argumentTwo = gt;
				}
			}
			if (s.hasNext("eq")) {
				//System.out.println("AndCondition: EQ");
				//System.out.println("AndCondition: " + s.next());
				s.next();

				if (!s.hasNext("\\(")) {
					Parser.fail("Missing (", s);
				}

				//System.out.println("AndCondition: " + s.next());
				s.next();

				Condition eq = new EqualToCondition();
				eq.parse(s);

				if (argumentOne == null) {
					//System.out.println("AndCondition: ArgumentOne empty. Adding to ArgumentOne.");
					argumentOne = eq;
				} else if (argumentOne != null) {
					//System.out.println("AndCondition: ArgumentOne is not empty. Adding to ArgumentTwo.");
					argumentTwo = eq;
				}
			}
		}

		//Returning true
		if (argumentOne != null && argumentTwo != null) {
			//System.out.println("AndCondition: ArgumentOne and ArgumentTwo were not null. Returning True.");
			return true;
		}

		//Returning false
		if (argumentOne == null) {
			Parser.fail("Argument One is empty.", s);
			//System.out.println("AndCondition: ArgumentOne was null. Returning false.");
		}
		if (argumentTwo == null) {
			Parser.fail("Argument Two is empty.", s);
			//System.out.println("AndCondition: ArgumentTwo was null. Returning false.");
		}

		return false;
	}

	public String toString() {
		return "and(" + argumentOne + "," + argumentTwo + ")";
	}

}
