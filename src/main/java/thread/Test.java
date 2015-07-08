package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {

	public static void main(String[] args) {

		SimpleArray sharedSimpleArray = new SimpleArray(9);

		ExecutorService threadExecutor = Executors.newCachedThreadPool();
		threadExecutor.execute(new MyThread(1, sharedSimpleArray));
		threadExecutor.execute(new MyThread(11, sharedSimpleArray));
		threadExecutor.execute(new MyThread(21, sharedSimpleArray));
		threadExecutor.shutdown();
		boolean tasksEnded = false;
		try {
			tasksEnded = threadExecutor.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (tasksEnded)

			System.out.println(sharedSimpleArray); // print contents

		else

			System.out.println(

			"Timed out while waiting for tasks to finish.");
	}
}
