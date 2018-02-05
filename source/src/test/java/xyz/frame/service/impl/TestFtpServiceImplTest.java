package xyz.frame.service.impl;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xyz.frame.TestSpringApplication;
import xyz.frame.service.TestFtpService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestSpringApplication.class)
public class TestFtpServiceImplTest {

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
