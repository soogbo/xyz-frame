package xyz.frame.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * view进入通用Controller
 * 
 * Created by shisp on 2017年12月22日.
 */
@Controller
public class AccessController {
    private static final Logger logger = LoggerFactory.getLogger(AccessController.class);
    private static final String INDEX = "index";
    private static final String SPRIT = "/";
    private static final String REDIRECT = "redirect:";

    @RequestMapping(value = "/{view1}", method = { RequestMethod.POST, RequestMethod.GET })
    public String view1Jsp(@PathVariable("view1") String view1, Model model) {
        if (!INDEX.equals(view1)) view1 = REDIRECT + SPRIT + view1 + SPRIT + INDEX;
        logger.info("come in view1:{}", view1);
        return view1;
    }

    @RequestMapping(value = "/{view1}/{view2}", method = { RequestMethod.POST, RequestMethod.GET })
    public String view12Jsp(@PathVariable("view1") String view1, @PathVariable("view2") String view2, Model model) {
        if (StringUtils.isBlank(view2))
            view2 = INDEX;
        logger.info("come in view1/view2:{}/{}", view1, view2);
        if (StringUtils.isBlank(view1))
            return view2;
        return view1 + SPRIT + view2;
    }

}
