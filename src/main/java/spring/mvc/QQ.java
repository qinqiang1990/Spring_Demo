package spring.mvc;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
 
@Component
public class QQ {

	public Logger	logger=Logger.getLogger(QQ.class);

	@Value("${jms.broker_url}")
	public String jmshost;

	@Autowired
	private QQIO io;

	public QQIO getIo() {
		return io;
	}

	public void setIo(QQIO io) {
		this.io = io;
	}



	public void submit()
	{

		long begin= System.currentTimeMillis();

		logger.info("QQ>>submit");

		io.comput();	//Í¬²½
		while(io.getStat()!=State.end)
		{

		}
		complete();

		long end= System.currentTimeMillis();

		logger.info("running time>>"+(end-begin));


	}

	public void submitsync()
	{

		long begin= System.currentTimeMillis();

		logger.info("QQ>>submitsync");

		io.comput(this);	//Òì²½

		long end= System.currentTimeMillis();

		logger.info("running time>>"+(end-begin));


	}
	public void complete()
	{
		logger.info("QQ>>complete");
	}


}
