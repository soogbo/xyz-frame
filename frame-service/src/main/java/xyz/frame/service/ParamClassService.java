package xyz.frame.service;

import java.util.List;

import xyz.frame.pojo.po.ParamClass;

/**
 * @Description 系统参数分类service
 * @author shisp
 * @date 2017年12月26日
 */
public interface ParamClassService {

    List<ParamClass> listAllParamClass();

    ParamClass getByClassCode(String classCode);

    ParamClass saveOrUpdate(ParamClass paramClass);

    int deleteById(Long classId);

}
