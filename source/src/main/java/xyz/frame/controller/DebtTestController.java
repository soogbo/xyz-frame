package xyz.frame.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.frame.json.FrameJsonUtils;
import xyz.frame.utils.SysLog;

/**
 * 测试jsp访问
 * 
 * Created by shisp on 2017年12月17日.
 */
@Controller
@RequestMapping("/gateway")
public class DebtTestController {

	@RequestMapping(value="/loan/deduct",method=RequestMethod.POST)
	@ResponseBody
	@SysLog(table = "test_table_record")
	public String helloJsp(HttpServletRequest request,HttpServletResponse response,
	        @RequestParam String reqUuid,
	        @RequestParam String deductionNo,
	        @RequestParam BigDecimal deductionPrincipal,
	        @RequestParam BigDecimal deductionOverdueFee,
	        @RequestParam BigDecimal deductionInterest,
	        @RequestParam Long customerId,
	        @RequestParam Long billId,
	        @RequestParam Long productId
	        ) {
		System.out.println("HelloJspController.helloJsp().hello");
		
		Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("code", "success");
		hashMap.put("result", true);
		return FrameJsonUtils.toJson(hashMap);
	}

}
