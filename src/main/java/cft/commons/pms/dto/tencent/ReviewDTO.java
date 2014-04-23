package cft.commons.pms.dto.tencent;

import lombok.Data;

@Data
public class ReviewDTO {
	private String id;// 评论id
	private String text;// 评论内容
	private String nick;// 评论人
	private int timestamp;// 评论时间
}
