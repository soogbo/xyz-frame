package xyz.frame.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
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
            if(null==ftpUtil) return flag;
            ftpUtil.ftpLogin();
            // 获取文件路径
            String filePath = "ws/20180206/payoff/";
            String fileName = "payoff_1.txt";
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

    @SuppressWarnings("finally")
    @Override
    public Boolean testFtpUpload(Long ftpId,String uploadPath,String fileName,InputStream in) {
        FtpUtil upFtp = null;
        Boolean status = false;
        if (null==in) return status;
        try {
            upFtp = ftpParamService.getFtpById(ftpId);
            upFtp.ftpLogin();
            if (upFtp.uploadFile(in, uploadPath, fileName)) {
                status = true;
                logger.info("文件上传FTP success!!!");
            }
        } catch (Exception e) {
            logger.error("文件上传FTP失败:{}", e.getMessage(), e);
        } finally {
            if (upFtp != null) {
                try{ 
                    upFtp.ftpLogOut();
                }catch(Exception e) {
                    logger.error("ftp关闭失败");
                }
            } 
            return status;
        }
    }

    @Override
    public File testFtpDownload(Long ftpId,String uploadPath,String fileName) {
        FtpUtil ftpUtil = null;
        try{
            ftpUtil = ftpParamService.getFtpById(ftpId);
            ftpUtil.ftpLogin();
            // 获取文件路径           
            InputStream ftpInputStream = ftpUtil.downloadFileInputStream2(uploadPath, fileName);                
            if (ftpInputStream != null) {
                //创建临时文件
                File txtFile = File.createTempFile("download_", ".txt");
                OutputStream output = new FileOutputStream(txtFile);
                //下载到临时文件
                IOUtils.copy(ftpInputStream, output);
                output.close();
                ftpInputStream.close();
                return txtFile;
            } 
        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }finally {
            if (ftpUtil != null) {
                try{ 
                    ftpUtil.ftpLogOut();
                }catch(Exception e) {
                    logger.error("ftp关闭失败");
                }
            }            
        }
        return null;
    }

}
