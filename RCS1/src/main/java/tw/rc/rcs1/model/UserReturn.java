package tw.rc.rcs1.model;

public class UserReturn {
	private Long id;
	private String account;
	private String name;
	private String mesg;
	
	
	public String getMesg() {
		return mesg;
	}
	public void setMesg(String mesg) {
		this.mesg = mesg;
	}
	public Long getId() {
		return id;
	}
	public String getAccount() {
		return account;
	}

	public String getName() {
		return name;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setAccount(String account) {
		this.account = account;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
