/**
 * 
 */
package xyz.frame.service;


/**
 * 测试ftp链接
 * @author shisp
 * @date 2018-2-05 20:30:39
 */
public interface TestFtpService {

    Boolean testFtpConn(Long ftpId);
    
    void testFtpUpload();
    
    void testFtpDownload();
}
