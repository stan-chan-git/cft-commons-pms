package cft.commons.pms.model.api;

import lombok.Data;

@Data
public class MessageId {
    private String id;
    private String tencentId;
    private String sinaId;
    private String facebookId;
    private String weichatId;
}
