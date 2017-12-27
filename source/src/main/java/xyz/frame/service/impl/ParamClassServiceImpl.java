package xyz.frame.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.frame.mapper.ParamClassMapper;
import xyz.frame.mapper.ParamParamMapper;
import xyz.frame.pojo.po.ParamClass;
import xyz.frame.service.ParamClassService;

@Service("paramClassService")
public class ParamClassServiceImpl implements ParamClassService {

    @Autowired
    private ParamClassMapper paramClassMapper;
    @Autowired
    private ParamParamMapper paramParamMapper;

    @Override
    public List<ParamClass> listAllParamClass() {
        return paramClassMapper.selectAll();
    }

    @Override
    public ParamClass getByClassCode(String classCode) {
        return paramClassMapper.getByClassCode(classCode);
    }

    @Override
    public ParamClass saveOrUpdate(ParamClass paramClass) {
        ParamClass updateParamClass = null;
        Date nowDate = new Date();
        if (paramClass.getId() != null) {
            ParamClass findOne = paramClassMapper.selectByPrimaryKey(paramClass.getId());
            findOne.setClassCode(paramClass.getClassCode());
            findOne.setClassName(paramClass.getClassName());
            findOne.setUpdateAt(nowDate);
            paramClassMapper.updateByPrimaryKey(findOne);
            updateParamClass = findOne;
        } else {
            paramClass.setCreateAt(nowDate);
            paramClass.setUpdateAt(nowDate);
            paramClassMapper.insert(paramClass);
            updateParamClass = paramClass;
        }
        return updateParamClass;
    }

    @Override
    public int deleteById(Long classId) {
        paramParamMapper.updateClassIdToNull(classId);
        return paramClassMapper.deleteByPrimaryKey(classId);
    }

}
