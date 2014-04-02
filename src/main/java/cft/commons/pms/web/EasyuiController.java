package cft.commons.pms.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cft.commons.core.model.display.JqPageObject;
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
	JqPageObject findAllApplication(HttpServletRequest request) {
		JqPageObject pno = new JqPageObject();
		Page<EasyuiApplication> pages = new Page<EasyuiApplication>();
		pages.setPageNo(1);
		pages.setPageSize(10);
		List<EasyuiApplication> pageData = easyuiApplicationService
				.getAllEasyuiApplication(pages);
		pno.setRows(pageData);
		pno.setPage(1);
		pno.setRecords(pageData.size());
		pno.setTotal(pages.getTotalPage());
		return pno;
	}
}
