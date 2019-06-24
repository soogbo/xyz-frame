package xyz.java.main.java8;

/**
 * 接口默认方法:不能用于lambda表达式
 * 
 * @author shisp
 * @date 2018-12-14 14:01:58
 */
public class DefaultInterfaceMethodDemo {

    public static void main(String[] args) {
        CaseService cs = new CaseServiceImpl();
        Integer addCase = cs.addCase(1);
        String delCase = cs.deleteCase(2);
        System.out.println("addCase:" + addCase);
        System.out.println("deleteCase:" + delCase);

        CaseService cs2 = new CaseService() {
            @Override
            public Integer addCase(Integer num) {
                return 3;
            }
        };
        Integer addCase2 = cs2.addCase(1);
        String delCase2 = cs2.deleteCase(2);
        System.out.println("addCase:" + addCase2);
        System.out.println("deleteCase:" + delCase2);

    }
}

/**
 * 接口默认方法
 */
interface CaseService {
    Integer addCase(Integer num);

    default String deleteCase(Integer num) {
        return "delete " + num;
    };
}

/**
 * 实现，可以不重写默认方法直接调用
 */
class CaseServiceImpl implements CaseService {
    @Override
    public Integer addCase(Integer num) {
        return num;
    }
}
