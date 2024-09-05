package tw.rc.apis;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.SortedMap;

import org.json.JSONArray;
import org.json.JSONObject;


public class RCUtils {
	public static String loadView(String view) throws Exception{
		// view => view1, view2....
		String source = String.format(
				"C:\\Users\\User\\eclipse-workspace\\RCWeb\\src\\main\\webapp\\views\\%s.html", view);
		File viewFile = new File(source);
		int len = (int)viewFile.length();
		byte[] buf = new byte[len];
		BufferedInputStream bin = new BufferedInputStream(new FileInputStream(viewFile));
		bin.read(buf);
		bin.close();
		
		return new String(buf);
		
	}
	
	public static String calc(String x, String y, String op) {
		try {
			int intX = Integer.parseInt(x);
			int intY = Integer.parseInt(y);
			StringBuffer sb = new StringBuffer();
			switch(op) {
				case "1" : sb.append(intX + intY); break;
				case "2" : sb.append(intX - intY); break;
				case "3" : sb.append(intX * intY); break;
				case "4" : 
					sb.append(intX / intY).append(".....").append(intX % intY); break;
			}
			return sb.toString();
			
		}catch (Exception e) {
			return "";
		}
	}
	
	public static String createScore() {
		return (int)(Math.random()*101) + "";
	}
	
	public static LinkedList<HashMap<String, String>> parseFood(String json) {
		LinkedList<HashMap<String, String>> items = new LinkedList<>();
		JSONArray root = new JSONArray(json);
		System.out.println(root.length());
		for (int i=0; i<root.length(); i++) {
			JSONObject row = root.getJSONObject(i);
			HashMap<String, String> item = new HashMap<>();
			item.put("aid", row.getString("ID"));
			item.put("aname", row.getString("Name"));
			item.put("acity", row.getString("City"));
			item.put("atown", row.getString("Town"));
			item.put("apicurl", row.getString("PicURL"));
			item.put("alat", row.getString("Latitude"));
			item.put("alng", row.getString("Longitude"));
			
			items.add(item);
		}
		return items;
		
	}
	
	public static String order2JSON(SortedMap[] rows) {
		JSONObject root = new JSONObject();
		if (rows.length > 0) {
			root.put("OrderID", rows[0].getOrDefault("OrderID", ""));
			root.put("CustomID", rows[0].getOrDefault("CustomerID", ""));
			root.put("CustomName", rows[0].getOrDefault("CompanyName", ""));
			root.put("EmployeeID", rows[0].getOrDefault("EmployeeID", ""));
			root.put("LastName", rows[0].getOrDefault("LastName", ""));
			root.put("OrderDate", rows[0].getOrDefault("OrderDate", ""));
			
			JSONArray details = new JSONArray();
			for (int i=0; i<rows.length; i++) {
				SortedMap<String, String> row = rows[i];
				
				JSONObject detail = new JSONObject();
				detail.put("ProductID", row.getOrDefault("ProductID", ""));
				detail.put("ProductName", row.getOrDefault("ProductName", ""));
				detail.put("UnitPrice", row.getOrDefault("UnitPrice", ""));
				detail.put("Qty", row.getOrDefault("Quantity", ""));
				
				details.put(detail);
			}
			
			root.put("details", details);
		}
		
		return root.toString();
	}

	
	
}
