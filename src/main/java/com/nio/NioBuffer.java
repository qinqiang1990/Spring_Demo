package com.nio;
 
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioBuffer{ 

	public static void main(String[] args) throws Exception {
		String from="D:\\HelloWorld\\Hello2\\src\\main\\java\\log4j.properties";
		String to="D:\\HelloWorld\\Hello2\\src\\main\\java\\log4j_copy.properties";
		read(from);
		transfer(from,to);
		read(to);

	}

	public static void transfer(String from,String to) throws Exception
	{
		RandomAccessFile fromFile = new RandomAccessFile(from, "rw");
		FileChannel      fromChannel = fromFile.getChannel();

		RandomAccessFile toFile = new RandomAccessFile(to, "rw");
		FileChannel      toChannel = toFile.getChannel();

		long position = 0;
		long count = fromChannel.size();

		toChannel.transferFrom(fromChannel,position, count);
	}

	public static void write(String file) throws Exception
	{
		RandomAccessFile aFile = new RandomAccessFile(file, "rw");  
		FileChannel channel = aFile.getChannel();  
		String newData = "New String to write to file..." + System.currentTimeMillis();
		ByteBuffer buf = ByteBuffer.allocate(48);
		buf.clear();
		buf.put(newData.getBytes());
		buf.flip();
		while(buf.hasRemaining()) {
			channel.write(buf);
		}
	}


	public	static	void	read(String file) throws Exception
	{
		RandomAccessFile aFile = new RandomAccessFile(file, "rw");  
		FileChannel inChannel = aFile.getChannel();  
		ByteBuffer buf = ByteBuffer.allocate(256); 
		int  bytesRead =inChannel.read(buf);
		while( bytesRead !=-1)
		{
			//make buf for read
			buf.flip();
			while(buf.hasRemaining())
			{
				System.out.print((char)buf.get());
			}
			buf.clear();//make buffer ready for writing  
			bytesRead =inChannel.read(buf);
		}
		aFile.close();
	}

}