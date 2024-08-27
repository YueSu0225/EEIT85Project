package tw.rc.apis;

public class Member {
	private int id;
	private String account, passwd, name;
	public int getId() {
		return id;
	}
	public String getAccount() {
		return account;
	}
	public String getPasswd() {
		return passwd;
	}
	public String getName() {
		return name;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return String.format("%d : %s", id, name);
	}
	
	
	
}
