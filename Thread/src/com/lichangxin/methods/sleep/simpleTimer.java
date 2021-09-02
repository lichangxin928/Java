package com.lichangxin.methods.sleep;

public class simpleTimer {
    public static void main(String[] args) throws Exception{
        int time = 60;
        if(args.length == 1){
            time = Integer.parseInt(args[0]);
        }
        while(true){
            System.out.println("time " + time);
            time--;
            if(time<0){
                break;
            }else{
                Thread.sleep(1000);
            }
        }
        System.out.println("end !!!");
    }
}
