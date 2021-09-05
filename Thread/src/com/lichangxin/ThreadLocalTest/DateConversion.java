package com.lichangxin.ThreadLocalTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConversion {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss" );
    // 定义线程类
    static class parseDate implements Runnable{
        private  int i = 0;

        public parseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                String text = "2022年11月22日 08:38:" + i%60;
                Date date = sdf.parse(text);
                System.out.println(i + "---" + date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }
    public static void main(String[] args) {
        /*
        * 在多线程环境下，把字符串转换为日期对象
        * */
        for(int i = 0 ;i<100;i++){
            new Thread( new parseDate(i)).start();
        }
    }
}
