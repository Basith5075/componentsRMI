package arithmetic;

public class BasicArith2 implements ArithmeticProd{

	public BasicArith2() {
	
	}
	@Override
	public void calculation() {
	System.out.println("Please Enter the name of the Student : ");
	String name = sc.nextLine();
	System.out.println("Please Enter First Number : ");
	int a = sc.nextInt();
	
	System.out.println("Please Enter Second Number : ");
	int b = sc.nextInt();
	
	System.out.printf("Sum of %d and %d is %d ", a,b,(a*b));
		System.out.println("Basic arithmetic");
	}
}

interface ArithmeticProd extends Arithmetic{
	
}