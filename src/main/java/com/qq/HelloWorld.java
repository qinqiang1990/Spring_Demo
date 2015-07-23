package com.qq;

import org.apache.log4j.Logger;
import com.netty.HelloNetty5ClientHandler;

public class HelloWorld{ 
	public static Logger	logger=Logger.getLogger(HelloWorld.class);
	public static void main(String[] args) {  

		QQ q1=new QQ(new QQIO());

		for(int i=0;i<10;i++)
			q1.submitsync();

		logger.info("QQsync>>submitsync"+q1.total);


		QQ q2=new QQ(new QQIO());
		for(int i=0;i<10;i++)
			q2.submit();
		logger.info("QQ>>submit"+q2.total);
	}


}