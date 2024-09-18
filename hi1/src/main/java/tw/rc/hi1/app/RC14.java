package tw.rc.hi1.app;

import tw.rc.h1.dao.AccountCartDao;
import tw.rc.h1.model.Account;
import tw.rc.h1.model.Cart;

public class RC14 {

	public static void main(String[] args) {
		Cart cart =new Cart();
		//cart.setStatus("shopping");
		
		cart.setStatus("close");
		
		Account account = new Account();
		account.setAccount("BBBB");
		account.setCart(cart);
		
		AccountCartDao dao = new AccountCartDao();
		dao.addAccount(account);

	}

}
