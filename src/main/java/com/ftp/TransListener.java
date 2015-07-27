package com.ftp;

public class TransListener implements EventListener {

	public void update(long complete, long size) {
		// TODO Auto-generated method stub
		System.out.println(complete+"/"+size+"="+complete*100/size+"%");
	}
	
}
