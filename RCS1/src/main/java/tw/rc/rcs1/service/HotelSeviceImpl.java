package tw.rc.rcs1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tw.rc.rcs1.dao.HotelDao;
import tw.rc.rcs1.model.Hotel;

@Component
public class HotelSeviceImpl implements HotelService{

	@Autowired
	private HotelDao hotelDao;
	
	
	@Override
	public void addHotelFromUrl(String url) {//Service商業邏輯,想辦法到URL,轉物件,換欄位,
		RestTemplate restTemplate = new RestTemplate();
		String content = restTemplate.getForObject(url, String.class);
		
		String json = content.replace("\"Name\":", "\"name\":")//將URL的Name換成資料庫設定的name
					.replace("\"Address\":", "\"address\":")
					.replace("\"Tel\":", "\"tel\":");
		
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<Hotel> list = mapper.readValue(json, new TypeReference<List<Hotel>>() {});
			System.out.println(list.size());
			int dbnum = hotelDao.add(list);
			System.out.println(dbnum);
		} catch (Exception e) {
			System.out.println(e);
		} 
	}


	@Override
	public Hotel addHotel(Hotel hotel) {
		return hotelDao.add(hotel);
	}


	@Override
	public Hotel getHotel(Long id) {
		return hotelDao.get(id);
	}


	@Override
	public List<Hotel> getHotel(String key) {
		return hotelDao.get(key);
	}
}
