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
        String uuid = getUuidFromSession(session); // 从session中获取UUID

        if (uuid != null) {
            // 将用户的session存储
            userSessions.put(uuid, session);
        } else {
            // 将管理员的session存储
            adminSessions.put(session, null);
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        String[] parts = payload.split(":"); // 假设消息格式为 "targetUUID:message"

        if (parts.length == 2) {
            String targetUUID = parts[0];
            String chatMessage = parts[1];

            // 如果目标用户存在，发送消息
            WebSocketSession userSession = userSessions.get(targetUUID);
            if (userSession != null && userSession.isOpen()) {
                userSession.sendMessage(new TextMessage(chatMessage));
            }
        } else {
            // 管理员发送消息
            for (WebSocketSession adminSession : adminSessions.keySet()) {
                if (adminSession.isOpen()) {
                    adminSession.sendMessage(new TextMessage(payload)); // 广播给所有管理员
                }
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 清理连接
        userSessions.values().remove(session);
        adminSessions.remove(session);
    }

    private String getUuidFromSession(WebSocketSession session) {
        // 从session中提取UUID
        return session.getUri().getQuery().split("=")[1]; // 例: uuid=xxxx
    }
}
