package com.atguigu.mybatis_plus_demo.config;


import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ServerEndpoint
 * <p>
 * 使用springboot的唯一区别是要@Component声明下，而使用独立容器是由容器自己管理websocket的，但在springboot中连容器都是spring管理的。
 * <p>
 * 虽然@Component默认是单例模式的，但springboot还是会为每个websocket连接初始化一个bean，所以可以用一个静态set保存起来。
 *
 * @author sam
 * @since 2017/9/13
 */
@ServerEndpoint("/chatRoom/{userName}") //WebSocket客户端建立连接的地址
@Component
public class ChatRoomServerEndpoint {

    /**
     * 存活的session集合（使用线程安全的map保存）
     */
    private static Map<String, Session> livingSessions = new ConcurrentHashMap<>();

    /**
     * 建立连接的回调方法
     *
     * @param session  与客户端的WebSocket连接会话
     * @param userName 用户名，WebSocket支持路径参数
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userName") String userName) {
        livingSessions.put(session.getId(), session);
//        sendMessageToAll(userName + " 加入聊天室");
//        sendMessageToAll(userName + " 上线");
    }

    /**
     * 收到客户端消息的回调方法
     *
     * @param message 客户端传过来的消息
     * @param session 对应的session
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("userName") String userName) {
        sendMessageToAll(userName + "|-|" + message);
//        String[] parts = message.split("-");
//        sendMessageToAll(parts[0],userName,parts[1]);
//        sendMessage(session, userName + "-" +message);
    }


    /**
     * 发生错误的回调方法
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 关闭连接的回调方法
     */
    @OnClose
    public void onClose(Session session, @PathParam("userName") String userName) {
        livingSessions.remove(session.getId());
//        sendMessageToAll(userName + " 退出聊天室");
//        sendMessageToAll(userName + " 下线");
    }


    /**
     * 单独发送消息
     *
     * @param session
     * @param message
     */
    public void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 群发消息
     *
     * @param message
     */
    public void sendMessageToAll(String message) {
        livingSessions.forEach((sessionId, session) -> {
//            System.out.println(sessionId);
//            if(sessionId.equals(uid2) || sessionId.equals(uid1)) {
//                System.out.println('?');
                sendMessage(session, message);
//            }
        });
    }

}


