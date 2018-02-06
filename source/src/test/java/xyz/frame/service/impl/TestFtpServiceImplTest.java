package xyz.frame.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import xyz.frame.AbstractTest;
import xyz.frame.json.FrameJsonUtils;
import xyz.frame.service.TestFtpService;

public class TestFtpServiceImplTest extends AbstractTest {

    @Autowired
    private TestFtpService testFtpService;

    @Test
    public void testTestFtpConn() {
        Boolean testFtpConn = testFtpService.testFtpConn(1L);
        System.out.println(testFtpConn);
    }

    @Test
    public void testTestFtpUpload() {
        InputStream in = null;
        try {
            in = new FileInputStream("C:\\Windows\\system.ini");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Boolean isUpload = testFtpService.testFtpUpload(1L, "ws/20180206/", "system.ini", in);
        System.out.println(isUpload);
    }

    @Test
    public void testTestFtpDownload() {
        File file = testFtpService.testFtpDownload(1L, "ws/20180206/payoff/", "payoff_1.txt");
        System.out.println(FrameJsonUtils.toJson(file.length()));
    }

}
