package cft.commons.pms.web.api.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.ByteArrayPartSource;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.json.JSONArray;
import org.json.JSONObject;

import cft.commons.core.util.HttpClientUtils;
import cft.commons.pms.dto.facebook.FacebookDTO;
import cft.commons.pms.dto.tencent.WeiBoDTO;

public class ApiUtils {
	
	private static String clientip = "";

	///////////////////////////////Sina工具方法////////////////////////////////
	/* 日期格式化 */
	public static String SinaDateFormat(String date) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", new Locale(
				"ENGLISH", "CHINA"));
		Date tempdate = format.parse(date);
		// format.applyPattern("yyyy年MM月dd日 HH时mm分ss秒");
		format.applyPattern("yyyy年MM月dd日 HH时mm分");
		return format.format(tempdate);
	}

	/* 日期格式化 */
	public static String DateFormat(String date) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", new Locale(
				"ENGLISH", "CHINA"));
		Date tempdate = format.parse(date);
		format.applyPattern("yyyy/MM/dd/");
		return format.format(tempdate);
	}

	/* 重写post方式 */
	public static String postMethodRequestWithFile(String url, Map<String, String> params,
			Map<String, String> header, Map<String, byte[]> itemsMap) {
		System.out.println("post request is begin! url =" + url);
		HttpClient hc = new HttpClient();
		try {
			PostMethod pm = new PostMethod(url);
			if (header != null) {
				for (String head_key : header.keySet()) {
					if (head_key == null || header.get(head_key) == null)
						continue;
					pm.addRequestHeader(head_key, header.get(head_key));
				}
			}
			int part_size = 1;
			if (params != null)
				part_size = params.size();
			if (itemsMap != null)
				part_size = part_size + itemsMap.size();
			Part[] parts = new Part[part_size];
			int index = 0;
			if (itemsMap != null) {
				for (String item_name : itemsMap.keySet()) {
					if (itemsMap.get(item_name) == null)
						continue;
					parts[index] = new FilePart(item_name, new ByteArrayPartSource(item_name,
							itemsMap.get(item_name)), "multipart/form-data;", "utf-8");
					index++;
				}
			}

			if (params != null) {
				for (String param_key : params.keySet()) {
					if (param_key == null || params.get(param_key) == null)
						continue;
					parts[index] = new StringPart(param_key, params.get(param_key), "utf-8");
					index++;
				}
			}
			pm.setRequestEntity(new MultipartRequestEntity(parts, pm.getParams()));
			pm.getParams().setContentCharset("utf8");
			hc.executeMethod(pm);
			String ret = pm.getResponseBodyAsString();
			System.err.println(ret);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("post request is end! url =" + url);
		}
		return "";
	}

	/* 图片文件转换 */
	@SuppressWarnings("resource")
	public static byte[] readFileImage(String filename) throws IOException {
		BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(
				filename));
		int len = bufferedInputStream.available();
		byte[] bytes = new byte[len];
		int r = bufferedInputStream.read(bytes);
		if (len != r) {
			bytes = null;
			throw new IOException("读取文件不正确");
		}
		bufferedInputStream.close();
		return bytes;
	}

	public static Map<String, String> header = new HashMap<String, String>();

	static {
		header.put("Accept-Language", "zh-CN,zh;q=0.8");
		header.put("User-Agent", "test sina api");
		header.put("Accept-Charset", "utf-8;q=0.7,*;q=0.3");
	}
	
	
	
///////////////////////////////Tencent工具方法////////////////////////////////////
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
		String COMMON_URL = "https://open.t.qq.com/api";
		String COMMON_PARAM = "oauth_consumer_key=801495189&oauth_version=2.a&scope=all";
		
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
		             + "&clientip=" + getClientIP()
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

	
	///////////////////////////////Facebook工具方法////////////////////////////////
	/* 日期格式化(具体到时分) 获取到的格式：2014-04-24T02:42:37+0000；返回格式：yyyy年MM月dd日 HH时mm分*/
	public static String FacebookTimeFormat(String date) throws ParseException {
	
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", new Locale(
		"ENGLISH", "CHINA"));
		Date tempdate = format.parse(date);
		// format.applyPattern("yyyy年MM月dd日 HH时mm分ss秒");
		format.applyPattern("yyyy年MM月dd日 HH时mm分");
		return format.format(tempdate);
	}
	
	/* 日期格式化(具体到年月日) 获取到的格式：2014-04-24T02:42:37+0000；返回格式：yyyy-MM-dd*/
	public static String FacebookDateFormat(String date) throws ParseException {
	
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", new Locale(
		"ENGLISH", "CHINA"));
		Date tempdate = format.parse(date);
		format.applyPattern("yyyy-MM-dd");
		return format.format(tempdate);
	}
	
	/* 按时间冒泡排序 */
	public static List<FacebookDTO> FacebookOrderByUpdateTime(List<FacebookDTO> list){
		for (int i = 0; i < list.size() - 1; i++) {  
            for (int j = 1; j < list.size() - i; j++) {
                String a = list.get(j - 1).getUpdate_time();
                String b = list.get(j).getUpdate_time();
  
                if (a.compareTo(b) < 0) { // 比较两个时间的大小  
  
                    FacebookDTO temp = list.get(j - 1);  
                    list.set((j - 1), list.get(j));  
                    list.set(j, temp);  
                }
            }
        }
		return list;
	}
	
	/* 程序请求，获取到跳转后的地址*/
	//连接对象  
	private static HttpURLConnection conn;   
	/** *//** 
	* 根据url连接某地址，并返回返回码. 
	* 返回码说明： 
	* 0~200为正常情况，其中200为OK 
	* 其余都为错误的情况，具体请参见w3 
	* @param urlStr 需连接的url字符串 
	*/  
	public static String connect(String urlStr) throws Exception {  
		URL url = new URL(urlStr);   
		conn = (HttpURLConnection) url.openConnection();
		conn.getResponseCode();
//		System.out.println("返回码: " + conn.getResponseCode());   
		//如果定向的地址经过重定向，  
		//那么conn.getURL().toString()显示的是重定向后的地址  
//		System.out.println(conn.getURL().toString());   
		return conn.getURL().toString();   
	}  
	/** *//** 
	* 中断连接. 
	*/  
	public static void disconnect() {  
	conn.disconnect();   
	}
	
}
