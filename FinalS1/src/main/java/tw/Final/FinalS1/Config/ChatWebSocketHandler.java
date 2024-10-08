package tw.Final.FinalS1.Config;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 从session中获取UUID
        String uuid = (String) session.getAttributes().get("uuid");
        
        // 处理消息逻辑，例如，转发消息到指定用户
        System.out.println("Received message from user with UUID " + uuid + ": " + message.getPayload());
        
        // 这里可以实现消息的转发逻辑
    }

    // 可以实现其他方法，如onOpen、onClose等
}

