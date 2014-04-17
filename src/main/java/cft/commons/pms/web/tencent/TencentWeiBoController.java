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

import cft.commons.core.util.HttpClientUtils;
import cft.commons.pms.dto.tencent.ReviewDTO;
import cft.commons.pms.dto.tencent.WeiBoDTO;

@Controller
public class TencentWeiBoController {

	private static final String APP_KEY = "801495189";
	private static final String CLIENT_SECET = "ff66b84d1e37af0b630639de332ef996";
	private static final String REDIRECT_URL = "http://localhost:8088/pms/tencent/tweibo.do";
	//调用API需要的公共部分
	private static final String COMMON_URL = "https://open.t.qq.com/api";
	private static final String COMMON_PARAM = "oauth_consumer_key=801495189&oauth_version=2.a&scope=all";
	

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
	public String sendWeiBo(String content,HttpServletRequest request) throws Exception{
		
		String url = COMMON_URL + "/t/add";
		
		Map<String,String> nvpMap = new HashMap<String,String>();
		//私有参数
		nvpMap.put("format", "json");
		nvpMap.put("content", content);
		nvpMap.put("clientip", IPUtils.getClientIP());
		//公有参数
		nvpMap.put("access_token",(String)request.getSession().getAttribute("tencent_token"));
		nvpMap.put("oauth_consumer_key",APP_KEY);
		nvpMap.put("openid",(String)request.getSession().getAttribute("openid"));
		nvpMap.put("openkey",(String)request.getSession().getAttribute("openkey"));
		nvpMap.put("oauth_version", "2.a");
		nvpMap.put("scope", "all");
		//发出请求,返回字符串结果
		String result = HttpClientUtils.httpPost(nvpMap, url, 5000, 5000);

		/** 将字符串转成JSON,获取需要的信息    **/
		JSONObject jsonObject = new JSONObject(result);
		int errcode = (Integer)jsonObject.get("errcode");
		int ret = (Integer)jsonObject.get("ret");
		
		//根据官方文档,当errcode=0&&ret=0即请求成功
		if(errcode == 0 && ret == 0){
			request.setAttribute("text_weibo", "发表成功！请登录腾讯微博查看");
		}
		
		return "tencent/result";
	}
	
	
	/* 获取当前用户微博列表   */	
	@RequestMapping(value="getWeiBoList.do")
	public String getWeiBoList(HttpServletRequest request) throws Exception{
		//拼接请求url
		String url = COMMON_URL + "/statuses/broadcast_timeline?"
				     + "format=json"
				     + "&pageflag=0"
				     + "&pagetime=0"
				     + "&reqnum=5"
				     + "&lastid=0"
				     + "&type=3"
				     + "&contenttype=0"
				     + "&access_token=" + request.getSession().getAttribute("tencent_token")
		             + "&openid=" + request.getSession().getAttribute("openid")
		             + "&openkey=" + request.getSession().getAttribute("openkey")
		             + "&clientip=" + IPUtils.getClientIP()
		             + "&" + COMMON_PARAM;
		
		String result = HttpClientUtils.httpGet(url, 5000, 5000);

        /* 将返回的结果转换成JSON,获取微博数据    */
		JSONObject json = new JSONObject(result);
		JSONObject data = json.getJSONObject("data");
		JSONArray info = data.getJSONArray("info");
		List<WeiBoDTO> wbList = new ArrayList<WeiBoDTO>();
		//多条微博，循环获取需要的数据
		for(int i = 0 ; i < info.length() ; i++){
			JSONObject object = (JSONObject)info.get(i);
			
			//微博dto,装入微博相关信息
			WeiBoDTO wb = new WeiBoDTO();
			wb.setId((String)object.get("id"));
			wb.setText((String)object.get("text"));
			wb.setOrgintext((String)object.get("text"));
			wb.setNick((String)object.get("nick"));
			wb.setHead((String)object.get("head"));
			wb.setType((Integer)object.get("type"));
			wb.setTimestamp((Integer)object.get("timestamp"));
			
			wbList.add(wb);
		}
		
		if(wbList != null && !wbList.isEmpty()){
			request.setAttribute("wbList", wbList);
		}
		
		return "tencent/userWeiBoList";
	}
	
	
	/* 获取评论  */
	@RequestMapping(value="getReview.do")
	public String getReview(String rootid,HttpServletRequest request) throws Exception{
		//请求url
		String url = COMMON_URL + "/t/re_list?"
				     + "format=json"
				     + "&flag=1"
				     + "&rootid=377850008710440"
				     + "&pageflag=0"
				     + "&pagetime=0"
				     + "&reqnum=10"
				     + "&twitterid=0"
				     + "&access_token=" + request.getSession().getAttribute("tencent_token")
		             + "&openid=" + request.getSession().getAttribute("openid")
		             + "&openkey=" + request.getSession().getAttribute("openkey")
		             + "&clientip=" + IPUtils.getClientIP()
				     + "&" + COMMON_PARAM;

		String result = HttpClientUtils.httpGet(url, 5000, 5000);
	
		/* 将返回结果转换成JSON,获取需要的数据    */
		JSONObject json = new JSONObject(result);
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
		}
		
		return "tencent/getReview";
	}
	
	
	/* 转发微博    */
	@RequestMapping(value="forwardWeiBo.do")
	public String forwardWeiBo(String reid,String content,HttpServletRequest request) throws Exception{
		//请求url
		content = "测试转发微博";
		reid="377850008710440";
		String url = COMMON_URL + "/t/re_add";
		
		Map<String,String> nvpMap = new HashMap<String,String>();
		//私有参数
		nvpMap.put("format", "json");
		nvpMap.put("content", content);
		nvpMap.put("clientip", IPUtils.getClientIP());
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
		
		if(errcode == 0 && ret == 0){
			request.setAttribute("success_info", "转发成功！请登录腾讯微博查看结果");
		}
		
		return "tencent/result";
	}
	
}
