/**
 * 
 */
package xyz.frame.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import xyz.frame.service.ImportFileService;
import xyz.frame.utils.ResponseResult;
import xyz.frame.utils.RestResultUtil;

/**
 * @Description 读取文件数据到DB
 * @author shisp
 * @date 2018年04月17日  下午1:14:37
 */

@RestController
@RequestMapping("/file")
@Api(value = "读取文件数据到DB")
public class ImportFileController {
    private static final Logger logger = LoggerFactory.getLogger(ImportFileController.class);
    @Autowired
    private ImportFileService importFileService;

    @ApiOperation(value = "读取文件")
    @GetMapping(value = "/readFileToDb")
    public ResponseResult<?> readFileToDb(@RequestParam(value = "fileName") String fileName) {
        logger.info("读取文件数据到DB,fileName={}",fileName);
        importFileService.ImportFileToDb(fileName);
        return RestResultUtil.success();
    }
    
}
