package xyz.frame.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import xyz.frame.exception.ServiceException;

/**
 * @Description 对象转换工具
 * @author shisp
 * @date 2017年12月8日  下午2:43:55
 */
public class FrameBeanUtils {

    /**
     * 将实体对象转成业务对象
     *
     * @param e
     *            实体对象，需要继承baseEntity
     * @param classse
     *            目标bo class
     * @return T 目标bo
     */
    public static <T, E> T mapToBo(final E source, final Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        T target = null;
        try {
            target = targetClass.newInstance();
            BeanUtils.copyProperties(source, target);
        } catch (Exception e1) {
            throw new ServiceException(11, "数据转换异常", e1);
        }
        return target;
    }

    /**
     * 将实体对象列表转成业务对象列表
     *
     * @param eList
     *            实体对象列表，需要继承baseEntity
     * @param classse
     *            目标bo class
     * @return 列表
     */
    public static <T, E> List<T> mapToBoList(final List<E> sourceList, final Class<T> targetClass) {
        if (null == sourceList || sourceList.isEmpty()) {
            return null;
        }

        List<T> resultList = new ArrayList<>();
        for (E e : sourceList) {
            resultList.add(mapToBo(e, targetClass));
        }

        return resultList;
    }

}
