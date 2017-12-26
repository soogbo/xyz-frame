package xyz.frame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import xyz.frame.service.ParamParamService;

/**
 * 测试jsp访问
 * 
 * Created by shisp on 2017年12月17日.
 */
@Controller
public class SysParamController {

    private ParamParamService paramParamService;
    
	@RequestMapping("/sysParam")
	public String sysParam(Model model) {
//		System.out.println("HelloJspController.helloJsp().hello");
//		model.addAttribute("msg", "hello,2018");
		return "sysParam";
	}
	
	

}
