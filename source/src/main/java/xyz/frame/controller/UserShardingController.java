package xyz.frame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.frame.mapper.UserShardingMapper;
import xyz.frame.pojo.po.UserSharding;
import xyz.frame.utils.GeneralResponse;

@RestController
@RequestMapping("/userSharding")
public class UserShardingController {

    @Autowired
    private UserShardingMapper userShardingMapper;

    @GetMapping("/findAll")
    public GeneralResponse<List<UserSharding>> findAll() {
        return GeneralResponse.success(userShardingMapper.findAllUser());
    }

    @GetMapping("/findById")
    public GeneralResponse<UserSharding> findById(Long id) {
        return GeneralResponse.success(userShardingMapper.selectByPrimaryKey(id));
    }

    @GetMapping("/findByUserId")
    public GeneralResponse<UserSharding> findByUserId(Long userId) {
        return GeneralResponse.success(userShardingMapper.findByUserId(userId));
    }

    @PostMapping("/save")
    public GeneralResponse<UserSharding> save(UserSharding userSharding) {
        userShardingMapper.insert(userSharding);
        return GeneralResponse.success(userSharding);
    }

    @PostMapping("/updateById")
    public GeneralResponse<UserSharding> updateById(UserSharding userSharding) {
        userShardingMapper.updateByPrimaryKey(userSharding);
        return GeneralResponse.success(userSharding);
    }

}
