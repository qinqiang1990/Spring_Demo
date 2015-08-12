package propagation.nested;

public class ServiceA {

	public ServiceB B = new ServiceB();

	public void methodA() {
		System.out.println("methodA");
		B.methodB();
	}
}
