package cft.commons.pms.web.tencent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cft.commons.core.util.HttpClientUtils;
import cft.commons.pms.dto.tencent.ReviewDTO;
import cft.commons.pms.dto.tencent.WeiBoDTO;
import cft.commons.pms.web.sweibo.sinaUtil.SinaUtil;

@Controller
public class TencentWeiBoController {

	private static final String APP_KEY = "801495189";
	private static final String CLIENT_SECET = "ff66b84d1e37af0b630639de332ef996";
	private static final String REDIRECT_URL = "http://localhost:8088/pms/tencent/tweibo.do";
	//调用API需要的公共部分
	private static final String COMMON_URL = "https://open.t.qq.com/api";
	//公有参数
	private static final String COMMON_PARAM = "oauth_consumer_key=" + APP_KEY
			                                   + "&oauth_version=2.a&scope=all";
	
	
	
    
/////////////////////////////////////////////////////////////
/////////////////////以下为*.do请求处理方法////////////////////////
/////////////////////////////////////////////////////////////
	/* 回调地址  */
	@RequestMapping(value = "tweibo.do")
	public String tweibo(String code, String openid, String openkey,HttpServletRequest request) throws Exception {
		//获取code，通过code发出请求获取access_token
		String url = "https://open.t.qq.com/cgi-bin/oauth2/access_token?client_id=" + APP_KEY
				     + "&client_secret=" + CLIENT_SECET + "&redirect_uri=" + REDIRECT_URL
				     + "&grant_type=authorization_code&code=" + code;
		
		//发出请求，成功则返回带access_token的url字符串
		String result = HttpClientUtils.httpGet(url, 9000, 9000);
		String[] params = result.split("&");
		String tencent_token = "";
		
		//遍历切割后的字符串,获取access_token
		for (String param : params) {
			if (param.contains("access_token")) {
				tencent_token = param.split("=")[1];
				break;
			}
		}
		
		//判断是否授权成功
		if(!tencent_token.equals("")){
			//调用API需要的部分公有参数
			request.getSession().setAttribute("tencent_token", tencent_token);
		    request.getSession().setAttribute("openid", openid);
			request.getSession().setAttribute("openkey", openkey);
		}
		
		return "easyui/layout";
	}
    
    /* 发送一条微博消息   */	
	@RequestMapping(value="sendWeiBo.do")
	public @ResponseBody
	String sendWeiBo(String content,HttpServletRequest request) throws Exception{
		
		String url = COMMON_URL + "/t/add";
		
		Map<String,String> nvpMap = new HashMap<String,String>();
		//私有参数
		nvpMap.put("format", "json");
		nvpMap.put("content", content);
		nvpMap.put("clientip", Utils.getClientIP());
		//公有参数
		nvpMap.put("access_token",(String)request.getSession().getAttribute("tencent_token"));
		nvpMap.put("oauth_consumer_key",APP_KEY);
		nvpMap.put("openid",(String)request.getSession().getAttribute("openid"));
		nvpMap.put("openkey",(String)request.getSession().getAttribute("openkey"));
		nvpMap.put("oauth_version", "2.a");
		nvpMap.put("scope", "all");
		//发出请求,返回字符串结果
		String result = HttpClientUtils.httpPost(nvpMap, url, 5000, 5000);

        System.out.println(result);

		/** 将字符串转成JSON,获取需要的信息    **/
		JSONObject jsonObject = new JSONObject(result);
		int errcode = (Integer)jsonObject.get("errcode");
		int ret = (Integer)jsonObject.get("ret");
		
		//根据官方文档,当errcode=0&&ret=0即请求成功
		if(errcode == 0 && ret == 0){
			return "success";
		}
		
		return "failure";
	}
	
	
	/* 发布一条带图片的微博    */
	@RequestMapping(value="sendPicWeiBo.do")
	public @ResponseBody
	String sendPicWeiBo(String content,HttpServletRequest request) throws Exception{
		
		String url = COMMON_URL + "/t/add_pic";
		
		Map<String,String> nvpMap = new HashMap<String,String>();
		Map<String, byte[]> itemsMap = new HashMap<String, byte[]>();
		
		//API需要的参数
		nvpMap.put("format", "json");
		nvpMap.put("content", content);
		nvpMap.put("clientip", Utils.getClientIP());
		nvpMap.put("access_token", (String)request.getSession().getAttribute("tencent_token"));
		nvpMap.put("openid", (String)request.getSession().getAttribute("openid"));
		nvpMap.put("openkey", (String)request.getSession().getAttribute("openkey"));
		nvpMap.put("oauth_consumer_key", APP_KEY);
		nvpMap.put("oauth_version", "2.a");
		nvpMap.put("scope", "all");
		
		//读入图片,转成字节
		byte[] b = SinaUtil.readFileImage(request.getSession().getServletContext().getRealPath("/") + "/static/images/test.jpg");
		itemsMap.put("pic", b);
		
		//发出请求
		String info = SinaUtil.postMethodRequestWithFile(url, nvpMap, SinaUtil.header, itemsMap);
		
		if(info.equals("")){
			return "failure";
		}
		
		return "success";
	}
	
	
	/* 获取评论  */
	@RequestMapping(value="getReview.do")
	public String getReview(String rootid,HttpServletRequest request) throws Exception{
		//请求url
		String url = COMMON_URL + "/t/re_list?"
				     + "format=json"
				     + "&flag=1"
				     + "&rootid=" + rootid
				     + "&pageflag=0"
				     + "&pagetime=0"
				     + "&reqnum=10"
				     + "&twitterid=0"
				     + "&access_token=" + request.getSession().getAttribute("tencent_token")
		             + "&openid=" + request.getSession().getAttribute("openid")
		             + "&openkey=" + request.getSession().getAttribute("openkey")
		             + "&clientip=" + Utils.getClientIP()
				     + "&" + COMMON_PARAM;

		String result = HttpClientUtils.httpGet(url, 5000, 5000);
//System.out.println(result);
		/* 将返回结果转换成JSON,获取需要的数据    */
		JSONObject json = new JSONObject(result);
		String msg = (String)json.get("msg");
		if(msg.equals("have no tweet")){
			request.setAttribute("message", "此微博目前没有被评论");
			return "tencent/getReview";
		}
		
		JSONObject data= json.getJSONObject("data");
		JSONArray info = data.getJSONArray("info");
		List<ReviewDTO> reList = new ArrayList<ReviewDTO>();
		
		for(int i = 0 ; i < info.length() ; i++){
			JSONObject obj = info.getJSONObject(i);
			
			//将需要的信息放入dto
			ReviewDTO re = new ReviewDTO();
			re.setId((String)obj.get("id"));
			re.setNick((String)obj.get("nick"));
			re.setText((String)obj.get("origtext"));
			re.setTimestamp((Integer)obj.get("timestamp"));
			
			reList.add(re);
		}
		
		if(reList != null && !reList.isEmpty()){
			request.setAttribute("reList", reList);
		}else{
			System.out.println("空");
		}
		
		return "tencent/getReview";
	}
	
	
	/* 转发微博    */
	@RequestMapping(value="forwardWeiBo.do")
	public String forwardWeiBo(String reid,String content,HttpServletRequest request) throws Exception{
		//请求url
		String url = COMMON_URL + "/t/re_add";
		
		Map<String,String> nvpMap = new HashMap<String,String>();
		//私有参数
		nvpMap.put("format", "json");
		nvpMap.put("content", content);
		nvpMap.put("clientip", Utils.getClientIP());
		nvpMap.put("reid", reid);
		//公有参数
		nvpMap.put("access_token", (String)request.getSession().getAttribute("tencent_token"));
		nvpMap.put("openid", (String)request.getSession().getAttribute("openid"));
		nvpMap.put("openkey", (String)request.getSession().getAttribute("openkey"));
		nvpMap.put("oauth_consumer_key", APP_KEY);
		nvpMap.put("oauth_version", "2.a");
		nvpMap.put("scope", "all");
		
		String result = HttpClientUtils.httpPost(nvpMap, url, 5000, 5000);

		/*  将返回的字符串转换成JSON,获取需要的数据       */
		JSONObject json = new JSONObject(result);
		int errcode = (Integer)json.get("errcode");
		int ret = (Integer)json.get("ret");
		
		//当errcode==0&&ret==0时,请求成功
		if(errcode == 0 && ret == 0){
			request.setAttribute("success_info", "转发成功！请登录腾讯微博查看结果");
		}
		
		return "tencent/result";
	}
	
	
	/*  获取关注的人的最新微博    */
	@RequestMapping(value="getFocusPeopleWeiBo.do")
	public @ResponseBody
	String getFocusPeopleWeiBo(HttpServletRequest request) throws Exception{
		//请求url
		String url = COMMON_URL + "/statuses/home_timeline?"
				     + "format=json"
				     + "&pageflag=0"
				     + "&pagetime=0"
				     + "&reqnum=20"
				     + "&type=3"
				     + "&contenttype=0"
				     + "&access_token=" + request.getSession().getAttribute("tencent_token")
				     + "&openid=" + request.getSession().getAttribute("openid")
				     + "&openkey=" + request.getSession().getAttribute("openkey")
				     + "&clientip=" + Utils.getClientIP()
				     + "&" + COMMON_PARAM;
		
		String result = HttpClientUtils.httpGet(url, 5000, 5000);
//System.out.println(result);
		/*将返回的字符串转换成JSON,获取需要的数据*/
        JSONObject json = new JSONObject(result);
        JSONObject data = json.getJSONObject("data");
        JSONArray info = data.getJSONArray("info");
        List<WeiBoDTO> focusList = new ArrayList<WeiBoDTO>();
        
        for(int i = 0 ; i < info.length() ; i++){
        	JSONObject obj = info.getJSONObject(i);
        	//用户唯一id，用来排除自己的微博
        	String uid = (String)obj.get("openid");
        
        	//用uid排除自己的微博
        	if(!request.getSession().getAttribute("openid").equals(uid)){
        		//获取当前日期
        		String nowDate = Utils.getNowDate();
        		//获取微博发表时间
        		String wbDate = Utils.timestampToDate((Integer)obj.get("timestamp"));
        		
        		//获取当前日期发表的微博,排除其他微博
        		if(wbDate.equals(nowDate)){
        			WeiBoDTO wb = new WeiBoDTO();
		        	wb.setId((String)obj.get("id"));
		        	wb.setNick((String)obj.get("nick"));
		        	wb.setType((Integer)obj.get("type"));
		        	wb.setTimestamp((Integer)obj.get("timestamp"));	
		        	wb.setText((String)obj.get("text"));
		        	wb.setDate(Utils.getWeiBoTime((Integer)obj.get("timestamp")));//将获取的timestamp转换时间格式
		        	
		        	//判断此微博是不是转发的
		        	if((Integer)obj.get("type") == 2){
		        		JSONObject source = (JSONObject)obj.get("source");
		        		wb.setOrigtext((String)source.get("origtext"));
		        	}
		        	
		        	focusList.add(wb);	
        		}
        	}
        }
        
        //将list拼接成字符串需要的变量
        String resultData = "";
        String content ="";
    	String begin = "{\"weibo\":[";
    	String end = "]}";
        
        if(focusList == null || focusList.isEmpty()){
             return "failure";
        }else{
        	
        	//若长度为1，则不需要加逗号,否则需注意加逗号
            if(focusList.size() == 1){	
        		String weibo = "{\"id\":" + "\"" + focusList.get(0).getId() + "\"" +
        				       ",\"content\":" + "\"" + focusList.get(0).getText() + "\"" +
        				       ",\"name\":" + "\"" + focusList.get(0).getNick() + "\"" +
        				       ",\"time\":" + "\"" + focusList.get(0).getDate() + "\"" +
        	                   "}";
        		
        		content = content + weibo;
            }else if(focusList.size() > 1){
            	for(int i = 0 ; i < focusList.size() - 1 ; i++){
	        		String weibo = "{\"id\":" + "\"" + focusList.get(i).getId() + "\"" +
	        				       ",\"content\":" + "\"" + focusList.get(i).getText() + "\"" +
	        				       ",\"name\":" + "\"" + focusList.get(i).getNick() + "\"" +
	        				       ",\"time\":" + "\"" + focusList.get(i).getDate() + "\"" +
	        	                   "},";
	        		
	        		content = content + weibo;
	        	}
	        	
	        	content = content + "{\"id\":" + "\"" + focusList.get(focusList.size()).getId() + "\"" +
				                    ",\"content\":" + "\"" + focusList.get(focusList.size()).getText() + "\"" +
				                    ",\"name\":" + "\"" + focusList.get(focusList.size()).getNick() + "\"" +
				                    ",\"time\":" + "\"" + focusList.get(focusList.size()).getDate() + "\"" +
	                                "}";
            }
        	
        	resultData = begin + content + end;
        }
        
        return resultData;
	}
	
	
	/*  获取转播数和评论数     */
	@RequestMapping(value="getReviewAndBroadcast.do")
	public @ResponseBody String getReviewAndBroadcast(HttpServletRequest request) throws Exception{
		String ids = "377850008710440";
		
		//请求url
		String url = COMMON_URL + "/t/re_count?"
				     + "format=json"
				     + "&ids=" + ids
				     + "&flag=2"
				     + "&access_token=" + request.getSession().getAttribute("tencent_token")
				     + "&openid=" + request.getSession().getAttribute("openid")
				     + "&openkey" + request.getSession().getAttribute("openkey")
				     + "&clientip" + Utils.getClientIP()
				     + "&" + COMMON_PARAM;
		
		String result = HttpClientUtils.httpGet(url, 5000, 5000);

        /* 将返回的字符串转换成JSON,获取需要的数据  */
        JSONObject json = new JSONObject(result);
        JSONObject data = json.getJSONObject("data");
        JSONObject wbID = data.getJSONObject(ids);
        
        int count = (Integer)wbID.get("count");
        int mcount = (Integer)wbID.get("mcount");
		
        
		return "count=" +count + ",mcount="+mcount;
	}
}
