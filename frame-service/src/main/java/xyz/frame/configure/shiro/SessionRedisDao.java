package xyz.frame.configure.shiro;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xyz.frame.configure.redis.FrameRedis;

@Component
public class SessionRedisDao extends EnterpriseCacheSessionDAO {
    /**
     * session redis key
     */
    private static final String SHIRO_SESSION_PREFIX = "xyz_frame:shiro_session:";

    /**
     * session 过期时间(单位: 分钟) 24小时
     */
    private static final int SHIRO_SESSION_EXPIRE = 1440;

    @Autowired
    private FrameRedis frameRedis;
    // 创建session，保存到数据库
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        frameRedis.setObject((SHIRO_SESSION_PREFIX+sessionId.toString()).getBytes(), sessionToByte(session), SHIRO_SESSION_EXPIRE);
        return sessionId;
    }

    // 获取session
    @Override
    protected Session doReadSession(Serializable sessionId) {
        // 先从缓存中获取session，如果没有再去数据库中获取
        Session session = super.doReadSession(sessionId); 
        if(session == null){
            byte[] bytes = frameRedis.getObject((SHIRO_SESSION_PREFIX+sessionId.toString()).getBytes());
            if(bytes != null && bytes.length > 0){
                session = byteToSession(bytes);    
            }
        }
        return session;
    }

    // 更新session的最后一次访问时间
    @Override
    protected void doUpdate(Session session) {
        super.doUpdate(session);
        frameRedis.setObject((SHIRO_SESSION_PREFIX+session.getId().toString()).getBytes(), sessionToByte(session), SHIRO_SESSION_EXPIRE);
    }

    // 删除session
    @Override
    protected void doDelete(Session session) {
        super.doDelete(session);
        frameRedis.delString(SHIRO_SESSION_PREFIX+session.getId());
    }

    // 把session对象转化为byte保存到redis中
    public byte[] sessionToByte(Session session){
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        byte[] bytes = null;
        try {
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(session);
            bytes = bo.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
    
    // 把byte还原为session
    public Session byteToSession(byte[] bytes){
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
        ObjectInputStream in;
        SimpleSession session = null;
        try {
            in = new ObjectInputStream(bi);
            session = (SimpleSession) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return session;
    }
    
}