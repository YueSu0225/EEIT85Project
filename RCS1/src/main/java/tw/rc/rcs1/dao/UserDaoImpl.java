package tw.rc.rcs1.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import tw.rc.rcs1.model.Hotel;
import tw.rc.rcs1.model.User;

@Component
public class UserDaoImpl implements UserDao{

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public User add(User user) {
		String sql ="INSERT INTO user (account,passwd,name) VALUES (:account, :passwd, :name)";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("account", user.getAccount());
		map.put("passwd", user.getPasswd());
		map.put("name", user.getName());
		
		// 以下取得新增user的 id
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		MapSqlParameterSource source = new MapSqlParameterSource(map);
		
		int num = namedParameterJdbcTemplate.update(sql, source, keyHolder);
		if (num > 0) {
			Long id = keyHolder.getKey().longValue();
			user.setId(id);
			return user;
		}else {
			return null;
		}
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User user, Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User user, Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getById(Long id) {
		String sql = "SELECT id,account,name,icon FROM user WHERE id = :id";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		List<User> list = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

		if (list.size()>0) {
			return list.get(0);
		}else {
			return null;
		}
	}

	@Override
	public User getByAccount(String account) {
		// TODO Auto-generated method stub
		return null;
	}

}
