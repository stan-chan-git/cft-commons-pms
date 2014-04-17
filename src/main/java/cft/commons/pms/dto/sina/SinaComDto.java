package cft.commons.pms.dto.sina;

public class SinaComDto {

	private Long id;// 评论的ID
	
	private String text;//评论的内容
	private String screen_name;// 评论作者的用户昵称
	private String created_at;// 评论创建时间
	private String textStatus;//微博信息内容
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getScreen_name() {
		return screen_name;
	}
	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getTextStatus() {
		return textStatus;
	}
	public void setTextStatus(String textStatus) {
		this.textStatus = textStatus;
	}
	
}
