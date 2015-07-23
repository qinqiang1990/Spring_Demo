package com.nio;

import java.net.InetSocketAddress; 
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class NioClient{

	public	static Logger	logger=Logger.getLogger(NioClient.class);

	public static void main(String[] args) throws Exception {
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.connect(new InetSocketAddress("127.0.0.1", 9999));
		logger.info("client conect succes");
		Scanner sc = new Scanner(System.in);
		String data = sc.nextLine();  
		while(!data.equals("quit"))
		{
			ByteBuffer buf = ByteBuffer.allocate(48);  
			buf.clear();  
			buf.put(data.getBytes());  
			buf.flip();  
			while(buf.hasRemaining()) {  
				//send message
				socketChannel.write(buf);  
			}
			//receive message

			data=sc.nextLine();
		}
		socketChannel.close(); 
	}



}