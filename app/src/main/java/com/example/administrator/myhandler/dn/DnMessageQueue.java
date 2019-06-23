package com.example.administrator.myhandler.dn;

import android.os.Message;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DnMessageQueue {

    //使用阻塞队列 装置message(安卓源码是使用message对象池)
    BlockingQueue<DnMessage> blockingQueue = new ArrayBlockingQueue<>(50);

    //消息入队
    public void enQueueMessage(DnMessage dnMessage) {
        try {
            blockingQueue.put(dnMessage);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //获取message消息
    public DnMessage next() {
        try {
            return blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }




}
