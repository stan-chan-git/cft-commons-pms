package cft.commons.pms.service.api.util;

import java.util.concurrent.Callable;

import cft.commons.pms.dto.api.PostMessageDTO;
import cft.commons.pms.service.api.TencentService;

public class MyCallable implements Callable<Object> {
	private int state;
	private String info;
	private PostMessageDTO dto;
	

	public MyCallable(int state,PostMessageDTO dto) {
	  this.state = state;
	  this.dto = dto;
	}

	public Object call() throws Exception {
	   Thread.sleep(1000);
	   if(state == 0){
		   TencentService tencentService = new TencentService();
		   info = tencentService.sendWeiBo(dto.getContent(), dto.getTencent_token(), dto.getOpenid(), dto.getOpenkey());
	   }else if(state == 1){
		   
	   }else if(state == 2){
		   
	   }
	   
	   return info;
	}
}
