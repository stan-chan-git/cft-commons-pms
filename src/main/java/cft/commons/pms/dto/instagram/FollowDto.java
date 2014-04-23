package cft.commons.pms.dto.instagram;

import lombok.Data;

@Data
public class FollowDto {
    private String media_id;
	private String username;
	private String photo;
	private String title; //发布标题
	private String type;//类型是photo OR media
	private String url;//图片url
	private String date;
	
	
	
	
	
}
