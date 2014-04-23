package cft.commons.pms.web.tencent;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import cft.commons.core.util.HttpClientUtils;
import cft.commons.pms.dto.tencent.WeiBoDTO;

public class Utils {
   
   private static String clientip = "";
   //调用API需要的公共部分
   private static final String COMMON_URL = "https://open.t.qq.com/api";
   //公有参数
   private static final String COMMON_PARAM = "oauth_consumer_key=801495189&oauth_version=2.a&scope=all";
   
   /* 获取客户端ip的静态方法  */
   public static String getClientIP() throws UnknownHostException{
	   if(clientip == null || clientip.equals("")){
			InetAddress address = InetAddress.getLocalHost();
			clientip = address.getHostAddress(); 
	   }
		
	   return clientip;
   }
    
   
   
   /* *
	 * 这个方法用来获取当前用户微博列表,主要作用是辅助前台的
	 * 一些显示效果以及提供微博id
	 * */
	public static List<WeiBoDTO> getWeiBoList(String access_token,String openid,String openkey) throws Exception{
		//拼接请求url
		String url = COMMON_URL + "/statuses/broadcast_timeline?"
				     + "format=json"
				     + "&pageflag=0"
				     + "&pagetime=0"
				     + "&reqnum=5"
				     + "&lastid=0"
				     + "&type=3"
				     + "&contenttype=0"
				     + "&access_token=" + access_token
		             + "&openid=" + openid
		             + "&openkey=" + openkey
		             + "&clientip=" + Utils.getClientIP()
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
			wb.setNick((String)object.get("nick"));
			wb.setHead((String)object.get("head"));
			wb.setType((Integer)object.get("type"));
			wb.setTimestamp((Integer)object.get("timestamp"));
			
			//判断微博是否是转发的
			if((Integer)object.get("type") == 2){
				JSONObject source = (JSONObject)object.get("source");
				wb.setOrigtext((String)source.get("origtext"));
			}
			
			
			wbList.add(wb);
		}
		
		return wbList;
	}
	
	
	/* *
	 * 将获取到的timestamp格式的时间转换成标准时间格式,主要用于转换在前台显示的timestamp
	 * */
	public static String getWeiBoTime(Integer timestamp){
		Long wbTime = Long.parseLong(timestamp.toString());
		Date date = new Date(wbTime * 1000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月dd日   HH时mm分");
		String sendDate = sdf.format(date);
	    
		return sendDate;
	}
	
	/* *
	 * 将获取到的timestamp格式的时间转换成标准时间格式，如：yyyy-MM-dd HH:mm
	 * */
	public static String timestampToDate(Integer timestamp){
		Long wbTime = Long.parseLong(timestamp.toString());
		Date date = new Date(wbTime * 1000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String sendDate = sdf.format(date);
	    
		return sendDate;
	}
	
	
	/* *
	 * 获取当前日期，如：yyyy-MM-dd
	 * */
	public static String getNowDate(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String nowDate = sdf.format(date);
		
		return nowDate;
	}
	
	
	public static void main(String[] args) {
		
	}
}
