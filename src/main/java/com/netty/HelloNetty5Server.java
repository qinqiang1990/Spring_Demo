package com.netty;

import io.netty.bootstrap.*;
import io.netty.channel.*;
import io.netty.channel.nio.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class HelloNetty5Server{ 


	public void start(int port) throws Exception {

		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		ServerBootstrap b = new ServerBootstrap();
		b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
		.childHandler(new ChannelInitializer<SocketChannel>(){
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				// TODO Auto-generated method stub
				// register handler
				ch.pipeline().addLast(new HelloNetty5ServerHandler());

			}}).option(ChannelOption.SO_BACKLOG, 128)
			.childOption(ChannelOption.SO_KEEPALIVE, true);
		ChannelFuture f = b.bind(port).sync();
		f.channel().closeFuture().sync();
		workerGroup.shutdownGracefully();
		bossGroup.shutdownGracefully();

	}

	public static void main(String[] args) throws Exception {
		HelloNetty5Server server = new HelloNetty5Server();
		server.start(8000);
	}

}