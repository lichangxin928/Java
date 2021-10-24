package com.lcx.pa1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("school")
public class School {
    private String name;
    @Resource
    private Person person;

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", person=" + person +
                '}';
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
