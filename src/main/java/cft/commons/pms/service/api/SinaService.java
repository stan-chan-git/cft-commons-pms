package cft.commons.pms.service.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cft.commons.core.util.HttpClientUtils;
import cft.commons.pms.dto.sina.SinaDTO;
import cft.commons.pms.web.api.util.constants.SinaConstants;

public class SinaService {
	
	private int cTimeout = 5000;
	private int sTimeout = 5000;
	
	public SinaDTO sina_Statuses_Update(String token,String status) {
		String url = SinaConstants.StatusesUpdateUrl;
		Map<String, String>map = new HashMap<String, String>();
		map.put("access_token", token);
		map.put("status", status);
		
		try {
			url = HttpClientUtils.httpPost(map, url, cTimeout, sTimeout);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;	
	}
	
}
