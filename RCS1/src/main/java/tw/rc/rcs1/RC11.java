package tw.rc.rcs1;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RC11 {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@RequestMapping("/rc111")//使用jdbc,sql語法增刪修
	public String rc111() {
		String sql ="INSERT INTO user (account,passwd,name) VALUES ('rc','rc','rc')";
		Map<String, Object> map = new HashMap<String, Object>();
		namedParameterJdbcTemplate.update(sql, map);
		return "rc111";
	}
	
	@RequestMapping("/rc112")//使用jdbc,update方法,map
	public String rc112() {
		String sql ="INSERT INTO user (account,passwd,name) VALUES (:account, :passwd, :name)";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("account", "mark");
		map.put("passwd", "mark");
		map.put("name", "馬克");
		namedParameterJdbcTemplate.update(sql, map);
		return "rc112";
	}
	@RequestMapping("/rc113/{id}")
	public String rc113(@PathVariable Long id) {
		String sql = "DELETE FROM user WHERE id = :id";
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		namedParameterJdbcTemplate.update(sql, map);
		return "rc113";
	}
	
}
