package tw.rc.hi1.app;

import tw.rc.h1.dao.AccountCartDao;
import tw.rc.h1.model.Account;
import tw.rc.h1.model.Cart;

public class RC18 {

	public static void main(String[] args) {
		AccountCartDao dao = new AccountCartDao();
		try{
			Account account = dao.queryByAccountId(3);
			
//			account.setCart(null);		
//			dao.updateAccount(account);
			
			Cart cart = dao.queryCartByCartId(3);
			account.setCart(cart);
			dao.updateAccount(account);
			
		}catch (Exception e) {
			System.out.println(e);
		}
	}

}
