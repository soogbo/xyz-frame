package xyz.frame.service;

import java.util.List;

import xyz.frame.pojo.po.ParamParam;

/**
 * @Description 系统参数service
 * @author shisp
 * @date 2017年12月26日
 */
public interface ParamParamService {

    /**
     * 根据ClassCodeList查询对应参数list
     * 
     * @param classCodeList
     * @return
     */
    List<ParamParam> findListByClassCode(List<String> classCodeList);

    /**
     * 查询全部
     * 
     * @param
     * @return
     */
    List<ParamParam> findAll();
}
