import java.util.Map;
import java.util.Scanner;


public class ExpressionNode implements RobotProgramNode{

	private Expression expression;

	@Override
	public void execute(Robot robot, Map<Variable, Expression> variables) {
		// TODO Auto-generated method stub
	}

	public boolean parse(Scanner s){
		System.out.println("ExpressionNode: Parse");
		System.out.println("ExpressionNode Next: "+s.next());

		//Numbers
		if(s.hasNext("-?[1-9][0-9]*|0")){
			System.out.println("ExpressionNode: Number");
			System.out.println("ExpressionNode Next: "+s.next());

			expression = new Number(s.nextInt());
		}

		//Sensors
		if(s.hasNext("fuelLeft")){
			System.out.println("ExpressionNode: fuelLeft");
			System.out.println("ExpressionNode Next: "+s.next());
			expression = new FuelLeft();
		}
		if(s.hasNext("oppLR")){
			System.out.println("ExpressionNode: oppLR");
			System.out.println("ExpressionNode Next: "+s.next());
			expression = new OpponentLeftRight();
		}
		if(s.hasNext("oppFB")){
			System.out.println("ExpressionNode: oppFB");
			System.out.println("ExpressionNode Next: "+s.next());
			expression = new OpponentFrontBack();
		}
		if(s.hasNext("numBarrels")){
			System.out.println("ExpressionNode: numBarrels");
			System.out.println("ExpressionNode Next: "+s.next());
			expression = new NumberBarrels();
		}
		if(s.hasNext("barrelLR")){
			System.out.println("ExpressionNode: barrelLR");
			System.out.println("ExpressionNode Next: "+s.next());
			expression = new BarrelLeftRight();
		}
		if(s.hasNext("barrelFB")){
			System.out.println("ExpressionNode: barrelLR");
			System.out.println("ExpressionNode Next: "+s.next());
			expression = new BarrelFrontBack();
		}
		if(s.hasNext("wallDist")){
			System.out.println("ExpressionNode: wallDist");
			System.out.println("ExpressionNode Next: "+s.next());
			expression = new WallDistance();
		}

		//Operators
		if(s.hasNext("add")){
			System.out.println("ExpressionNode: Operator Add");
			System.out.println("ExpressionNode Next: "+s.next());
			expression = new AdditionOperator();
		}
		if(s.hasNext("sub")){
			System.out.println("ExpressionNode: Operator Subtraction");
			System.out.println("ExpressionNode Next: "+s.next());
			expression = new SubtractionOperator();
		}
		if(s.hasNext("mul")){
			System.out.println("ExpressionNode: Operator Multiplication");
			System.out.println("ExpressionNode Next: "+s.next());
			expression = new MultiplicationOperator();
		}
		if(s.hasNext("div")){
			System.out.println("ExpressionNode: Operator Division");
			System.out.println("ExpressionNode Next: "+s.next());
			expression = new DivisionOperator();
		}

		if(expression!=null){
			System.out.println("ExpressionNode: Expression was not null. RETURNING TRUE.");
			return true;
		}

		System.out.println("ExpressionNode: All were null. RETURNING FALSE.");
		return false;
	}

	public String toString(){
		if(expression!=null){
			return expression.toString();
		}
		else{
			return "EXPALLNULL";
		}
	}

}
