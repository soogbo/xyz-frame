package xyz.frame.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.frame.mapper.ParamParamMapper;
import xyz.frame.service.ParamParamService;

@Service("paramParamService")
public class ParamParamServiceImpl implements ParamParamService{

    @Autowired
    private ParamParamMapper paramParamMapper;
    
    
    
}
