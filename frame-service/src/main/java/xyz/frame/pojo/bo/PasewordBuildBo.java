package xyz.frame.pojo.bo;

/**
 * 自动生成密码bo
 * 
 * @author shisp
 * @date 2018-3-05 18:02:36
 */
public class PasewordBuildBo {

    /**
     * 姓名
     */
    private String name;
    
    /**
     * 出生日期 yyyy-MM-dd
     */
    private String date;
    
    /**
     * 识别码
     */
    private String tagnum;
    
    /**
     * 使用环境：一般为注册网站域名
     */
    private String domain;
    
    /**
     * 身份证，可为空
     */
    private String idcard;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTagnum() {
        return tagnum;
    }
    public void setTagnum(String tagnum) {
        this.tagnum = tagnum;
    }
    public String getDomain() {
        return domain;
    }
    public void setDomain(String domain) {
        this.domain = domain;
    }
    public String getIdcard() {
        return idcard;
    }
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }
    
}
