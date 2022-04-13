package com.lcx.pubComfirm;


import com.lcx.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;

import java.util.UUID;

/**
 * 发布确认模式
 * 1. 单个确认
 * 2. 批量确认
 * 3. 异步批量确认
 */
public class ConfirmMessage {

    // 发送消息个数
    public static final int  MESSAGE_COUNT = 1000;


    public static void main(String[] args) throws Exception {
        // 1. 单个确认 65480 68065
        // ConfirmMessage.SingleConfirm();
        // 2. 批量确认 171
        // ConfirmMessage.BatchConfirm();
        // 3. 异步确认 195
        ConfirmMessage.SyncConfirm();

    }

    // 单个确认
    public static void SingleConfirm() throws Exception{
        Channel channel = RabbitMqUtils.getChannel();
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName,true,false,false,null);
        // 开启发布确认
        channel.confirmSelect();
        // 开始时间
        long begin = System.currentTimeMillis();
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("",queueName,null,message.getBytes());
            channel.waitForConfirms();
        }
        long end = System.currentTimeMillis();
        System.out.println("总消耗时间：" + (end - begin));
    }
    // 批量确认
    public static void BatchConfirm() throws Exception{
        Channel channel = RabbitMqUtils.getChannel();
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName,true,false,false,null);
        // 开启发布确认
        channel.confirmSelect();
        // 开始时间
        int batchSize = 100;
        long begin = System.currentTimeMillis();
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("",queueName,null,message.getBytes());
            if(i+1 % batchSize == 0){
                channel.waitForConfirms();
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("总消耗时间：" + (end - begin));
    }
    // 异步确认
    public static void SyncConfirm() throws Exception{
        Channel channel = RabbitMqUtils.getChannel();
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName,true,false,false,null);
        // 开启发布确认
        channel.confirmSelect();


        // 准备消息的监听，监听哪些消息成功了，哪些消息失败了
        channel.addConfirmListener(
                (tag,mul)->{
                    // 2. 删除确认的消息
                    System.out.println("确认的消息：" + tag);
        },
                (tag,mul)->{
                    // 3. 打印未确认的消息
                    System.out.println("没有确认的消息：" + tag);
        });
        // 开始时间
        long begin = System.currentTimeMillis();

        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("",queueName,null,message.getBytes());
            // 1. 记录下所有要发送的消息
        }

        long end = System.currentTimeMillis();
        System.out.println("总消耗时间：" + (end - begin));
    }
}

