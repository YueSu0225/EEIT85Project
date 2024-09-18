package tw.rc.rcs1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class travel {
	@JsonProperty("Name")
	private String name;
	@JsonProperty("Address")
	private String addr;
	@JsonProperty("Tel")
	private String tel;
	public String getName() {
		return name;
	}
	public String getAddr() {
		return addr;
	}
	public String getTel() {
		return tel;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	
	
}
