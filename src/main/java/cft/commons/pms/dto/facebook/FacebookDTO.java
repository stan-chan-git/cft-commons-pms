package cft.commons.pms.dto.facebook;

import lombok.Data;

/**
 * 
 * @author Tifffany
 *
 */

@Data
public class FacebookDTO {
	
	private String postID;//微博ID
	private String userId;//用户ID
	private String message;//微博信息内容
	private String source;// 微博来源
	private String userName;// 用户名
	private String imageUrl;//图片url
	private String type;
	private String update_time;// 微博创建时间
	
}
