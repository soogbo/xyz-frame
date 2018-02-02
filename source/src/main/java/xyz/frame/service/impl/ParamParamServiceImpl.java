package xyz.frame.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.frame.mapper.ParamParamMapper;
import xyz.frame.service.ParamParamService;

@SuppressWarnings("unused")
@Service("paramParamService")
public class ParamParamServiceImpl implements ParamParamService{
    private static final Logger logger = LoggerFactory.getLogger(TaskPoolServiceImpl.class);
    
    @Autowired
    private ParamParamMapper paramParamMapper;
    
    
    
}
