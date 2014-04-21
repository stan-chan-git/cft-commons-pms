package cft.commons.pms.dto.instagram;

public class CommentDto {
  
	private String text;
	private String commentName;
	private String link;
	private String profile_picture;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getCommentName() {
		return commentName;
	}
	public void setCommentName(String commentName) {
		this.commentName = commentName;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getProfile_picture() {
		return profile_picture;
	}
	public void setProfile_picture(String profile_picture) {
		this.profile_picture = profile_picture;
	}
	
	
	
}
