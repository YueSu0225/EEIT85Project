package tw.Final.FinalS1.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat") // 监听消息
    public void handleChatMessage(@Payload String message) {
        // 假设消息格式为 "targetUUID:message"
        String[] parts = message.split(":", 2);
        if (parts.length == 2) {
            String targetUUID = parts[0]; // 目标用户 UUID
            String chatMessage = parts[1]; // 聊天内容
            
            // 发送消息给目标用户
            messagingTemplate.convertAndSend("/user/" + targetUUID + "/queue/messages", chatMessage);
        }
    }
}
