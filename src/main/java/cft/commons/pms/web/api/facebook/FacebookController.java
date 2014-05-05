package cft.commons.pms.web.api.facebook;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cft.commons.core.util.HttpClientUtils;
import cft.commons.pms.dto.facebook.FacebookDTO;
import cft.commons.pms.web.api.util.ApiUtils;

/**
 * facebook 请求处理
 * @author tify
 */
@Controller
public class FacebookController {

	private static final String APP_KEY = "137410796429161";
	private static final String CLIENT_SECET = "1f943be78994b12b88680dfe331acec7";
	private static final String REDIRECT_URL = "http://localhost:8080/pms/facebook/facebook.do";
    
	/**
	 * facebook授权处理
	 */
	@RequestMapping(value = "facebook.do")
	public String facebook(String code, String openid, String openkey,HttpServletRequest request) throws Exception {
		//获取code，通过code发出请求获取access_token
		String url = "https://graph.facebook.com/oauth/access_token?client_id=" + APP_KEY
				     + "&client_secret=" + CLIENT_SECET + "&redirect_uri=" + REDIRECT_URL
				     + "&code=" + code;
			
		//发出请求，成功则返回带access_token的url字符串
		String result = HttpClientUtils.httpGet(url, 10000, 10000);
		String[] params = result.split("&");
		String facebook_token = "";
		//遍历切割后的字符串,获取access_token
		for (String param : params) {
			if (param.contains("access_token")) {
				facebook_token = param.split("=")[1];
				break;
			}
		}
		//判断是否授权成功
		if(!facebook_token.equals("")){
			//调用API需要的部分公有参数
			request.getSession().setAttribute("facebook_token", facebook_token);
		}
//		System.out.println("facebook_token:" + facebook_token);
		return "easyui/layout";
	}
	
	/**
	 * 用js发布消息（只用于跳转，不为整合部分）
	 */
	@RequestMapping(value = "writeNewPost.do")
	public String writeNewPost(HttpServletRequest request){
		return "facebook/writeNewPost";
	}
	
	/**
	 * 获取好友以及当前用户的动态
	 */
	@RequestMapping(value="facebookFriendsDyn.do")
	public @ResponseBody
	String facebookFriendsDyn(HttpServletRequest request) throws Exception{
		
		//日期格式化
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//获取当前日期
		String nowDate = sdf.format(new Date());
		List<FacebookDTO> friendList = new ArrayList<FacebookDTO>();
		String facebook_token = (String) request.getSession().getAttribute("facebook_token");
		
		//把自己的动态设置进去
		String myInfoURL = "https://graph.facebook.com/me/feed?"
			     + "&access_token=" + facebook_token;
		String myInfo = HttpClientUtils.httpGet(myInfoURL, 10000, 10000);
		
		/*将返回的字符串转换成JSON,获取需要的数据*/
		JSONObject myInfoJson = new JSONObject(myInfo);
		JSONArray myInfoData = myInfoJson.getJSONArray("data");
		
		
		for (int i = 0; i < 11; i++) {//先取当前用户的前10条微博
			
			FacebookDTO myDTO = new FacebookDTO();
			//初始设置
			myDTO.setImageUrl("null");
			myDTO.setMessage("分享");
			
			JSONObject myInfojo = (JSONObject) myInfoData.get(i);//当前用户的全部信息
			
			JSONObject fromJson = (JSONObject) myInfojo.get("from");//当前用户的id和name
			
			//获取微博发表时间
			//对时间进行处理，返回格式是2014-04-24T02:42:37+0000
			String myUpdateTime = (String) myInfojo.get("updated_time");
				//取得发表日期，并格式化
			String myUpdateDate = ApiUtils.FacebookDateFormat(myUpdateTime);
				//取得发表时间，并格式化
			myUpdateTime = ApiUtils.FacebookTimeFormat(myUpdateTime);
			
			//获取当天微博信息，及必须要有文字信息或者图片
			if (myUpdateDate.equals(nowDate) && (myInfojo.has("message") || myInfojo.has("picture"))) {
				myDTO.setUserId((String) fromJson.get("id"));
				myDTO.setUserName("我");
				myDTO.setPostID((String) myInfojo.get("id"));
				myDTO.setUpdate_time(myUpdateTime);
				if (myInfojo.has("message")) {
					myDTO.setMessage((String) myInfojo.get("message"));
				}
				if (myInfojo.has("picture")) {
					myDTO.setImageUrl((String) myInfojo.get("picture"));
				}
				friendList.add(myDTO);//添加显示自己的动态
			}
		}
		
		//取到授权用户的朋友列表，返回json数据只包含朋友的id和name
		String URLfriends = "https://graph.facebook.com/me/friends?"
				     + "&access_token=" + facebook_token;
		
		String friends = HttpClientUtils.httpGet(URLfriends, 10000, 10000);
		
		/*将返回的字符串转换成JSON,获取需要的数据*/
		JSONObject friendJson = new JSONObject(friends);
		JSONArray friendData = friendJson.getJSONArray("data");
        
		//1.循环取出授权用户的朋友的id，先取10个好友，若要取全部则friendData.length()
        for (int i = 0; i < 11; i++) {
			JSONObject friendjo = (JSONObject) friendData.get(i);
            String userId = friendjo.getString("id");
            String userName = friendjo.getString("name");
            
            String URLuser = "https://graph.facebook.com/"
            + userId
            + "/feed?access_token="
            + facebook_token;
            
            //根据朋友id，获得朋友的所有信息
            String userFeed = HttpClientUtils.httpGet(URLuser, 10000, 10000);
            /*将返回的字符串转换成JSON,获取需要的数据*/
    		JSONObject feedJson = new JSONObject(userFeed);
    		JSONArray feedData = feedJson.getJSONArray("data");
    		
    		//2.取得每个朋友信息，获取发表的内容、时间等信息，获取前5条数据
            for (int j = 0; j < feedData.length(); j++) {
    			JSONObject feedjo = (JSONObject) feedData.get(j);
    			
        		//获取微博发表时间
        			//对时间进行处理，返回格式是2014-04-24T02:42:37+0000
        		String updateTime = (String) feedjo.get("updated_time");
        			//取得发表日期（年月日）
        		String updateDate = ApiUtils.FacebookDateFormat(updateTime);
        			//取得发表时间（时分秒）
        		updateTime = ApiUtils.FacebookTimeFormat(updateTime);
        		
        		//主要获取当日好友微博，判断是否有这个内容，并把所需要的内容存入dto
        		if(updateDate.equals(nowDate) && (feedjo.has("message") || feedjo.has("picture"))){
        			
        			FacebookDTO fbDto = new FacebookDTO();
        			//初始设置
        			fbDto.setImageUrl("null");
        			fbDto.setMessage("分享");
        			
        			fbDto.setPostID((String) feedjo.get("id"));
        			fbDto.setUserName(userName);
        			fbDto.setUpdate_time(updateTime);
        			//判断获取的json是否有message字段
        			if(feedjo.has("message")){
        				fbDto.setMessage((String) feedjo.get("message"));
        			}
        			//判断获取的json是否有picture字段
        			if(feedjo.has("picture")){
        				fbDto.setImageUrl((String) feedjo.get("picture"));
        			}
        			friendList.add(fbDto);
        			
    			}
        		
//	        	//判断此微博是不是转发的
//	        	if((Integer)obj.get("type") == 2){
//	        		JSONObject source = (JSONObject)obj.get("source");
//	        		wb.setOrigtext((String)source.get("origtext"));
//	        	}
            }//2.for
    		
        }//1.for
        //获取的好友动态按时间先后排序
        friendList = ApiUtils.FacebookOrderByUpdateTime(friendList);
//        System.out.println(friendList);
      //拼成所需要的json
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
        		contentObj.put("images",friendList.get(i).getImageUrl());
        		jsonArray.put(contentObj);
        	}
        	
        	resultData = jsonArray.toString();
        }
//        System.out.println("friendList" + friendList);
//        System.out.println("resultData::" + resultData);
        return resultData;
	}//friendDyn
}
