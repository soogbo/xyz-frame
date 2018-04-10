package xyz.frame.batch;

public class Person {
	//ID
    private Integer personId;
    //姓名
    private String personName;
    //年龄
    private String personAge;
    //性别
    private String personSex;
    
    public Person(){};
	public Person( String personName, String personAge,
			String personSex) {
		this.personName = personName;
		this.personAge = personAge;
		this.personSex = personSex;
	}
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
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

    
    
}