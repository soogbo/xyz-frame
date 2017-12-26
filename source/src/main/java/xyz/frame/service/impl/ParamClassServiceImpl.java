package xyz.frame.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.frame.mapper.ParamClassMapper;
import xyz.frame.service.ParamClassService;

@Service("paramClassService")
public class ParamClassServiceImpl implements ParamClassService{

    @Autowired
    private ParamClassMapper paramClassMapper;
    
    
    
}
