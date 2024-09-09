package tw.rc.h1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cid")
	private int cid;
	
	@Column(name = "status")
	private String status;
	
	@OneToOne(mappedBy = "cart")
	private Account account;//表示 Cart 和 Account 之間存在一對一的關聯

	public int getCid() {
		return cid;
	}

	public String getStatus() {
		return status;
	}

	public Account getAccount() {
		return account;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	
	
	
}
