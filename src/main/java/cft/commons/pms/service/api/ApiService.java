package cft.commons.pms.service.api;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cft.commons.pms.dao.api.ApiDAO;
import cft.commons.pms.model.api.PostMessage;

@Service
public class ApiService {
	@Autowired
    private ApiDAO apiDAO;
	
	public void savePostMessage(String content,Date servertime,Date dateline){
		PostMessage pm = new PostMessage();
		
		pm.setContent(content);
		pm.setServertime(servertime);
		pm.setDateline(dateline);
		
		apiDAO.savePostMessage(pm);
	}
}
