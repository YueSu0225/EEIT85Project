package tw.RC.tutor;

import java.io.BufferedInputStream;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

public class RC61 {

	public static void main(String[] args) {
		try {
			URL url = new URL("https://data.moa.gov.tw/Service/OpenData/ODwsv/ODwsvAgriculturalProduce.aspx");
			URLConnection conn = url.openConnection();
			
			BufferedInputStream bin = new BufferedInputStream(conn.getInputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(bin));
			
			String line; StringBuffer sb = new StringBuffer();
			while( (line = reader.readLine()) != null ) {
				sb.append(line);
				
			}
			bin.close();
//			System.out.println(sb);//驗證有無抓到資料
			parseJSON(sb.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void parseJSON(String json) {
		JSONArray root = new JSONArray(json);//根是陣列
		System.out.println(root.length());
		
		String url = "jdbc:mysql://localhost:3306/rc";
		Properties prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "root");
		
		try {
			Connection conn = DriverManager.getConnection(url, prop);
			
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM gift");//刪除gift所有資料
			stmt.executeUpdate("ALTER TABLE gift AUTO_INCREMENT = 1");//改造表名為gift的Key(auto_increment)=Values(1),id從1開始
																	  
			
			String sql = "INSERT INTO gift (name,feature,addr,picurl,city,town,lat,lng)" + 
					"VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			for (int i = 0; i<root.length(); i++) {
				JSONObject element = root.getJSONObject(i);
				String name = element.getString("Name");
				String feature = element.getString("Feature");
				String addr = element.getString("SalePlace");
				String picurl= element.getString("Column1");
				String city = element.getString("County");
				String town = element.getString("Township");
				String lat = element.getString("Longitude");
				String lng = element.getString("Latitude");
			
				pstmt.setString(1, name);
				pstmt.setString(2, feature);
				pstmt.setString(3, addr);
				pstmt.setString(4, picurl);
				pstmt.setString(5, city);
				pstmt.setString(6, town);
				
				try {
					pstmt.setDouble(7, Double.parseDouble(lat));
					pstmt.setDouble(8, Double.parseDouble(lng));
				}catch (Exception e) {
					pstmt.setDouble(7, 0);
					pstmt.setDouble(8, 0);
				}
				
				pstmt.executeUpdate();
				
			
			}
		
			}catch (Exception e) {
			System.out.println(e);
		}
		
		
		
			

					
			
		}
		
		
		
//		JSONObject r = new JSONObject(json);//假設根是物件{}用這個
	}
	
	
	
	
	
	
	
	
	
	
	


