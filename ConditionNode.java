import java.util.Map;
import java.util.Scanner;

public class ConditionNode{

	private Condition condition;

	public boolean evaluate(Robot r, Map<Variable, Expression> variables){
		//System.out.println("ConditionNode: Condition (EQ|GT|LT)");
		return condition.evaluate(r, variables);
	}

	public boolean parse(Scanner s) {
		//System.out.println("ConditionNode: Parse");

		while (Parser.gobble(Parser.OPENPAREN, s) || Parser.gobble(Parser.CLOSEPAREN, s) ||
				Parser.gobble(Parser.OPENBRACE, s) || Parser.gobble(Parser.CLOSEBRACE, s) || Parser.gobble(Parser.COMMA, s)) {

			Parser.gobble(Parser.OPENPAREN, s);
			Parser.gobble(Parser.CLOSEPAREN, s);
			Parser.gobble(Parser.OPENBRACE, s);
			Parser.gobble(Parser.CLOSEBRACE, s);
			Parser.gobble(Parser.COMMA, s);
		}

		if(s.hasNext("lt")){
			LessThanCondition lessThan = new LessThanCondition();

			while (Parser.gobble(Parser.OPENPAREN, s) || Parser.gobble(Parser.CLOSEPAREN, s) ||
					Parser.gobble(Parser.OPENBRACE, s) || Parser.gobble(Parser.CLOSEBRACE, s) || Parser.gobble(Parser.COMMA, s)) {

				Parser.gobble(Parser.OPENPAREN, s);
				Parser.gobble(Parser.CLOSEPAREN, s);
				Parser.gobble(Parser.OPENBRACE, s);
				Parser.gobble(Parser.CLOSEBRACE, s);
				Parser.gobble(Parser.COMMA, s);
			}

			if(lessThan.parse(s)){
				condition = lessThan;
				//System.out.println("ConditionNode: LT True");

				return true;
			}
			else{
				//System.out.println("ConditionNode: LT False");
				Parser.fail("Condition Node LessThan failed. ",s);
				return false;
			}
		}
		if(s.hasNext("gt")){
			GreaterThanCondition greaterThan = new GreaterThanCondition();

			while (Parser.gobble(Parser.OPENPAREN, s) || Parser.gobble(Parser.CLOSEPAREN, s) ||
					Parser.gobble(Parser.OPENBRACE, s) || Parser.gobble(Parser.CLOSEBRACE, s) || Parser.gobble(Parser.COMMA, s)) {

				Parser.gobble(Parser.OPENPAREN, s);
				Parser.gobble(Parser.CLOSEPAREN, s);
				Parser.gobble(Parser.OPENBRACE, s);
				Parser.gobble(Parser.CLOSEBRACE, s);
				Parser.gobble(Parser.COMMA, s);
			}

			if(greaterThan.parse(s)){
				condition = greaterThan;
				//System.out.println("ConditionNode: GT True");
				return true;
			}
			else{
				//System.out.println("ConditionNode: GT False");
				Parser.fail("Condition Node GreaterThan failed.",s);
				return false;
			}
		}
		if(s.hasNext("eq")){
			EqualToCondition equalTo = new EqualToCondition();

			while (Parser.gobble(Parser.OPENPAREN, s) || Parser.gobble(Parser.CLOSEPAREN, s) ||
					Parser.gobble(Parser.OPENBRACE, s) || Parser.gobble(Parser.CLOSEBRACE, s) || Parser.gobble(Parser.COMMA, s)) {

				Parser.gobble(Parser.OPENPAREN, s);
				Parser.gobble(Parser.CLOSEPAREN, s);
				Parser.gobble(Parser.OPENBRACE, s);
				Parser.gobble(Parser.CLOSEBRACE, s);
				Parser.gobble(Parser.COMMA, s);
			}

			if(equalTo.parse(s)){
				condition = equalTo;
				//System.out.println("ConditionNode: EQ True");
				return true;
			}
			else{
				//System.out.println("ConditionNode: EQ False");
				Parser.fail("Condition node EqualTo failed.", s);
				return false;
			}
		}

		//TODO:
		//Checking for further conditions....
		if(s.hasNext("and")){
			AndCondition and = new AndCondition();

			while (Parser.gobble(Parser.OPENPAREN, s) || Parser.gobble(Parser.CLOSEPAREN, s) ||
					Parser.gobble(Parser.OPENBRACE, s) || Parser.gobble(Parser.CLOSEBRACE, s) || Parser.gobble(Parser.COMMA, s)) {

				Parser.gobble(Parser.OPENPAREN, s);
				Parser.gobble(Parser.CLOSEPAREN, s);
				Parser.gobble(Parser.OPENBRACE, s);
				Parser.gobble(Parser.CLOSEBRACE, s);
				Parser.gobble(Parser.COMMA, s);
			}

			if(and.parse(s)){
				condition = and;
				//System.out.println("ConditionNode: And true");
				return true;
			}
			else{
				//System.out.println("ConditionNode: And false");
				Parser.fail("Condition Node Condition Node failed.",s);
				return false;
			}
		}

		if(s.hasNext("or")){
			OrCondition or = new OrCondition();

			while (Parser.gobble(Parser.OPENPAREN, s) || Parser.gobble(Parser.CLOSEPAREN, s) ||
					Parser.gobble(Parser.OPENBRACE, s) || Parser.gobble(Parser.CLOSEBRACE, s) || Parser.gobble(Parser.COMMA, s)) {

				Parser.gobble(Parser.OPENPAREN, s);
				Parser.gobble(Parser.CLOSEPAREN, s);
				Parser.gobble(Parser.OPENBRACE, s);
				Parser.gobble(Parser.CLOSEBRACE, s);
				Parser.gobble(Parser.COMMA, s);
			}

			if(or.parse(s)){
				condition = or;
				//System.out.println("ConditionNode: Or true");
				return true;
			}
			else{
				//System.out.println("ConditionNode: Or false");
				Parser.fail("Condition Node Or failed in Condition Node. ",s);
				return false;
			}
		}

		if(s.hasNext("not")){
			NotCondition not = new NotCondition();

			while (Parser.gobble(Parser.OPENPAREN, s) || Parser.gobble(Parser.CLOSEPAREN, s) ||
					Parser.gobble(Parser.OPENBRACE, s) || Parser.gobble(Parser.CLOSEBRACE, s) || Parser.gobble(Parser.COMMA, s)) {

				Parser.gobble(Parser.OPENPAREN, s);
				Parser.gobble(Parser.CLOSEPAREN, s);
				Parser.gobble(Parser.OPENBRACE, s);
				Parser.gobble(Parser.CLOSEBRACE, s);
				Parser.gobble(Parser.COMMA, s);
			}

			if(not.parse(s)){
				condition = not;
				//System.out.println("ConditionNode: Not true");
				return true;
			}
			else{
				//System.out.println("ConditionNode: Not false");
				Parser.fail("Condition Node Not failed.", s);
				return false;
			}
		}


		Parser.fail("Condition node failed. ",s);
		return false;
	}

	public String toString(){
		if(condition!=null){
			return ""+condition;
		}
		return "";
	}

}
