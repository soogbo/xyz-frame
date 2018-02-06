package xyz.frame.pojo.po;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * FTP服务参数表
 */
@Entity
@Table(name = FtpParam.TABLE_NAME)
public class FtpParam implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = " s_ftp_param ";
    public static final String COLUMN_LIST = " id,host,port,username,password,root,type,create_at,update_at ";
    public static final String COLUMN_LIST_ALIAS_T = " t.id,t.host,t.port,t.username,t.password,t.root,t.type,t.create_at,t.update_at ";        
    
    /**
     * FTP服务ID
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
     * 连接地址
     */
    @Column(name = "host")
    private String host;
    
    /**
     * 端口
     */
    @Column(name = "port")
    private Integer port;
    
    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;
    
    /**
     * 密码
     */
    @Column(name = "password")
    private String password;
    
    /**
     * root路径
     */
    @Column(name = "root")
    private String root;
    
    /**
     * 类型
     */
    @Column(name = "type")
    private Integer type;
    
    /**
     * 创建时间
     */
    @Column(name = "create_at")
    private java.util.Date createAt;
    
    /**
     * 更新时间
     */
    @Column(name = "update_at")
    private java.util.Date updateAt;
    
	/**
	 * 设置FTP服务ID
	 * @param id FTP服务ID
	 */    
    public void setId(Long id){
        this.id = id;
    }
    
	/**
	 * 获取FTP服务ID
	 * @return id
	 */    
    public Long getId(){
        return this.id;
    }    
    
	/**
	 * 设置连接地址
	 * @param host 连接地址
	 */    
    public void setHost(String host){
        this.host = host;
    }
    
	/**
	 * 获取连接地址
	 * @return host
	 */    
    public String getHost(){
        return this.host;
    }    
    
	/**
	 * 设置端口
	 * @param port 端口
	 */    
    public void setPort(Integer port){
        this.port = port;
    }
    
	/**
	 * 获取端口
	 * @return port
	 */    
    public Integer getPort(){
        return this.port;
    }    
    
	/**
	 * 设置用户名
	 * @param username 用户名
	 */    
    public void setUsername(String username){
        this.username = username;
    }
    
	/**
	 * 获取用户名
	 * @return username
	 */    
    public String getUsername(){
        return this.username;
    }    
    
	/**
	 * 设置密码
	 * @param password 密码
	 */    
    public void setPassword(String password){
        this.password = password;
    }
    
	/**
	 * 获取密码
	 * @return password
	 */    
    public String getPassword(){
        return this.password;
    }    
    
	/**
	 * 设置root路径
	 * @param root root路径
	 */    
    public void setRoot(String root){
        this.root = root;
    }
    
	/**
	 * 获取root路径
	 * @return root
	 */    
    public String getRoot(){
        return this.root;
    }    
    
	/**
	 * 设置类型
	 * @param type 类型
	 */    
    public void setType(Integer type){
        this.type = type;
    }
    
	/**
	 * 获取类型
	 * @return type
	 */    
    public Integer getType(){
        return this.type;
    }    
    
	/**
	 * 设置创建时间
	 * @param createAt 创建时间
	 */    
    public void setCreateAt(java.util.Date createAt){
        this.createAt = createAt;
    }
    
	/**
	 * 获取创建时间
	 * @return createAt
	 */    
    public java.util.Date getCreateAt(){
        return this.createAt;
    }    
    
	/**
	 * 设置更新时间
	 * @param updateAt 更新时间
	 */    
    public void setUpdateAt(java.util.Date updateAt){
        this.updateAt = updateAt;
    }
    
	/**
	 * 获取更新时间
	 * @return updateAt
	 */    
    public java.util.Date getUpdateAt(){
        return this.updateAt;
    }    
}