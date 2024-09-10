package tw.rc.h1.model;

import java.security.PublicKey;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "course")

public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)
	private Set<Student> students = new HashSet<>();
	
	public Course() {}
	public Course(String name) {this.name = name;}
	
	public int getId() {
		return id;
	}
	public String getname() {
		return name;
	}
	public Set<Student> getStudents() {
		return students;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setname(String name) {
		this.name = name;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
	
	
	
}
