package cft.commons.pms.web.instagram;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cft.commons.core.util.HttpClientUtils;
import cft.commons.pms.dto.instagram.CommentDto;
import cft.commons.pms.dto.instagram.FollowDto;
import cft.commons.pms.dto.instagram.LikeMediaDto;
import cft.commons.pms.dto.instagram.ShareDto;
import cft.commons.pms.web.tencent.Utils;

@Controller
public class InstagramController {

	private static final String APP_KEY = "195a0d5137fc46c58ef5f4db4281972e";
	private static final String CLIENT_SECET = "b3e18ec7fd524df6be0067a91504847f";
	private static final String REDIRECT_URL = "http://localhost:8088/pms/instagram/instagramApi.do";

	@RequestMapping(value = "instagramApi.do")
	public String instagram(String code, Model model, HttpServletRequest request)
			throws IOException {
		System.out.println("======================");
		System.out.println(code);

		// 授权instagram
		String url = "https://api.instagram.com/oauth/access_token";
		Map<String, String> nvpMap = new HashMap<String, String>();
		nvpMap.put("client_id", APP_KEY);
		nvpMap.put("client_secret", CLIENT_SECET);
		nvpMap.put("redirect_uri", REDIRECT_URL);
		nvpMap.put("code", code);
		nvpMap.put("grant_type", "authorization_code");
		String result = HttpClientUtils.httpPost(nvpMap, url, 9000, 9000);

		JSONObject json = new JSONObject(result);
		JSONObject user = json.getJSONObject("user");

		String instagram_token = json.getString("access_token");
		String uid = user.getString("id");
		System.out.println("result=" + result);
		System.out.println(user);
		System.out.println("token =" + instagram_token);
		System.out.println("uid =" + uid);

		// 判断是否授权成功
		if (!instagram_token.equals("")) {
			// 调用API需要的部分公有参数
			request.getSession().setAttribute("instagram_token", instagram_token);
			request.getSession().setAttribute("uid", uid);

			// 在页面使用，提示授权成功
			request.setAttribute("instagram_info", "success");
		} else {
			request.setAttribute("instagram_info", "failure");
		}

		model.addAttribute("instagram_token", instagram_token);
		model.addAttribute("uid", uid);
		model.addAttribute("success", "instagram授权成功");
		// 获取关注用户的信息
		/*
		 * String guanzhuUrl="https://api.instagram.com/v1/users/"+uid+
		 * "/follows?access_token="+access_token; String result2 =
		 * HttpClientUtils.httpGet(guanzhuUrl, 9000, 9000);
		 * System.out.println(result2);
		 */
		// 获取用户的列表该用户后跟。
		String followByUrl = "https://api.instagram.com/v1/users/" + uid
				+ "/followed-by?access_token=" + instagram_token;
		String result2 = HttpClientUtils.httpGet(followByUrl, 9000, 9000);

		return "easyui/layout";

	}

	@RequestMapping(value = "create.do")
	public String Create() throws IOException {

		// 创建一个订阅

		String subscriptionUrl = "https://api.instagram.com/v1/subscriptions?client_id=" + APP_KEY
				+ "&client_secret=" + CLIENT_SECET + "&object=user" + "&aspect=media"
				+ "&verify_token=myVerifyToken" + "&callback_url=" + REDIRECT_URL;

		String result2 = HttpClientUtils.httpGet(subscriptionUrl, 3000, 9000);
		return result2;

	}

	// 获取关注人信息
	@RequestMapping(value = "followFirend.do")
	public  @ResponseBody  String FollowFirend(String uid, String instagram_token, Model model,
			HttpServletRequest request) throws IOException {

		uid = (String) request.getSession().getAttribute("uid");
		instagram_token = (String) request.getSession().getAttribute("instagram_token");
		String followByUrl = "https://api.instagram.com/v1/users/"+uid+"/follows?access_token="+instagram_token;
		String result2 = HttpClientUtils.httpGet(followByUrl, 9000, 9000);

		JSONObject json2 = new JSONObject(result2);
		JSONArray data = json2.getJSONArray("data");
		List<FollowDto> names = new ArrayList<FollowDto>();
		
		
		for (int i = 0; i < data.length(); i++) {
			JSONObject jo = (JSONObject) data.get(i);
			String joString = jo.toString(i);
            String userId=jo.getString("id");
			String userName = jo.getString("username");
			String profile_picture = jo.getString("profile_picture");
			// names.add(userName);
			// names.add(profile_picture);
			//FollowDto follow = new FollowDto();
			
			//follow.setUsername(userName);
			//follow.setPhoto(profile_picture);
			//names.add(follow);
			
			//获取用户信息
			String userUrl="https://api.instagram.com/v1/users/"+userId+"/media/recent/?access_token="+instagram_token;
			String userResult = HttpClientUtils.httpGet(userUrl, 9000, 9000);
			 
			System.out.println("userResult"+userResult);
			System.out.println(userId);
			System.out.println(joString);
			System.out.println(userName);
			System.out.println("userResult========="+userResult);
			System.out.println("names=========" + names.size());
			JSONObject json3 = new JSONObject(userResult);
			JSONArray frienddata = json3.getJSONArray("data");
			for (int f = 0; f < frienddata.length(); f++) {
				JSONObject fridendjo = (JSONObject) frienddata.get(f);
				
	            String link=fridendjo.getString("link");
				String created_time=Utils.timestampToDate((Integer.parseInt(fridendjo.getString("created_time"))));
				
	            System.out.println("link========="+link);
	            
	            String shareUrl="http://api.instagram.com/oembed?url="+link;
	    		
	    		String shareResult=HttpClientUtils.httpGet(shareUrl, 9000, 9000);
	    		
	    		JSONObject shareMedia = new JSONObject(shareResult);
	    		String url = shareMedia.getString("url");
	    		String author_name=shareMedia.getString("author_name");
	    		String type=shareMedia.getString("type");
	    		String title=shareMedia.getString("title");
	    		String media_id=shareMedia.getString("media_id");
	    	    System.out.println(shareMedia);
	    		
	    		
	    		
	    		FollowDto followDto=new FollowDto();
	    		
	    		followDto.setTitle(title);
	    		followDto.setUsername(userName);
	    		followDto.setType(type);
	    		followDto.setUrl(url);
	    		followDto.setPhoto(profile_picture);
	    		followDto.setMedia_id(media_id);
	    		followDto.setDate(created_time);
	    		names.add(followDto);
				
			}
           System.out.println("frienddara=========="+frienddata);
			
		}
		
		
		
		 
        //将list拼接成字符串需要的变量
        String resultData = "";
        String content ="";
    	String begin = "[";
    	String end = "]";
        
    	if(names==null||names.isEmpty()){
    		
    		return "empty";
    		
    	}else{
    		
    		//若长度为1，则不需要加逗号,否则需注意加逗号
            if(names.size() == 1){	
        		String weibo = "{\"id\":" + "\"" + names.get(0).getMedia_id() + "\"" +
 				       ",\"content\":" + "\"" + names.get(0).getTitle() + "\"" +
 				       ",\"name\":" + "\"" + names.get(0).getUsername() + "\"" +
 				       ",\"time\":" + "\"" + names.get(0).getDate()+ "\"" +
 	                   "},";
        		
        		content = content + weibo;
            }else if(names.size() > 1){
            	for(int i = 0 ; i < names.size() - 1 ; i++){
	        		String weibo =  "{\"id\":" + "\"" + names.get(i).getMedia_id() + "\"" +
	  				       ",\"content\":" + "\"" + names.get(i).getTitle() + "\"" +
	 				       ",\"name\":" + "\"" + names.get(i).getUsername() + "\"" +
	 				       ",\"time\":" + "\"" + names.get(i).getDate()+ "\"" +
	 	                   "},";
	        		
	        		content = content + weibo;
	        	}
            	
            	content = content +  "{\"id\":" + "\"" + names.get(names.size()-1).getMedia_id() + "\"" +
  				       ",\"content\":" + "\"" + names.get(names.size()-1).getTitle() + "\"" +
  				       ",\"name\":" + "\"" + names.get(names.size()-1).getUsername() + "\"" +
  				       ",\"time\":" + "\"" + names.get(names.size()-1).getDate()+ "\"" +
  	                   "},";
            
            }
            
            resultData = begin + content + end;
            
    	}
    	
		
    	return resultData;

	}

	// 获取评论
	@RequestMapping(value = "comment.do")
	public String Comment(String uid, String instagram_token, HttpServletRequest request)
			throws IOException {

		uid = (String) request.getSession().getAttribute("uid");
		instagram_token = (String) request.getSession().getAttribute("instagram_token");
		// 获取媒体的的信息和id
		String mdeiaId = "https://api.instagram.com/v1/users/"+uid+"/media/recent/?access_token="+instagram_token;

		String result2 = HttpClientUtils.httpGet(mdeiaId, 9000, 9000);
		List<CommentDto> comments = new ArrayList<CommentDto>();
		
		JSONObject jsonComment = new JSONObject(result2);
		JSONArray data = jsonComment.getJSONArray("data");
		System.out.println("data" + data);
		for (int i = 0; i < data.length(); i++) {
			JSONObject jo = (JSONObject) data.get(i);
			//String joString = jo.toString();
			String MediaId = jo.getString("id");
			String link = jo.getString("link");
			//String images=jo.getString("images");
			
			//String images=jo.getString("images");
			// System.out.println("id="+MediaId);
			// System.out.println(userName);
			// request.getSession().setAttribute("joString", joString);
           // System.out.println("images========="+images);
			String commentUrl = "https://api.instagram.com/v1/media/" + MediaId
					+ "/comments?access_token=" + instagram_token;
			String commentResult = HttpClientUtils.httpGet(commentUrl, 9000, 9000);

			JSONObject jsonComment2 = new JSONObject(commentResult);
			JSONArray data2 = jsonComment2.getJSONArray("data");
			// String commentString=commentjo.toString();
			// String comment=data2.getString("text");
			// System.out.println("comment"+comment);
			//CommentDto commentDto = new CommentDto();
			for (int a = 0; a < data2.length(); a++) {
				CommentDto commentDto = new CommentDto();
				JSONObject comment = (JSONObject) data2.get(a);
				String text = comment.getString("text");
				JSONObject from = comment.getJSONObject("from");
				String commentName = from.getString("username");
				String profile_picture = from.getString("profile_picture");
				System.out.println("text=" + text);
				System.out.println("commentName=" + commentName);
				
				commentDto.setCommentName(commentName);
				commentDto.setText(text);
				commentDto.setLink(link);
				commentDto.setProfile_picture(profile_picture);
				
				comments.add(commentDto);
				System.out.println("comments=" + comments.size());

			}

			System.out.println("评论=" + data2);
		}
		
		request.setAttribute("comments", comments);
		return "instagram/comment";

	}

	// 获取用户谁喜欢这个项目的媒体列表。
	@RequestMapping(value = "likeMedia.do")
	public String likeMedia(String uid, String instagram_token, HttpServletRequest request)
			throws IOException {

		uid = (String) request.getSession().getAttribute("uid");
		instagram_token = (String) request.getSession().getAttribute("instagram_token");
		// 获取媒体的的信息和id
		String mdeiaId = "https://api.instagram.com/v1/users/self/feed?access_token="
				+ instagram_token;

		String result2 = HttpClientUtils.httpGet(mdeiaId, 9000, 9000);

		JSONObject jsonComment = new JSONObject(result2);

		JSONArray data = jsonComment.getJSONArray("data");
         
		
		List<LikeMediaDto> likeList=new ArrayList<LikeMediaDto>();
		
		System.out.println("data" + data);

		for (int i = 0; i < data.length(); i++) {
			JSONObject jo = (JSONObject) data.get(i);
			String joString = jo.toString();
			String MediaId = jo.getString("id");
            //获取用户谁喜欢这个项目的媒体列表。
			String likeMediaUrl = "https://api.instagram.com/v1/media/" + MediaId
					+ "/likes?access_token=" + instagram_token;
			String likeMediaResult = HttpClientUtils.httpGet(likeMediaUrl, 9000, 9000);
			System.out.println("likeMediaResult=" + likeMediaResult);
             
			JSONObject likeMedia = new JSONObject(likeMediaResult);
			
			JSONArray Likedata = likeMedia.getJSONArray("data");
			System.out.println("likedata="+Likedata);
		  for (int likes = 0; likes < Likedata.length(); likes++) {
				
				JSONObject LikeJo=(JSONObject) Likedata.get(likes);
				String likeName=LikeJo.getString("username");
				System.out.println("likeName"+likeName);
				LikeMediaDto like=new LikeMediaDto();
				like.setUserName(likeName);
				likeList.add(like);
				
			}
		
			
		}
		 request.setAttribute("likeList",likeList);
		return "instagram/likeMedia";

	}
	
	//评论
	@RequestMapping(value="goComment.do")
	public String toComment(){
		
		
		return "instagram/createComment";
		
	}
	@RequestMapping(value="createComment.do")
	public String createComment(String uid, String instagram_token, HttpServletRequest request)throws IOException{
		
		uid = (String) request.getSession().getAttribute("uid");
		instagram_token = (String) request.getSession().getAttribute("instagram_token");
		//获取到评论内容
		String text=request.getParameter("comment");
		System.out.println(text);
		// 获取媒体的的信息和id
		String mdeiaId = "https://api.instagram.com/v1/users/self/feed?access_token="
				+ instagram_token;

		String result2 = HttpClientUtils.httpGet(mdeiaId, 9000, 9000);

		JSONObject jsonComment = new JSONObject(result2);

		JSONArray data = jsonComment.getJSONArray("data");
		
         
		
		
		
		System.out.println("data" + data);

		for (int i = 0; i < data.length(); i++) {
			JSONObject jo = (JSONObject) data.get(i);
			String joString = jo.toString();
			String MediaId = jo.getString("id");
		
			String commentUrl="https://api.instagram.com/v1/media/"+MediaId+"/comments?access_token="+instagram_token
					+"&text="+text;
			
			
			String result = HttpClientUtils.httpGet(commentUrl, 9000, 9000);
			System.out.println("comment1="+result);
		
	}
		return "instagram/comment";
	
	}
	
	
	//获取流行的media
	@RequestMapping(value="popular.do")
	public String popularMedia(String uid, String instagram_token, HttpServletRequest request) throws IOException{
		  
		uid = (String) request.getSession().getAttribute("uid");
		instagram_token = (String) request.getSession().getAttribute("instagram_token");
		
		String popuLarUrl="https://api.instagram.com/v1/media/popular?access_token="+instagram_token;
		
		String popularResult=HttpClientUtils.httpGet(popuLarUrl, 9000, 9000);
		 
		System.out.println("popularResult="+popularResult);
		
		return "instagram/popular";
		
	}
	
	//分享
	@RequestMapping(value="share.do")
	public String shareMedia(String uid, String instagram_token, HttpServletRequest request) throws IOException{
		
		
		uid = (String) request.getSession().getAttribute("uid");
		instagram_token = (String) request.getSession().getAttribute("instagram_token");
		// 获取媒体的的信息和id
		String mdeiaId = "https://api.instagram.com/v1/users/"+uid+"/media/recent/?access_token="+instagram_token;

		String result2 = HttpClientUtils.httpGet(mdeiaId, 9000, 9000);
		
		List<ShareDto> share = new ArrayList<ShareDto>();
		JSONObject jsonComment = new JSONObject(result2);
		JSONArray data = jsonComment.getJSONArray("data");
		System.out.println("data" + data);
		for (int i = 0; i < data.length(); i++) {
			JSONObject jo = (JSONObject) data.get(i);
			//String joString = jo.toString();
			String MediaId = jo.getString("id");
			String link = jo.getString("link");
		
		request.getRequestURI();
		String shareUrl="http://api.instagram.com/oembed?url="+link;
		
		String shareResult=HttpClientUtils.httpGet(shareUrl, 9000, 9000);
		
		JSONObject shareMedia = new JSONObject(shareResult);
		String url = shareMedia.getString("url");
		String author_name=shareMedia.getString("author_name");
		String type=shareMedia.getString("type");
		String title=shareMedia.getString("title");
		
		ShareDto shareDto=new ShareDto();
		
		shareDto.setTitle(title);
		shareDto.setAuthor_name(author_name);
		shareDto.setType(type);
		shareDto.setUrl(url);
		
		System.out.println(url);
		System.out.println(title);
		System.out.println(author_name);
		share.add(shareDto);
		
		
		
	}
		
	
		request.setAttribute("share", share);
		return "instagram/share";
	}
	
}