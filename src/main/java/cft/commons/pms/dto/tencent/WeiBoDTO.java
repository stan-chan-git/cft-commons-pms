package cft.commons.pms.dto.tencent;

public class WeiBoDTO {
	private Long id;// 微博唯一id
	private String text;// 微博内容
	private String orgintext;// 微博原始内容
	private String nick;// 发表者昵称
	private String head;// 发表者头像url
	private Long timetamp;// 发表时间

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

	public String getOrgintext() {
		return orgintext;
	}

	public void setOrgintext(String orgintext) {
		this.orgintext = orgintext;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public Long getTimetamp() {
		return timetamp;
	}

	public void setTimetamp(Long timetamp) {
		this.timetamp = timetamp;
	}
}
