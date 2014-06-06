package cft.commons.pms.dto.facebook;

import lombok.Data;

/**
 * 
 * @author Tify
 *
 */

@Data
public class CommentDTO {
	private String commentId;
	private Boolean userLikes;
	private String message;
	private int likeCount;
	private Boolean canRemove;
	private String fromId;
	private String fromName;
	private String createTime;

}
