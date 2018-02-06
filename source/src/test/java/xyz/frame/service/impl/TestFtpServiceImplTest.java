package xyz.frame.service.impl;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import xyz.frame.AbstractTest;
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
        fail("Not yet implemented");
    }

    @Test
    public void testTestFtpDownload() {
        fail("Not yet implemented");
    }

}
