/**
 * 
 */
package xyz.frame.service;

import java.io.File;
import java.io.InputStream;

/**
 * 测试ftp链接
 * @author shisp
 * @date 2018-2-05 20:30:39
 */
public interface TestFtpService {

    Boolean testFtpConn(Long ftpId);
    
    Boolean testFtpUpload(Long ftpId,String uploadPath,String fileName,InputStream in);
    
    File testFtpDownload(Long ftpId,String uploadPath,String fileName);
}
