import java.util.Map;
import java.util.Scanner;

public class OrCondition implements Condition {

	private Condition argumentOne;
	private Condition argumentTwo;

	@Override
	public boolean evaluate(Robot robot, Map<Variable, Expression> variables) {
		//System.out.println("Or: Evaluate");

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

		if(argumentOne instanceof LessThanCondition){
			lt1 = (LessThanCondition) argumentOne;
			//System.out.println("Or: "+lt1.toString());
		}
		else if(argumentOne instanceof GreaterThanCondition){
			gt1 = (GreaterThanCondition) argumentOne;
			//System.out.println("Or: "+gt1.toString());
		}
		else if(argumentOne instanceof EqualToCondition){
			eq1 = (EqualToCondition) argumentOne;
			//System.out.println("Or: "+eq1.toString());
		}
		else if(argumentOne instanceof AndCondition){
			and1 = (AndCondition) argumentOne;
			//System.out.println("Or: "+and1.toString());
		}
		else if(argumentOne instanceof OrCondition){
			or1 = (OrCondition) argumentOne;
			//System.out.println("Or: "+or1.toString());
		}
		else if(argumentOne instanceof NotCondition){
			not1 = (NotCondition) argumentOne;
			//System.out.println("Or: "+not1.toString());
		}

		if(argumentTwo instanceof LessThanCondition){
			lt2 = (LessThanCondition) argumentTwo;
			//System.out.println("Or: "+lt2.toString());
		}
		else if(argumentTwo instanceof GreaterThanCondition){
			gt2 = (GreaterThanCondition) argumentTwo;
			//System.out.println("Or: "+gt2.toString());
		}
		else if(argumentTwo instanceof EqualToCondition){
			eq2 = (EqualToCondition) argumentTwo;
			//System.out.println("Or: "+eq2.toString());
		}
		else if(argumentTwo instanceof AndCondition){
			and2 = (AndCondition) argumentTwo;
			//System.out.println("Or: "+and2.toString());
		}
		else if(argumentTwo instanceof OrCondition){
			or2 = (OrCondition) argumentTwo;
			//System.out.println("Or: "+or2.toString());
		}
		else if(argumentTwo instanceof NotCondition){
			not2 = (NotCondition) argumentTwo;
			//System.out.println("Or: "+not2.toString());
		}

		//Calculate
		if(lt1!=null){
			argOneValue = lt1.evaluate(robot,variables);
			//System.out.println("Or: LT1 Evaluate");
		}
		else if(gt1!=null){
			argOneValue = gt1.evaluate(robot,variables);
			//System.out.println("Or: GT1 Evaluate");
		}
		else if(eq1!=null){
			argOneValue = eq1.evaluate(robot,variables);
			//System.out.println("Or: EQ1 Evaluate");
		}
		else if(and1!=null){
			argOneValue = and1.evaluate(robot,variables);
			//System.out.println("Or: AND1 Evaluate");
		}
		else if(or1!=null){
			argOneValue = or1.evaluate(robot,variables);
			//System.out.println("Or: OR1 Evaluate");
		}
		else if(not1!=null){
			argOneValue = not1.evaluate(robot,variables);
			//System.out.println("Or: NOT1 Evaluate");
		}

		if(lt2!=null){
			argTwoValue = lt2.evaluate(robot,variables);
			//System.out.println("Or: LT2 Evaluate");
		}
		else if(gt2!=null){
			argTwoValue = gt2.evaluate(robot,variables);
			//System.out.println("Or: GT2 Evaluate");
		}
		else if(eq2!=null){
			argTwoValue = eq2.evaluate(robot,variables);
			//System.out.println("Or: EQ2 Evaluate");
		}
		else if(and2!=null){
			argTwoValue = and2.evaluate(robot,variables);
			//System.out.println("Or: AND2 Evaluate");
		}
		else if(or2!=null){
			argTwoValue = or2.evaluate(robot,variables);
			//System.out.println("Or: OR2 Evaluate");
		}
		else if(not2!=null){
			argTwoValue = not2.evaluate(robot,variables);
			//System.out.println("Or: NOT2 Evaluate");
		}

		//System.out.println("Or: ArgOne is "+argOneValue+"   ArgTwo is "+argTwoValue);

		if(argOneValue==true || argTwoValue==true){
			//System.out.println("Or: Returning true");
			return true;
		}
		else{
			//System.out.println("Or: Returning false");
			return false;
		}
	}

	@Override
	public boolean parse(Scanner s) {
		//System.out.println("OrCondition: Parse");
		//System.out.println("OrCondition Sucking in: "+s.next());
		s.next();

		while (argumentOne == null || argumentTwo == null) {

			if (!s.hasNext("and|or|not|lt|gt|eq")) {
				//System.out.println("OrCondition: Next: " + s.next());
				s.next();
			}

			//And Or Not
			if (s.hasNext("and")) {
				//System.out.println("OrCondition: And");
				//System.out.println("OrCondition: " + s.next());
				s.next();

				if(!s.hasNext("\\,|\\)|\\(")){
					Parser.fail("Or Condition expected ,",s);
				}

				//System.out.println("OrCondition: Next: " + s.next());
				s.next();

				Condition and = new OrCondition();
				and.parse(s);

				if (argumentOne == null) {
					//System.out.println("OrCondition: ArgumentOne empty. Adding to ArgumentOne.");
					argumentOne = and;
				} else if (argumentOne != null) {
					//System.out.println("OrCondition: ArgumentOne is not empty. Adding to ArgumentTwo.");
					argumentTwo = and;
				}
			}
			if (s.hasNext("or")) {
				//System.out.println("OrCondition: Or");
				//System.out.println("OrCondition: " + s.next());
				s.next();

				if(!s.hasNext("\\,|\\)|\\(")){
					Parser.fail("Or Condition expected ,",s);
				}

				//System.out.println("OrCondition: Next: " + s.next());
				s.next();

				Condition or = new OrCondition();
				or.parse(s);

				if (argumentOne == null) {
					//System.out.println("OrCondition: ArgumentOne empty. Adding to ArgumentOne.");
					argumentOne = or;
				} else if (argumentOne != null) {
					//System.out.println("OrCondition: ArgumentOne is not empty. Adding to ArgumentTwo.");
					argumentTwo = or;
				}
			}
			if (s.hasNext("not")) {
				//System.out.println("OrCondition: Not");
				//System.out.println("OrCondition: " + s.next());
				s.next();

				if(!s.hasNext("\\,|\\)|\\(")){
					Parser.fail("Or Condition expected ,",s);
				}

				//System.out.println("OrCondition: Next: " + s.next());
				s.next();

				Condition not = new OrCondition();
				not.parse(s);

				if (argumentOne == null) {
					//System.out.println("OrCondition: ArgumentOne empty. Adding to ArgumentOne.");
					argumentOne = not;
				} else if (argumentOne != null) {
					//System.out.println("OrCondition: ArgumentOne is not empty. Adding to ArgumentTwo.");
					argumentTwo = not;
				}
			}

			//lt gt eq
			if (s.hasNext("lt")) {
				//System.out.println("OrCondition: LT");
				//System.out.println("OrCondition: " + s.next());
				s.next();

				if(!s.hasNext("\\,|\\)|\\(")){
					Parser.fail("Or Condition expected ,",s);
				}

				//System.out.println("OrCondition: Next: " + s.next());
				s.next();

				Condition lt = new LessThanCondition();
				lt.parse(s);

				if (argumentOne == null) {
					//System.out.println("OrCondition: ArgumentOne empty. Adding to ArgumentOne.");
					argumentOne = lt;
				} else if (argumentOne != null) {
					//System.out.println("OrCondition: ArgumentOne is not empty. Adding to ArgumentTwo.");
					argumentTwo = lt;
				}
			}
			if (s.hasNext("gt")) {
				//System.out.println("OrCondition: GT");
				//System.out.println("OrCondition: " + s.next());
				s.next();

				if(!s.hasNext("\\,|\\)|\\(")){
					Parser.fail("Or Condition expected ,",s);
				}

				System.out.println("OrCondition: Next: " + s.next());

				Condition gt = new GreaterThanCondition();
				gt.parse(s);

				if (argumentOne == null) {
					//System.out.println("OrCondition: ArgumentOne empty. Adding to ArgumentOne.");
					argumentOne = gt;
				} else if (argumentOne != null) {
					//System.out.println("OrCondition: ArgumentOne is not empty. Adding to ArgumentTwo.");
					argumentTwo = gt;
				}
			}
			if (s.hasNext("eq")) {
				//System.out.println("OrCondition: EQ");
				//System.out.println("OrCondition: " + s.next());
				s.next();

				if(!s.hasNext("\\,|\\)|\\(")){
					Parser.fail("Or Condition expected ,",s);
				}

				//System.out.println("OrCondition: Next: " + s.next());
				s.next();

				Condition eq = new EqualToCondition();
				eq.parse(s);

				if (argumentOne == null) {
					//System.out.println("OrCondition: ArgumentOne empty. Adding to ArgumentOne.");
					argumentOne = eq;
				} else if (argumentOne != null) {
					//System.out.println("OrCondition: ArgumentOne is not empty. Adding to ArgumentTwo.");
					argumentTwo = eq;
				}
			}
		}

		//Returning true
		if (argumentOne != null && argumentTwo != null) {
			//System.out.println("OrCondition: ArgumentOne and ArgumentTwo were not null. Returning True.");
			return true;
		}

		//Returning false
		if (argumentOne == null) {
			//System.out.println("OrCondition: ArgumentOne was null. Returning false.");
		}
		if (argumentTwo == null) {
			//System.out.println("OrCondition: ArgumentTwo was null. Returning false.");
		}


		Parser.fail("OrCondition argument 1 and/or 2 were null.",s);
		return false;
	}

	public String toString() {
		return "or(" + argumentOne + "," + argumentTwo + ")";
	}

}
