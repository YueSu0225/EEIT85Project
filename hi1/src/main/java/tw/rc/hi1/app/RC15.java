package tw.rc.hi1.app;

import tw.rc.h1.dao.AccountCartDao;
import tw.rc.h1.model.Account;
import tw.rc.h1.model.Cart;

public class RC15 {

	public static void main(String[] args) {
		AccountCartDao dao = new AccountCartDao();
		try {
			
			Account account =  dao.queryByAccountId(6);
			System.out.println("-----------------------");
			System.out.println(account.getAccount());
			System.out.println("-------------------------");
			
			Cart cart = account.getCart();
			if (cart != null) {
				System.out.println(cart.getStatus());
			}else {
				System.out.println("cart is null");
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
