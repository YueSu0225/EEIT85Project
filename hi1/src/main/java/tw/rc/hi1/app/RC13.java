package tw.rc.hi1.app;

import tw.rc.h1.dao.AccountCartDao;
import tw.rc.h1.model.Account;
import tw.rc.h1.model.Cart;

public class RC13 {

	public static void main(String[] args) {
		Account account = new Account();
		account.setAccount("shit");
		
		
		Cart cart = new Cart();
		cart.setStatus("shopping");
		
		account.setCart(cart);
		
		AccountCartDao dao = new AccountCartDao();
		dao.addAccount(account);
	}

}
