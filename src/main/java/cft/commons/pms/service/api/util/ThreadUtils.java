package cft.commons.pms.service.api.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import cft.commons.pms.dto.api.PostMessageDTO;

public class ThreadUtils {
	private int i = 0;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Future> threadUuils(PostMessageDTO dto){
		// 创建一个线程池
	    ExecutorService pool = Executors.newFixedThreadPool(2);
	    // 创建多个有返回值的任务
		List<Future> list = new ArrayList<Future>();
	    
	    while(i < 1){
		    Callable c = new MyCallable(i,dto);
		    // 执行任务并获取Future对象
		    Future f = pool.submit(c);
		    list.add(f);	
		    i++;
	    }
	    // 关闭线程池
	    pool.shutdown();
	    
	    return list;
	}
}

