package tw.rc.apis;

import javax.websocket.Session;

public class WSClient {
	private Session sessions;
	private boolean isClient1;
	public Session getSessions() {
		return sessions;
	}
	public boolean isClient1() {
		return isClient1;
	}
	public void setSessions(Session sessions) {
		this.sessions = sessions;
	}
	public void setClient1(boolean isClient1) {
		this.isClient1 = isClient1;
	}

}
