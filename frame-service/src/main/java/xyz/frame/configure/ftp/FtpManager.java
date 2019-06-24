package xyz.frame.configure.ftp;

public class FtpManager {

    private static final int FTP = 1;

    private static final int SFTP = 2;

    public static FtpUtil getFtp(int type, String host, int port, String username, String password, String root) {
        // 判断ftp类型
        FtpUtil ftp;
        if (type == FTP) {
            ftp = new FtpUtilImpl(host, port, username, password, root);
        } else if (type == SFTP) {
            ftp = new SftpUtilImpl(host, port, username, password, root);
        } else {
            ftp = null;
        }
        return ftp;
    }
}
