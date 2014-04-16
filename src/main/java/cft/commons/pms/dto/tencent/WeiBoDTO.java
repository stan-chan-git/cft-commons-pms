package cft.commons.pms.dto.tencent;

public class WeiBoDTO {
	private String id;// 微博唯一id
	private String text;// 微博内容
	private String orgintext;// 微博原始内容
	private String nick;// 发表者昵称
	private String head;// 发表者头像url
	private int type;//微博类型,1-原创发表，2-转载，3-私信，4-回复，5-空回，6-提及，7-评论
	private int timestamp;// 发表时间

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public int getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}
}
