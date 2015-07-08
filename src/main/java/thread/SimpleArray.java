package thread;

import java.util.Random;

public class SimpleArray {

	private final int array[]; // the shared integer array

	private int writeIndex = 0; // index of next element to be written

	private int sum = 0;

	private final static Random generator = new Random();

	public SimpleArray(int size)

	{

		array = new int[size];

	}

	public synchronized void add(int value) {

		int position = writeIndex;

		try {

			Thread.sleep(generator.nextInt(500));

		} catch (InterruptedException ex) {

			ex.printStackTrace();

		}
		array[position] = value;

		++writeIndex;

		sum += value;

		System.out.println(Thread.currentThread().getId() + " " + position
				+ " " + value);

	}

	public String toString()

	{

		String arrayString = "\nContents of SimpleArray:\n";

		for (int i = 0; i < array.length; i++)

			arrayString += array[i] + " ";

		return arrayString + " " + sum;

	}

}