package testcases;

public class MethodOverloading {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MethodOverloading mo=new MethodOverloading();
		mo=new MethodOverloading(12);
		mo=new MethodOverloading("Charrlie");

	}
	public MethodOverloading() {
		System.out.println("Constructor without parameters");
	}
	public MethodOverloading(int i) {
		System.out.println("Constructor with integer parameter : "+i);
	}
	public MethodOverloading(String s) {
		System.out.println("Constructor with String parameter : "+s);
	}

}
