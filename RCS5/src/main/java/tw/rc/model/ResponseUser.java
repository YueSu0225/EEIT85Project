package tw.rc.model;

public class ResponseUser {
	private UserStatus userStatus;
	private String mesg;
	private User user;
	
	
	public UserStatus getUserStatus() {
		return userStatus;
	}
	public String getMesg() {
		return mesg;
	}
	public User getUser() {
		return user;
	}
	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}
	public void setMesg(String mesg) {
		this.mesg = mesg;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
