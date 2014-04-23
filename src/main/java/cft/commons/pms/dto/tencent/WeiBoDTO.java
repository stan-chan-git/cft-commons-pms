package cft.commons.pms.dto.tencent;

import lombok.Data;

@Data
public class WeiBoDTO {
	private String id;// 微博唯一id
	private String text;// 微博内容
	private String origtext;// 微博原始内容
	private String nick;// 发表者昵称
	private String head;// 发表者头像url
	private String[] imageUrls;//图片url
	private int type;//微博类型,1-原创发表，2-转载，3-私信，4-回复，5-空回，6-提及，7-评论
	private int timestamp;// 发表时间
	private String date;//发表时间,UI使用
}
