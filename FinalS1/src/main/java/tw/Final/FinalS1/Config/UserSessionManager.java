package tw.Final.FinalS1.Config;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserSessionManager {

    // 使用 UUID 做分辨哪個人使用
    private final Map<String, String> userSessions = new ConcurrentHashMap<>();

    public void addUser(String userUUID, String sessionId) {
        userSessions.put(userUUID, sessionId);
    }

    public void removeUser(String userUUID) {
        userSessions.remove(userUUID);
    }

    public String getSessionId(String userUUID) {
        return userSessions.get(userUUID);
    }
}
