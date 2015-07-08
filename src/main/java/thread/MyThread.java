package thread;

public class MyThread extends Thread {

	private final SimpleArray sharedSimpleArray;

	private final int startValue;
	  
	public MyThread(int value, SimpleArray array) {

		startValue = value;

		sharedSimpleArray = array;
	}

	@Override
	public void run() {

		for (int i = startValue; i < startValue + 3; i++) {

			sharedSimpleArray.add(i);

		}
	}

}

/*
 * Runnable myRunnable = new Runnable() { public void run() {
 * System.out.println("Runnable running"); } };
 */