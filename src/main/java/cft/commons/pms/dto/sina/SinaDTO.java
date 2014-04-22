package cft.commons.pms.dto.sina;

import lombok.Data;

@Data
public class SinaDTO {

	private Long id;//微博ID
	private String userId;//微博作者的用户ID
	private String text;//微博信息内容
	private String source;// 微博来源
	private String screen_name;// 微博作者的用户昵称
	private String created_at;// 微博创建时间
	
	private String content;
	private String name;
	private String time;
	
}
