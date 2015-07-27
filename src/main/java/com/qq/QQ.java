package com.qq;

import org.apache.log4j.Logger;

public class QQ {

	public Logger	logger=Logger.getLogger(QQ.class);
	
	private QQIO io;

	public QQIO getIo() {
		return io;
	}

	public void setIo(QQIO io) {
		this.io = io;
	}

	public QQ(QQIO io) {
		super();
		this.io = io;
	}

	public void submit()
	{
		
		long begin= System.currentTimeMillis();
		
		logger.info("QQ>>submit");
		
		//io.comput(this);	//异步
		
		io.comput();	//同步
		while(io.getStat()!=State.end)
		{
			
		}
		complete();
		
		long end= System.currentTimeMillis();
		
		logger.info("running time>>"+(end-begin));
		
		total+=end-begin;
	}

	public long total=0;
	
	public void submitsync()
	{
		
		long begin= System.currentTimeMillis();
		
		logger.info("QQ>>submitsync");
		
		io.comput(this);	//异步
		  
		long end= System.currentTimeMillis();
		
		logger.info("running time>>"+(end-begin));
		
		total+=end-begin;
	}
	public void complete()
	{
		logger.info("QQ>>complete");
	}


}
