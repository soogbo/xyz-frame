package xyz.frame.configure;

import java.io.IOException;
import java.io.InputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * @date 2018-7-24 15:34:26
 */
public class IpLogConfig extends ClassicConverter {
    private static final Logger logger = LoggerFactory.getLogger(IpLogConfig.class);
    private static String fristLocalHostIp = null;

    @Override
    public String convert(ILoggingEvent event) {
        String ip = null;
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("application.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            String localIp = properties.getProperty("log.local.ip");
            ip = getFristLocalHostIp(localIp);
        } catch (SocketException e) {
            logger.error("IpLogConfig error, errorMsg:{}", e.getMessage(), e);
        } catch (IOException e1) {
            logger.error("IpLogConfig error, errorMsg:{}", e1.getMessage(), e1);
        }
        return ip;
    }

    private static String getFristLocalHostIp(String ipStr) throws SocketException {
        if (fristLocalHostIp != null) {
            return fristLocalHostIp;
        }
        Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip = null;
        while (allNetInterfaces.hasMoreElements()) {
            NetworkInterface netInterface = allNetInterfaces.nextElement();
            Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                ip = addresses.nextElement();
                if (ip != null && ip instanceof Inet4Address && !ipStr.equals(ip.getHostAddress())) {
                    fristLocalHostIp = ip.getHostAddress();
                    ipStr = ip.getHostAddress();
                    break;
                }
            }
            if (fristLocalHostIp != null) {
                break;
            }
        }
        return ipStr;
    }
}