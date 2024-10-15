package tw.Final.FinalS1.Config;

import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatWebSocketHandler extends TextWebSocketHandler {
    // 儲存連接的使用者與後台管理員 映射
    private Map<String, WebSocketSession> userSessions = new ConcurrentHashMap<>();
    private Map<WebSocketSession, String> adminSessions = new ConcurrentHashMap<>();

    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String query = session.getUri().getQuery();

        if (query != null && query.contains("admin=true")) {
            // 剛後台管理員的session儲存
            adminSessions.put(session, "admin");
            System.out.println("後台管理員已連接: " + session.getId());
        } else {
            String userUUID = getUuidFromSession(session); // 獲取使用者的UUID
            if (userUUID != null) {
                userSessions.put(userUUID, session); // 儲存使用者的session
                System.out.println("使用者連接: " + userUUID);
            } else {
                System.out.println("警告: 用户 UUID 是 null: " + session.getId());
            }
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        String[] parts = payload.split(":");

        if (parts.length == 3 && parts[0].equals("admin")) {
            // 後臺管理員發送信息
        	String targetUUID = parts[1]; // 目標使用者的 UUID
            String chatMessage = parts[2]; // 聊天內容

            // 發送信息給使用者 用UUID分辨是誰
            WebSocketSession userSession = userSessions.get(targetUUID);
            if (userSession != null && userSession.isOpen()) {
                userSession.sendMessage(new TextMessage(chatMessage));
                System.out.println("管理員發送消息给使用者 " + targetUUID + ": " + chatMessage);
            } else {
                System.out.println("使用者: "+ targetUUID + " 不在線或不存在");
            }
        } else if (parts.length == 2) {
            // 使用者發送信息
            String targetUUID = parts[0]; // 使用者的 UUID
            String chatMessage = parts[1]; // 聊天内容

            // 廣播给所有後台((我只有一個後台
            for (WebSocketSession adminSession : adminSessions.keySet()) {
                if (adminSession.isOpen()) {
                    adminSession.sendMessage(new TextMessage(targetUUID + ": " + chatMessage));
                    System.out.println("廣播给所有後台管理員: " + targetUUID + ": " + chatMessage);
                }
            }
        } else {
            System.out.println("收到的信息格式不正確: " + payload);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 移除連接
        userSessions.values().remove(session);
        adminSessions.remove(session);
        System.out.println("連接已關閉: " + session.getId());
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
