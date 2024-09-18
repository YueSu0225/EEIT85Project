package tw.rc.rcs1.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tw.rc.rcs1.model.Hotel;

public class HotelRowMapper implements RowMapper<Hotel>{

	@Override
	public Hotel mapRow(ResultSet rs, int rowNum) throws SQLException {
		Hotel hotel = new Hotel();
		hotel.setId(rs.getLong("id"));
		hotel.setName(rs.getString("name"));
		hotel.setAddress(rs.getString("addr"));
		hotel.setTel(rs.getString("tel"));
		
		return hotel;
	}
	
}
