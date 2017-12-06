/**
 * 
 */
package xyz.frame.service;

import java.util.List;

import xyz.frame.pojo.po.UserPo;

/**
 * @Description 
 * @author shisp
 * @date 2017年11月28日  下午8:10:50
 */
public interface UserService {

    List<UserPo> findAllUser();
    
}
