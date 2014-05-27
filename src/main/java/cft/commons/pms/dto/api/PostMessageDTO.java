package cft.commons.pms.dto.api;

import lombok.Data;

@Data
public class PostMessageDTO {
    private String content;
    
    private String tencent_token;
    private String openid;
    private String openkey;
    
    
}
