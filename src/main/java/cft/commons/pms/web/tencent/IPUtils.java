package cft.commons.pms.web.tencent;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPUtils {
   
   private static String clientip = "";
   
   /* 获取客户端ip的静态方法  */
   public static String getClientIP() throws UnknownHostException{
	   if(clientip == null || clientip.equals("")){
			InetAddress address = InetAddress.getLocalHost();
			clientip = address.getHostAddress(); 
	   }
		
	   return clientip;
    }
}
