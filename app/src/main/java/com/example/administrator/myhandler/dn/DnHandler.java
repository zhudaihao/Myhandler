package com.example.administrator.myhandler.dn;


public class DnHandler {
    /**
     * 构造方法 获取DnLooper  DnMessageQueue
     */
    final DnLooper mLooper;
    final DnMessageQueue mQueue;

    public DnHandler() {
        mLooper = DnLooper.myLooper();
        if (mLooper == null) {
            throw new RuntimeException("Can't create handler inside thread " + Thread.currentThread() + " that has not called Looper.prepare()");
        }
        mQueue = mLooper.mQueue;
    }


    /**
     * 发送消息
     */
    public void sendMessage(DnMessage message) {
        //将消息放入消息队列
        enqueueMessage(message);
    }

    private void enqueueMessage(DnMessage message) {
        //把message里面都是handler赋值为当前发送消息的handler
        message.target = this;
        //消息入队
        mQueue.enQueueMessage(message);
    }


    /**
     * 接受消息
     */
    public void disPatchMessage(DnMessage message) {
        handlerMessage(message);
    }

    //接受发送的信息
    public void handlerMessage(DnMessage message) {
    }

}
