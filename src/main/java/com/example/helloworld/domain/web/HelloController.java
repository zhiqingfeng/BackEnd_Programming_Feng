package com.example.helloworld.domain.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class HelloController {
	
	@RequestMapping("index")
	public String hello(@RequestParam(name="currency")String currency,
			@RequestParam(name ="country")String country) {
		return "Hello " + currency +" Your country mayeb is "+ country;
	}
	
	//@RequestMapping("index")
	//public String hello(@RequestParam(name="currency")String currency, 
	// @RequestParam(name="country")String country) {
	//	return "Hello " + currency + "your country maybe is: "+ country;
	//}
}
