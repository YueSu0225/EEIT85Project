package tw.rc.model;

public class MyTestResponse {
	private Integer code;
	private String mesg;
	private MyTest myTest;
	
	
	public Integer getCode() {
		return code;
	}
	public String getMesg() {
		return mesg;
	}
	public MyTest getMyTest() {
		return myTest;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public void setMesg(String mesg) {
		this.mesg = mesg;
	}
	public void setMyTest(MyTest myTest) {
		this.myTest = myTest;
	}
	
	
}
