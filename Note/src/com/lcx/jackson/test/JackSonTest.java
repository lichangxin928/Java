package com.lcx.jackson.test;

import com.lcx.jackson.domain.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class JackSonTest {

    // java 对象转换为 json
    @Test
    public void test1(){
        // 创建 Person 对象
        Person person = new Person();
        person.setName("张三");
        person.setAge(18);
        person.setGender("男");

        // 创建 Jackson 核心对象
        ObjectMapper mapper = new ObjectMapper();
        // 转换
        /**
         * 转换方法
         *      writeValue(参数1：obj)
         *          参数1：
         *              File：将obj对象转换为JSON 字符串，并保存到指定的文件
         *              Write：将 obj对象转换为JSON 字符串，并将 json数据填充到字符输出流中
         *              OutputStream：将ojb对象转换为JSON字符串，并将json数据填充到字节输出流中、
         *
         *      writeValueString(Obj)
         * @JsonIgnore
         * @JsonFormat
         */
        try {
            String s = mapper.writeValueAsString(person);
            System.out.println(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test2(){
        // 创建 Person 对象
        Person p1 = new Person();
        p1.setName("张三");
        p1.setAge(18);
        p1.setGender("男");

        Person p2 = new Person();
        p2.setName("张三");
        p2.setAge(18);
        p2.setGender("男");
        Person p3 = new Person();

        p3.setName("张三");
        p3.setAge(18);
        p3.setGender("男");

        // 创建 Jackson 核心对象
        ObjectMapper mapper = new ObjectMapper();
        // 转换
        // 创建 list
        ArrayList<Person> people = new ArrayList<>();
        people.add(p1);
        people.add(p2);
        people.add(p3);

        try {
            String s = mapper.writeValueAsString(people);
            System.out.println(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void test3(){
        // 创建 Person 对象
        Person p1 = new Person();
        p1.setName("张三");
        p1.setAge(18);
        p1.setGender("男");

        Person p2 = new Person();
        p2.setName("张三");
        p2.setAge(18);
        p2.setGender("男");
        Person p3 = new Person();

        p3.setName("张三");
        p3.setAge(18);
        p3.setGender("男");

        // 创建 Jackson 核心对象
        ObjectMapper mapper = new ObjectMapper();
        // 转换
        // 创建 list
        HashMap<String, Object> map = new HashMap<>();
        map.put("p1",p1);
        map.put("p2",p2);
        map.put("p3",p3);

        try {
            String s = mapper.writeValueAsString(map);
            System.out.println(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
