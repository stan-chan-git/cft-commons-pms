package cft.commons.pms.web;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AndroidController {

	@RequestMapping(value = "test.do")
	public @ResponseBody String helloAndroid() {
		JSONObject obj = new JSONObject();
		obj.put("name","stan");
		obj.put("add","stan");
		obj.put("email","stan");
		
		obj.remove("sex");
		System.out.println(obj.toString());
		return "hello Android,spring mvc";
	}
	@RequestMapping(value = "authorize")
	public String authorize() {
		System.out.println(111111);
		return null;
	}
}
