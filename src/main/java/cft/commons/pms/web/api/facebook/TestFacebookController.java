package cft.commons.pms.web.api.facebook;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cft.commons.core.util.HttpClientUtils;
import cft.commons.pms.dto.facebook.CommentDTO;
import cft.commons.pms.dto.facebook.FacebookDTO;
import cft.commons.pms.dto.facebook.UserDTO;
import cft.commons.pms.web.api.util.ApiUtils;

/**
 * Test facebook 请求处理
 * @author tify
 */
@Controller
public class TestFacebookController {
	//all of this after facebook authorise, authorization in FacebookController.java
	
	/**
	 * 1.用js发布消息可发图片（只用于跳转）
	 */
	@RequestMapping(value = "writeNewPost.do")
	public String writeNewPost(HttpServletRequest request){
		return "facebook/writeNewPost";
	}
	
	/**
	 * 2.获取好友列表
	 */
	@RequestMapping(value = "getFriendlist.do")
	public String getFriendlist(HttpServletRequest request, Model model) throws Exception{
		List<UserDTO> friendList = new ArrayList<UserDTO>();
		
		String facebook_token = (String) request.getSession().getAttribute("facebook_token");
		String friendsUrl = "https://graph.facebook.com/me/friends?access_token=" + facebook_token;
		
		//Get friend list
		String friends = HttpClientUtils.httpGet(friendsUrl, 10000, 10000);
		//防止没授权报异常
		if(friends == null || friends == ""){
			return "facebook/getFriendlist";
		}
		/*将返回的字符串转换成JSON,获取需要的数据*/
		JSONObject friendsJson = new JSONObject(friends);
		JSONArray friendsData = friendsJson.getJSONArray("data");
		for (int i = 0; i < friendsData.length(); i++) {
			
			//get friend id , name
			JSONObject friendsjo = (JSONObject) friendsData.get(i);
            String friendId = friendsjo.getString("id");
            String friendName = friendsjo.getString("name");
//			System.out.println("friend info::::" + friendId + " , " + friendName);
			
			//according friendId, get friend headPortrait
			String friendPortraitUrl = "https://graph.facebook.com/"
					+ friendId
					+ "/picture?type=small&access_token="
					+ facebook_token;
			
			//ApiUtils.connect() 获取跳转后的url
			String friendPortrait = ApiUtils.connect(friendPortraitUrl);
			ApiUtils.disconnect();  
//			System.out.println("friendPortrait::" +friendPortrait);
            UserDTO friendDTO = new UserDTO();
			friendDTO.setUserId(friendId);
			friendDTO.setUserName(friendName);
			friendDTO.setUserHead(friendPortrait);
			friendList.add(friendDTO);
			
			model.addAttribute("friendList", friendList);
			
		}
//		System.out.println("friendList::" + friendList);
		return "facebook/getFriendlist";
	}
	
	/**
	 * 3.js分享（只是用于跳转）
	 */
	@RequestMapping(value = "sharePost.do")
	public String SharePost(HttpServletRequest request){
		return "facebook/sharePost";
	}
	
	/**
	 * 4.Get My All Posts with like and share
	 */
	@RequestMapping(value="getMyPosts.do")
	public String getMyPosts(HttpServletRequest request, Model model) throws Exception{
		
		List<FacebookDTO> myList = new ArrayList<FacebookDTO>();
		String facebook_token = (String) request.getSession().getAttribute("facebook_token");
		
		if(null == facebook_token || "" == facebook_token){
			return "facebook/getMyPosts";
		}
		//把自己的动态设置进去
		String myInfoURL = "https://graph.facebook.com/me/feed?"
			     + "&access_token=" + facebook_token;
		String myInfo = HttpClientUtils.httpGet(myInfoURL, 10000, 10000);
		
		/*将返回的字符串转换成JSON,获取需要的数据*/
		JSONObject myInfoJson = new JSONObject(myInfo);
		JSONArray myInfoData = myInfoJson.getJSONArray("data");
		
		
		for (int i = 0; i < myInfoData.length(); i++) {//或者可以先取当前用户的前10条微博
			
			FacebookDTO myDTO = new FacebookDTO();
			
			//初始设置
			myDTO.setImageUrl("null");
			myDTO.setMessage("Share");
			myDTO.setShareNum(0);
			
			JSONObject myInfojo = (JSONObject) myInfoData.get(i);//当前用户的全部信息
			
			JSONObject fromJson = (JSONObject) myInfojo.get("from");//当前用户的id和name
			
			//获取微博发表时间
			//对时间进行处理，返回格式是2014-04-24T02:42:37+0000
			String myUpdateTime = (String) myInfojo.get("updated_time");
				//取得发表时间，并格式化
			myUpdateTime = ApiUtils.FacebookTimeFormat(myUpdateTime);
			
			//获取当天微博信息，及必须要有文字信息或者图片
			if ((myInfojo.has("message") || myInfojo.has("picture"))) {
				myDTO.setUserId((String) fromJson.get("id"));
				myDTO.setPostID((String) myInfojo.get("id"));
				myDTO.setUpdate_time(myUpdateTime);
				if (myInfojo.has("message")) {
					myDTO.setMessage((String) myInfojo.get("message"));
				}
				if (myInfojo.has("picture")) {
					myDTO.setImageUrl((String) myInfojo.get("picture"));
				}
				if (myInfojo.has("shares")) {
					JSONObject shareOJ = (JSONObject) myInfojo.get("shares");
					myDTO.setShareNum((Integer) shareOJ.get("count"));
				}
    			if(myInfojo.has("comments")){
    				JSONObject myComment =  (JSONObject) myInfojo.get("comments");
    				JSONArray comments = (JSONArray) myComment.get("data");
    				
    				List<CommentDTO> commentlist = new ArrayList<CommentDTO>();
    				for (int j = 0; j < comments.length(); j++) {
    					CommentDTO comDTO = new CommentDTO();
    					//评论信息
    					JSONObject commentjo = (JSONObject) comments.get(j);
//    					System.out.println("commentjo::::::::" +j+ commentjo);
    					comDTO.setUserLikes((Boolean) commentjo.get("user_likes"));
    					comDTO.setMessage((String) commentjo.get("message"));
    					comDTO.setCommentId((String) commentjo.get("id"));
    					comDTO.setLikeCount((Integer) commentjo.get("like_count"));
    					comDTO.setCanRemove((Boolean) commentjo.get("can_remove"));
    					//时间格式处理
    					String commentTime = (String) commentjo.get("created_time");
    					commentTime = ApiUtils.FacebookTimeFormat(commentTime);
    					comDTO.setCreateTime(commentTime);
    					//评论人
    					JSONObject fromjo = (JSONObject) commentjo.get("from");
    					comDTO.setFromId((String) fromjo.get("id"));
    					comDTO.setFromName((String) fromjo.get("name"));
//    					System.out.println("comDTO" + j +":::::"+ comDTO);
    					commentlist.add(comDTO);
    				}
//    				System.out.println("commentlist:::::"+i + commentlist);
    				myDTO.setComDTO(commentlist);
//    				System.out.println("myDTO:::" + myDTO);
    				//评论数
    				int commentNum = comments.length();
        			myDTO.setCommentNum(commentNum);
    			}
				myList.add(myDTO);//添加显示自己的动态
			}
		}
		
        //获取我的动态按时间先后排序
		myList = ApiUtils.FacebookOrderByUpdateTime(myList);
//        System.out.println(myList);
        
        model.addAttribute("myList", myList);
        return "facebook/getMyPosts";
	}
	
	/**
	 * 5.Home Page (All Dynamic)
	 */
	@RequestMapping(value="getAllDyn.do")
	public String facebookFriendsDyn(HttpServletRequest request, Model model) throws Exception{

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
		//防止没授权报错
		if(myInfo == null || myInfo == ""){
			return "facebook/getAllDyn";
		}
		/*将返回的字符串转换成JSON,获取需要的数据*/
		JSONObject myInfoJson = new JSONObject(myInfo);
		JSONArray myInfoData = myInfoJson.getJSONArray("data");
		
		
		for (int i = 0; i < myInfoData.length(); i++) {//或者可以先取当前用户的前10条微博
			
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
        
		//1.循环取出授权用户的朋友的id，可以先取10个好友，若要取全部则friendData.length()
        for (int i = 0; i < friendData.length(); i++) {
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
        			fbDto.setUserId(userId);
        			//判断获取的json是否有message字段
        			if(feedjo.has("message")){
        				fbDto.setMessage((String) feedjo.get("message"));
        			}
        			//判断获取的json是否有picture字段
        			if(feedjo.has("picture")){
        				fbDto.setImageUrl((String) feedjo.get("picture"));
        			}
//        			//判断获取的json是否有comments字段
        			if(feedjo.has("comments")){
	    				JSONObject friendComment =  (JSONObject) feedjo.get("comments");
	    				JSONArray comments = (JSONArray) friendComment.get("data");
	    				
	    				List<CommentDTO> commentlist = new ArrayList<CommentDTO>();
	    				for (int m = 0; m < comments.length(); m++) {
	    					CommentDTO comDTO = new CommentDTO();
	    					//评论信息
	    					JSONObject commentjo = (JSONObject) comments.get(m);
	//    					System.out.println("commentjo::::::::" +m+ commentjo);
	    					comDTO.setUserLikes((Boolean) commentjo.get("user_likes"));
	    					comDTO.setMessage((String) commentjo.get("message"));
	    					comDTO.setCommentId((String) commentjo.get("id"));
	    					comDTO.setLikeCount((Integer) commentjo.get("like_count"));
	    					comDTO.setCanRemove((Boolean) commentjo.get("can_remove"));
	    					//时间格式处理
	    					String commentTime = (String) commentjo.get("created_time");
	    					commentTime = ApiUtils.FacebookTimeFormat(commentTime);
	    					comDTO.setCreateTime(commentTime);
	    					//评论人
	    					JSONObject fromjo = (JSONObject) commentjo.get("from");
	    					comDTO.setFromId((String) fromjo.get("id"));
	    					comDTO.setFromName((String) fromjo.get("name"));
	//    					System.out.println("comDTO" + m +":::::"+ comDTO);
	    					commentlist.add(comDTO);
	    				}
	//    				System.out.println("commentlist:::::"+i + commentlist);
	    				fbDto.setComDTO(commentlist);
	//    				System.out.println("myDTO:::" + myDTO);
	    				//评论数
	    				int commentNum = comments.length();
	    				fbDto.setCommentNum(commentNum);
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
        
        //把所需要的数据存到model里面
        model.addAttribute("allDyn", friendList);
        
        return "facebook/getAllDyn";
	}//friendDyn
	
}
