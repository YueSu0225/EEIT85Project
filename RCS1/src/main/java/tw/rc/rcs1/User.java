package tw.rc.rcs1;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/*
 * json表示
 * {
 * 	"id" : 1,
 *  "name" : "RC"
 * } 
 */

public class User {
	@NotNull
	private Long id;
	
	@Size(min = 6, max = 10)
	private String name;
	
	@Email
	private String email;//Email驗證
	
	@Pattern(regexp = "^[A-Z][12][0-9]{8}$")
	private String twid;//正規表示法驗證	
	
//	@NotBlank
//	private String account;//驗證String不能空字串
	
	@NotEmpty //List, Set, Map, []  驗證size要 > 0
	private List courses;
	
	public String getTwid() {
		return twid;
	}
	public void setTwid(String twid) {
		this.twid = twid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}		
	
}
