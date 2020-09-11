package com.xander.java8._03methodRef;

import java.util.Objects;

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
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getAge() == person.getAge() &&
                Objects.equals(getName(), person.getName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getAge());
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
