package tw.rc.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

import tw.rc.apis.WSClient;

@ServerEndpoint("/mycenter")
public class mycenter {
	private static HashSet<Session> sessions;
	private static HashMap<String, WSClient> users ; // <id, client>
	private static boolean hasClient1; // boolean 預設為false
	
	public mycenter() {//物件初始化
		System.out.println("MyServer");
		if (sessions == null ) {
			sessions = new HashSet<Session>();
			users = new HashMap<String, WSClient>();
		}
	}
	
	@OnOpen
	public void onOpen(Session session) {//服務初始化,連線開始
		System.out.println("onOpen");
		if (sessions.add(session)) {
			WSClient user = new WSClient();
			user.setSessions(session);
			users.put(session.getId(), user);
			System.out.println("Count:" + sessions.size());

		}
	}
	
	@OnMessage
	public void onMessage(String mesg, Session session) {//服務初始化,傳送信息
		System.out.println("onMessage" + mesg);
		JSONObject root = new JSONObject(mesg);
		if (root.getBoolean("isInit")) {
			if (root.getBoolean("isClient1")) {
				if (!hasClient1) {
				hasClient1 = true;
				users.get(session.getId()).setClient1(true);
				System.out.println("isClient1");
				}else {
					System.out.println("remove");
					try {
						session.close();
					} catch (IOException e) {
					}
				}
			}else {
				users.get(session.getId()).setClient1(false);
				System.out.println("isClient2");
			}
		}else {
			System.out.println("drawing....");
			// 要畫圖,發送JSON
			JSONObject sendMesg = new JSONObject();
			if (root.getBoolean("isClear")) {
				sendMesg.put("isClear", true);
				System.out.println("Clear");
			}else {
				sendMesg.put("isClear", false);
				sendMesg.put("NewLine", root.getBoolean("isNewLine"));
				sendMesg.put("x", root.getInt("x"));
				sendMesg.put("y", root.getInt("y"));
				System.out.println(root.getBoolean("isNewLine")?"newline":"drawline");
			}
			
			//廣播出去
			System.out.println("======>" + sendMesg.toString());
			
			Set<String> ids =users.keySet();
			for (String id : ids) {
				if (!users.get(id).isClient1()) {
					try {
						users.get(id).getSessions().getBasicRemote().sendText(sendMesg.toString());
					} catch (IOException e) {
					}
				}
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
