package cft.commons.pms.service.api;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import cft.commons.core.util.HttpClientUtils;

public class InstagramService {
     
	private static final String APP_KEY = "195a0d5137fc46c58ef5f4db4281972e";
	private static final String CLIENT_SECET = "b3e18ec7fd524df6be0067a91504847f";
	private static final String REDIRECT_URL = "http://localhost:8088/pms/instagram/instagramApi.do";

	
	
	public String follow(String uid, String instagram_token) throws IOException{
		
		String instagram_info="";
		String url="https://api.instagram.com/v1/users/" + uid
				+ "/followed-by?access_token=" + instagram_token;
		
		String resultUrl=HttpClientUtils.httpGet(url, 9000, 9000);
		
		JSONObject json2 = new JSONObject(resultUrl);
		JSONArray data = json2.getJSONArray("data");
		
		return null;
		
	}
	
	
}
