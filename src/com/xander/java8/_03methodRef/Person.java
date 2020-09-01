package com.xander.java8._03methodRef;

/**
 * Description:
 *
 * @author Xander
 * datetime: 2020/8/31 23:59
 */
public class Person {

    //姓名
    private String name;
    //年龄
    private long age;

    public Person(String name, long age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    /**
     * 打招呼
     *
     * @param p
     */
    public void sayHello(Person p) {
        System.out.println(this.getName() + " say hello to " + p.getName());
    }
}
