package cft.commons.pms.model.api;

import lombok.Data;

@Data
public class PostMessage {
    private String id;//记录id
    private String content;//微博内容
    private String publisher;//发表人
    private String servertime;//服务器时间，即自身服务器发出请求的时间
    private String dateline;//平台返回时间
    private String imgUrl;//图片url
    private String videoUrl;//视频url
    private String geo;//地理位置信息
    private String forwardingNum;//转发数
    private String commentsNum;//评论数
    private String praiseNum;//点赞数
}
