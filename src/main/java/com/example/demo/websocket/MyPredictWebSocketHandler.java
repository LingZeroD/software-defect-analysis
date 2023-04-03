package com.example.demo.websocket;

import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MyPredictWebSocketHandler implements WebSocketHandler {
    private static Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private Map<String, WebSocketSession> ipSessionMap = new ConcurrentHashMap<>();
    private Timer timer;
    private static int count = 0;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String ip = ((InetSocketAddress) session.getRemoteAddress()).getAddress().getHostAddress();

        if (ipSessionMap.containsKey(ip)) {//保证一个ip只有一个连接
            session.close();
            System.out.println("ip:" + ip + "已经连接");
            return;
        }
        System.out.println("--------------------------------------");


        String username = getUsernameFromUri(session.getUri().toString());
        System.out.println(username);
        sessions.put(username, session);
        ipSessionMap.put(ip, session);
        //开始定时任务
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (count == 0) {
                    try {
                        session.close();
                        sessions.remove(username);
                        System.out.println("定时任务关闭连接");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                count = 0;
            }
        }, 15000, 15000);
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        count = 1;
        System.out.println("收到消息");

    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        sessions.remove(getUsernameFromUri(webSocketSession.getUri().toString()));
        timer.cancel();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String ip = ((InetSocketAddress) session.getRemoteAddress()).getAddress().getHostAddress();
        sessions.remove(getUsernameFromUri(session.getUri().toString()));
        ipSessionMap.remove(ip);
        timer.cancel();
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    private String getUsernameFromUri(String uri) {
        String[] split = uri.split("/");
        return split[split.length - 1];
    }


    public static void sendMessage(String username, String message) {
        try {
            if (sessions.containsKey(username)&& StrUtil.isNotBlank(username)) {
                WebSocketSession session = sessions.get(username);

                session.sendMessage(new TextMessage(message));
                count = 1;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
