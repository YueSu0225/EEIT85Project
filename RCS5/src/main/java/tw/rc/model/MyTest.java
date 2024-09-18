package tw.rc.model;

import java.util.LinkedList;
import java.util.List;

public class MyTest {
	private Long id;
	private String name;
	private List<String> friends;
	
	public MyTest() {
		friends = new LinkedList<String>();
	}
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public List<String> getFriends() {
		return friends;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setFriends(List<String> friends) {
		this.friends = friends;
	}
	
	public MyTest addFriend(String friend) {
		friends.add(friend);
		return this;
	}
	
	
}
