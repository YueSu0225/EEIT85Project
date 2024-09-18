package tw.rc.hi1.app;

import tw.rc.h1.dao.AccountCartDao;
import tw.rc.h1.model.Account;
import tw.rc.h1.model.Cart;

public class RC17 {

	public static void main(String[] args) {
		AccountCartDao dao = new AccountCartDao();
		try {
			Account account = dao.queryByAccountId(3);
			System.out.println("---------------");
			System.out.println(account.getAccount());
			
			if(account.getCart() == null ) {
				System.out.println("no cart");
				Cart cart = new Cart();
				cart.setStatus("shopping");
				
				account.setCart(cart);
				
				dao.updateAccount(account);
				
			}else {
				System.out.println("has cart");
				account.getCart().setStatus("colse");
				dao.updateAccount(account);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
