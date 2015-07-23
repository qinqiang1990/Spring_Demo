package com.netty;


import org.apache.log4j.Logger;
import org.apache.commons.logging.LogFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class HelloNetty5ClientHandler extends SimpleChannelInboundHandler<Object>
{
	Logger	logger=Logger.getLogger(HelloNetty5ClientHandler.class);
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.info("HelloClientIntHandler.channelRead");
		ByteBuf result = (ByteBuf) msg;
		byte[] result1 = new byte[result.readableBytes()];
		result.readBytes(result1);
		logger.info("Server said:" + new String(result1));
		result.release();
	}
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		logger.info("HelloClientIntHandler.channelActive");
		String msg = "Are you ok?";
		ByteBuf encoded = ctx.alloc().buffer(4 * msg.length());
		encoded.writeBytes(msg.getBytes());
		ctx.write(encoded);
		ctx.flush();
	}
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		logger.info("CLIENT:msgReceived");
	}
}
