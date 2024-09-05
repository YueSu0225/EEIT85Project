package tw.RC.tutor;

import java.security.PublicKey;
import java.util.Random;

import tw.RC.apis.TWID;
import tw.RC.apis.TWdate;
import tw.RC.apis.TWphone;
import tw.RC.apis.TWtelephone;
import tw.RC.apis.TWtime;

public class RC17 {

	public static void main(String[] args) {
//		System.out.println(TWID.isRight("A123456789"));
//		
//		Random random = new Random();
//		System.out.println(random.nextInt(1, 7));
		
		
		
		TWID id1 = new TWID();
		TWID id2 = new TWID(false);
		TWID id3 = new TWID('B');
		TWID id4 = new TWID(true, 'Y');
		//TWID id5 = TWID.newTWID("Y120239847");
		System.out.println(id1.getId());
		System.out.println(id2.getId());
		System.out.println(id3.getId());
		System.out.println(id4.getId());
		//System.out.println(id5.getId());
		
		System.out.println(TWphone.isRight("09-04567891"));
		TWphone phone1 = TWphone.newTWphone("09-12345678");
		System.out.println(phone1.getphone());
		System.out.println(TWdate.isRight("2024-08-02"));
		System.out.println(TWtelephone.isRight("01-2234567"));
		System.out.println(TWtime.isRight("22:59:59"));
		
	}

}
