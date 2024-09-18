package tw.rc.rcs1.dao;

import java.util.List;

import tw.rc.rcs1.model.Hotel;

public interface HotelDao {
	public Hotel add(Hotel hotel);
	public Integer add(List<Hotel> hotels);
	
	public Hotel get(Long id);
	public List<Hotel> get(String key);
}
