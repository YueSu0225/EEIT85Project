package tw.rc.apis;

public class Member {
	private int id;
	private String account, passwd, name;
	private String icon;  //此icon是存放base64-format
	
	
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
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
