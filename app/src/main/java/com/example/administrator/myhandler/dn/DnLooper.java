package com.example.administrator.myhandler.dn;

import android.os.Looper;
import android.os.MessageQueue;

public class DnLooper {
    private static DnLooper sMainLooper;  // guarded by Looper.class
    static final ThreadLocal<DnLooper> sThreadLocal = new ThreadLocal<DnLooper>();
    final DnMessageQueue mQueue;

    public DnLooper() {
        mQueue = new DnMessageQueue();
    }

    //创建主线程全局唯一的DnLooper
    public static void prepareMainLooper() {
        prepare();
        synchronized (Looper.class) {
            if (sMainLooper != null) {
                throw new IllegalStateException("The main DnLooper has already been prepared.");
            }
            sMainLooper = myLooper();
        }
    }

    //创建主线程全局唯一的DnLooper 并放进ThreadLocal
    private static void prepare() {
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one DnLooper may be created per thread");
        }
        sThreadLocal.set(new DnLooper());
    }

    //获取主线程全局唯一的DnLooper
    public static DnLooper myLooper() {
        return sThreadLocal.get();
    }


    //循环从messagequeue取消息
    public void loop() {
        DnLooper dnLooper = myLooper();
        DnMessageQueue mQueue = dnLooper.mQueue;
        while (true) {
            DnMessage message = mQueue.next();
            if (message != null) {
                message.target.disPatchMessage(message);
            }
        }

    }
}
