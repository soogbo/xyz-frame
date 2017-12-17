package xyz.frame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试jsp访问
 * 
 * Created by shisp on 2017年12月17日.
 */
@Controller
@RequestMapping("/test")
//public class HelloJspController {
public class HelloJspView {

	@RequestMapping("/helloJsp")
	public String helloJsp(Model model) {
		System.out.println("HelloJspController.helloJsp().hello");
		return "hello";
	}

}
