/**
 * 
 */
package xyz.frame.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.frame.mapper.UserMapper;
import xyz.frame.pojo.po.UserPo;
import xyz.frame.service.UserService;

/**
 * @Description 
 * @author shisp
 * @date 2017年11月28日  下午8:12:41
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    
    @Override
    public List<UserPo> findAllUser() {
        return userMapper.selectAll();
    }

}
