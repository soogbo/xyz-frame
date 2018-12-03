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
    
    /**
     * 查询全部：从库
     * 
     * @param
     * @return
     */
    List<ParamParam> findAllSlave();
    
    /**
     * 删除全部
     * 
     * @param
     * @return
     */
    void testDeleteAll();
}
