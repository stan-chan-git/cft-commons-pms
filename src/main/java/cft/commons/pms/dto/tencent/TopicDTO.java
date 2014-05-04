package cft.commons.pms.dto.tencent;

import lombok.Data;

@Data
public class TopicDTO {
	private String id;//话题id
	private String keywords;//搜索关键字
	private String name;//话题关键字
	private int favnum;//被收藏次数
	private int tweetnum;//话题下微博数
}
