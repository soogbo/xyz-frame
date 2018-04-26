package xyz.frame.pojo.po;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * quizzes
 */
@Entity
@Table(name = Quizzes.TABLE_NAME)
public class Quizzes implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = " quizzes ";
    public static final String COLUMN_LIST = " id,quiz,school,type,contributor,end_time,cur_time,answer,options,create_at,updated_at ";
    public static final String COLUMN_LIST_ALIAS_T = " t.id,t.quiz,t.school,t.type,t.contributor,t.end_time,t.cur_time,t.answer,t.options,t.create_at,t.updated_at ";        
    
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
     * quiz
     */
    @Column(name = "quiz")
    private String quiz;
    
    /**
     * school
     */
    @Column(name = "school")
    private String school;
    
    /**
     * type
     */
    @Column(name = "type")
    private String type;
    
    /**
     * contributor
     */
    @Column(name = "contributor")
    private String contributor;
    
    /**
     * end_time
     */
    @Column(name = "end_time")
    private String endTime;
    
    /**
     * cur_time
     */
    @Column(name = "cur_time")
    private String curTime;
    
    /**
     * answer
     */
    @Column(name = "answer")
    private Integer answer;
    
    /**
     * options
     */
    @Column(name = "options")
    private String options;
    
    /**
     * create_at
     */
    @Column(name = "create_at")
    private java.util.Date createAt;
    
    /**
     * updated_at
     */
    @Column(name = "updated_at")
    private java.util.Date updatedAt;
    
	/**
	 * 设置id
	 * @param id id
	 */    
    public void setId(Long id){
        this.id = id;
    }
    
	/**
	 * 获取id
	 * @return id
	 */    
    public Long getId(){
        return this.id;
    }    
    
	/**
	 * 设置quiz
	 * @param quiz quiz
	 */    
    public void setQuiz(String quiz){
        this.quiz = quiz;
    }
    
	/**
	 * 获取quiz
	 * @return quiz
	 */    
    public String getQuiz(){
        return this.quiz;
    }    
    
	/**
	 * 设置school
	 * @param school school
	 */    
    public void setSchool(String school){
        this.school = school;
    }
    
	/**
	 * 获取school
	 * @return school
	 */    
    public String getSchool(){
        return this.school;
    }    
    
	/**
	 * 设置type
	 * @param type type
	 */    
    public void setType(String type){
        this.type = type;
    }
    
	/**
	 * 获取type
	 * @return type
	 */    
    public String getType(){
        return this.type;
    }    
    
	/**
	 * 设置contributor
	 * @param contributor contributor
	 */    
    public void setContributor(String contributor){
        this.contributor = contributor;
    }
    
	/**
	 * 获取contributor
	 * @return contributor
	 */    
    public String getContributor(){
        return this.contributor;
    }    
    
	/**
	 * 设置end_time
	 * @param endTime end_time
	 */    
    public void setEndTime(String endTime){
        this.endTime = endTime;
    }
    
	/**
	 * 获取end_time
	 * @return endTime
	 */    
    public String getEndTime(){
        return this.endTime;
    }    
    
	/**
	 * 设置cur_time
	 * @param curTime cur_time
	 */    
    public void setCurTime(String curTime){
        this.curTime = curTime;
    }
    
	/**
	 * 获取cur_time
	 * @return curTime
	 */    
    public String getCurTime(){
        return this.curTime;
    }    
    
	/**
	 * 设置answer
	 * @param answer answer
	 */    
    public void setAnswer(Integer answer){
        this.answer = answer;
    }
    
	/**
	 * 获取answer
	 * @return answer
	 */    
    public Integer getAnswer(){
        return this.answer;
    }    
    
	/**
	 * 设置options
	 * @param options options
	 */    
    public void setOptions(String options){
        this.options = options;
    }
    
	/**
	 * 获取options
	 * @return options
	 */    
    public String getOptions(){
        return this.options;
    }    
    
	/**
	 * 设置create_at
	 * @param createAt create_at
	 */    
    public void setCreateAt(java.util.Date createAt){
        this.createAt = createAt;
    }
    
	/**
	 * 获取create_at
	 * @return createAt
	 */    
    public java.util.Date getCreateAt(){
        return this.createAt;
    }    
    
	/**
	 * 设置updated_at
	 * @param updatedAt updated_at
	 */    
    public void setUpdatedAt(java.util.Date updatedAt){
        this.updatedAt = updatedAt;
    }
    
	/**
	 * 获取updated_at
	 * @return updatedAt
	 */    
    public java.util.Date getUpdatedAt(){
        return this.updatedAt;
    }    
}