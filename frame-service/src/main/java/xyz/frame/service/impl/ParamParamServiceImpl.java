package xyz.frame.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.frame.datasource.ReadSlave;
import xyz.frame.datasource2.TargetDataSource;
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
    
    @Override
    @ReadSlave
    @TargetDataSource(name="SLAVE")
    public List<ParamParam> findAllSlave() {
        return paramParamMapper.selectAll();
    }
    
    @Override
    public void testDeleteAll() {
        paramParamMapper.testDeleteAll();
    }

    @Override
    public int save(ParamParam paramParam) {
        return paramParamMapper.insert(paramParam);
    }

}
