package thread;

import java.util.concurrent.atomic.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorPool {

	public static void main(String[] args) {
		schedulePool();
		  
	}

	public static void schedulePool() {
		ScheduledExecutorService schedulePool = Executors
				.newScheduledThreadPool(1);
		// 5秒后执行任务
		schedulePool.schedule(new Runnable() {
			public void run() {
				System.out.println("爆炸");
			}
		}, 5, TimeUnit.SECONDS);
		// 5秒后执行任务，以后每2秒执行一次
		schedulePool.scheduleAtFixedRate(new Runnable() {
			public void run() {
				System.out.println("爆炸");
			}
		}, 5, 2, TimeUnit.SECONDS);
	}

	public static void threadPool() {
		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		for (int i = 1; i < 5; i++) {
			final int taskID = i;
			threadPool.execute(new Runnable() {
				public void run() {
					for (int i = 1; i < 5; i++) {
						try {
							Thread.sleep(20);// 为了测试出效果，让每次任务执行都需要一定时间
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("第" + taskID + "次任务的第" + i + "次执行");
					}
				}
			});
		}

		threadPool.shutdown();// 任务执行完毕，关闭线程池

	}
}