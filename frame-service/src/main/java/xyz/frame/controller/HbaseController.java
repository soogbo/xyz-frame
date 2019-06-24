package xyz.frame.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.frame.hbase.QueryTest;
import xyz.frame.utils.GeneralResponse;

/**
 * @author shisp
 * @date 2018-7-27 15:57:57
 */
@RestController
@RequestMapping("/hbase")
public class HbaseController {
    private static final Logger logger = LoggerFactory.getLogger(HbaseController.class);

    @GetMapping(value = "/listTables")
    public GeneralResponse<String> listTables() {
        logger.info("hbase listTables");
        QueryTest.listTables();
        return GeneralResponse.success();
    }

}
