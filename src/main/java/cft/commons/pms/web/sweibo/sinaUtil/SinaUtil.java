package cft.commons.pms.web.sweibo.sinaUtil;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.ByteArrayPartSource;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;

public class SinaUtil {

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

}
