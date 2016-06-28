package testcases;

import testcases.MethodOverriding.B;

public class MethodOverriding{
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	MethodOverriding a=new MethodOverriding();
	a.print();
	a.new B().print();
	
	}
	
	public void print()
		{
			System.out.println("Class A");
		}
	
	public class B extends MethodOverriding
	{
		public void print()
		{
			System.out.println("Class B");
		}
		
	}

}


