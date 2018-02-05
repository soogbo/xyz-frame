package xyz.frame.configure.ftp;

import java.io.ByteArrayOutputStream;

public interface FtpParamService {

    FtpUtil getFtpById(Long ftpId);

    ByteArrayOutputStream downloadFileFromFtp(FtpUtil ftpClient, String remotePath, String remoteFileName, String localFileName);

    Boolean removeFile(FtpUtil ftpClient, String remotePath, String remoteFileName);

}
