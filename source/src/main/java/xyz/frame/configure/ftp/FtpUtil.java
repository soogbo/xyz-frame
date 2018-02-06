package xyz.frame.configure.ftp;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public interface FtpUtil {

    boolean ftpLogin();

    void ftpLogOut();

    String getRoot();

    String getPath();

    boolean rmdir(String remotePath);

    boolean deleteDir(String ftpPath);

    boolean uploadFile(InputStream inputStream, String remoteUpLoadPath, String fileName);

    ByteArrayOutputStream downloadFile(String remoteDownLoadPath, String remoteFileName);

    InputStream downloadFileInputStream2(String remoteDownLoadPath, String remoteFileName);

    void removeFile(String directory, String deleteFile) throws Exception;
    
    boolean isFileExist(String remoteDownLoadPath, String remoteFileName);
}
