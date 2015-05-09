package com.nio;

import java.net.InetSocketAddress; 
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class NioServer{

	public	static Logger	logger=Logger.getLogger(NioServer.class);

	public static void main(String[] args) throws Exception {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();  

		serverSocketChannel.socket().bind(new InetSocketAddress(9999));  

		while(true){  
			SocketChannel socketChannel =serverSocketChannel.accept();  

			//do something with socketChannel...
			logger.info("server conect succes");

			ByteBuffer buf = ByteBuffer.allocate(256); 
			int  bytesRead =socketChannel.read(buf);
			while( bytesRead !=-1)
			{
				//make buf for read
				buf.flip();
				while(buf.hasRemaining())
				{
					System.out.print((char)buf.get());
				}
				buf.clear();//make buffer ready for writing  
				bytesRead =socketChannel.read(buf);
			}
			socketChannel.close(); 


		}  


	}



}