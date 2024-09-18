package tw.rc.h1.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table( name = "account")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "account")
	private String account;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cartid", referencedColumnName = "cid")
	private Cart cart;

	public int getId() {
		return id;
	}

	public String getAccount() {
		return account;
	}

	public Cart getCart() {
		return cart;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	
	
	
}
