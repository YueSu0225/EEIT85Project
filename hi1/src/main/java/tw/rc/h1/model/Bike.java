package tw.rc.h1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bike")
public class Bike {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bid")
	private int bid;
	
	@Column(name = "uid")
	private int uid;
	
	@Column(name = "color")
	private String color;
	
	@Column(name = "speed")
	private double speed;
	
	
	
}
