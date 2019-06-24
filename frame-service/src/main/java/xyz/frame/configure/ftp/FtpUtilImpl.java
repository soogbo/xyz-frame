package xyz.frame.configure.ftp;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.parser.UnixFTPEntryParser;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * Created by Felix_qiu on 6/7/14.
 */
public class FtpUtilImpl implements FtpUtil {

    private static String OS = System.getProperty("os.name").toLowerCase();

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    private FTPClient ftpClient;
    private String hostname;
    private int port;
    private String username;
    private String password;
    private String root;

    public FtpUtilImpl(String hostname, int port, String username, String password, String root) {
        this.hostname = hostname;
        this.port = port;
        this.username = username;
        this.password = password;
        this.root = root;
        ftpClient = getClient();
    }

    @Override
    public boolean ftpLogin() {
        boolean isLogin = false;
        this.ftpClient.setControlEncoding("GBK");
        try {
            if (this.port > 0) {
                this.ftpClient.connect(this.hostname, this.port);
            } else {
                this.ftpClient.connect(this.hostname);
            }
            // FTP服务器连接回答
            int reply = this.ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                this.ftpClient.disconnect();
                return isLogin;
            }
            this.ftpClient.login(this.username, this.password);
            // 设置传输协议
            this.ftpClient.enterLocalPassiveMode();
            this.ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            isLogin = true;
        } catch (Exception e) {
            isLogin = false;
            return isLogin;
            //throw new ServiceException(e.getMessage(), e);
        }
        this.ftpClient.setBufferSize(1024 * 2);
        //this.ftpClient.setDataTimeout(30 * 1000);
        return isLogin;
    }

    /**
     * @退出关闭服务器链接
     */
    @Override
    public void ftpLogOut() {
        if (null != this.ftpClient && this.ftpClient.isConnected()) {
            try {
                boolean reuslt = this.ftpClient.logout();// 退出FTP服务器
                if (reuslt) {
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    this.ftpClient.disconnect();// 关闭FTP服务器的连接
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
        }
    }

    @Override
    public String getRoot() {
        return this.root;
    }

    @Override
    public String getPath() {
        return "/";
    }

    @Override
    public boolean uploadFile(InputStream inputStream, String remoteUpLoadPath, String fileName) {
        BufferedInputStream inStream = null;
        boolean success = false;
        try {
            gotoRoot();
//            ftpClient.changeWorkingDirectory(root);
            //ftpClient.makeDirectory(remoteUpLoadPath);
            createDirs(remoteUpLoadPath);
            //this.ftpClient.changeWorkingDirectory(remoteUpLoadPath);// 改变工作路径
            inStream = new BufferedInputStream(inputStream);
          /*  success = this.ftpClient.storeFile(fileName, inStream);*/
            success = this.ftpClient.storeFile(new String(fileName.getBytes(), "UTF-8"), inStream);
            if (success == true) {
                return success;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(inStream);
            IOUtils.closeQuietly(inputStream);
        }
        return success;
    }

    @Override
    public boolean rmdir(String remotePath) {

        boolean success = false;
        try {
            // TODO 先观察文件位置是否正确，暂不进行删除
            logger.info("rmdir, root : " + root + ", path : " + remotePath);
//            ftpClient.changeWorkingDirectory(root);
//            success = ftpClient.removeDirectory(remotePath);
            success = true;
        } catch (Exception e) {
//        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return success;
    }

    public InputStream downloadFileInputStream(String remoteDownLoadPath, String remoteFileName) {
        InputStream inputStream = null;
        try {
            if (remoteDownLoadPath == null || !StringUtils.hasText(remoteDownLoadPath.trim())
                    || remoteFileName == null || !StringUtils.hasText(remoteFileName.trim())) {
                return null;
            }
            if ("/".equals(root)) {
                inputStream = this.ftpClient.retrieveFileStream(remoteDownLoadPath + remoteFileName);
            } else {
                inputStream = this.ftpClient.retrieveFileStream(root + remoteDownLoadPath + remoteFileName);
            }
            return inputStream;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * 上面的同名类似方法在测试中会抛出 socket close 错误
     *
     * @param remoteDownLoadPath
     * @param remoteFileName
     * @return
     * @throws com.admin.collection.exception.CollectionException
     */
    public InputStream downloadFileInputStream2(String remoteDownLoadPath, String remoteFileName) {
        InputStream inputStream = null;
        try {
            if (remoteDownLoadPath == null || !StringUtils.hasText(remoteDownLoadPath.trim())
                    || remoteFileName == null || !StringUtils.hasText(remoteFileName.trim())) {
                return null;
            }
            if ("/".equals(root)) {
                inputStream = this.ftpClient.retrieveFileStream(remoteDownLoadPath + remoteFileName);
            } else {
                inputStream = this.ftpClient.retrieveFileStream(root + remoteDownLoadPath + remoteFileName);
            }
            return inputStream;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public ByteArrayOutputStream downloadFile(String remoteDownLoadPath, String remoteFileName) {
        ByteArrayOutputStream outStream = null;
        boolean success = false;
        try {
            if (remoteDownLoadPath == null || !StringUtils.hasText(remoteDownLoadPath.trim())
                    || remoteFileName == null || !StringUtils.hasText(remoteFileName.trim())) {
                return null;
            }
            outStream = new ByteArrayOutputStream();
            if ("/".equals(root)) {
                success = this.ftpClient.retrieveFile(remoteDownLoadPath + remoteFileName, outStream);
            } else {
                success = this.ftpClient.retrieveFile(root + remoteDownLoadPath + remoteFileName, outStream);
            }
            logger.info("ftp downloadFile remoteDownLoadPath: {}", root + remoteDownLoadPath + remoteFileName);
            if (success) {
                return outStream;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            if (null != outStream) {
                try {
                    outStream.flush();
                    outStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
        }
        return null;
    }

    /**
     * 【功能描述：删除文件夹】
     *
     * @param remotePath 文件夹的地址
     * @return true 表似成功，false 失败
     * @throws Exception
     */
    public boolean deleteDir(String remotePath) {
        try {
//            ftpClient.changeWorkingDirectory(root);
            return iterateDelete(root + remotePath);
        } catch (Exception e) {
            logger.error("delete remote path failed: {}", remotePath);
            return false;
        }
    }

    public boolean iterateDelete(String remotePath) throws Exception {
        FTPFile[] files = this.ftpClient.listFiles(remotePath);
        boolean flag;
        for (FTPFile f : files) {
            String fileName = f.getName();
            if (".".equals(fileName) || "..".equals(fileName)) {
                continue;
            }
            String path = remotePath + "/" + fileName;
            logger.info("iterateDelete remotePath: {}", path);
            if (f.isFile()) {
                // 是文件就删除文件
                ftpClient.deleteFile(path);
                logger.info("delete remote file ok!: {}", path);
            } else if (f.isDirectory()) {
                iterateDelete(path);
            }
        }
        // 每次删除文件夹以后就去查看该文件夹下面是否还有文件，没有就删除该空文件夹
        FTPFile[] files2 = ftpClient.listFiles(remotePath);
        if (files2.length == 2) {
            flag = ftpClient.removeDirectory(remotePath);
            logger.info("delete remote dir ok!: {}", remotePath);
        } else {
            flag = false;
        }
        return flag;
    }

    /**
     * 将FTP的当前目录指定到ROOT下
     */
    private void gotoRoot() {
        try {
            // 将当前目录指定到根目录
            ftpClient.changeWorkingDirectory("");
            // 将当前目录指定到ROOT
            createDirs(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private FTPClient getClient() {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setDefaultPort(port);
//        ftpClient.configure(getClientConfig());
        ftpClient.configure(new FTPClientConfig(UnixFTPEntryParser.class.getPackage().getName() + UnixFTPEntryParser.class.getSimpleName()));
        ftpClient.setControlEncoding("GBK");
        return ftpClient;
    }

    @SuppressWarnings("unused")
    private static FTPClientConfig getClientConfig() {
        String sysType = null;
        if (isLinux()) {
            sysType = FTPClientConfig.SYST_UNIX;
        } else if (isWindows()) {
            sysType = FTPClientConfig.SYST_NT;
        }
        FTPClientConfig config = new FTPClientConfig(sysType);
        config.setRecentDateFormatStr("yyyy-MM-dd HH:mm");
        return config;
    }

    public void createDirs(String remoteUpLoadPath) throws IOException {
        String[] dirs = remoteUpLoadPath.split("/");
        for (String dir : dirs) {
            if ("".equals(dir))
                continue;
            this.ftpClient.mkd(dir);
            this.ftpClient.changeWorkingDirectory(dir);

        }
    }

    private static boolean isLinux() {
        return OS.indexOf("linux") >= 0;
    }

    private static boolean isWindows() {
        return OS.indexOf("windows") >= 0;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("host:").append(hostname).append(", ");
        sb.append("port:").append(port).append(", ");
        sb.append("username:").append(username).append(", ");
        sb.append("root:").append(root).append(", ");

        return sb.toString();
    }

    /**
     * 删除ftp上的文件
     *
     * @param directory  要删除文件所在目录
     * @param deleteFile 要删除的文件
     */
    @Override
    public void removeFile(String directory, String deleteFile) throws Exception {
        if (ftpClient != null) {
            try {
                this.ftpClient.changeWorkingDirectory(directory);
                this.ftpClient.deleteFile(deleteFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 判断文件是否存在
     */
	@Override
	public boolean isFileExist(String remoteDownLoadPath, String remoteFileName) {
		if(ftpClient != null) {
			try {
				String realRemoteDownLoadPath = remoteDownLoadPath;
	            if ("/".equals(root)) {
	            	realRemoteDownLoadPath = remoteDownLoadPath;
	            } else {
	            	realRemoteDownLoadPath = root + remoteDownLoadPath;	                
	            }				
	             
				this.ftpClient.changeWorkingDirectory(realRemoteDownLoadPath);
				InputStream in = this.ftpClient.retrieveFileStream(remoteFileName);
				if(in!=null) {
					in.close();
					return true;
				}
			} catch (IOException e) {
//				e.printStackTrace();
				return false;
			}
		}
		return false;
	}    
}
