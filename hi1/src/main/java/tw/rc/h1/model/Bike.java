package tw.rc.h1.model;

import java.util.jar.Attributes.Name;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bike")
public class Bike {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bid")
	private int bid;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid")
	private User user;
	
	@Column(name = "color")
	private String color;
	
	@Column(name = "speed")
	private double speed;

	public int getBid() {
		return bid;
	}



	public String getColor() {
		return color;
	}

	public double getSpeed() {
		return speed;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}



	public void setColor(String color) {
		this.color = color;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
