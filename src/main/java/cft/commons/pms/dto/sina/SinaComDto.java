package cft.commons.pms.dto.sina;

import lombok.Data;

@Data
public class SinaComDto {

	private Long id;// 评论的ID
	
	private String text;//评论的内容
	private String screen_name;// 评论作者的用户昵称
	private String created_at;// 评论创建时间
	private String textStatus;//微博信息内容
	private String thumbnail_pic;//缩略图片地址
	
}
