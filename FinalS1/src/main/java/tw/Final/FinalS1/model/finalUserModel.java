package tw.Final.FinalS1.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity	
@Table(name = "user")
public class finalUserModel {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    private String account;
    private String password;
    private String email;
    private String googleId;
    private String uuid;
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private cartModel cart;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private wishListModel wishList;

    
    


	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}

	public cartModel getCart() {
		return cart;
	}

	public void setCart(cartModel cart) {
		this.cart = cart;
	}

	public wishListModel getWishList() {
		return wishList;
	}

	public void setWishList(wishListModel wishList) {
		this.wishList = wishList;
	}

    
    

}
