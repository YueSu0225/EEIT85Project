package tw.rc.hi1.app;

import tw.rc.h1.dao.AccountCartDao;
import tw.rc.h1.model.Account;

public class RC16 {

	public static void main(String[] args) {
		Account account = new Account();
		account.setAccount("newRC");
		
		AccountCartDao dao = new AccountCartDao();
		dao.addAccount(account);
	}

}
