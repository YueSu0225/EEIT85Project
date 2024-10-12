package tw.Final.FinalS1.Config;

import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatWebSocketHandler extends TextWebSocketHandler {
    // 存储连接的用户和管理员的映射
    private Map<String, WebSocketSession> userSessions = new ConcurrentHashMap<>();
    private Map<WebSocketSession, String> adminSessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String query = session.getUri().getQuery();

        if (query != null && query.contains("admin=true")) {
            // 将管理员的session存储
            adminSessions.put(session, "admin");
            System.out.println("管理员已连接: " + session.getId());
        } else {
            String userUUID = getUuidFromSession(session); // 获取用户 UUID
            if (userUUID != null) {
                userSessions.put(userUUID, session); // 存储用户的session
                System.out.println("用户已连接: " + userUUID);
            } else {
                System.out.println("警告: 用户 UUID 为 null，session: " + session.getId());
            }
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        String[] parts = payload.split(":");

        if (parts.length == 3 && parts[0].equals("admin")) {
            // 管理员发送消息
        	String targetUUID = parts[1]; // 目标用户的 UUID
            String chatMessage = parts[2]; // 聊天内容

            // 发送消息给目标用户
            WebSocketSession userSession = userSessions.get(targetUUID);
            if (userSession != null && userSession.isOpen()) {
                userSession.sendMessage(new TextMessage(chatMessage));
                System.out.println("管理员发送消息给用户 " + targetUUID + ": " + chatMessage);
            } else {
                System.out.println(targetUUID + " 不在线或不存在");
                System.out.println("当前用户会话列表: " + userSessions.keySet()); 
            }
        } else if (parts.length == 2) {
            // 用户发送消息
            String targetUUID = parts[0]; // 目标用户的 UUID
            String chatMessage = parts[1]; // 聊天内容

            // 发送消息给目标用户
//            WebSocketSession userSession = userSessions.get(targetUUID);
//            if (userSession != null && userSession.isOpen()) {
//                userSession.sendMessage(new TextMessage(chatMessage));
//                System.out.println("用户 " + targetUUID + " 发送消息: " + chatMessage);
//            }

            // 广播给所有管理员
            for (WebSocketSession adminSession : adminSessions.keySet()) {
                if (adminSession.isOpen()) {
                    adminSession.sendMessage(new TextMessage(targetUUID + ": " + chatMessage));
                    System.out.println("广播消息给管理员: " + targetUUID + ": " + chatMessage);
                }
            }
        } else {
            System.out.println("收到的消息格式无效: " + payload);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 清理连接
        userSessions.values().remove(session);
        adminSessions.remove(session);
        System.out.println("连接已关闭: " + session.getId());
    }

    private String getUuidFromSession(WebSocketSession session) {
        // 从session中提取UUID
        String[] queryParams = session.getUri().getQuery().split("&");
        for (String param : queryParams) {
            if (param.startsWith("uuid=")) {
                return param.split("=")[1]; // 例: uuid=xxxx
            }
        }
        return null; // 返回 null 如果没有找到 UUID
    }
}
