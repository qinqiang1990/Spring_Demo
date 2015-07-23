package com.netty;


import org.apache.log4j.Logger;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class HelloNetty5ServerHandler extends SimpleChannelInboundHandler<Object>
{
	Logger	logger=Logger.getLogger(HelloNetty5ServerHandler.class);
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		logger.info("HelloServerInHandler.channelRead");
		ByteBuf result = (ByteBuf) msg;
		byte[] result1 = new byte[result.readableBytes()];
		result.readBytes(result1);
		String resultStr = new String(result1);
		logger.info("Client said:" + resultStr);
		result.release();
		// send msg to client
		String response = "I am ok!";
		ByteBuf encoded = ctx.alloc().buffer(4 * response.length());
		encoded.writeBytes(response.getBytes());
		ctx.write(encoded);
		ctx.flush();
	}
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		// TODO Auto-generated method stub
		logger.info("SERVER:msgReceived");
	}
}