package tw.rc.rcs1;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tw.rc.rcs1.model.User;
import tw.rc.rcs1.model.travel;

@RestController
public class RC13 {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@RequestMapping("/rc131")
	public String rc13(@RequestBody List<User> userList) {
		String sql ="INSERT INTO user (account,passwd,name) VALUES (:account, :passwd, :name)";
		
		MapSqlParameterSource[] source = new MapSqlParameterSource[userList.size()];
		for (int i = 0; i < userList.size(); i++) {
			User user = userList.get(i);
			source[i] = new MapSqlParameterSource();
			source[i].addValue("account", user.getAccount());
			source[i].addValue("passwd", BCrypt.hashpw(user.getPasswd(),BCrypt.gensalt()));
			source[i].addValue("name", user.getName());
		}
		
		namedParameterJdbcTemplate.batchUpdate(sql, source);
		
		return "ok";
	}
	
	@RequestMapping("/rc132")
	public String rc132() {//撈url的open source資料
		RestTemplate restTemplate = new RestTemplate();
		
		String url = "https://data.moa.gov.tw/Service/OpenData/ODwsv/ODwsvTravelStay.aspx";
		String content = restTemplate.getForObject(url, String.class);
		System.out.println(content);
		
		
		
		return "rc132";
	}
	
	@RequestMapping("/rc133")
	public User rc133() throws Exception{//撈url的open source資料
		String user = "{\"account\":\"test1\",\"passwd\":\"test2\",\"name\":\"test3\"}";
		ObjectMapper mapper = new ObjectMapper();
		try {
			User newuser = mapper.readValue(user, User.class);
			return newuser;
		} catch (Exception e) {
			System.out.println(e);
			throw new Exception();
		} 
	
	}
	@RequestMapping("/rc135")
	public List<User> rc135() throws Exception{//撈url的open source資料
		String user = "[{\"account\":\"test1\",\"passwd\":\"test2\",\"name\":\"test3\"},{\"account\":\"test1\",\"passwd\":\"test2\",\"name\":\"test3\"}]";
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<User> list = mapper.readValue(user, new TypeReference<List<User>>() {});
			System.out.println(list.size());
			return list;
		} catch (Exception e) {
			System.out.println(e);
			throw new Exception();
		} 
	
	}
	
	@RequestMapping("/rc134")
	public String rc134() {
		 RestTemplate restTemplate = new RestTemplate();
	        ObjectMapper mapper = new ObjectMapper();
	        String url = "https://data.moa.gov.tw/Service/OpenData/ODwsv/ODwsvTravelStay.aspx";
	        String content = restTemplate.getForObject(url, String.class);

	        try {
	            // Assuming the JSON content is an array of travel objects
	            List<travel> travelList = mapper.readValue(content,
	                mapper.getTypeFactory().constructCollectionType(List.class, travel.class));

	            String sql = "INSERT INTO travel (name, addr, tel) VALUES (:Name, :Address, :Tel)";

	         // 創建批量插入的參數
	    		MapSqlParameterSource[] sources = new MapSqlParameterSource[travelList.size()];
	    		for (int i = 0; i < travelList.size(); i++) {
	    			travel travel = travelList.get(i);
	    			sources[i] = new MapSqlParameterSource();
	    			sources[i].addValue("Name", travel.getName());
	    			sources[i].addValue("Address", travel.getAddr());
	    			sources[i].addValue("Tel", travel.getTel());

	    		}


	            namedParameterJdbcTemplate.batchUpdate(sql, sources);

	        } catch (Exception e) {
	            e.printStackTrace();
	            return "Error processing data";
	        }

	        return "Data successfully inserted";
	    }
	
	
}
