package tw.rc.rcs1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.rc.rcs1.model.Hotel;
import tw.rc.rcs1.service.HotelService;

@RequestMapping("/hotel")
@RestController
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	@PostMapping("/addHotels")
	public void addHotels() {
		String url = "https://data.moa.gov.tw/Service/OpenData/ODwsv/ODwsvTravelStay.aspx";
		hotelService.addHotelFromUrl(url);
	}
	@PostMapping("/addHotel")
	public Hotel addHotel(@RequestBody Hotel hotel) {
		return hotelService.addHotel(hotel);
	}
	
	@GetMapping("/get/{id}")
	public Hotel getHotel(@PathVariable Long id) {
		return hotelService.getHotel(id);
	}
	
	@GetMapping("/search/{key}")
	public List<Hotel> getHotel(@PathVariable String key) {
		return hotelService.getHotel(key);
	}
}
