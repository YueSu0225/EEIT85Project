package tw.Final.FinalS1.controller;
//
//import jakarta.websocket.*;
//import jakarta.websocket.server.ServerEndpoint;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//@ServerEndpoint("/chat")
//public class ChatController {
//    private static Map<String, Session> userSessions = new HashMap<>();
//
//    @OnOpen
//    public void onOpen(Session session) {
//        String userId = session.getRequestParameterMap().get("uuid").get(0); // 获取用户 UUID
//        userSessions.put(userId, session);
//        System.out.println("New connection: " + userId);
//    }
//
//    @OnMessage
//    public void onMessage(String message, Session session) {
//        // 解析消息以获取目标用户的 UUID
//        String[] parts = message.split(":", 2);
//        String targetUserId = parts[0]; // 目标用户 UUID
//        String chatMessage = parts[1]; // 聊天内容
//
//        Session targetSession = userSessions.get(targetUserId);
//        if (targetSession != null) {
//            try {
//                targetSession.getBasicRemote().sendText(chatMessage); // 发送消息给目标用户
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @OnClose
//    public void onClose(Session session) {
//        // 移除用户会话
//        userSessions.values().remove(session);
//        System.out.println("Connection closed: " + session.getId());
//    }
//}
//


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat") // 指定处理路径
    @SendTo("/topic/messages") // 广播到所有订阅者
    public String handleChatMessage(String message) throws Exception {
        // 在这里可以处理消息，例如添加时间戳等
        return message; // 返回的消息会广播给所有订阅者
    }
}
