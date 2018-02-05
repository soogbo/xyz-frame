package xyz.frame.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.frame.mapper.ParamParamMapper;
import xyz.frame.pojo.po.ParamParam;
import xyz.frame.service.ParamParamService;

@Service("paramParamService")
public class ParamParamServiceImpl implements ParamParamService {

    @Autowired
    private ParamParamMapper paramParamMapper;

    @Override
    public List<ParamParam> findListByClassCode(List<String> classCodeList) {
        return paramParamMapper.findListByClassCode(classCodeList);
    }

    @Override
    public List<ParamParam> findAll() {
        return paramParamMapper.selectAll();
    }

}
