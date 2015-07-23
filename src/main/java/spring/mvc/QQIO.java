package spring.mvc;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class QQIO {

	public Logger	logger=Logger.getLogger(QQIO.class);
	
	private int stat = State.start;

	public int getStat() {
		return stat;
	}
	public void setStat(int stat) {
		this.stat = stat;
	}
	  
	private QQ	qq;
 
	public void comput(QQ qq)
	{
		logger.info("QQIO>>comput");
		this.qq=qq;
		this.setStat(State.running);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setStat(State.end);
		qqnotify();
	}

	
	public void comput()
	{
		logger.info("QQIO>>comput");
		this.setStat(State.running);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setStat(State.end);		 
	}
	
	
	public void qqnotify()
	{
		logger.info("QQIO>>qqnotify");
		qq.complete();
	}

}
