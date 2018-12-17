package xyz.frame.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import xyz.frame.pojo.po.OptimisticLock;
import xyz.frame.utils.FrameMapper;

/**
 * @Author wanghao create by 2018-08-31 锁信息mapper
 */
public interface OptimisticLockMapper extends FrameMapper<OptimisticLock> {

    /**
     * 根据条件查询
     * 
     * @param param
     */
    @Select({ "<script>", "select * from ", OptimisticLock.TABLE_NAME, " WHERE end_time > now() ",
            " <if test=\"lockKey!=null and lockKey!=''\" > AND lock_key = #{lockKey} </if>", 
            " <if test=\"lockStatus!=null \" > AND lock_status = #{lockStatus} </if>",
            "</script>" })
    List<OptimisticLock> getValidLock(@Param("lockKey") String lockKey, @Param("lockStatus") Integer lockStatus);
}