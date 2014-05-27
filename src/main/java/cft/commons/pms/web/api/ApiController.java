package cft.commons.pms.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cft.commons.pms.service.api.ApiService;



@Controller
public class ApiController {
	@Autowired
    private ApiService apiService;
    
}
