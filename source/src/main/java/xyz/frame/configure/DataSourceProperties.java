package xyz.frame.configure;
import java.util.ArrayList;
import java.util.List;

public class DataSourceProperties {
	
	private String driverClassName = "com.mysql.jdbc.Driver";
	//初始化连接数量
	private int initialSize = 10;
	//最小空闲连接数
	private int minIdle = 10;	
	//最大并发连接数
	private int maxActive = 400;
	//配置获取连接等待超时的时间
	private int maxWait = 10000;
	//配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
	private int timeBetweenEvictionRunsMillis = 60000;
	//配置一个连接在池中最小生存的时间，单位是毫秒
	private int minEvictableIdleTimeMillis = 300000;
	
	private String validationQuery = "SELECT 1 FROM DUAL";
	
	private boolean testWhileIdle = true;
	
	private boolean testOnBorrow = false;
	
	private boolean testOnReturn = false;			
	
	private Cluster cluster;
	
	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public int getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	public int getInitialSize() {
		return initialSize;
	}

	public void setInitialSize(int initialSize) {
		this.initialSize = initialSize;
	}

	public int getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(int maxWait) {
		this.maxWait = maxWait;
	}

	public int getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

	public int getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}

	public void setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}

	public int getMinEvictableIdleTimeMillis() {
		return minEvictableIdleTimeMillis;
	}

	public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
		this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
	}

	public String getValidationQuery() {
		return validationQuery;
	}

	public void setValidationQuery(String validationQuery) {
		this.validationQuery = validationQuery;
	}

	public boolean isTestWhileIdle() {
		return testWhileIdle;
	}

	public void setTestWhileIdle(boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}

	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public boolean isTestOnReturn() {
		return testOnReturn;
	}

	public void setTestOnReturn(boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}

	public Cluster getCluster() {
		return cluster;
	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}

	public static class Cluster {
		private JdbcConfig master;
		private List<DataSourceProperties.JdbcConfig> slave = new ArrayList<>();

		public JdbcConfig getMaster() {
			return master;
		}

		public void setMaster(JdbcConfig master) {
			this.master = master;
		}

		public List<DataSourceProperties.JdbcConfig> getSlave() {
			return slave;
		}
	}

	public static class JdbcConfig {
	    
		private String url;		
		private String username;
		private String password;
		
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}			
	}
	
}
