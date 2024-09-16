package tw.rc.h1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "memberinfo")
public class MemberInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "addr")
	private String addr;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "birthday")
	private String birthday;
	
	public int getId() {
		return id;
	}
	public String getAddr() {
		return addr;
	}
	public String getPhone() {
		return phone;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	
}
