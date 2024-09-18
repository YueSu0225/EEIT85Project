package tw.rc.hi1.app;

import tw.rc.h1.dao.AccountCartDao;
import tw.rc.h1.model.Account;
import tw.rc.h1.model.Cart;

public class RC19 {

	public static void main(String[] args) {
		AccountCartDao dao = new AccountCartDao();
		try {//砍account連Cart就一起刪除了
			Account account = dao.queryByAccountId(5);
//			Cart cart = account.getCart();
//			
//			dao.removeCart(cart);
			dao.removeAccount(account);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
