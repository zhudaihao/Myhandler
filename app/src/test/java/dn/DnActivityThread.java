package dn;


import com.example.administrator.myhandler.dn.DnHandler;
import com.example.administrator.myhandler.dn.DnLooper;
import com.example.administrator.myhandler.dn.DnMessage;

import org.junit.Test;

//app入口类
public class DnActivityThread {
    @Test
    public void main() {
        /**
         *创建主线程全局唯一的Looper和messageQueue
         */
        DnLooper.prepareMainLooper();


        /**
         *创建handler对象
         */
        final DnHandler dnHandler = new DnHandler() {
            @Override
            public void handlerMessage(DnMessage message) {
                super.handlerMessage(message);
                System.out.println(message.obj.toString());

            }
        };

        /**
         *发消息
         */
        new Thread() {
            @Override
            public void run() {
                super.run();
                DnMessage message = new DnMessage();
                message.what = 1;
                message.obj = "测试发送消息";
                dnHandler.sendMessage(message);
            }
        }.start();


        /**
         *循环取出消息
         */
        //先获取Looper
        DnLooper dnLooper = DnLooper.myLooper();
        //循环获取消息
        dnLooper.loop();


    }


}
