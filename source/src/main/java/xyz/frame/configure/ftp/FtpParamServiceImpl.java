package xyz.frame.configure.ftp;

import java.io.ByteArrayOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.frame.mapper.FtpParamMapper;
import xyz.frame.pojo.po.FtpParam;


/**
 * Created by zuoyx on 2016/8/31.
 */
@Service
public class FtpParamServiceImpl implements FtpParamService {
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(FtpParamServiceImpl.class);

    @Autowired
    private FtpParamMapper ftpParamMapper;

    @Override
    public FtpUtil getFtpById(Long ftpId) {
        FtpParam ftpParam = ftpParamMapper.selectByPrimaryKey(ftpId);
        return FtpManager.getFtp(ftpParam.getType(), ftpParam.getHost(), ftpParam.getPort(),
                ftpParam.getUsername(), ftpParam.getPassword(), ftpParam.getRoot());
    }

    
    @SuppressWarnings("finally")
	@Override
    public ByteArrayOutputStream downloadFileFromFtp(FtpUtil ftpClient, String remotePath, String remoteFileName, String localFileName) {
        ByteArrayOutputStream out = null;
        try {
            out = ftpClient.downloadFile(remotePath, remoteFileName);
        } catch (Exception e) {
            return null;
        } finally {

            return out;
        }
    }

    @SuppressWarnings("finally")
	@Override
    public Boolean removeFile(FtpUtil ftpClient, String remotePath, String remoteFileName) {
        Boolean success = false;
        try {
            ftpClient.removeFile(remotePath, remoteFileName);
            success = true;
        } catch (Exception e) {
            return false;
        } finally {
            return success;
        }
    }

}
