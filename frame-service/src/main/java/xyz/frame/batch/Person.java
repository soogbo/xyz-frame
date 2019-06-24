package xyz.frame.batch;

import java.util.Date;

public class Person {
    // ID
    private Long personId;
    // 姓名
    private String personName;
    // 年龄
    private String personAge;
    // 性别
    private String personSex;
    // 性别
    private Date createAt;

    public Person() {
    }

    public Person(String personName, String personAge, String personSex, Date createAt) {
        this.personName = personName;
        this.personAge = personAge;
        this.personSex = personSex;
        this.createAt = createAt;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonAge() {
        return personAge;
    }

    public void setPersonAge(String personAge) {
        this.personAge = personAge;
    }

    public String getPersonSex() {
        return personSex;
    }

    public void setPersonSex(String personSex) {
        this.personSex = personSex;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

}