package xyz.frame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.frame.pojo.po.ParamParam;
import xyz.frame.service.ParamParamService;
import xyz.frame.utils.ResponseResult;
import xyz.frame.utils.RestResultUtil;

/**
 * @Description param设置
 * @author shisp
 * @date 2018年2月5日  下午7:22:48
 */
@RestController
public class ParamController {

    @Autowired
    private ParamParamService paramParamService;

    /*@RequestMapping("/sysParam")
    public String sysParam(Model model) {
        return "sysParam";
    }*/

    @GetMapping("/param/findAll")
    public ResponseResult<?> findAll() {
        List<ParamParam> list = paramParamService.findAll();
        return RestResultUtil.success(list);
    }

}
