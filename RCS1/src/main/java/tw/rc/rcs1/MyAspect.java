package tw.rc.rcs1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Before("execution(* tw.rc.rcs1.BigLottery.*(..))")//要去執行這個路徑的大樂透的所有方法
	public void beforeTest1() {
		System.out.println("beforeTest1()");
	}
	
	@Before("execution(* tw.rc.rcs1.Lottery.*(..))")
	public void beforeTest2() {
		System.out.println("beforeTest2()");
	}
	
	@Before("execution(* tw.rc.rcs1.Lottery.createLottery(..))")
	public void log() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");
			Date now = new Date();
			String time = sdf.format(now);
			File file = new File("log/mylog.txt");
			
//			Resource res = resourceLoader.getResource("classpath:mylog.txt");
			BufferedWriter br = new BufferedWriter(new FileWriter(file, true));
			br.write(String.format("%s : message \n", time));
			br.flush();
			br.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	
	}
	
	@Before("execution(* tw.rc.rcs1.BigLottery.m1(..))")//要去執行這個路徑的大樂透的所有方法
	public void beforeTest3() {
		System.out.println("beforeTest3()");
	}
	
	
	@After("execution(* tw.rc.rcs1.BigLottery.m1(..))")//要去執行這個路徑的大樂透的所有方法
	public void beforeTest4() {
		System.out.println("beforeTest4()");
	}
	
	
	@Around("execution(* tw.rc.rcs1.BigLottery.poker1(..))")
	public Object aroundTest5(ProceedingJoinPoint joinPoint) throws Throwable{
		long start = System.currentTimeMillis();//執行開始時間
		Object obj = joinPoint.proceed();
		System.out.println("==>" + (System.currentTimeMillis()-start));
		return obj;
	}
	
}
