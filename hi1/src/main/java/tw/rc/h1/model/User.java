package tw.rc.h1.model;

import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityResult;
import jakarta.persistence.FetchType;
import jakarta.persistence.FieldResult;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Table;

/*
@SqlResultSetMapping(
		name = "userBike",
		entities = {
				@EntityResult(
						entityClass = User.class, fields = {
								@FieldResult(name = "id", column = "user_id"),
								@FieldResult(name = "name", column = "user_name")
						}
				),
				@EntityResult(
						entityClass = Bike.class, fields = {
								@FieldResult(name = "id", column = "bike_id"),
								@FieldResult(name = "uid", column = "bike_uid"),
								@FieldResult(name = "color", column = "bike_color"),
								@FieldResult(name = "speed", column = "bike_speed")
								
						}
				)
		}		
)*/
@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Bike> bikes;

	public User() {
		bikes = new LinkedList<Bike>();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Bike> getBikes() {
		return bikes;
	}

	public void setBikes(List<Bike> bikes) {
		this.bikes = bikes;
	}
	
	public void addBike(Bike bike) {
		bike.setUser(this);
		bikes.add(bike);
	}
	
	public void removeBike(Bike bike) {
		bikes.remove(bike);
	}
	
	
}
