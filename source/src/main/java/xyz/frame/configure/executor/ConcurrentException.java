package xyz.frame.configure.executor;
/**
 * 异步执行的异常
 */
@Deprecated
public class ConcurrentException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 执行的bean
	 */
	private String beanName = null;

    /**
     * 执行的方法
     */
    private String method = null;

    /**
     * 执行的参数
     */
    private Object[] args = null;

    /**
     * construct
     * @param beanName
     * @param method
     * @param args
     * @param e
     */
    public ConcurrentException(String beanName, String method, Object[] args, Throwable e) {
        super(e);
        this.beanName = beanName;
        this.method = method;
        this.args = args;
    }

    /**
     * construct
     * @param beanName
     * @param method
     * @param args
     */
    public ConcurrentException(String beanName, String method, Object[] args) {
        super();
        this.beanName = beanName;
        this.method = method;
        this.args = args;
    }

    public String getBeanName() {
        return beanName;
    }


    public String getMethod() {
        return method;
    }

    public Object[] getArgs() {
        return args;
    }
}
