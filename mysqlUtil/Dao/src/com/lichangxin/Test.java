package com.lichangxin;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        historyDao historyDao = new historyDao();
        ResultSet resultSet =  historyDao.Select();
//        System.out.println("查询操作：");
//        while (true){
//            try {
//                if (!resultSet.next()) break;
//                System.out.print(resultSet.getString(1));
//                System.out.print(resultSet.getString(2));
//                System.out.print(resultSet.getInt(3));
//                System.out.print(resultSet.getString(4));
//                System.out.println();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//        }
//        System.out.println("添加操作");
//        System.out.println(historyDao.Insert("123@qq.com","海绵宝宝",40,"2021-06-16 14:26:12"));
//        System.out.println("删除操作");
//        System.out.println(historyDao.Delete("123@qq.com"));
//        System.out.println("修改操作");
//        System.out.println(historyDao.Update("123@qq.com","花园宝宝",50,"2021-06-16 14:26:12"));
    }
}
