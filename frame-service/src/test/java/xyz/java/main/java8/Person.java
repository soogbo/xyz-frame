package xyz.java.main.java8;

/**
 * 测试使用对象
 * 
 * @author shisp
 * @date 2018-12-17 14:28:54
 */
public class Person {
    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + "]";
    }
}