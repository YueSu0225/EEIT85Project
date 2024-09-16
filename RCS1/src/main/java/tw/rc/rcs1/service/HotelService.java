package tw.rc.rcs1.service;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import tw.rc.rcs1.model.Hotel;

public interface HotelService {
	public void addHotelFromUrl(String url);
	public Hotel addHotel(Hotel hotel);
	public Hotel getHotel(Long id);
	public List<Hotel> getHotel(String key);
}
