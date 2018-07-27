package xyz.frame.rbac.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import xyz.frame.configure.redis.RedisService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @author shisp
 * @date 2018-7-27 15:28:05
 */
//@Component
public class ShiroSessionDao extends EnterpriseCacheSessionDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroSessionDao.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private RedisService redisService;


    /**
     * session redis key
     */
    private static final String SHIRO_SESSION_PREFIX = "xyz_frame:shiro_session:";

    /**
     * session 过期时间(单位: 分钟) 24小时
     */
    private static final int SHIRO_SESSION_EXPIRE = 1440;

    /**
     * 创建session，保存到redis集群中
     */
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        LOGGER.info("sessionId: {}", sessionId);
        /*BoundValueOperations<String, Object> sessionValueOperations = redisTemplate
                .boundValueOps(SHIRO_SESSION_PREFIX + sessionId.toString());*/
        BoundHashOperations<String, Object, Object> hashOperations = redisTemplate.boundHashOps(SHIRO_SESSION_PREFIX + sessionId.toString());
        hashOperations.put(SHIRO_SESSION_PREFIX + sessionId.toString(), sessionId);
//        redisTemplate.opsForHash().put(SHIRO_SESSION_PREFIX + sessionId.toString(), SHIRO_SESSION_PREFIX + sessionId.toString(), session);
        
        
//        sessionValueOperations.set(sessionToByte(session));
        // session 24小时
        hashOperations.expire(SHIRO_SESSION_EXPIRE, TimeUnit.MINUTES);
        return sessionId;
    }

    /**
     * 获取session
     *
     * @param sessionId
     * @return
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {

        Session session = super.doReadSession(sessionId);
        if (session == null) {
            BoundHashOperations<String, Object, Object> hashOperations = redisTemplate.boundHashOps(SHIRO_SESSION_PREFIX + sessionId.toString());

            
            /*BoundValueOperations<String, Object> sessionValueOperations = redisTemplate
                    .boundValueOps(SHIRO_SESSION_PREFIX + sessionId.toString());*/
            
            RedisOperations<String, ?> operations = hashOperations.getOperations();
            BoundHashOperations<String, Object, Object> boundHashOps = operations.boundHashOps(SHIRO_SESSION_PREFIX + sessionId.toString());
            
            session = (Session) hashOperations.get(SHIRO_SESSION_PREFIX + sessionId.toString());
        }
        return session;
    }

    /**
     * 更新session
     *
     * @param session
     */
    @Override
    protected void doUpdate(Session session) {
        Serializable sessionId = super.doCreate(session);

        super.doUpdate(session);
        BoundHashOperations<String, Object, Object> hashOperations = redisTemplate.boundHashOps(SHIRO_SESSION_PREFIX + sessionId.toString());

        
        /*BoundValueOperations<String, Object> sessionValueOperations = redisTemplate
                .boundValueOps(SHIRO_SESSION_PREFIX + session.getId().toString());*/
        hashOperations.put(SHIRO_SESSION_PREFIX + sessionId.toString(), sessionId);
        hashOperations.expire(SHIRO_SESSION_EXPIRE, TimeUnit.MINUTES);
    }

    /**
     * 删除失效session
     */
    @Override
    protected void doDelete(Session session) {

        redisTemplate.delete(SHIRO_SESSION_PREFIX + session.getId().toString());
        super.doDelete(session);
    }

    // 把session对象转化为byte保存到redis中
    private byte[] sessionToByte(Session session){
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
    private Session byteToSession(byte[] bytes){
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
