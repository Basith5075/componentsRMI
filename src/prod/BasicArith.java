package prod;

public class BasicArith implements Product{

	public BasicArith() {
	
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
