package tw.Final.FinalS1.Config;

import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.context.event.EventListener;

@Component
public class WebSocketEventListener {

    private final UserSessionManager userSessionManager;

    public WebSocketEventListener(UserSessionManager userSessionManager) {
        this.userSessionManager = userSessionManager;
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String userId = headerAccessor.getUser().getName(); // 获取用户 ID
        String sessionId = headerAccessor.getSessionId();
        userSessionManager.addUser(userId, sessionId); // 添加用户
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String userId = headerAccessor.getUser().getName();
        userSessionManager.removeUser(userId); // 移除用户
    }
}

