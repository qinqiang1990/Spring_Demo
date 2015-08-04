package thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Atomic {
	final static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	// 原子Integer递增对象
	public static AtomicInteger counter_integer = new AtomicInteger(0);
	// 一个int类型的变量
	public static int count_int = 0;

	public static void main(String[] args) {

		CountDownLatch latch = new CountDownLatch(2);// 两个工人的协作
		Worker worker1 = new Worker("za", 5000, latch,count_int);
		Worker worker2 = new Worker("li", 8000, latch,count_int);
		worker1.start();//
		worker2.start();//
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 等待所有工人完成工作
		System.out.println("all work done at " + sdf.format(new Date()));
		System.out.println("counter_integer at " + counter_integer);
		
	}

}

class Worker extends Thread {
	final static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	String workerName;
	int workTime;
	CountDownLatch latch;
	int counter_integer;
	public Worker(String workerName, int workTime, CountDownLatch latch,int counter_integer) {
		this.workerName = workerName;
		this.workTime = workTime;
		this.latch = latch;
		this.counter_integer=counter_integer;
	}

	public void run() {
		System.out.println("Worker " + workerName + " do work begin at "
				+ sdf.format(new Date()));
		doWork();// 工作了
		System.out.println("Worker " + workerName + " do work complete at "
				+ sdf.format(new Date()));
		latch.countDown();// 工人完成工作，计数器减一

	}

	private void doWork() {
	/*	try {
			Thread.sleep(workTime); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		
		for(int i=0;i<workTime;i++)
		{
			counter_integer++;
			//counter_integer.incrementAndGet();
		}
	}
}