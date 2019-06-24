package xyz.frame.pojo.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户entity
 */
@Entity
@Table(name = User.TABLE_NAME)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "u_user";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 邀请人id
    @Column(name = "parent_id")
    private Long parentId;

    // 用户名
    @Column(name = "username")
    private String username;

    // 密码
    @Column(name = "password")
    private String password;

    // 手机号
    @Column(name = "telephone")
    private String telephone;

    // imei
    @Column(name = "imei")
    private String imei;

    // user_mac
    @Column(name = "user_mac")
    private String userMac;

    // total总积分
    @Column(name = "score_total")
    private Integer scoreTotal;

    // 可用积分
    @Column(name = "score_use")
    private Integer scoreUse;

    // 是否可用:1-可用 0-注销
    @Column(name = "status")
    private Integer status;

    // score_flag
    @Column(name = "score_flag")
    private Integer scoreFlag;

    // 创建时间
    @Column(name = "create_at")
    private Date createAt;

    // 更新时间
    @Column(name = "update_at")
    private Date updateAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getUserMac() {
        return userMac;
    }

    public void setUserMac(String userMac) {
        this.userMac = userMac;
    }

    public Integer getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(Integer scoreTotal) {
        this.scoreTotal = scoreTotal;
    }

    public Integer getScoreUse() {
        return scoreUse;
    }

    public void setScoreUse(Integer scoreUse) {
        this.scoreUse = scoreUse;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getScoreFlag() {
        return scoreFlag;
    }

    public void setScoreFlag(Integer scoreFlag) {
        this.scoreFlag = scoreFlag;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}
