package tw.rc.rcs1;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TodayLottery implements Lottery{
	
	@Override
	public Integer createLottery() {
		int lottery = new Random().nextInt(1,38+1);
		return lottery;
	}
	
}
