package com.lichangxin.CAS;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class Test4 {
    /*
    * AtomicIntegerFieldUpdate 可以对原子整数字段进行更新
    * 1）字符必须使用volatile修饰
    * 2）只能是实例变量，不能是静态变量，也不能用final修饰
    * */
    public static void main(String[] args) throws Exception {
        User user = new User(1234,10);
        for(int i = 0;i < 10;i++){
            new subThread(user).start();
        }
        Thread.sleep(1000);
        System.out.println(user);
    }
}
class User {
    int id ;
    volatile int age;
    public User(int id ,int age){
        this.id = id;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id && age == user.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, age);
    }
}
class subThread extends Thread{
    private User user; // 要更新的User对象
    // 创建更新器
    private AtomicIntegerFieldUpdater<User> updater = AtomicIntegerFieldUpdater.newUpdater(User.class,"age");

    public subThread(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        //age 自增
        for(int i = 0 ;i<10;i++){
            System.out.println(updater.getAndIncrement(user));
        }
    }
}