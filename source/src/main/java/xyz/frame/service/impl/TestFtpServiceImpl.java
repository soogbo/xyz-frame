package xyz.frame.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.frame.configure.ftp.FtpParamService;
import xyz.frame.configure.ftp.FtpUtil;
import xyz.frame.service.TestFtpService;

@Service("testFtpService")
public class TestFtpServiceImpl implements TestFtpService {
    private static final Logger logger = LoggerFactory.getLogger(TestFtpServiceImpl.class);

    @Autowired
    private FtpParamService ftpParamService;

    @Override
    public Boolean testFtpConn(Long ftpId) {
        Boolean flag = false;
        FtpUtil ftpUtil = null;
        try {
            ftpUtil = ftpParamService.getFtpById(ftpId);
            ftpUtil.ftpLogin();
            // 获取文件路径
            String filePath = "ws/20180205/payoff/";
            String fileName = "payoff.txt";
            // 判断文件是否存在
            flag = ftpUtil.isFileExist(filePath, fileName);
        } catch (Exception ex) {
            logger.error("文件检测失败:{}", ex.getMessage(), ex);
            flag = false;
        } finally {
            if (ftpUtil != null) {
                try {
                    ftpUtil.ftpLogOut();
                } catch (Exception e) {
                    logger.error("ftp关闭失败");
                    ftpUtil = null;
                }
            }
        }
        return flag;
    }

    @Override
    public void testFtpUpload() {

    }

    @Override
    public void testFtpDownload() {

    }

}
