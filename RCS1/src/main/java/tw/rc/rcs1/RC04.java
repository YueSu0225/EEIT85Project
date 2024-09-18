package tw.rc.rcs1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RC04 {
	@Autowired
	@Qualifier("bigLottery")
	private Lottery lottery1;
	
	@Autowired
	@Qualifier("powerLottery")
	private Lottery lottery2;
	
	@Autowired
	private MySetting mySetting;
	
	@RequestMapping("/rc04")
	public String rc04() {
		
		System.out.println(String.format("%s : %d",
				mySetting.getBigLottery(), lottery1.createLottery()));
		System.out.println(String.format("%s : %d",
				mySetting.getBigLottery(), lottery2.createLottery()));
		((BigLottery)lottery1).m1();
		((BigLottery)lottery1).poker1();		
		
		return "rc04";
	}
		
}
