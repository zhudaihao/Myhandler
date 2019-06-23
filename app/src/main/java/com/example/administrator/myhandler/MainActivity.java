package com.example.administrator.myhandler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {
    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        send();
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Log.e("zdh", "----打印-" + msg.what);
        }
    };

    private void send() {
        Message message1 = new Message();
        message1.what = 1;

        Message message2 = new Message();
        message2.what = 2;

        Message message3 = new Message();
        message3.what = 3;

        handler.sendMessageDelayed(message1, 20);
        handler.sendMessageDelayed(message2, 10);
        handler.sendMessageDelayed(message3, 15);

        /**
         * 关于延迟入队理解
         *
         *当执行handler.sendMessageDelayed(message1, 20);方法时，message1已放进message链表里面并且在链表头部
         * //在messagequeue的enqueuemessage方法里面会走else代码（因为p.when=-1m656s1ms）
         * messagequeue里面的message赋值为message1
         *
         * 当执行handler.sendMessageDelayed(message2, 10);方法时，message2也会放进message链表，
         * （message2链表跟message1链表是同一个，并且在链表里面排序是：message1→message2）
         * //在messagequeue的enqueuemessage方法里面会走if里面代码 因为message2的when小于message1的when，
         * messagequeue里面的message赋值为message2；
         *message2会直接放到链表头部， message链表里面排序是：message2→message1
         *
         * 当执行handler.sendMessageDelayed(message3, 15);方法时，message3也会放进message链表，
         *（链表里面排序是message2→message1→message3）
         * //在messagequeue的enqueuemessage方法里面会走else代码（因为message3的when大于message2的when）
         * 在else里面会有个死循环，遍历message链表的message，拿message3的when和链表里面的message的when对比大小，
         * 当对比到一个比message3的when大的时间退出循环，在循环中对比过的message会重新排序
         * （链表里面排序是message2→message3→message1）
         * （messagequeue里面的message赋值还是message2）
         *
         * （如果是这样流程那链表里面的message排序是按when从小到大排序，不会出现中间的message的when会小于前面的message的when的情况）
         */}


}
