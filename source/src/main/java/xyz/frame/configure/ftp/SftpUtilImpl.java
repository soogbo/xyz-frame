package xyz.frame.configure.ftp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SftpUtilImpl implements FtpUtil {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private static String OS = System.getProperty("os.name").toLowerCase();
    /**
     * Sftp
     */
    private ChannelSftp sftp;
    private String host;
    private int port;
    private String username;
    private String password;
    private String root;

    /**
     * 构造函数
     *
     * @param host     主机
     * @param port     端口
     * @param username 用户名
     * @param password 密码
     */
    public SftpUtilImpl(String host, int port, String username, String password, String root) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.root = root;
    }

    /**
     * 连接sftp服务器
     *
     * @throws Exception
     */
    @Override
    public boolean ftpLogin() {
        JSch jsch = new JSch();
        Session sshSession = null;
        try {
            sshSession = jsch.getSession(this.username, this.host, this.port);
            logger.info(SftpUtilImpl.class + "Session created.");

            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect(20000);

            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            this.sftp = (ChannelSftp) channel;
            logger.info(SftpUtilImpl.class + " Session connected.");
            return true;
        } catch (Exception e) {
            logger.error("create sftp failed", e);
            if (sshSession != null) {
                if (sshSession.isConnected()) {
                    sshSession.disconnect();
                }
            }
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * Disconnect with server
     *
     * @throws Exception
     */
    @Override
    public void ftpLogOut() {
        if (this.sftp != null) {
            if (this.sftp.isConnected()) {
                logger.info("session close");
                try {
                    this.sftp.quit();
                    this.sftp.disconnect();
                    Session s = this.sftp.getSession();
                    s.disconnect();
                    logger.info("session close ok!");
                } catch (Exception e) {
                    logger.error("session close failed", e);
                }
            } else if (this.sftp.isClosed()) {
                logger.info(SftpUtilImpl.class + " sftp is closed already");
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

    /**
     * 上传单个文件
     *
     * @param inputStream      要上传的流
     * @param remoteUpLoadPath 上传的目录
     * @param fileName         上传的文件名
     * @throws Exception
     */
    @Override
    public boolean uploadFile(InputStream inputStream, String remoteUpLoadPath, String fileName) {
        try {
            sftp.cd(root);
            createDirs(remoteUpLoadPath);
            sftp.put(inputStream, new String(fileName.getBytes(), "UTF-8"));
            inputStream.close();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    @Override
    public boolean rmdir(String remotePath) {

        boolean success = false;
        try {
            // TODO 先观察文件位置是否正确，暂不进行删除
            logger.info("rmdir, root : " + root + ", path : " + remotePath);
//            sftp.cd(root);
//            sftp.rmdir(remotePath);
            success = true;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return success;
    }

    @SuppressWarnings("static-access")
    public void createDirs(String remoteUpLoadPath) throws IOException, SftpException {
        String[] dirs = remoteUpLoadPath.split("/");
        for (String dir : dirs) {
            if (StringUtils.isNotEmpty(dir.trim())) {
                try {
                    sftp.cd(dir);
                } catch (SftpException sException) {
                    if (sftp.SSH_FX_NO_SUCH_FILE == sException.id) {
                        sftp.mkdir(dir);
                        sftp.cd(dir);
                    }
                }
            }
        }
    }

    /**
     * 下载文件
     *
     * @param remoteDownLoadPath 下载目录
     * @param remoteFileName     下载的文件
     */
    @Override
    public ByteArrayOutputStream downloadFile(String remoteDownLoadPath, String remoteFileName) {
        ByteArrayOutputStream outStream = null;
        try {
            outStream = new ByteArrayOutputStream();
            if (remoteDownLoadPath == null || StringUtils.isEmpty(remoteDownLoadPath.trim())
                    || remoteFileName == null || StringUtils.isEmpty(remoteFileName.trim())) {
                return null;
            }

            if ("/".equals(root)) {
                this.sftp.cd(remoteDownLoadPath);
            } else {
                this.sftp.cd(root + remoteDownLoadPath.trim());
            }

            sftp.get(remoteFileName, outStream);
            return outStream;
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
    }

    @Override
    public InputStream downloadFileInputStream2(String remoteDownLoadPath, String remoteFileName)  {
        InputStream inputStream = null;
        try {
            if (remoteDownLoadPath == null || StringUtils.isEmpty(remoteDownLoadPath.trim())
                    || remoteFileName == null || StringUtils.isEmpty(remoteFileName.trim())) {
                return null;
            }
            if ("/".equals(root)) {
                inputStream = this.sftp.get(remoteDownLoadPath + remoteFileName);
            } else {
                inputStream = this.sftp.get(root + remoteDownLoadPath + remoteFileName);
            }
            return inputStream;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 【功能描述：删除文件夹】
     *
     * @param remotePath 文件夹的地址
     * @return true 成功，false 失败
     * @throws Exception
     */
    public boolean deleteDir(String remotePath) {
        try {
            logger.info("delete remote path: {}", remotePath);
            sftp.cd(root);
            return iterateDelete(remotePath);
        } catch (Exception e) {
            logger.error("delete remote path failed: {}", remotePath);
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public boolean iterateDelete(String remotePath) throws Exception {
        Vector<ChannelSftp.LsEntry> entryVector = sftp.ls(remotePath);
        Iterator<ChannelSftp.LsEntry> it = entryVector.iterator();
        boolean flag;
        while (it.hasNext()) {
            ChannelSftp.LsEntry lsEntry = it.next();
            String fileName = lsEntry.getFilename();
            if (".".equals(fileName) || "..".equals(fileName)) {
                continue;
            }
            String path = remotePath + "/" + fileName;
            logger.info("iterateDelete remotePath: {}", path);
            if (lsEntry.getLongname().startsWith("d")) {
                // 是文件夹就继续遍历文件夹
                iterateDelete(path);
            } else {
                sftp.rm(path);
                logger.info("delete remote file ok!: {}", path);
            }
        }
        // 每次删除文件夹以后就去查看该文件夹下面是否还有文件，没有就删除该空文件夹
        Vector<ChannelSftp.LsEntry> vector = sftp.ls(remotePath);
        if (vector.size() == 2) {
            sftp.rmdir(remotePath);
            logger.info("delete remote dir ok!: {}", remotePath);
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("host:").append(host).append(", ");
        sb.append("port:").append(port).append(", ");
        sb.append("username:").append(username).append(", ");
        sb.append("root:").append(root).append(", ");

        return sb.toString();
    }

    /**
     * 删除文件
     *
     * @param directory  要删除文件所在目录
     * @param deleteFile 要删除的文件
     * @throws Exception
     */
    @Override
    public void removeFile(String directory, String deleteFile) throws Exception {
        this.sftp.cd(directory);
        this.sftp.rm(deleteFile);
    }
    
    /**
     * 判断文件是否存在
     */
	@Override
	public boolean isFileExist(String remoteDownLoadPath, String remoteFileName) {
		
		try {
            if ("/".equals(root)) {
                this.sftp.cd(remoteDownLoadPath);
            } else {
                this.sftp.cd(root + remoteDownLoadPath.trim());
            }			
			InputStream in = this.sftp.get(remoteFileName);
			if(in!=null) {
				return true;
			}
		} catch (SftpException e) {
			e.printStackTrace();
		}
		return false;
	}    

}