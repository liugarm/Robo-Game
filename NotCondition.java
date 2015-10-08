import java.util.Map;
import java.util.Scanner;


public class NotCondition implements Condition{

	private Condition condition;

	@Override
	public boolean evaluate(Robot robot, Map<Variable, Expression> variables) {
		//System.out.println("Not: Evaluate");

		//Result values
		boolean argOneValue = false;

		//Argument One
		LessThanCondition lt1 = null;
		GreaterThanCondition gt1 = null;
		EqualToCondition eq1 = null;
		AndCondition and1 = null;
		OrCondition or1 = null;
		NotCondition not1 = null;

		if(condition instanceof LessThanCondition){
			lt1 = (LessThanCondition) condition;
		}
		else if(condition instanceof GreaterThanCondition){
			gt1 = (GreaterThanCondition) condition;
		}
		else if(condition instanceof EqualToCondition){
			eq1 = (EqualToCondition) condition;
		}
		else if(condition instanceof AndCondition){
			and1 = (AndCondition) condition;
		}
		else if(condition instanceof OrCondition){
			or1 = (OrCondition) condition;
		}
		else if(condition instanceof NotCondition){
			not1 = (NotCondition) condition;
		}

		//Calculate
		if(lt1!=null){
			argOneValue = lt1.evaluate(robot,variables);
		}
		else if(gt1!=null){
			argOneValue = gt1.evaluate(robot,variables);
		}
		else if(eq1!=null){
			argOneValue = eq1.evaluate(robot,variables);
		}
		else if(and1!=null){
			argOneValue = and1.evaluate(robot,variables);
		}
		else if(or1!=null){
			argOneValue = or1.evaluate(robot,variables);
		}
		else if(not1!=null){
			argOneValue = not1.evaluate(robot,variables);
		}

		if(!argOneValue){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public boolean parse(Scanner s) {
		//System.out.println("NotCondition: Parse");
		//System.out.println("NotCondition Sucking in: "+s.next());
		s.next();

		if(!s.hasNext("and|or|not|lt|gt|eq")){
			//System.out.println("NotCondition: Next: "+s.next());
			s.next();
		}

		//And Or Not
		if(s.hasNext("and")){
			//System.out.println("NotCondition: And");
			//System.out.println("NotCondition: "+s.next());
			//System.out.println("NotCondition: Next: "+s.next());
			s.next();
			s.next();

			Condition and = new NotCondition();
			and.parse(s);
			condition = and;

		}
		if(s.hasNext("or")){
			//System.out.println("NotCondition: Or");
			//System.out.println("NotCondition: "+s.next());
			//System.out.println("NotCondition: Next: "+s.next());
			s.next();
			s.next();

			Condition or = new NotCondition();
			or.parse(s);

			condition = or;

		}
		if(s.hasNext("not")){
			//System.out.println("NotCondition: Not");
			//System.out.println("NotCondition: "+s.next());
			//System.out.println("NotCondition: Next: "+s.next());
			s.next();

			Condition not = new NotCondition();
			not.parse(s);

			condition = not;

		}

		//lt gt eq
		if(s.hasNext("lt")){
			//System.out.println("NotCondition: LT");
			//System.out.println("NotCondition: "+s.next());
			//System.out.println("NotCondition: Next: "+s.next());
			s.next();
			s.next();

			Condition lt = new LessThanCondition();
			lt.parse(s);

			condition = lt;

		}
		if(s.hasNext("gt")){
			//System.out.println("NotCondition: GT");
			//System.out.println("NotCondition: "+s.next());
			//System.out.println("NotCondition: Next: "+s.next());
			s.next();

			Condition gt = new GreaterThanCondition();
			gt.parse(s);

			condition = gt;

		}
		if(s.hasNext("eq")){
			//System.out.println("NotCondition: EQ");
			//System.out.println("NotCondition: "+s.next());
			//System.out.println("NotCondition: Next: "+s.next());
			s.next();
			s.next();

			Condition eq = new EqualToCondition();
			eq.parse(s);
			condition = eq;
		}

		//Returning true
		if(condition!=null){
			//System.out.println("NotCondition: condition was not null. Returning True.");
			return true;
		}

		//Returning false
		//System.out.println("NotCondition: condition was null. Returning false.");

		return false;
	}

	public String toString(){
		return "not("+condition+")";
	}


}
