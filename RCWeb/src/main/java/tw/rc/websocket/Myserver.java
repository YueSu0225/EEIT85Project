package tw.rc.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/myserver")
public class Myserver {
	private static HashSet<Session> sessions;//因為users來的session檢查不重複,所以用hashset
	private static HashMap<String, Session> users;//然後產生HashMap存放session
	
	public Myserver() {//物件初始化
		System.out.println("MyServer");
		if (sessions == null ) {
			sessions = new HashSet<Session>();
			users = new HashMap<String, Session>();
		}
	}
	
	@OnOpen
	public void onOpen(Session session) {//服務初始化,連線開始
		System.out.println("onOpen");
		if (sessions.add(session)) {
			users.put(session.getId(), session);
			System.out.println("Count:" + sessions.size());

		}
	}
	
	@OnMessage
	public void onMessage(String mesg, Session session) {//服務初始化,傳送信息
		System.out.println("onMessage" + mesg);
		
		for (Session user : sessions) {
			try {
				user.getBasicRemote().sendText(mesg);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		
	}
	
	@OnClose
	public void onClose(Session session) {//服務初始化,關閉
		
		users.remove(session.getId());
		sessions.remove(session);
		System.out.println("onClose:" + sessions.size());

	}
	
	@OnError
	public void onError(Session session, Throwable t) {//服務初始化,發生錯誤
		System.out.println("onError" +t.toString());

	}
	
	
}
