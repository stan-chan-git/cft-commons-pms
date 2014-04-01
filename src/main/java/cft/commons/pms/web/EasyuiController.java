package cft.commons.pms.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cft.commons.pms.model.EasyuiApplication;
import cft.commons.pms.plugins.Page;
import cft.commons.pms.plugins.PaginationObject;
import cft.commons.pms.service.EasyuiApplicationService;

@Controller
public class EasyuiController extends BaseController{
	static Logger log = LoggerFactory.getLogger(EasyuiController.class);

	@Autowired
	private EasyuiApplicationService easyuiApplicationService;

	@RequestMapping(value = "/layout")
	public String layout() {
		log.info("layoutlayoutlayout");
		return "/easyui/layout";
	}

	@RequestMapping(value = "/testTag-A")
	public String testTag_A() {
		log.info("testTag-A");
		return "/easyui/testTag-A";
	}

	@RequestMapping(value = "/application")
	public String application() {
		log.info("application");
		return "/easyui/application";
	}

	@RequestMapping(value = "/findAllApplication")
	public @ResponseBody
	PaginationObject findAllApplication(@RequestParam("rows")String pageSize,@RequestParam("page")String pageNo) {
		log.info("pageNo:"+pageNo+"==================pageSize:"+pageSize);
		PaginationObject pno = new PaginationObject();
		Page<EasyuiApplication> pages = new Page<EasyuiApplication>();
		pages.setPageNo(Integer.parseInt(pageNo));
		pages.setPageSize(Integer.parseInt(pageSize));
		
		List<EasyuiApplication> pageData = easyuiApplicationService
				.getAllEasyuiApplication(pages);
		pno.setRows(pageData);
		pno.setTotal(String.valueOf(String.valueOf(pages.getTotalRecord())));
		return pno;
	}
}
