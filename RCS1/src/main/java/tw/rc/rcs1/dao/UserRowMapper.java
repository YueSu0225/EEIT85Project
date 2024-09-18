package tw.rc.rcs1.dao;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import org.springframework.jdbc.core.RowMapper;

import tw.rc.rcs1.model.Hotel;
import tw.rc.rcs1.model.User;

public class UserRowMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();


		user.setId(rs.getLong("id"));
		user.setAccount(rs.getString("account"));
		user.setName(rs.getString("name"));
		Blob blob = rs.getBlob("icon");
		
		if(blob != null) {
			byte[] iconBytes = blob.getBytes(1, (int)(blob.length()));
			String iconBase64 = Base64.getEncoder().encodeToString(iconBytes);
			user.setIcon(iconBytes);
			user.setIconBase64(iconBase64);
		
		}else {
			user.setIcon(null);
			user.setIconBase64(null);
		}

		return user;
	}
	
}
