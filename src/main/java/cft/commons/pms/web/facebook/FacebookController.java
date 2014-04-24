package cft.commons.pms.web.facebook;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cft.commons.core.util.HttpClientUtils;
import cft.commons.pms.dto.facebook.FacebookDTO;

@Controller
public class FacebookController {

	private static final String APP_KEY = "137410796429161";
	private static final String CLIENT_SECET = "1f943be78994b12b88680dfe331acec7";
	private static final String REDIRECT_URL = "http://localhost:8088/pms/facebook/facebook.do";
    
/////////////////////////////////////////////////////////////
/////////////////////以下为*.do请求处理方法////////////////////////
/////////////////////////////////////////////////////////////
	/**
	 * facebook授权
	 */
	@RequestMapping(value = "facebook.do")
	public String facebook(String code, String openid, String openkey,HttpServletRequest request) throws Exception {
		//获取code，通过code发出请求获取access_token
		String url = "https://graph.facebook.com/oauth/access_token?client_id=" + APP_KEY
				     + "&client_secret=" + CLIENT_SECET + "&redirect_uri=" + REDIRECT_URL
				     + "&code=" + code;
			
		//发出请求，成功则返回带access_token的url字符串
		String result = HttpClientUtils.httpGet(url, 9000, 9000);
		String[] params = result.split("&");
		String facebook_token = "";
		//遍历切割后的字符串,获取access_token
		for (String param : params) {
			if (param.contains("access_token")) {
				facebook_token = param.split("=")[1];
				System.out.println("facebook_token:::::::::::::::" + facebook_token);
				break;
			}
		}
		//判断是否授权成功
		if(!facebook_token.equals("")){
			//调用API需要的部分公有参数
			request.getSession().setAttribute("facebook_token", facebook_token);
		}
		return "easyui/layout";
	}
	
	/**
	 * 用js发布消息（试验，不是整合部分）
	 */
	@RequestMapping(value = "writeNewPost.do")
	public String writeNewPost(HttpServletRequest request){
		return "facebook/writeNewPost";
	}
	
	/**
	 * 获取好友动态
	 */
	@RequestMapping(value="facebookFriendsDyn.do")
	public @ResponseBody
	String facebookFriendsDyn(HttpServletRequest request) throws Exception{
		
		List<FacebookDTO> friendList = new ArrayList<FacebookDTO>();
		String facebook_token = (String) request.getSession().getAttribute("facebook_token");
		
		//取到授权用户的朋友列表，返回json数据只包含朋友的id和name
		String URLfriends = "https://graph.facebook.com/me/friends?"
				     + "&access_token=" + facebook_token;
		
		String friends = HttpClientUtils.httpGet(URLfriends, 10000, 10000);
//		System.out.println(friends);
		
		/*将返回的字符串转换成JSON,获取需要的数据*/
		JSONObject friendJson = new JSONObject(friends);
		JSONArray friendData = friendJson.getJSONArray("data");
        
		//1.循环取出授权用户的朋友的id
        for (int i = 0; i < 5; i++) {
			JSONObject friendjo = (JSONObject) friendData.get(i);
//			System.out.println("friendjo::::::::::::::" + friendjo);
            String userId = friendjo.getString("id");
            String userName = friendjo.getString("name");
            
//            System.out.println("userName:::::::::::" + userName);
            
            String URLuser = "https://graph.facebook.com/"
            + userId
            + "/feed?access_token="
            + facebook_token;
            
            //根据朋友id，获得朋友的所有信息
            String userFeed = HttpClientUtils.httpGet(URLuser, 10000, 10000);
            /*将返回的字符串转换成JSON,获取需要的数据*/
    		JSONObject feedJson = new JSONObject(userFeed);
    		JSONArray feedData = feedJson.getJSONArray("data");
    		
    		//2.取得每个朋友信息，获取发表的内容、时间等信息
            for (int j = 0; j < feedData.length(); j++) {
    			JSONObject feedjo = (JSONObject) feedData.get(j);
//    			System.out.println("feedjo::::::::::::::" + feedjo);
//    			System.out.println("hasKey:::::::::" + feedjo.has("message"));
    			
    			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    			//获取当前日期
        		String nowDate = sdf.format(new Date());
        		//获取微博发表时间
//        		System.out.println(feedjo.get("updated_time"));
        		String updateTime = (String) feedjo.get("updated_time");
        		String[] dateTime = updateTime.split("T");
//        		System.out.println("dateTime::::::::::::" + dateTime[0]);
        		String updateDate = dateTime[0];
        		
        		//现在主要需取得message的内容，判断是否有这个内容，并把所需要的内容存入dto
        		if(feedjo.has("message") && updateDate.equals(nowDate)){
        			FacebookDTO fbDto = new FacebookDTO();
        			fbDto.setPostID((String) feedjo.get("id"));
        			fbDto.setUserName(userName);
        			fbDto.setUpdate_time(updateTime);
        			fbDto.setMessage((String) feedjo.get("message"));
//        			System.out.println("DTO::::::::::::::::::" + fbDto);
        			friendList.add(fbDto);
//    	            System.out.println("--------------------一条post-----------------------------------------");
    			}
        		
//	        	//判断此微博是不是转发的
//	        	if((Integer)obj.get("type") == 2){
//	        		JSONObject source = (JSONObject)obj.get("source");
//	        		wb.setOrigtext((String)source.get("origtext"));
//	        	}
            }//2.for
//    		System.out.println("==================以上是一个user的post==================================");
    		
        }//1.for
      //json拼成所需要的字符串
        String resultData = "";
        
        if(friendList == null || friendList.isEmpty()){
             return "empty";
        }else{
        	JSONArray jsonArray = new JSONArray();
        	for(int i = 0 ; i < friendList.size(); i++){
        		JSONObject contentObj = new JSONObject();
        		contentObj.put("id",friendList.get(i).getPostID());
        		contentObj.put("content",friendList.get(i).getMessage());
        		contentObj.put("name",friendList.get(i).getUserName());
        		contentObj.put("time",friendList.get(i).getUpdate_time());
        		jsonArray.put(contentObj);
        	}
        	
        	resultData = jsonArray.toString();
//        	System.out.println("resultData::::::::::::::" + resultData);
        }
        
        return resultData;
	}//friendDyn
}
