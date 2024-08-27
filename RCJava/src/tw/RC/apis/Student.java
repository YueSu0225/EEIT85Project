package tw.RC.apis;

import java.io.Serializable;

public class Student implements Serializable {//宣告可序列化implements Serializable
	private String name;//定義一個字串為name的屬性
	private int math, ch, eng;//int為math, ch, eng的屬性
	private Bike bike;
	
	public Student(String name, int math, int ch, int eng) {//建構式:將屬性初始化,裡面敘述句只會執行一次
		this.name = name;//初始化
		this.math = math;//初始化
		this.ch = ch;//初始化
		this.eng = eng;//初始化
		bike = new Bike();
	}
	
	public Bike getBike() {return bike;}
	
	public int sum() {return math+ch+eng;}//製作呼叫方法sum()傳回math+ch+eng;
	public double avg() {return sum()/3.0;}//製作呼叫方法avg()傳回sum()/3.0;
	
	@Override
	public String toString() {
		return String.format("%s:%d:%d:%d", name, math, ch, eng);
	}
	
}
