package tw.RC.apis;

import java.io.Serializable;

public class Point implements Serializable {
	private int x, y;//1.
	public Point(int x, int y) {//2.
		this.x = x; this.y = y;//3.
	}
	public int getX() {//4.
		return x;
	}
	public int getY() {//4.
		return y;
	}
	
}
